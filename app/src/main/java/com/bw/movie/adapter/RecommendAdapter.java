package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.bean.HotMovieData;
import com.bw.movie.bean.RecommendData;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class RecommendAdapter extends XRecyclerView.Adapter<RecommendAdapter.ViewHolder> {

    private List<RecommendData.ResultBean>rList;
    private Context mContext;

    public RecommendAdapter(List<RecommendData.ResultBean> rList, Context mContext) {
        this.rList = rList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view=View.inflate(mContext, R.layout.recommend_item,null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {

        holder.recommend_name.setText(rList.get(i).getName());
        holder.Recommend_Address.setText(rList.get(i).getAddress());
        holder.Recommend_Km.setText(rList.get(i).getFollowCinema()+"km");
        Glide.with(mContext).load(rList.get(i).getLogo()).into(holder.recommend_image);
    }

    @Override
    public int getItemCount() {
        return rList.size();
    }

    public class ViewHolder extends XRecyclerView.ViewHolder {

        ImageView recommend_image,Recommend_Zan;
        TextView recommend_name,Recommend_Address,Recommend_Km;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            recommend_image = itemView.findViewById(R.id.Recommend_Image);
            Recommend_Zan = itemView.findViewById(R.id.Recommend_Zan);
            recommend_name = itemView.findViewById(R.id.Recommend_Name);
            Recommend_Address = itemView.findViewById(R.id.Recommend_Address);
            Recommend_Km = itemView.findViewById(R.id.Recommend_Km);
        }
    }
}
