package com.bw.movie.ui;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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
        Home_Rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {

                    case R.id.Home_Rb1:
                        Home_Vp.setCurrentItem(0);
                        break;
                    case R.id.Home_Rb2:
                        Home_Vp.setCurrentItem(1);
                        break;
                    case R.id.Home_Rb3:
                        Home_Vp.setCurrentItem(2);
                        break;
                }

            }
        });

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

        //定义底部标签图片大小和位置
        Drawable drawable_news = getResources().getDrawable(R.drawable.ser_weibo);
        //当这个图片被绘制时，给他绑定一个矩形 ltrb规定这个矩形
        drawable_news.setBounds(0, 0, 50, 50);

        //定义底部标签图片大小和位置
        Drawable drawable_news1 = getResources().getDrawable(R.drawable.home_film);
        //当这个图片被绘制时，给他绑定一个矩形 ltrb规定这个矩形
        drawable_news1.setBounds(0, 0, 50, 50);

        //定义底部标签图片大小和位置
        Drawable drawable_news2 = getResources().getDrawable(R.drawable.home_cinema);
        //当这个图片被绘制时，给他绑定一个矩形 ltrb规定这个矩形
        drawable_news2.setBounds(0, 0, 50, 50);

        Home_Rb1 = (RadioButton) findViewById(R.id.Home_Rb1);

        Home_Rb2 = (RadioButton) findViewById(R.id.Home_Rb2);

        Home_Rb3 = (RadioButton) findViewById(R.id.Home_Rb3);

    }
}
