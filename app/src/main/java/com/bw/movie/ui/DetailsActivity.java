package com.bw.movie.ui;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.adapter.DiscussMovieAdapter;
import com.bw.movie.adapter.MyrecyFlowAdapter;
import com.bw.movie.adapter.TheatrePopXiangAdapter;
import com.bw.movie.adapter.TimeMovieAdapter;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.ComingSoonData;
import com.bw.movie.bean.DetailsData;
import com.bw.movie.bean.DiscussMovieData;
import com.bw.movie.bean.TimeMovieData;
import com.bw.movie.presenter.PresenterImpl;
import com.bw.movie.utils.Contacts;
import com.bw.movie.utils.SpUtils;
import com.bw.movie.view.IView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import recycler.coverflow.CoverFlowLayoutManger;
import recycler.coverflow.RecyclerCoverFlow;

public class DetailsActivity extends BaseActivity implements IView {
    //你好
    private ImageView home_group_dingwei_image;
    private TextView Text_Name, Text_Address;
    private ImageView Ding;
    private RelativeLayout home_group_relay;
    private RecyclerCoverFlow home_group_recyflow;
    private RelativeLayout home_group_relay2;
    // private View View;
    private View view;
    private View viewXing;
    private List<ComingSoonData.ResultBean> mComingList = new ArrayList<>();
    private HashMap<String, Object> hashMap;
    private int id;
    private PresenterImpl presenter;
    private List<TimeMovieData.ResultBean> mList = new ArrayList<>();
    private List<DetailsData.ResultBean>dList=new ArrayList<>();
    private List<DiscussMovieData.ResultBean>pList=new ArrayList<>();
    private RecyclerView time_xrecy;
    private TimeMovieAdapter timeMovieAdapter;
    private TextView theatre_rb1;
    private TextView theatre_text1,theatre_text2;
    private TextView theatre_rb2;
    private RelativeLayout theatre_layout;
    private RecyclerView Theatre_Recy,Theatre_Recy_Ping;
    private HashMap<String, Object> map;
    TextView xaing_address,xiang_phone,xiang_di,xiang_gong,xiang_zi;
    @Override
    protected int initLayout() {
        return R.layout.activity_details;


    }

    @Override
    protected void initView() {
        home_group_dingwei_image = findViewById(R.id.home_group_dingwei_image);
        Text_Name = findViewById(R.id.Text_Name);
        Text_Address = findViewById(R.id.Text_Address);
        home_group_recyflow = findViewById(R.id.home_group_recyflow);
        time_xrecy = findViewById(R.id.Time_Xrecy);
        Ding = findViewById(R.id.Ding);
        //View= findViewById(R.id.View);
        MyrecyFlowAdapter myrecyFlowAdapter = new MyrecyFlowAdapter(getApplicationContext(), mComingList);
        home_group_recyflow.setAdapter(myrecyFlowAdapter);
        home_group_recyflow.setGreyItem(true);
        time_xrecy.setLayoutManager(new LinearLayoutManager(this));


        //影院详情
        view = View.inflate(DetailsActivity.this, R.layout.movie_theatre_pop, null);
        home_group_dingwei_image.setOnClickListener(this);
        theatre_rb1=view.findViewById(R.id.theatre_rb1);
        theatre_rb2=view.findViewById(R.id.theatre_rb2);
        theatre_text1=view.findViewById(R.id.theatre_text1);
        theatre_text2=view.findViewById(R.id.theatre_text2);
        Theatre_Recy=view.findViewById(R.id.Theatre_Recy);
        Theatre_Recy_Ping=view.findViewById(R.id.Theatre_Recy_Ping);
        Theatre_Recy_Ping.setLayoutManager(new LinearLayoutManager(this));
        theatre_rb1.setOnClickListener(this);
        theatre_rb2.setOnClickListener(this);
//        viewXing = View.inflate(DetailsActivity.this, R.layout.theatre_pop_xiang, null);
//        xaing_address = viewXing.findViewById(R.id.xaing_address);
//        xiang_phone = viewXing.findViewById(R.id.xiang_phone);
//        xiang_di = viewXing.findViewById(R.id.xiang_di);
//        xiang_gong = viewXing.findViewById(R.id.xiang_gong);
//        xiang_zi = viewXing.findViewById(R.id.xiang_zi);
    }

