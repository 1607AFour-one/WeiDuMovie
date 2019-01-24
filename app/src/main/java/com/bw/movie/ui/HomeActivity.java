package com.bw.movie.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;

import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setClicks() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void setProgress() {

    }

    @Override
    public void onClick(View v) {

    }
}
