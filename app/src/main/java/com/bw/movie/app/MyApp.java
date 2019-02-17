package com.bw.movie.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.bw.movie.utils.DensityUtils;

import java.util.LinkedList;
import java.util.List;

public class MyApp extends Application {

    public static Context context;
    private static MyApp mInstance;
    private static List<Activity> activityList = new LinkedList<Activity>();

    //
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        context = this;
        DensityUtils.setDensity(this);
    }
    public static MyApp getInstance() {
        return mInstance;
    }

    public void addActivity(Activity activity)  {
        activityList.add(activity);
    }
    public void removeActivity(Activity activity){
        activityList.remove(activity);
    }
    public void exitAllActivity(){
        for(Activity activity:activityList) {
            activity.finish();
        }
    }


}
