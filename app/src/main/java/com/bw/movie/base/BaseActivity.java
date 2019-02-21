package com.bw.movie.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.bw.movie.app.MyApp;
import com.bw.movie.utils.SpUtils;
import com.bw.movie.weight.ErrorView;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener,NetBroadcastReceiver.NetStatusMonitor {

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 100) {
                errorView.setVisibility(View.VISIBLE);
            } else {
                errorView.setVisibility(View.GONE);
            }
        }
    };
    private boolean netStatus;
    private NetBroadcastReceiver netBroadcastReceiver;
    private ErrorView errorView;
   /* private void diaLog(Context context){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle("提示");
        builder.setMessage("网络中断");
        builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent =  new Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS);
                startActivity(intent);

            }
        });
        builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        AlertDialog dialog=builder.create();
        dialog.show();

    }*/

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(enableRightSliding()){

            SlidingLayout slidingLayout = new SlidingLayout(this);
            slidingLayout.replaceCurrentLayout(this);
        }
        MyApp.getInstance().addActivity(this);
        init();
        errorView = new ErrorView(this,null);

        ((ViewGroup) getWindow().getDecorView()).addView(errorView);
    }
    protected boolean enableRightSliding(){
        return true;
    }
    /*
     * 解决沉浸式头部顶住头
     * */
    public static void setRootViewFitsSystemWindows(Activity activity, boolean fitSystemWindows) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ViewGroup winContent = (ViewGroup) activity.findViewById(android.R.id.content);
            if (winContent.getChildCount() > 0) {
                ViewGroup rootView = (ViewGroup) winContent.getChildAt(0);
                if (rootView != null) {
                    rootView.setFitsSystemWindows(fitSystemWindows);
                }
            }
        }

    }
    //    初始化SP
    public SpUtils initSP() {
        SpUtils spUtils = new SpUtils();
        return spUtils;
    }
    /*
     * 加入沉浸式状态栏效果
     * */
    public void setImmersion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.flags |= flagTranslucentNavigation;
                window.setAttributes(attributes);
                getWindow().setStatusBarColor(Color.TRANSPARENT);
            } else {
                Window window = getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.flags |= flagTranslucentStatus | flagTranslucentNavigation;
                window.setAttributes(attributes);
            }
        }
    }
    /*
    *
    * 初始化布局
    * */
    protected abstract int initLayout();

    /**
     * 初始化View
     */
    protected abstract void initView();
    /*
     * 注册点击事件
     * */
    protected abstract void setClicks();
    /**
     * 设置监听
     */
    protected abstract void initListener();
    /**
     * 判断当前的网络连接
     *connectivityManager.getActiveNetworkInfo();
     * @return
     */
    protected abstract void setProgress();
    public boolean isConnNet(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isAvailable();
    }
    void init(){
        if(initLayout() !=0){
            setContentView(initLayout());
            setImmersion();
            setRootViewFitsSystemWindows(this,true);
            initView();
            initListener();
            setClicks();
            setProgress();
        } else {
            throw new IllegalArgumentException("please load the activity layout");
        }

    };


    public void openActivity(Class<?> targetActivityClass, Bundle bundle) {
        Intent intent = new Intent(this, targetActivityClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }
    //Activity跳转
    public void openActivity(Class<?> targetActivityClass) {
        openActivity(targetActivityClass, null);
    }

    // short吐司
    public void showShort(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //注册网络状态监听的广播
        registerBroadcastReceiver();

    }

    private void registerBroadcastReceiver() {
        //注册广播
        if (netBroadcastReceiver == null) {
            netBroadcastReceiver = new NetBroadcastReceiver();
            IntentFilter filter = new IntentFilter();
            filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
            registerReceiver(netBroadcastReceiver, filter);
            //设置监听
            netBroadcastReceiver.setStatusMonitor(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (netBroadcastReceiver != null) {
            //注销广播
            unregisterReceiver(netBroadcastReceiver);
        }

    }

    @Override
    public void onNetChange(boolean netStatus) {
        this.netStatus = netStatus;
        isNetConnect();
    }
    public boolean getNetStatus(){
        return netStatus;
    }

    private void isNetConnect() {
        Message message = new Message();
        if (netStatus) {
            message.what = 99;
            handler.sendMessage(message);
        } else {
            message.what = 100;
            handler.sendMessage(message);
        }
    }


}
