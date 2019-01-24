package com.bw.movie.app;

import android.app.Application;
import android.content.Context;

import com.bw.movie.utils.DensityUtils;

public class MyApp extends Application {

    public static Context context;

    //
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        DensityUtils.setDensity(this);
    }
}
