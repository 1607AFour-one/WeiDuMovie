package com.bw.movie.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.adapter.QueryCinemaAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.bean.NearMovieData;
import com.bw.movie.bean.QueryCinemaData;
import com.bw.movie.presenter.PresenterImpl;
import com.bw.movie.utils.Contacts;
import com.bw.movie.utils.SpUtils;
import com.bw.movie.view.IView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AttentionFragment extends BaseFragment implements IView {


    private XRecyclerView Attention_Xrecy;
    private List<QueryCinemaData.ResultBean>qList=new ArrayList<>();
    private int mNer=1;
    private int mCount=10;
    private HashMap<String, Object> headmap;
    private HashMap<String, Object> map;
    private int userId;
    private String sessionId;
    private PresenterImpl presenter;

    public AttentionFragment() {
        // Required empty public constructor
    }


    @Override
    protected int setLayoutId() {
        return R.layout.fragment_attention;
    }

    @Override
    protected void init(View view, Bundle savedInstanceState) {
        Attention_Xrecy=view.findViewById(R.id.Attention_Xrecy);
        Attention_Xrecy.setLayoutManager(new LinearLayoutManager(getContext()));
    }


    @Override
    public void fetchData() {

        userId = SpUtils.getInt("userId");
        sessionId = SpUtils.getString("sessionId");
        headmap = new HashMap<>();
        map = new HashMap<>();
        headmap.put("userId", userId);
        headmap.put("sessionId", sessionId);
        map.put("page", 1 + "");
        map.put("count", 10 + "");
        presenter = new PresenterImpl(this);
        QueryCinemaAdapter queryCinemaAdapter=new QueryCinemaAdapter(qList,getContext());
        Attention_Xrecy.setAdapter(queryCinemaAdapter);
        presenter.requestGEt(Contacts.QUERY_CINEMA, map, headmap,QueryCinemaData.class);

        Attention_Xrecy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

                qList.clear();
                map.clear();
                mNer = 1;
                map.put("page", mNer + "");
                map.put("count", mCount + "");
                headmap.put("userId", userId);
                headmap.put("sessionId", sessionId);

                presenter.requestGEt(Contacts.QUERY_CINEMA, map, headmap, QueryCinemaData.class);
            }

            @Override
            public void onLoadMore() {

                mNer++;
                map.put("page", mNer + "");
                map.put("count", mCount + "");
                headmap.put("userId", userId);
                headmap.put("sessionId", sessionId);
                presenter.requestGEt(Contacts.QUERY_CINEMA, map, headmap, QueryCinemaData.class);
            }
        });


    }

    @Override
    public void successData(Object data) {
        if(data instanceof QueryCinemaData){
            QueryCinemaData queryCinemaData= (QueryCinemaData) data;
            qList.addAll(queryCinemaData.getResult());
            Attention_Xrecy.loadMoreComplete();
            Attention_Xrecy.refreshComplete();
        }

    }

    @Override
    public void errorMsg(Object error) {

    }


}
