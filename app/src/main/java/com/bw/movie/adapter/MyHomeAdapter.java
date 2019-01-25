package com.bw.movie.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class MyHomeAdapter extends FragmentPagerAdapter {

    private List<Fragment>fList;
    private Context mContext;

    public MyHomeAdapter(FragmentManager fm, List<Fragment> fList, Context mContext) {
        super(fm);
        this.fList = fList;
        this.mContext = mContext;
    }



    @Override
    public Fragment getItem(int i) {
        return fList.get(i);
    }

    @Override
    public int getCount() {
        return fList.size();
    }
}
