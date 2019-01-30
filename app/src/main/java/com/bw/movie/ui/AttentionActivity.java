package com.bw.movie.ui;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bw.movie.R;
import com.bw.movie.adapter.AttentionAdapter;
import com.bw.movie.custom.MyViewPager;
import com.bw.movie.fragment.AttentionFragment;
import com.bw.movie.fragment.CinemaFragment;

import java.util.ArrayList;
import java.util.List;

public class AttentionActivity extends AppCompatActivity {

    private RadioButton Attention_Rb1;
    private RadioButton Attention_Rb2;
    private RadioGroup Attention_Rg;
    private MyViewPager Attention_Vp;
    private List<Fragment> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attention);
        initView();
        initData();
        AttentionAdapter attentionAdapter=new AttentionAdapter(getSupportFragmentManager(),mList,AttentionActivity.this);
        Attention_Vp.setOffscreenPageLimit(3);  //拖住布局
        Attention_Vp.setB(false);  //锁死
        Attention_Vp.setAdapter(attentionAdapter);

        Attention_Rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.Attention_Rb1:
                        Attention_Vp.setCurrentItem(0);
                        break;
                    case R.id.Attention_Rb2:
                        Attention_Vp.setCurrentItem(1);
                        break;
                }
            }
        });
        //让状态栏透明
        changeStatusBarColor();
        //让状态栏透明
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

    }

    private void initData() {
        mList = new ArrayList<>();
        mList.add(new AttentionFragment());
        mList.add(new CinemaFragment());
    }

    private void initView() {
        Attention_Rb1 = (RadioButton) findViewById(R.id.Attention_Rb1);
        Attention_Rb2 = (RadioButton) findViewById(R.id.Attention_Rb2);
        Attention_Rg = (RadioGroup) findViewById(R.id.Attention_Rg);
        Attention_Vp = (MyViewPager) findViewById(R.id.Attention_Vp);
    }



    /**
     * 让状态栏变透明
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
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
}
