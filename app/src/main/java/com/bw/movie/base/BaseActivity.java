package com.bw.movie.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.bw.movie.utils.SpUtils;

public abstract class BaseActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
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




}
