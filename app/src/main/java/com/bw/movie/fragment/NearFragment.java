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
import com.bw.movie.bean.ClearMovieData;
import com.bw.movie.bean.ClickMovieData;
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
    private XRecyclerView Recommend_Xrecy;
    private List<RecommendData.ResultBean>rList=new ArrayList<>();
    private List<NearMovieData.ResultBean>nList=new ArrayList<>();
    private RecommendAdapter recommendAdapter;

    private int index=1;
    private int count=10;
    private int mNer=1;
    private int mCount=10;
    private int userId;
    private String sessionId;
    private PresenterImpl presenter;
    private NearAdapter nearAdapter;
    private HashMap<String, Object> headmap;
    private HashMap<String, Object> recoMap;
    private HashMap<String, Object> nearMap;

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
        Recommend_Xrecy=view.findViewById(R.id.Recommend_Xrecy);
        Near_Rg= view.findViewById(R.id.Near_Rg);
        Near_Xrecy.setLayoutManager(new LinearLayoutManager(getActivity()));
        Recommend_Xrecy.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void fetchData() {

        presenter = new PresenterImpl(this);
        recommendAdapter = new RecommendAdapter(rList,getActivity());
        Recommend_Xrecy.setAdapter(recommendAdapter);
        userId = SpUtils.getInt("userId");
        sessionId = SpUtils.getString("sessionId");
        recoMap = new HashMap<>();
        headmap = new HashMap<>();
        headmap.put("userId", userId);
        headmap.put("sessionId", sessionId);
        recoMap.put("page",index+"");
        recoMap.put("count",count+"");
        presenter.requestGEt(Contacts.RECOMMEND_URL, recoMap, headmap,RecommendData.class);
        Recommend_Xrecy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                rList.clear();
                recoMap.clear();
                index=1;
                recoMap.put("page",index+"");
                recoMap.put("count",count+"");
                headmap.put("userId", userId);
                headmap.put("sessionId", sessionId);
                presenter.requestGEt(Contacts.RECOMMEND_URL, recoMap, headmap,RecommendData.class);
            }
            @Override
            public void onLoadMore() {
                //rList.clear();
                recoMap.clear();
                index++;
                recoMap.put("page",index+"");
                recoMap.put("count",count);
                headmap.put("userId", userId);
                headmap.put("sessionId", sessionId);
                presenter.requestGEt(Contacts.RECOMMEND_URL, recoMap, headmap,RecommendData.class);

            }
        });

        //推荐点赞
        recommendAdapter.getItemPostion(new RecommendAdapter.ItemListener() {


            @Override
            public void onitemPosition(int i) {

               //Toast.makeText(getContext(),rList.get(i).getId(),Toast.LENGTH_SHORT).show();

                HashMap<String, Object> clickMap = new HashMap<>();
                clickMap.put("cinemaId",rList.get(i).getId());

                if (rList.get(i).getFollowCinema() == 2) {

                    presenter.requestGEt(Contacts.CLICKMOVIE_URL, clickMap, headmap, ClickMovieData.class);
                    recommendAdapter.notifyDataSetChanged();
                    rList.get(i).setFollowCinema(1);

                  }else{

                    presenter.requestGEt(Contacts.CLEARMOVIE_URL, clickMap, headmap, ClearMovieData.class);
                    recommendAdapter.notifyDataSetChanged();
                    rList.get(i).setFollowCinema(2);
                }

            }
        });






        nearAdapter = new NearAdapter(nList,getActivity());
        Near_Xrecy.setAdapter(nearAdapter);
        userId = SpUtils.getInt("userId");
        sessionId = SpUtils.getString("sessionId");
        //Toast.makeText(getActivity(),sessionId+"",Toast.LENGTH_SHORT).show();
        //Toast.makeText(getActivity(),userId+"",Toast.LENGTH_SHORT).show();
        nearMap = new HashMap<>();
        nearMap.put("page",mNer+"");
        nearMap.put("count",mCount+"");
        nearMap.put("longitude",116.30551391385724+"");
        nearMap.put("latitude",40.04571807462411+"");
        presenter.requestGEt(Contacts.NEARMOVIE_URL, nearMap, headmap,NearMovieData.class);

        Near_Xrecy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

                nList.clear();
                nearMap.clear();
                mNer=1;
                nearMap.put("page",mNer+"");
                nearMap.put("count",mCount+"");
                nearMap.put("userId", userId);
                nearMap.put("sessionId", sessionId);
                nearMap.put("longitude",116.30551391385724+"");
                nearMap.put("latitude",40.04571807462411+"");
                presenter.requestGEt(Contacts.NEARMOVIE_URL, nearMap, headmap,NearMovieData.class);
            }

            @Override
            public void onLoadMore() {

                //rList.clear();
               // Rmap.clear();
                mNer++;
                nearMap.put("page",mNer+"");
                nearMap.put("count",mCount+"");
                headmap.put("userId", userId);
                headmap.put("sessionId", sessionId);
                nearMap.put("longitude",116.30551391385724+"");
                nearMap.put("latitude",40.04571807462411+"");
                presenter.requestGEt(Contacts.NEARMOVIE_URL, nearMap, headmap,NearMovieData.class);
            }
        });

        Near_Rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {



            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){
                    case R.id.Near_Rb1:
//                        Toast.makeText(getActivity(),"推荐影院",Toast.LENGTH_SHORT).show();
                        //Near_Xrecy.setVisibility(View.GONE);
                        Near_Xrecy.setVisibility(View.GONE);
                        Recommend_Xrecy.setVisibility(View.VISIBLE);
                        break;
                    case R.id.Near_Rb2:
                        Toast.makeText(getActivity(),"附近影院",Toast.LENGTH_SHORT).show();

                        Near_Xrecy.setVisibility(View.VISIBLE);
                        Recommend_Xrecy.setVisibility(View.GONE);


                        //f附近点赞

                        nearAdapter.getItem(new NearAdapter.ItemClicke() {
                            @Override
                            public void setItem(int i) {


                                HashMap<String,Object>clearMap=new HashMap<>();
                                clearMap.put("cinemaId",nList.get(i).getId());

                                if (nList.get(i).getFollowCinema() == 2) {

                                    presenter.requestGEt(Contacts.CLICKMOVIE_URL, clearMap, headmap, ClickMovieData.class);
                                    nearAdapter.notifyDataSetChanged();
                                    nList.get(i).setFollowCinema(1);

                                }else{

                                    presenter.requestGEt(Contacts.CLEARMOVIE_URL, clearMap, headmap, ClearMovieData.class);
                                    nearAdapter.notifyDataSetChanged();
                                    nList.get(i).setFollowCinema(2);
                                }


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
            Recommend_Xrecy.loadMoreComplete();
            Recommend_Xrecy.refreshComplete();



        }

        if(data instanceof ClickMovieData){
            ClickMovieData clickMovieData= (ClickMovieData) data;
            Toast.makeText(getActivity(),clickMovieData.getMessage(),Toast.LENGTH_SHORT).show();

        }

        if(data instanceof ClearMovieData){
            ClearMovieData clearMovieData= (ClearMovieData) data;
            Toast.makeText(getActivity(),clearMovieData.getMessage(),Toast.LENGTH_SHORT).show();

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
