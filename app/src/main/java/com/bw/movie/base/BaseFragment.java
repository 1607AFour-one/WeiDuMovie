package com.bw.movie.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * author(张渊卓)
 * date:2019/1/24
 */
public abstract class BaseFragment extends LazyLoadFragment{

    protected View rootView;
    private Intent intent;

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
}

