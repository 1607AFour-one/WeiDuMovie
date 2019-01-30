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
import com.bw.movie.bean.QueryCinemaData;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class QueryCinemaAdapter extends XRecyclerView.Adapter<QueryCinemaAdapter.ViewHolder> {
   private List<QueryCinemaData.ResultBean>qList;
   private Context mContext;

    public QueryCinemaAdapter(List<QueryCinemaData.ResultBean> qList, Context mContext) {
        this.qList = qList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=View.inflate(mContext, R.layout.querycinema_item,null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.QueryCinema_Name.setText(qList.get(i).getName());
        viewHolder.QueryCinema_Address.setText(qList.get(i).getAddress());
        Glide.with(mContext).load(qList.get(i).getLogo()).into(viewHolder.QueryCinema_Image);
    }

    @Override
    public int getItemCount() {
        return qList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView QueryCinema_Image;
        TextView QueryCinema_Name,QueryCinema_Address;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            QueryCinema_Image=  itemView.findViewById(R.id.QueryCinema_Image);
            QueryCinema_Name=itemView.findViewById(R.id.QueryCinema_Name);
            QueryCinema_Address= itemView.findViewById(R.id.QueryCinema_Address);

        }
    }
}
