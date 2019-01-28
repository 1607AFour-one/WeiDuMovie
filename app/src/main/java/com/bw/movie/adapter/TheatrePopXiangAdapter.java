package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.DetailsData;

import java.util.List;

public class TheatrePopXiangAdapter extends RecyclerView.Adapter<TheatrePopXiangAdapter.ViewHolder> {

    private List<DetailsData.ResultBean>mList;
    private Context mContext;

    public TheatrePopXiangAdapter(List<DetailsData.ResultBean> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.theatre_pop_xiang, viewGroup, false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {

        holder.xaing_address.setText(mList.get(i).getAddress());
        holder.xiang_phone.setText(mList.get(i).getPhone());
        holder.xiang_di.setText(mList.get(i).getVehicleRoute());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView xaing_address,xiang_phone,xiang_di,xiang_gong,xiang_zi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            xaing_address = itemView.findViewById(R.id.xaing_address);
            xiang_phone = itemView.findViewById(R.id.xiang_phone);
            xiang_di = itemView.findViewById(R.id.xiang_di);
            xiang_gong = itemView.findViewById(R.id.xiang_gong);
            xiang_zi = itemView.findViewById(R.id.xiang_zi);
        }
    }
}
