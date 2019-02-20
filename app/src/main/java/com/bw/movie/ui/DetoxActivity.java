package com.bw.movie.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.adapter.TimeMovieAdapter;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.DetailsData;
import com.bw.movie.bean.MovieDetailData;
import com.bw.movie.bean.TimeMovieData;
import com.bw.movie.presenter.PresenterImpl;
import com.bw.movie.utils.Contacts;
import com.bw.movie.utils.SpUtils;
import com.bw.movie.view.IView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DetoxActivity extends BaseActivity implements IView {
    private TimeMovieAdapter timeMovieAdapter;
    private List<TimeMovieData.ResultBean> mList = new ArrayList<>();
    private List<DetailsData.ResultBean>dList=new ArrayList<>();
    private RecyclerView time_xrecy;
    private PresenterImpl presenter;
    private TextView text_name;
    private TextView text_address;
    private ImageView fanHui;
    private MovieDetailData movieDetailData;
    private ImageView predator;
    private TextView detail_name;
    private TextView detail_type;
    private TextView detail_director;
    private TextView details_time;
    private TextView details_address;


    @Override
    protected int initLayout() {
        return R.layout.activity_detox;
    }

    @Override
    protected void initView() {

        time_xrecy = findViewById(R.id.Time_Xrecy);
        time_xrecy.setLayoutManager(new LinearLayoutManager(this));
        text_name = findViewById(R.id.Text_Name);
        text_address = findViewById(R.id.Text_Address);
        fanHui = findViewById(R.id.FanHui);
        fanHui.setOnClickListener(this);

        //详情
        predator = findViewById(R.id.predator);
        detail_name = findViewById(R.id.detail_name);
        detail_type = findViewById(R.id.detail_type);
        detail_director = findViewById(R.id.detail_director);
        details_time = findViewById(R.id.details_time);
        details_address = findViewById(R.id.details_address);
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
        String cinemaId = intent.getStringExtra("cinemaId");
        String movieId = intent.getStringExtra("movieId");
        //Toast.makeText(this,cinemaId+"",Toast.LENGTH_SHORT).show();

        presenter = new PresenterImpl(this);
        HashMap<String, Object> mMap = new HashMap<>();
        HashMap<String, Object> hashMap=new HashMap<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("cinemaId", cinemaId + "");
        mMap.put("cinemasId", cinemaId + "");
        mMap.put("movieId", movieId + "");
        timeMovieAdapter = new TimeMovieAdapter(mList, getApplicationContext(),DetoxActivity.this);
        time_xrecy.setAdapter(timeMovieAdapter);
        //根据电影Id和影院ID查询电影排期
        presenter.requestGEt(Contacts.TIMEMOVIE_URL, mMap, hashMap, TimeMovieData.class);
        presenter.requestGEt(Contacts.SKIPMOIVE_URL, map, hashMap, DetailsData.class);

        HashMap<String, Object> headmap = new HashMap<>();
        HashMap<String, Object> cmap=new HashMap<>();
        int userId = SpUtils.getInt("userId");
        String sessionId=SpUtils.getString("sessionId");
        cmap.put("movieId",movieId+"");
        headmap.put("userId",userId);
        headmap.put("sessionId",sessionId);
        presenter.requestGEt(Contacts.MOVIE_DETAIL_URL, cmap, headmap,MovieDetailData.class);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.FanHui:
                finish();
                break;

        }
    }

    @Override
    public void successData(Object data) {

        if (data instanceof TimeMovieData) {

            TimeMovieData timeMovieData = (TimeMovieData) data;
            mList.addAll(timeMovieData.getResult());
            timeMovieAdapter.notifyDataSetChanged();
        }
        if (data instanceof DetailsData) {

            DetailsData detailsData = (DetailsData) data;
            DetailsData.ResultBean result = detailsData.getResult();
            text_name.setText(result.getName());
            text_address.setText(result.getAddress());
        }

        if(data instanceof MovieDetailData){
            movieDetailData = (MovieDetailData) data;
            Glide.with(DetoxActivity.this).load(movieDetailData.getResult().getImageUrl()).into(predator);
            detail_name.setText(movieDetailData.getResult().getName());
            detail_type.setText(movieDetailData.getResult().getMovieTypes());
            details_address.setText(movieDetailData.getResult().getPlaceOrigin());
            details_time.setText(movieDetailData.getResult().getDuration());
            detail_director.setText(movieDetailData.getResult().getDirector());
        }

    }

    @Override
    public void errorMsg(Object error) {

    }
}
