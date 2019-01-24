package com.bw.movie;



import android.os.Build;
import android.support.annotation.RequiresApi;
import android.transition.Explode;
import android.view.View;

import com.bw.movie.base.BaseActivity;

public class MainActivity extends BaseActivity {


    @Override
    protected int initLayout() {

        return R.layout.activity_main;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void initView() {

        getWindow().setEnterTransition(new Explode().setDuration(3000));
        getWindow().setExitTransition(new Explode().setDuration(3000));
    }

    @Override
    protected void setClicks() {


    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onClick(View v) {

    }
}
