package com.bw.movie.base;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.weight.ErrorView;

import butterknife.ButterKnife;

/**
 * author(张渊卓)
 * date:2019/1/24
 */
public  abstract class BaseFragment extends LazyLoadFragment implements NetBroadcastReceiver.NetStatusMonitor{

    protected View rootView;
    private Intent intent;
    private ErrorView errorView;
    private NetBroadcastReceiver netBroadcastReceiver;

    //获取fragment布局文件ID
    protected abstract int setLayoutId();

    //进行初始化的方法
    protected abstract void init(View view, Bundle savedInstanceState);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null){
            errorView = new ErrorView(getActivity(),null);
            errorView.setVisibility(View.GONE);
            ((ViewGroup) getActivity().getWindow().getDecorView()).addView(errorView);
            rootView = inflater.inflate(setLayoutId(), container, false);
            ButterKnife.bind(this, rootView);
            init(rootView, savedInstanceState);
        }
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        ((ViewGroup) rootView.getParent()).removeView(rootView);

    }

    protected void ChangeActivity(Class<?> cls,boolean isFinish){
        intent = new Intent(getActivity(),cls);
        startActivity(intent);
        if (isFinish) getActivity().finish();
    }
    private void registerBroadcastReceiver() {
        //注册广播
        if (netBroadcastReceiver == null) {
            netBroadcastReceiver = new NetBroadcastReceiver();
            IntentFilter filter = new IntentFilter();
            filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
            getActivity().registerReceiver(netBroadcastReceiver, filter);
            //设置监听
            netBroadcastReceiver.setStatusMonitor(this);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //注册网络状态监听的广播
        registerBroadcastReceiver();

    }

    @Override
    public void onNetChange(boolean netStatus) {
        if(netStatus){
            errorView.setVisibility(View.GONE);
        }else{
            errorView.setVisibility(View.VISIBLE);
        }

    }
}