    /**
     * 让状态栏变透明
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    @Override
    protected void setClicks() {

    }

    @Override
    protected void initListener() {

    }


    @Override
    protected void setProgress() {

        //让状态栏透明
        changeStatusBarColor();
        //让状态栏透明
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        //获取影院id；
        Intent intent = getIntent();
        id = intent.getIntExtra("cinemaId", 0);
        map = new HashMap<>();
        hashMap = new HashMap<>();
        map.put("cinemaId", id + "");
        // Toast.makeText(getApplication(),id+"",Toast.LENGTH_SHORT).show();
        int userId = SpUtils.getInt("userId");
        String sessionId = SpUtils.getString("sessionId");
        HashMap<String, Object> headmap = new HashMap<>();

        headmap.put("userId", userId);
        headmap.put("sessionId", sessionId);
        HashMap<String, Object> zMap = new HashMap<>();
        zMap.put("page", 1 + "");
        zMap.put("count", 10 + "");

        //评论
        HashMap<String, Object> pMap = new HashMap<>();
        pMap.put("cinemaId", id + "");
        pMap.put("page",1+"");
        pMap.put("count", 10 + "");
        presenter = new PresenterImpl(this);

        presenter.requestGEt(Contacts.SKIPMOIVE_URL, map, hashMap, DetailsData.class);
        //请求网络给轮播赋值
        presenter.requestGEt(Contacts.COMINGSOON_URL_, zMap, headmap, ComingSoonData.class);
        //评论
        DiscussMovieAdapter discussMovieAdapter=new DiscussMovieAdapter(pList,getApplicationContext());
        Theatre_Recy_Ping.setAdapter(discussMovieAdapter);
        presenter.requestGEt(Contacts.PINGLUNMOIVE_URL,pMap,headmap,DiscussMovieData.class);
        //根据电影ID和影院ID查询电影排期列表
        MyrecyFlowAdapter myrecyFlowAdapter = new MyrecyFlowAdapter(getApplicationContext(), mComingList);
        home_group_recyflow.setOnItemSelectedListener(new CoverFlowLayoutManger.OnSelected() {
            @Override

            public void onItemSelected(int position) {
                mList.clear();
                int movieId = position;
                HashMap<String, Object> mMap = new HashMap<>();
                mMap.put("cinemasId", id + "");
                mMap.put("movieId", movieId + "");
                //Toast.makeText(getApplicationContext(),movieId+"",Toast.LENGTH_SHORT).show();
                timeMovieAdapter = new TimeMovieAdapter(mList, getApplicationContext());
                time_xrecy.setAdapter(timeMovieAdapter);
                //根据电影Id和影院ID查询电影排期
                presenter.requestGEt(Contacts.TIMEMOVIE_URL, mMap, hashMap, TimeMovieData.class);
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.home_group_dingwei_image:
                //影院详情
                showShort("吐司详情");
                final PopupWindow popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.WRAP_CONTENT);
                popupWindow.setBackgroundDrawable(new ColorDrawable());
                popupWindow.setFocusable(true);
                // 设置点击popupwindow外屏幕其它地方消失
                popupWindow.setOutsideTouchable(true);


                Ding.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });

                popupWindow.setAnimationStyle(R.style.popwin_anim_style);
                popupWindow.showAtLocation(DetailsActivity.this.findViewById(R.id.details), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

                break;
            case R.id.theatre_rb1:
                showShort("详情");
                TheatrePopXiangAdapter theatrePopXiangAdapter=new TheatrePopXiangAdapter(dList,getApplicationContext());
                Theatre_Recy.setAdapter(theatrePopXiangAdapter);
                theatrePopXiangAdapter.notifyDataSetChanged();
                Theatre_Recy.setLayoutManager(new LinearLayoutManager(this));
                presenter.requestGEt(Contacts.SKIPMOIVE_URL, map, hashMap, DetailsData.class);
                Theatre_Recy.setVisibility(View.VISIBLE);
                Theatre_Recy_Ping.setVisibility(View.GONE);
                break;

            case R.id.theatre_rb2:
                showShort("评论");
                Theatre_Recy.setVisibility(View.GONE);
                Theatre_Recy_Ping.setVisibility(View.VISIBLE);
                break;

        }


    }

    @Override
    public void successData(Object data) {


        if (data instanceof DetailsData) {

            DetailsData detailsData = (DetailsData) data;

            DetailsData.ResultBean result = detailsData.getResult();
            Text_Name.setText(result.getName());
            Text_Address.setText(result.getAddress());
            Glide.with(this).load(result.getLogo()).into(home_group_dingwei_image);
            //TextView xaing_address,xiang_phone,xiang_di,xiang_gong,xiang_zi;
//            xaing_address.setText(result.getAddress());
//            xiang_phone.setText(result.getPhone());
//            xiang_di.setText(result.getVehicleRoute());
        }

        if (data instanceof ComingSoonData) {
            ComingSoonData comingSoonData = (ComingSoonData) data;
            mComingList.addAll(comingSoonData.getResult());

        }

        if (data instanceof TimeMovieData) {

            TimeMovieData timeMovieData = (TimeMovieData) data;
            //Toast.makeText(DetailsActivity.this, timeMovieData.getResult().size()+"", Toast.LENGTH_SHORT).show();
            mList.addAll(timeMovieData.getResult());
            timeMovieAdapter.notifyDataSetChanged();
        }

        if(data instanceof DiscussMovieData){
            DiscussMovieData discussMovieData= (DiscussMovieData) data;
            pList.addAll(discussMovieData.getResult());


        }
    }

    @Override
    public void errorMsg(Object error) {

    }



}
