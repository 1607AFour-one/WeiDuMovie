package com.bw.movie.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.adapter.CinemaAdapter;
import com.bw.movie.adapter.QueryCinemaAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.bean.CinemaData;
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
public class CinemaFragment extends BaseFragment implements IView {


    private RecyclerView Cinema_Xrecy;
    private List<CinemaData.ResultBean>cList=new ArrayList<>();
    private int mNer=1;
    private int mCount=10;
    private HashMap<String, Object> headmap;
    private HashMap<String, Object> map;
    private int userId;
    private String sessionId;
    private PresenterImpl presenter;
    private CinemaAdapter cinemaAdapter;

    public CinemaFragment() {
        // Required empty public constructor
    }


    @Override
    protected int setLayoutId() {
        return R.layout.fragment_cinema;
    }

    @Override
    protected void init(View view, Bundle savedInstanceState) {

        Cinema_Xrecy= view.findViewById(R.id.Cinema_Xrecy);
        Cinema_Xrecy.setLayoutManager(new LinearLayoutManager(getContext()));
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
        cinemaAdapter = new CinemaAdapter(cList,getContext());
        Cinema_Xrecy.setAdapter(cinemaAdapter);
        presenter.requestGEt(Contacts.CINEMA_CINEMA, map, headmap,CinemaData.class);




    }

    @Override
    public void successData(Object data) {
        if(data instanceof CinemaData){
            CinemaData cinemaData= (CinemaData) data;
            cList.addAll(cinemaData.getResult());
            cinemaAdapter.notifyDataSetChanged();

        }

    }

    @Override
    public void errorMsg(Object error) {

    }

}
