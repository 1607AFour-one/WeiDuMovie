package com.bw.movie.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.adapter.MyrecyFlowAdapter;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.ComingSoonData;
import com.bw.movie.bean.DetailsData;
import com.bw.movie.presenter.PresenterImpl;
import com.bw.movie.utils.Contacts;
import com.bw.movie.utils.SpUtils;
import com.bw.movie.view.IView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import recycler.coverflow.RecyclerCoverFlow;

public class DetailsActivity extends BaseActivity implements IView {

    private ImageView home_group_dingwei_image;
    private TextView Text_Name,Text_Address;
    private ImageView Ding;
    private RelativeLayout home_group_relay;
    private RecyclerCoverFlow home_group_recyflow;
    private RelativeLayout home_group_relay2;
    private View View;
    private List<ComingSoonData.ResultBean> mComingList=new ArrayList<>();
    @Override
    protected int initLayout() {
        return R.layout.activity_details;
    }

    @Override
    protected void initView() {
        home_group_dingwei_image= findViewById(R.id.home_group_dingwei_image);
        Text_Name= findViewById(R.id.Text_Name);
        Text_Address =findViewById(R.id.Text_Address);
        home_group_recyflow= findViewById(R.id.home_group_recyflow);
        Ding= findViewById(R.id.Ding);
        View= findViewById(R.id.View);
        MyrecyFlowAdapter myrecyFlowAdapter=new MyrecyFlowAdapter(getApplicationContext(),mComingList);
        home_group_recyflow.setAdapter(myrecyFlowAdapter);
        home_group_recyflow.setGreyItem(true);
    }

    @Override
    protected void setClicks() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void setProgress() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("cinemaId", 0);
        HashMap<String, Object> map = new HashMap<>();
        HashMap<String, Object> hashMap = new HashMap<>();
        map.put("cinemaId", id + "");
        // Toast.makeText(getApplication(),id+"",Toast.LENGTH_SHORT).show();
        int userId = SpUtils.getInt("userId");
        String sessionId=SpUtils.getString("sessionId");
        HashMap<String,Object> headmap=new HashMap<>();
        

        headmap.put("userId",userId);
        headmap.put("sessionId",sessionId);
        HashMap<String,Object> zMap=new HashMap<>();
        zMap.put("page",1+"");
        zMap.put("count",10+"");
        PresenterImpl presenter = new PresenterImpl(this);
        presenter.requestGEt(Contacts.SKIPMOIVE_URL, map, hashMap, DetailsData.class);
        presenter.requestGEt(Contacts.COMINGSOON_URL_,zMap,headmap,ComingSoonData.class);
        //根据电影Id和影院ID查询电影排期
        presenter.requestGEt(Contacts.TIMEMOVIE_URL,map,headmap,ComingSoonData.class);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void successData(Object data) {

        if(data instanceof DetailsData){

            DetailsData detailsData= (DetailsData) data;
            DetailsData.ResultBean result = detailsData.getResult();
            Text_Name.setText(result.getName());
            Text_Address.setText(result.getAddress());
            Glide.with(this).load(result.getLogo()).into(home_group_dingwei_image);

        }
        if(data instanceof ComingSoonData){
            ComingSoonData comingSoonData= (ComingSoonData) data;
            mComingList.addAll(comingSoonData.getResult());

        }

    }

    @Override
    public void errorMsg(Object error) {

    }


}
