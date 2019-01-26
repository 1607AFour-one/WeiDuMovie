package com.bw.movie.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.adapter.MovieListAdapter;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.ComingSoonData;
import com.bw.movie.bean.HotMovieData;
import com.bw.movie.bean.IntentMovieData;
import com.bw.movie.bean.ReleaseMovieData;

import java.util.ArrayList;
import java.util.List;

public class MovieListActivity extends BaseActivity {
    List<HotMovieData.ResultBean> mHotList=new ArrayList<>();
    List<ReleaseMovieData.ResultBean> mReleaseList=new ArrayList<>();
    List<ComingSoonData.ResultBean> mComingList=new ArrayList<>();
    private TextView mCity;
    private RadioGroup mRag;
    private RecyclerView mRecy;


    @Override
    protected int initLayout() {
        return R.layout.activity_movie_list;
    }

    @Override
    protected void initView() {
        mRag = findViewById(R.id.movie_list_rg);
        mCity = findViewById(R.id.movie_list_city);
        mRecy = findViewById(R.id.movie_list_recy);
        //findViewById(R.id.search_rb_hot);
        mRecy.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void setClicks() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void setProgress() {
        Intent intent=getIntent();
        IntentMovieData intentMovieData= (IntentMovieData) intent.getSerializableExtra("movieList");
        mHotList=intentMovieData.getmHotList();
        mReleaseList=intentMovieData.getmReleaseList();
        mComingList=intentMovieData.getmComingList();
        mRecy.setAdapter(new MovieListAdapter(MovieListActivity.this,0,mHotList,mReleaseList,mComingList));
        mRag.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.search_rb_hot:
                        mRecy.setAdapter(new MovieListAdapter(MovieListActivity.this,0,mHotList,mReleaseList,mComingList));
                        break;
                    case R.id.search_rb_release:
                        mRecy.setAdapter(new MovieListAdapter(MovieListActivity.this,1,mHotList,mReleaseList,mComingList));
                        break;
                    case R.id.search_rb_coming:
                        mRecy.setAdapter(new MovieListAdapter(MovieListActivity.this,2,mHotList,mReleaseList,mComingList));
                        break;
                }
            }
        });


    }

    @Override
    public void onClick(View v) {

    }
}
