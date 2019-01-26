package com.bw.movie.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.NearAdapter;
import com.bw.movie.adapter.RecommendAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.bean.HotMovieData;
import com.bw.movie.bean.NearMovieData;
import com.bw.movie.bean.RecommendData;
import com.bw.movie.presenter.PresenterImpl;
import com.bw.movie.utils.Contacts;
import com.bw.movie.utils.SpUtils;
import com.bw.movie.view.IView;
import com.bw.movie.weight.TopView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NearFragment extends BaseFragment implements IView {


    private TopView Top;
    private ImageView home_group_dingwei_image;
    private RelativeLayout near_relay;
    private RadioButton Near_Rb1;
    private RadioButton Near_Rb2;
    private RadioGroup Near_Rg;
    private XRecyclerView Near_Xrecy;
    private List<RecommendData.ResultBean>rList=new ArrayList<>();
    private List<NearMovieData.ResultBean>nList=new ArrayList<>();
    private RecommendAdapter recommendAdapter;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private int index=1;
    private int count=10;
    private int userId;
    private String sessionId;
    private PresenterImpl presenter;
    private NearAdapter nearAdapter;
    @Override
    protected int setLayoutId() {
        return R.layout.fragment_near;
    }

    @Override
    protected void init(View view, Bundle savedInstanceState) {
        Top= view.findViewById(R.id.Top);
        Near_Rb1=view.findViewById(R.id.Near_Rb1);
        Near_Rb2=view.findViewById(R.id.Near_Rb2);
        Near_Xrecy=view.findViewById(R.id.Near_Xrecy);
        Near_Rg= view.findViewById(R.id.Near_Rg);
        Near_Xrecy.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void fetchData() {
        presenter = new PresenterImpl(this);

        recommendAdapter = new RecommendAdapter(rList,getActivity());
        Near_Xrecy.setAdapter(recommendAdapter);
        userId = SpUtils.getInt("userId");
        sessionId = SpUtils.getString("sessionId");
        //Toast.makeText(getActivity(),sessionId+"",Toast.LENGTH_SHORT).show();
        // Toast.makeText(getActivity(),userId+"",Toast.LENGTH_SHORT).show();
        headmap = new HashMap<>();
        headmap.put("userId", userId);
        headmap.put("sessionId", sessionId);
        map = new HashMap<>();
        map.put("page",index+"");
        map.put("count",count+"");
        presenter.requestGEt(Contacts.RECOMMEND_URL, map, headmap,RecommendData.class);

        Near_Xrecy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                rList.clear();
                map.clear();
                index=1;
                HashMap<String,Object>downMap=new HashMap<>();

                map.put("page",index+"");
                //map.put("count",count+"");
                headmap.put("userId", userId);
                headmap.put("sessionId", sessionId);
                presenter.requestGEt(Contacts.RECOMMEND_URL, map, headmap,RecommendData.class);
            }

            @Override
            public void onLoadMore() {
                rList.clear();
                map.clear();
                index++;
                map.put("page",index+"");
                //map.put("count",count);
                headmap.put("userId", userId);
                headmap.put("sessionId", sessionId);
                presenter.requestGEt(Contacts.RECOMMEND_URL, map, headmap,RecommendData.class);

            }
        });


        Near_Rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {



            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){
                    case R.id.Near_Rb1:
                        Toast.makeText(getActivity(),"推荐影院",Toast.LENGTH_SHORT).show();
                        //Near_Xrecy.setVisibility(View.GONE);
                        recommendAdapter = new RecommendAdapter(rList,getActivity());
                        Near_Xrecy.setAdapter(recommendAdapter);

                        userId = SpUtils.getInt("userId");
                        sessionId = SpUtils.getString("sessionId");
                        //Toast.makeText(getActivity(),sessionId+"",Toast.LENGTH_SHORT).show();
                       // Toast.makeText(getActivity(),userId+"",Toast.LENGTH_SHORT).show();
                        headmap = new HashMap<>();
                        headmap.put("userId", userId);
                        headmap.put("sessionId", sessionId);
                        map = new HashMap<>();
                        map.put("page",index+"");
                        map.put("count",count+"");

                        presenter.requestGEt(Contacts.RECOMMEND_URL, map, headmap,RecommendData.class);

                        Near_Xrecy.setLoadingListener(new XRecyclerView.LoadingListener() {
                            @Override
                            public void onRefresh() {
                                rList.clear();
                                map.clear();
                                index=1;
                                map.put("page",index+"");
                                //map.put("count",count+"");
                                headmap.put("userId", userId);
                                headmap.put("sessionId", sessionId);
                                presenter.requestGEt(Contacts.RECOMMEND_URL, map, headmap,RecommendData.class);
                            }

                            @Override
                            public void onLoadMore() {
                                rList.clear();
                                map.clear();
                                index++;
                                map.put("page",index+"");
                                //map.put("count",count);
                                headmap.put("userId", userId);
                                headmap.put("sessionId", sessionId);
                                presenter.requestGEt(Contacts.RECOMMEND_URL, map, headmap,RecommendData.class);

                            }
                        });


                        break;
                    case R.id.Near_Rb2:
                        Toast.makeText(getActivity(),"附近影院",Toast.LENGTH_SHORT).show();
                        nearAdapter = new NearAdapter(nList,getActivity());
                        Near_Xrecy.setAdapter(nearAdapter);
                        userId = SpUtils.getInt("userId");
                        sessionId = SpUtils.getString("sessionId");
                        //Toast.makeText(getActivity(),sessionId+"",Toast.LENGTH_SHORT).show();
                        //Toast.makeText(getActivity(),userId+"",Toast.LENGTH_SHORT).show();
                        headmap = new HashMap<>();
                        headmap.put("userId", userId);
                        headmap.put("sessionId", sessionId);
                        map = new HashMap<>();

                        map.put("page",index+"");
                        map.put("count",count+"");
                        map.put("longitude",116.30551391385724+"");
                        map.put("latitude",40.04571807462411+"");
                        presenter.requestGEt(Contacts.NEARMOVIE_URL, map, headmap,NearMovieData.class);

                        Near_Xrecy.setLoadingListener(new XRecyclerView.LoadingListener() {
                            @Override
                            public void onRefresh() {
                                rList.clear();
                                map.clear();
                                index=1;
                                map.put("page",index+"");
                                //map.put("count",count+"");
                                headmap.put("userId", userId);
                                headmap.put("sessionId", sessionId);
                                map.put("longitude",116.30551391385724+"");
                                map.put("latitude",40.04571807462411+"");
                                presenter.requestGEt(Contacts.RECOMMEND_URL, map, headmap,NearMovieData.class);
                            }

                            @Override
                            public void onLoadMore() {
                                rList.clear();
                                map.clear();
                                index++;
                                map.put("page",index+"");
                                //map.put("count",count);
                                headmap.put("userId", userId);
                                headmap.put("sessionId", sessionId);
                                map.put("longitude",116.30551391385724+"");
                                map.put("latitude",40.04571807462411+"");
                                presenter.requestGEt(Contacts.RECOMMEND_URL, map, headmap,NearMovieData.class);

                            }
                        });
                        break;

                }

            }
        });

    }

    @Override
    public void successData(Object data) {

        if(data instanceof RecommendData){
            RecommendData recommendData= (RecommendData) data;
            rList.addAll(recommendData.getResult());
            recommendAdapter.notifyDataSetChanged();
           // Toast.makeText(getActivity(),recommendData.getMessage(),Toast.LENGTH_SHORT).show();
            Near_Xrecy.loadMoreComplete();
            Near_Xrecy.refreshComplete();


        }

        if(data instanceof NearMovieData){
            NearMovieData nearMovieData= (NearMovieData) data;
            nList.addAll(nearMovieData.getResult());
            nearAdapter.notifyDataSetChanged();
            Near_Xrecy.loadMoreComplete();
            Near_Xrecy.refreshComplete();
        }


    }

    @Override
    public void errorMsg(Object error) {

    }



}
