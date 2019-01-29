package com.bw.movie.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bw.movie.R;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attention);
        initView();
        initData();
    }

    private void initData() {
        List<Fragment> mList=new ArrayList<>();
        mList.add(new AttentionFragment());
        mList.add(new CinemaFragment());
    }

    private void initView() {
        Attention_Rb1 = (RadioButton) findViewById(R.id.Attention_Rb1);
        Attention_Rb2 = (RadioButton) findViewById(R.id.Attention_Rb2);
        Attention_Rg = (RadioGroup) findViewById(R.id.Attention_Rg);
        Attention_Vp = (MyViewPager) findViewById(R.id.Attention_Vp);
    }
}
