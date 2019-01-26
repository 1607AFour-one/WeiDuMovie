package com.bw.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.ComingSoonData;
import com.bw.movie.bean.HotMovieData;
import com.bw.movie.bean.IntentMovieData;
import com.bw.movie.bean.ReleaseMovieData;
import com.bw.movie.ui.MovieListActivity;
import com.bw.movie.ui.StartActivity;

import java.util.ArrayList;
import java.util.List;

import recycler.coverflow.RecyclerCoverFlow;

/**
 * author:author${车文飞}
 * data:2019/1/25
 */
public class HomeGroupAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int ONE_ITEM=0;
    private final int TWO_ITEM=1;
    private final int THREE_ITEM=2;
    private final int FORE_ITEM=3;
    List<HotMovieData.ResultBean> mHotList;
    List<ReleaseMovieData.ResultBean> mReleaseList;
    List<ComingSoonData.ResultBean> mComingList;
    private Context mContext;

    public HomeGroupAdapter(List<HotMovieData.ResultBean> mHotList, List<ReleaseMovieData.ResultBean> mReleaseList, List<ComingSoonData.ResultBean> mComingList, Context mContext) {
        this.mHotList = mHotList;
        this.mReleaseList = mReleaseList;
        this.mComingList = mComingList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=null;
        RecyclerView.ViewHolder holder=null;
        switch (i){
            case ONE_ITEM:
                view=LayoutInflater.from(mContext).inflate(R.layout.home_group_item,viewGroup,false);
                holder=new ViewHolder(view);
                return holder;

            case TWO_ITEM:
                view=LayoutInflater.from(mContext).inflate(R.layout.home_group_tem2,viewGroup,false);
                holder=new ViewHolder2(view);
                return holder;

            case THREE_ITEM:
                view=LayoutInflater.from(mContext).inflate(R.layout.home_group_tem2,viewGroup,false);
                holder=new ViewHolder3(view);
                return holder;


            case FORE_ITEM:
                view=LayoutInflater.from(mContext).inflate(R.layout.home_group_tem2,viewGroup,false);
                holder=new ViewHolder4(view);
                return holder;


        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if(viewHolder instanceof ViewHolder){
            MyrecyFlowAdapter myrecyFlowAdapter=new MyrecyFlowAdapter(mContext,mComingList);
            ((ViewHolder) viewHolder).recyflow.setAdapter(myrecyFlowAdapter);
            ((ViewHolder) viewHolder).recyflow.setGreyItem(true);
        }
        if(viewHolder instanceof ViewHolder2){
           // ((ViewHolder2) viewHolder).name.setText(mHotList.get(i).g);
            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(mContext);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            ((ViewHolder2) viewHolder).recy.setLayoutManager(linearLayoutManager);
            ((ViewHolder2) viewHolder).recy.setAdapter(new HomeChildHotAdapter(mContext,mHotList));
            ((ViewHolder2) viewHolder).more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(mContext,MovieListActivity.class);
                    intent.putExtra("movieList",new IntentMovieData(mHotList,mReleaseList,mComingList));
                    mContext.startActivity(intent);
                }
            });
        }
        if(viewHolder instanceof ViewHolder3){
            // ((ViewHolder2) viewHolder).name.setText(mHotList.get(i).g);
            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(mContext);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            ((ViewHolder3) viewHolder).recy.setLayoutManager(linearLayoutManager);
            ((ViewHolder3) viewHolder).recy.setAdapter(new HomeChildReleaseAdapter(mContext,mReleaseList));
            ((ViewHolder3) viewHolder).more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent=new Intent(mContext,MovieListActivity.class);
                    intent.putExtra("movieList",new IntentMovieData(mHotList,mReleaseList,mComingList));
                    mContext.startActivity(intent);
                }
            });
        }
        if(viewHolder instanceof ViewHolder4){
            // ((ViewHolder2) viewHolder).name.setText(mHotList.get(i).g);
            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(mContext);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            ((ViewHolder4) viewHolder).recy.setLayoutManager(linearLayoutManager);
            ((ViewHolder4) viewHolder).recy.setAdapter(new HomeChildComAdapter(mContext,mComingList));
            ((ViewHolder4) viewHolder).more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent=new Intent(mContext,MovieListActivity.class);
                    intent.putExtra("movieList",new IntentMovieData(mHotList,mReleaseList,mComingList));
                    mContext.startActivity(intent);
                }
            });
        }

    }



    @Override
    public int getItemCount() {
        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        if(position%4==0){
            return ONE_ITEM;
        }else if(position%4==1){
            return TWO_ITEM;
        }else if(position%4==2){
            return THREE_ITEM;
        }else if(position%4==3){
            return FORE_ITEM;
        }
       return 0;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerCoverFlow recyflow;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recyflow=itemView.findViewById(R.id.home_group_recyflow);
        }
    }
    public class ViewHolder2 extends RecyclerView.ViewHolder{
        TextView name;
        ImageView more;
        RecyclerView recy;

        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.home_group_item_tv);
            more=itemView.findViewById(R.id.home_group2_item_more);
            recy=itemView.findViewById(R.id.home_group_item2_recy);
        }
    }
    public class ViewHolder3 extends RecyclerView.ViewHolder{
        TextView name;
        ImageView more;
        RecyclerView recy;

        public ViewHolder3(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.home_group_item_tv);
            more=itemView.findViewById(R.id.home_group2_item_more);
            recy=itemView.findViewById(R.id.home_group_item2_recy);
        }
    }
    public class ViewHolder4 extends RecyclerView.ViewHolder{
        TextView name;
        ImageView more;
        RecyclerView recy;

        public ViewHolder4(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.home_group_item_tv);
            more=itemView.findViewById(R.id.home_group2_item_more);
            recy=itemView.findViewById(R.id.home_group_item2_recy);
        }
    }
}
