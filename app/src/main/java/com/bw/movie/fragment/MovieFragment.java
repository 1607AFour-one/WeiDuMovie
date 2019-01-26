package com.bw.movie.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.HomeGroupAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.bean.ComingSoonData;
import com.bw.movie.bean.HotMovieData;
import com.bw.movie.bean.ReleaseMovieData;
import com.bw.movie.presenter.PresenterImpl;
import com.bw.movie.utils.Contacts;
import com.bw.movie.utils.SpUtils;
import com.bw.movie.view.IView;
import com.bw.movie.weight.TopView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends BaseFragment implements IView {

    private RecyclerView groupRecy;
    List<HotMovieData.ResultBean> mHotList=new ArrayList<>();
    List<ReleaseMovieData.ResultBean> mReleaseList=new ArrayList<>();
    List<ComingSoonData.ResultBean> mComingList=new ArrayList<>();
    private HomeGroupAdapter mHomeGroupAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_movie;
    }

    @Override
    protected void init(View view, Bundle savedInstanceState) {
        groupRecy = view.findViewById(R.id.home_group_recy);
        groupRecy.setLayoutManager(new LinearLayoutManager(getActivity()));

    }
    @Override
    public void fetchData() {
        mHomeGroupAdapter = new HomeGroupAdapter(mHotList,mReleaseList,mComingList,getActivity());
        groupRecy.setAdapter(mHomeGroupAdapter);

        int userId = SpUtils.getInt("userId");
        String sessionId=SpUtils.getString("sessionId");
        HashMap<String,Object> headmap=new HashMap<>();
        headmap.put("userId",userId);
        headmap.put("sessionId",sessionId);
       // Toast.makeText(getActivity(), userId+"", Toast.LENGTH_SHORT).show();
        //Toast.makeText(getActivity(), sessionId+"", Toast.LENGTH_SHORT).show();
        HashMap<String,Object> map=new HashMap<>();
        map.put("page",1+"");
        map.put("count",10+"");
        //HomeGroupAdapter mGroupAdapter=new HomeGroupAdapter();
        PresenterImpl presenter=new PresenterImpl(this);
        presenter.requestGEt(Contacts.HOT_URL,map,headmap,HotMovieData.class);
        presenter.requestGEt(Contacts.RELEASE_URL,map,headmap,ReleaseMovieData.class);
        presenter.requestGEt(Contacts.COMINGSOON_URL_,map,headmap,ComingSoonData.class);


    }

    @Override
    public void successData(Object data) {
        if(data instanceof HotMovieData){
            //Toast.makeText(getActivity(), 111+"", Toast.LENGTH_SHORT).show();
            HotMovieData hotMovieData= (HotMovieData) data;
            mHotList.addAll(hotMovieData.getResult());
            mHomeGroupAdapter.notifyDataSetChanged();

        }
        if(data instanceof ReleaseMovieData){
            ReleaseMovieData releaseMovieData= (ReleaseMovieData) data;
            mReleaseList.addAll(releaseMovieData.getResult());
            mHomeGroupAdapter.notifyDataSetChanged();
        }
        if(data instanceof ComingSoonData){
            ComingSoonData comingSoonData= (ComingSoonData) data;
            mComingList.addAll(comingSoonData.getResult());
            mHomeGroupAdapter.notifyDataSetChanged();

        }

    }

    @Override
    public void errorMsg(Object error) {
        Log.e("zzz",error+"");
    }
}
