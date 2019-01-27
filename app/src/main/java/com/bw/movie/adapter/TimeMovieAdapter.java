package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.TimeMovieData;
import com.bw.movie.utils.Contacts;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TimeMovieAdapter extends RecyclerView.Adapter<TimeMovieAdapter.ViewHolder> {

    private List<TimeMovieData.ResultBean>mList;
    private Context mContext;

    public TimeMovieAdapter(List<TimeMovieData.ResultBean> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.movietime_item,viewGroup,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        holder.time_Name.setText(mList.get(i).getScreeningHall()+"");
       // holder.time_Language.setText(mList.get(i).getBeginTime()+"");
        //holder.time_over.setText("——"+mList.get(i).getEndTime()+"");
        holder.time_price.setText("￥"+0.01+"");
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView time_Name,time_Language,time,time_over,time_price;
        ImageView time_image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            time_Name= itemView.findViewById(R.id.time_Name);
            time_Language= itemView.findViewById(R.id.time_Language);
            time= itemView.findViewById(R.id.time);
            time_over= itemView.findViewById(R.id.time_over);
            time_price= itemView.findViewById(R.id.time_price);
            time_image= itemView.findViewById(R.id.time_image);
        }
    }
}
