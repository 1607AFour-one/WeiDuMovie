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
import com.bw.movie.bean.MessageData;
import com.bw.movie.bean.ReleaseMovieData;
import com.bw.movie.fragment.ComSoonFragment;
import com.bw.movie.fragment.HotMovieFragment;
import com.bw.movie.fragment.MovieFragment;
import com.bw.movie.fragment.ReleaseFragment;
import com.bw.movie.presenter.PresenterImpl;
import com.bw.movie.utils.Contacts;
import com.bw.movie.utils.SpUtils;
import com.bw.movie.view.IView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MovieListActivity extends BaseActivity implements IView {
    List<HotMovieData.ResultBean> mHotList=new ArrayList<>();
    List<ReleaseMovieData.ResultBean> mReleaseList=new ArrayList<>();
    List<ComingSoonData.ResultBean> mComingList=new ArrayList<>();
    private TextView mCity;
    private RadioGroup mRag;
    private RecyclerView mRecy;
    private HashMap map;
    private HashMap<String, Object> headMap;
    private PresenterImpl presenter;

    @Override
    protected int initLayout() {
        return R.layout.activity_movie_list;
    }

    @Override
    protected void initView() {

        mRag = findViewById(R.id.movie_list_rg);
        mCity = findViewById(R.id.movie_list_city);

    }

    @Override
    protected void setClicks() {
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void setProgress() {
        getSupportFragmentManager().beginTransaction().replace(R.id.movie_list_fr,new HotMovieFragment()).commit();


        mRag.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.search_rb_hot:
                        getSupportFragmentManager().beginTransaction().replace(R.id.movie_list_fr,new HotMovieFragment()).commit();
                        break;
                    case R.id.search_rb_release:
                        getSupportFragmentManager().beginTransaction().replace(R.id.movie_list_fr,new ReleaseFragment()).commit();

                        break;
                    case R.id.search_rb_coming:
                        getSupportFragmentManager().beginTransaction().replace(R.id.movie_list_fr,new ComSoonFragment()).commit();

                        break;
                }
            }
        });


    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void successData(Object data) {
        if(data instanceof MessageData){
            MessageData messageData= (MessageData) data;
            showShort(messageData.getMessage());
        }

    }

    @Override
    public void errorMsg(Object error) {

    }
}
