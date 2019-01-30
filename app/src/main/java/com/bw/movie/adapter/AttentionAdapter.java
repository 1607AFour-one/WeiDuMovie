package com.bw.movie.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class AttentionAdapter extends FragmentPagerAdapter {
    private List<Fragment>mList;
    private Context mContext;

    public AttentionAdapter(FragmentManager fm, List<Fragment> mList, Context mContext) {
        super(fm);
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    public Fragment getItem(int i) {
        return mList.get(i);
    }

    @Override
    public int getCount() {
        return mList.size();
    }
}
