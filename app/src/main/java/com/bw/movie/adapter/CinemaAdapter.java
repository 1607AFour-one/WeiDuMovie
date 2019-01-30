package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.bean.CinemaData;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CinemaAdapter extends RecyclerView.Adapter<CinemaAdapter.ViewHolder> {
    private List<CinemaData.ResultBean>cLIst;
    private Context mContext;

    public CinemaAdapter(List<CinemaData.ResultBean> cLIst, Context mContext) {
        this.cLIst = cLIst;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view=View.inflate(mContext, R.layout.cinema_item,null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.Cinema_Name.setText(cLIst.get(i).getName());
        viewHolder.Cinema_Address.setText(cLIst.get(i).getSummary());
        Glide.with(mContext).load(cLIst.get(i).getImageUrl()).into(viewHolder.Cinema_Image);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date=new Date(cLIst.get(i).getReleaseTime());
        viewHolder.Cinema_Km.setText(format.format(date));
    }

    @Override
    public int getItemCount() {
        return cLIst.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView Cinema_Image;
        TextView Cinema_Name,Cinema_Address,Cinema_Km;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Cinema_Image=  itemView.findViewById(R.id.Cinema_Image);
            Cinema_Name=itemView.findViewById(R.id.Cinema_Name);
            Cinema_Address= itemView.findViewById(R.id.Cinema_Address);
            Cinema_Km= itemView.findViewById(R.id.Cinema_Km);
        }
    }
}
