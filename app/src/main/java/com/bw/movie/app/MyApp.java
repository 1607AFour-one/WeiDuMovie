package com.bw.movie.app;

import android.app.Application;

import com.bw.movie.utils.DensityUtils;

public class MyApp extends Application {
    //
    @Override
    public void onCreate() {
        super.onCreate();
        DensityUtils.setDensity(this);
    }
}
