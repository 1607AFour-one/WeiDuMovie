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

import java.util.List;
import java.util.Random;

/**
 * author:author${车文飞}
 * data:2019/1/28
 * 剧照适配器
 */
public class MovieStillAdapter extends RecyclerView.Adapter<MovieStillAdapter.ViewhHolder> {
    private Context mContext;
    private List<String> posterList;

    public MovieStillAdapter(Context mContext, List<String> posterList) {
        this.mContext = mContext;
        this.posterList = posterList;
    }

    @NonNull
    @Override
    public ViewhHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.movie_still_item,viewGroup,false);
        ViewhHolder holder=new ViewhHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewhHolder viewhHolder, int i) {
        ViewGroup.LayoutParams layoutParams=viewhHolder.image.getLayoutParams();
        int mWidth=mContext.getResources().getDisplayMetrics().widthPixels;
        layoutParams.width=(mWidth-3*8)/2;
        Random random = new Random();
        int height = random.nextInt(300)+200;
        layoutParams.height = height;
        viewhHolder.itemView.setLayoutParams(layoutParams);
        Glide.with(mContext).load(posterList.get(i)).into(viewhHolder.image);
    }

    @Override
    public int getItemCount() {
        return posterList.size();
    }

    public class ViewhHolder extends RecyclerView.ViewHolder {
        ImageView image;
        public ViewhHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.still_image);
        }
    }
}
