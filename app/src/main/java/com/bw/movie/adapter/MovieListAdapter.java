package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.bean.ComingSoonData;
import com.bw.movie.bean.HotMovieData;
import com.bw.movie.bean.ReleaseMovieData;
import com.bw.movie.utils.GlideUtils;

import java.util.List;

/**
 * author:author${车文飞}
 * data:2019/1/26
 */
public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {
    private Context mContext;
    private int type;
    List<HotMovieData.ResultBean> mHotList;
    List<ReleaseMovieData.ResultBean> mReleaseList;
    List<ComingSoonData.ResultBean> mComingList;

    public MovieListAdapter(Context mContext, int type, List<HotMovieData.ResultBean> mHotList, List<ReleaseMovieData.ResultBean> mReleaseList, List<ComingSoonData.ResultBean> mComingList) {
        this.mContext = mContext;
        this.type = type;
        this.mHotList = mHotList;
        this.mReleaseList = mReleaseList;
        this.mComingList = mComingList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.movie_list_item,viewGroup,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        switch (type){
            case 0:
                GlideUtils.setDefaultImage(mContext,mHotList.get(i).getImageUrl(),viewHolder.image);
                viewHolder.title.setText(mHotList.get(i).getName());
                viewHolder.content.setText(mHotList.get(i).getSummary());
                break;
            case 1:
                GlideUtils.setDefaultImage(mContext,mReleaseList.get(i).getImageUrl(),viewHolder.image);
                viewHolder.title.setText(mReleaseList.get(i).getName());
                viewHolder.content.setText(mReleaseList.get(i).getSummary());
                break;
            case 2:
                GlideUtils.setDefaultImage(mContext,mComingList.get(i).getImageUrl(),viewHolder.image);
                viewHolder.title.setText(mComingList.get(i).getName());
                viewHolder.content.setText(mComingList.get(i).getSummary());
                break;
        }


    }
    @Override
    public int getItemCount() {
        if(type==0){
            return mHotList.size();
        }else if(type==1){
            return mReleaseList.size();
        }else {
          return mComingList.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        CheckBox dianZanCb;
        TextView title,content;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.movie_image);
            title=itemView.findViewById(R.id.movie_title);
            dianZanCb=itemView.findViewById(R.id.movie_dianzan);
            content=itemView.findViewById(R.id.movie_content);
        }
    }
}
