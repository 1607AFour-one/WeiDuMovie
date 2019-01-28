package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.bean.MovieDetailData;

import java.util.List;

import cn.jzvd.JZVideoPlayerStandard;

/**
 * author:author${车文飞}
 * data:2019/1/28
 */
public class JZVideoAdapter extends RecyclerView.Adapter<JZVideoAdapter.ViewHolder> {
    private Context mContext;
    private List<MovieDetailData.ResultBean.ShortFilmListBean> shortFilmList;

    public JZVideoAdapter(Context mContext, List<MovieDetailData.ResultBean.ShortFilmListBean> shortFilmList) {
        this.mContext = mContext;
        this.shortFilmList = shortFilmList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.movie_trailer_item,viewGroup,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.JZVideo.thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        viewHolder.JZVideo.setUp(shortFilmList.get(i).getVideoUrl(),JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL);
        Glide.with(mContext).load(shortFilmList.get(i).getImageUrl()).into(viewHolder.JZVideo.thumbImageView);

    }

    @Override
    public int getItemCount() {
        return shortFilmList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        JZVideoPlayerStandard JZVideo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            JZVideo= itemView.findViewById(R.id.JZVideo);
        }
    }
}
