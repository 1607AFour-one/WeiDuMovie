package com.bw.movie.adapter;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.bean.ComingSoonData;
import com.bw.movie.utils.GlideUtils;

import java.util.List;

/**
 * author:author${车文飞}
 * data:2019/1/25
 */
public class MyrecyFlowAdapter extends RecyclerView.Adapter<MyrecyFlowAdapter.ViewHolder> {
    private Context mContext;
    private List<ComingSoonData.ResultBean> mComingList;

    public MyrecyFlowAdapter(Context mContext, List<ComingSoonData.ResultBean> mComingList) {
        this.mContext = mContext;
        this.mComingList = mComingList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.recycleflow_item,viewGroup,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        GlideUtils.load(mContext,viewHolder.image,mComingList.get(i).getImageUrl(),R.mipmap.ic_launcher,8);

    }

    @Override
    public int getItemCount() {
        return mComingList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.recyflow_image);
        }
    }
}
