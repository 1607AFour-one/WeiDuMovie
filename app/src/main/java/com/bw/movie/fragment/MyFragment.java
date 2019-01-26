package com.bw.movie.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends BaseFragment {
    
    @Override
    protected int setLayoutId() {

        return R.layout.fragment_my;
    }

    @Override
    protected void init(View view, Bundle savedInstanceState) {



    }

    @Override
    public void fetchData() {

    }
}
