package com.bw.movie.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.MovieListAdapter;
import com.bw.movie.bean.ComingSoonData;
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
public class ComSoonFragment extends Fragment implements IView {

    private RecyclerView recy;
    List<ComingSoonData.ResultBean> mComingList=new ArrayList<>();
    private MovieListAdapter movieListAdapter;
    private HashMap<String, Object> headmap;
    private HashMap<String, Object> map;
    private PresenterImpl presenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_com_soon, null);
        initView(v);
        int userId = SpUtils.getInt("userId");
        String sessionId=SpUtils.getString("sessionId");
        headmap = new HashMap<>();
        headmap.put("userId",userId);
        headmap.put("sessionId",sessionId);
        //Toast.makeText(getActivity(), "曹尼玛", Toast.LENGTH_SHORT).show();

        map = new HashMap<>();
        map.put("page",1+"");
        map.put("count",10+"");
        presenter = new PresenterImpl(this);
        presenter.requestGEt(Contacts.HOT_URL, map, headmap,ComingSoonData.class);
        //--------------关注的接口回调

        movieListAdapter.setMovieListAdapterListener(new MovieListAdapter.MovieListAdapterListener() {
            @Override
            public void getMovieId(int movieId, int isFollowMovie) {
                map = new HashMap();
                map.put("movieId",movieId);

                if(isFollowMovie==1){
                    //如果为 1就要去请求关注接口否则反之
                    presenter.requestGEt(Contacts.FOLLMOVIE_URL, map, headmap,MessageData.class);
                }else{

                    presenter.requestGEt(Contacts.CANCEL_FOLLOWMOVIE, map, headmap,MessageData.class);
                }
            }
        });

        return v;
    }

    private void initView(View v) {
        recy = (RecyclerView) v.findViewById(R.id.comsoon_recy);

        recy.setLayoutManager(new LinearLayoutManager(getActivity()));
        movieListAdapter = new MovieListAdapter();
        movieListAdapter.setmContext(getActivity());
        movieListAdapter.setmComingList(mComingList);
        movieListAdapter.setType(2);
        recy.setAdapter(movieListAdapter);
    }
    @Override
    public void successData(Object data) {
        if(data instanceof ComingSoonData){
            ComingSoonData comingSoonData= (ComingSoonData) data;
            mComingList.addAll(comingSoonData.getResult());
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
