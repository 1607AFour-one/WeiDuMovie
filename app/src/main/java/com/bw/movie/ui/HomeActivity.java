package com.bw.movie.ui;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
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
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.MyHomeAdapter;
import com.bw.movie.custom.MyViewPager;
import com.bw.movie.fragment.MovieFragment;
import com.bw.movie.fragment.MyFragment;
import com.bw.movie.fragment.NearFragment;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends AppCompatActivity {


    private List<Fragment> fList;
    private RadioGroup Home_Rg;
    private MyViewPager Home_Vp;
    private RadioButton Home_Rb1;
    private RadioButton Home_Rb2;
    private RadioButton Home_Rb3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        initData();
        MyHomeAdapter myHomeAdapter = new MyHomeAdapter(getSupportFragmentManager(), fList, this);

        Home_Vp.setOffscreenPageLimit(3);  //拖住布局
        Home_Vp.setB(false);  //锁死
        Home_Vp.setAdapter(myHomeAdapter);

        Home_Rb1.setScaleX(1);
        Home_Rb1.setScaleY(1);
        Home_Rb2.setScaleX((float) 0.8);
        Home_Rb2.setScaleY((float) 0.8);
        Home_Rb3.setScaleX((float) 0.8);
        Home_Rb3.setScaleY((float) 0.8);


        Home_Rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {

                    case R.id.Home_Rb1:

                        Home_Vp.setCurrentItem(0);

                        Home_Rb1.setScaleX(1);
                        Home_Rb1.setScaleY(1);
                        Home_Rb2.setScaleX((float) 0.8);
                        Home_Rb2.setScaleY((float) 0.8);
                        Home_Rb3.setScaleX((float) 0.8);
                        Home_Rb3.setScaleY((float) 0.8);
                        ObjectAnimator ra = ObjectAnimator.ofFloat(Home_Rb1, "rotation", 0f, 360f);
                        ra.setDuration(1000);
                        ra.start();


                        break;
                    case R.id.Home_Rb2:
                        Home_Vp.setCurrentItem(1);

                        Home_Rb2.setScaleX(1);
                        Home_Rb2.setScaleY(1);
                        Home_Rb1.setScaleX((float) 0.8);
                        Home_Rb1.setScaleY((float) 0.8);
                        Home_Rb3.setScaleX((float) 0.8);
                        Home_Rb3.setScaleY((float) 0.8);
//                        ObjectAnimator ra1 = ObjectAnimator.ofFloat(Home_Rb2,"rotation", 0f, 360f);
//                        ra1.setDuration(1000);
//                        ra1.start();

                        break;
                    case R.id.Home_Rb3:
                        Home_Vp.setCurrentItem(2);
                        Home_Rb3.setScaleX(1);
                        Home_Rb3.setScaleY(1);
                        Home_Rb2.setScaleX((float) 0.8);
                        Home_Rb2.setScaleY((float) 0.8);
                        Home_Rb1.setScaleX((float) 0.8);
                        Home_Rb1.setScaleY((float) 0.8);
                        //ObjectAnimator ra2 = ObjectAnimator.ofFloat(Home_Rb3,"rotation", 0f, 360f);
//                        ra2.setDuration(1000);
//                        ra2.start();
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

        fList = new ArrayList<>();
        fList.add(new MovieFragment());
        fList.add(new NearFragment());
        fList.add(new MyFragment());

    }

    private void initView() {
        Home_Rg = (RadioGroup) findViewById(R.id.Home_Rg);
        Home_Vp = findViewById(R.id.Home_Vp);


        Home_Rb1 = (RadioButton) findViewById(R.id.Home_Rb1);

        Home_Rb2 = (RadioButton) findViewById(R.id.Home_Rb2);

        Home_Rb3 = (RadioButton) findViewById(R.id.Home_Rb3);

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
