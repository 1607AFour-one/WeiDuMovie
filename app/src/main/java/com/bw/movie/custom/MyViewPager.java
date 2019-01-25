package com.bw.movie.custom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MyViewPager extends ViewPager {


    private boolean b  = false;

    public void setB(boolean b) {
        this.b = b;
    }

    public MyViewPager(@NonNull Context context) {
        this(context,null);
    }

    public MyViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(b){
            return super.onTouchEvent(ev);
        }else {
            return  b ;
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (b){
            return super.onInterceptTouchEvent(ev);

        }else {
            return b;
        }
    }

}
