package com.bw.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.bean.HotMovieData;
import com.bw.movie.ui.MovieDeailsActivity;
import com.bw.movie.utils.GlideUtils;

import java.util.List;

/**
 * author:author${车文飞}
 * data:2019/1/25
 */
public class HomeChildHotAdapter extends RecyclerView.Adapter<HomeChildHotAdapter.ViewHolder> {
    Context mContext;
    List<HotMovieData.ResultBean> mHotList;

    public HomeChildHotAdapter(Context mContext, List<HotMovieData.ResultBean> mHotList) {
        this.mContext = mContext;
        this.mHotList = mHotList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.home_child_item,viewGroup,false);
        ViewHolder holder=new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        //GlideUtils.load(mContext,viewHolder.image,mHotList.get(i).getImageUrl(),R.mipmap.error,10);
        Glide.with(mContext).load(mHotList.get(i).getImageUrl()).into(viewHolder.image);
        viewHolder.tv.setText(mHotList.get(i).getName());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext,MovieDeailsActivity.class);
                intent.putExtra("movieId",mHotList.get(i).getId()+"");
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mHotList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.home_child_item_image);
            tv=itemView.findViewById(R.id.home_child_item_name);
        }
    }
}
