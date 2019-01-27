package com.bw.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.bean.NearMovieData;
import com.bw.movie.ui.DetailsActivity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class NearAdapter extends XRecyclerView.Adapter<NearAdapter.ViewHolder> {

    private List<NearMovieData.ResultBean>nList;
    private Context mContext;

    public NearAdapter(List<NearMovieData.ResultBean> nList, Context mContext) {
        this.nList = nList;
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
    public void onBindViewHolder(@NonNull ViewHolder holder, final int i) {
        holder.recommend_name.setText(nList.get(i).getName());
        holder.Recommend_Address.setText(nList.get(i).getAddress());
        holder.Recommend_Km.setText(nList.get(i).getDistance()+"km");
        Glide.with(mContext).load(nList.get(i).getLogo()).into(holder.recommend_image);

        holder.Recommend_Zan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                itemClicke.setItem(i);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext,DetailsActivity.class);
                intent.putExtra("cinemaId",nList.get(i).getId());
                mContext.startActivity(intent);

            }
        });


    }


    //点赞接口回调
    ItemClicke itemClicke;

    public interface  ItemClicke{
        void setItem(int i);
    }
    public void getItem(ItemClicke itemClicke){
        this.itemClicke=itemClicke;
    }


    @Override
    public int getItemCount() {
        return nList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
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
