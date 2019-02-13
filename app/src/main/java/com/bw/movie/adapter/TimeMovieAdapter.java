package com.bw.movie.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.bean.TimeMovieData;
import com.bw.movie.ui.SeatActivity;
import com.bw.movie.ui.StartActivity;
import com.bw.movie.utils.Contacts;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TimeMovieAdapter extends RecyclerView.Adapter<TimeMovieAdapter.ViewHolder> {

    private List<TimeMovieData.ResultBean> mList;
    private Context mContext;
    private Activity activity;

    public TimeMovieAdapter(List<TimeMovieData.ResultBean> mList, Context mContext, Activity activity) {
        this.mList = mList;
        this.mContext = mContext;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.movietime_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int i) {
        holder.time_Name.setText(mList.get(i).getScreeningHall() + "");
        //holder.time_Language.setText(mList.get(i).getBeginTime() + "");
        holder.time.setText(mList.get(i).getBeginTime()+"");
        holder.time_over.setText("——" + mList.get(i).getEndTime()+ "");
        holder.time_price.setText("￥" + 0.01 + "");

        holder.time_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity,SeatActivity.class);
                activity.startActivity(intent);

        }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView time_Name, time, time_over, time_price;
        ImageView time_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            time_Name = itemView.findViewById(R.id.time_Name);
           // time_Language = itemView.findViewById(R.id.time_Language);
            time = itemView.findViewById(R.id.time);
            time_over = itemView.findViewById(R.id.time_over);
            time_price = itemView.findViewById(R.id.time_price);
            time_image = itemView.findViewById(R.id.time_image);
        }
    }
}
