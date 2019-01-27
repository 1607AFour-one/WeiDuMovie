package com.bw.movie.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.DetailData;
import com.bw.movie.bean.MovieMessage;
import com.bw.movie.presenter.PresenterImpl;
import com.bw.movie.utils.Contacts;
import com.bw.movie.utils.SpUtils;
import com.bw.movie.view.IView;

import java.util.HashMap;

public class MovieDeailsActivity extends BaseActivity implements IView {

    private ImageView detailImage;
    private TextView movieName;
    private RadioGroup radioGroup;

    @Override
    protected int initLayout() {
        return R.layout.activity_movie_deails;
    }

    @Override
    protected void initView() {
        detailImage = findViewById(R.id.detail_image);
        movieName = findViewById(R.id.detail_name);
        radioGroup = findViewById(R.id.detail_rg);
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
        String movieId = intent.getStringExtra("movieId");
        int userId = SpUtils.getInt("userId");
        String sessionId=SpUtils.getString("sessionId");
        HashMap<String,Object> headmap=new HashMap<>();
        headmap.put("userId",userId);
        headmap.put("sessionId",sessionId);
        PresenterImpl presenter=new PresenterImpl(this);
        HashMap<String,Object> map=new HashMap<>();
        map.put("movieId",movieId);
        showShort(movieId+"");
        presenter.requestGEt(Contacts.FINDMOVIEBYID_URL,map,headmap,MovieMessage.class);

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void successData(Object data) {
        MovieMessage movieMessage= (MovieMessage) data;
        showShort(movieMessage.getMessage());
        Glide.with(MovieDeailsActivity.this).load(movieMessage.getResult().getImageUrl()).into(detailImage);
        movieName.setText(movieMessage.getResult().getName());

    }

    @Override
    public void errorMsg(Object error) {

    }
}
