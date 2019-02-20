package com.bw.movie.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.BuyAdapter;
import com.bw.movie.adapter.NearAdapter;
import com.bw.movie.adapter.RecommendAdapter;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.ClearMovieData;
import com.bw.movie.bean.ClickMovieData;
import com.bw.movie.bean.RecommendData;
import com.bw.movie.presenter.PresenterImpl;
import com.bw.movie.utils.Contacts;
import com.bw.movie.utils.SpUtils;
import com.bw.movie.view.IView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BuyActivity extends BaseActivity implements IView {
    private int index = 1;
    private int count = 10;
    private int userId;
    private String sessionId;
    private PresenterImpl presenter;
    private HashMap<String, Object> headmap;
    private HashMap<String, Object> recoMap;
    private List<RecommendData.ResultBean> rList = new ArrayList<>();

    private TextView buy_name;
    private XRecyclerView buy_xrecy;
    private BuyAdapter buyAdapter;
    private String movieId;
    private ImageView fanHui;

    @Override
    protected int initLayout() {
        return R.layout.activity_buy;
    }

    @Override
    protected void initView() {

        buy_name = findViewById(R.id.Buy_Name);
        buy_xrecy = findViewById(R.id.Buy_Xrecy);
        buy_xrecy.setLayoutManager(new LinearLayoutManager(this));
        fanHui = findViewById(R.id.FanHui);
        fanHui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
        String name = intent.getStringExtra("name");
        movieId = intent.getStringExtra("movieId");
        buy_name.setText(name);


        presenter = new PresenterImpl(this);
        buyAdapter = new BuyAdapter(rList, getApplicationContext(),BuyActivity.this,movieId);
        buy_xrecy.setAdapter(buyAdapter);
        userId = SpUtils.getInt("userId");
        sessionId = SpUtils.getString("sessionId");
        recoMap = new HashMap<>();
        headmap = new HashMap<>();
        headmap.put("userId", userId);
        headmap.put("sessionId", sessionId);
        recoMap.put("page", index + "");
        recoMap.put("count", count + "");
        presenter.requestGEt(Contacts.RECOMMEND_URL, recoMap, headmap, RecommendData.class);
        buy_xrecy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                rList.clear();
                recoMap.clear();
                index = 1;
                recoMap.put("page", index + "");
                recoMap.put("count", count + "");
                headmap.put("userId", userId);
                headmap.put("sessionId", sessionId);
                presenter.requestGEt(Contacts.RECOMMEND_URL, recoMap, headmap, RecommendData.class);
            }

            @Override
            public void onLoadMore() {
                //rList.clear();
                recoMap.clear();
                index++;
                recoMap.put("page", index + "");
                recoMap.put("count", count);
                headmap.put("userId", userId);
                headmap.put("sessionId", sessionId);
                presenter.requestGEt(Contacts.RECOMMEND_URL, recoMap, headmap, RecommendData.class);

            }
        });



        //推荐点赞
        buyAdapter.getItemPostion(new BuyAdapter.ItemListener() {


            @Override
            public void onitemPosition(int i) {

                //Toast.makeText(getContext(),rList.get(i).getId(),Toast.LENGTH_SHORT).show();

                HashMap<String, Object> clickMap = new HashMap<>();
                clickMap.put("cinemaId", rList.get(i).getId());

                if (rList.get(i).getFollowCinema() == 2) {

                    presenter.requestGEt(Contacts.CLICKMOVIE_URL, clickMap, headmap, ClickMovieData.class);
                    buyAdapter.notifyDataSetChanged();
                    rList.get(i).setFollowCinema(1);


                } else {

                    presenter.requestGEt(Contacts.CLEARMOVIE_URL, clickMap, headmap, ClearMovieData.class);
                    buyAdapter.notifyDataSetChanged();
                    rList.get(i).setFollowCinema(2);
                }


            }
        });


    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void successData(Object data) {

        if (data instanceof RecommendData) {
            RecommendData recommendData = (RecommendData) data;
            rList.addAll(recommendData.getResult());
            buyAdapter.notifyDataSetChanged();
            // Toast.makeText(getActivity(),recommendData.getMessage(),Toast.LENGTH_SHORT).show();
            buy_xrecy.loadMoreComplete();
            buy_xrecy.refreshComplete();


        }


        if (data instanceof ClickMovieData) {
            ClickMovieData clickMovieData = (ClickMovieData) data;
            Toast.makeText(BuyActivity.this, clickMovieData.getMessage(), Toast.LENGTH_SHORT).show();

        }

        if (data instanceof ClearMovieData) {
            ClearMovieData clearMovieData = (ClearMovieData) data;
            Toast.makeText(BuyActivity.this, clearMovieData.getMessage(), Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void errorMsg(Object error) {

    }
}
