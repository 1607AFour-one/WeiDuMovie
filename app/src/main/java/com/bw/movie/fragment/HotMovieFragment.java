package com.bw.movie.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.MovieListAdapter;
import com.bw.movie.bean.HotMovieData;
import com.bw.movie.bean.MessageData;
import com.bw.movie.presenter.PresenterImpl;
import com.bw.movie.utils.Contacts;
import com.bw.movie.utils.SpUtils;
import com.bw.movie.view.IView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotMovieFragment extends Fragment implements IView {
    List<HotMovieData.ResultBean> mHotList = new ArrayList<>();
    private MovieListAdapter movieListAdapter;
    private RecyclerView recy;
    private HashMap<String, Object> headmap;
    private HashMap<String, Object> map;
    private PresenterImpl presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_hot_movie, null);
        initView(v);
        int userId = SpUtils.getInt("userId");
        String sessionId=SpUtils.getString("sessionId");
        headmap = new HashMap<>();
        headmap.put("userId",userId);
        headmap.put("sessionId",sessionId);

        map = new HashMap<>();
        map.put("page",1+"");
        map.put("count",10+"");
        presenter = new PresenterImpl(this);
        presenter.requestGEt(Contacts.HOT_URL, map, headmap,HotMovieData.class);
        //-----
        movieListAdapter.setMovieListAdapterListener(new MovieListAdapter.MovieListAdapterListener() {
            @Override
            public void getMovieId(int movieId, int isFollowMovie) {
                map = new HashMap();
                map.put("movieId",movieId);

                if(isFollowMovie==1){
                    presenter.requestGEt(Contacts.FOLLMOVIE_URL, map,headmap,MessageData.class);
                }else{

                    presenter.requestGEt(Contacts.CANCEL_FOLLOWMOVIE, map,headmap,MessageData.class);
                }
            }
        });


        return v;

    }

    private void initView(View v) {
        recy = (RecyclerView) v.findViewById(R.id.hot_rexy);
        recy.setLayoutManager(new LinearLayoutManager(getActivity()));
        movieListAdapter = new MovieListAdapter();
        movieListAdapter.setmContext(getActivity());
        movieListAdapter.setmHotList(mHotList);
        movieListAdapter.setType(0);
        recy.setAdapter(movieListAdapter);
    }

    @Override
    public void successData(Object data) {
        if(data instanceof HotMovieData){
            HotMovieData hotMovieData= (HotMovieData) data;
            mHotList.addAll(hotMovieData.getResult());
            movieListAdapter.notifyDataSetChanged();
        }
        if(data instanceof MessageData){
            MessageData messageData= (MessageData) data;
            Toast.makeText(getActivity(), messageData.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void errorMsg(Object error) {

    }
}
