package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.bean.DiscussMovieData;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DiscussMovieAdapter extends RecyclerView.Adapter<DiscussMovieAdapter.ViewHolder> {
    private List<DiscussMovieData.ResultBean>pList;
    private Context mContext;

    public DiscussMovieAdapter(List<DiscussMovieData.ResultBean> pList, Context mContext) {
        this.pList = pList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.pinglun_item,viewGroup,false);
        /*View view=View.inflate(mContext, R.layout.pinglun_item,null);*/
        ViewHolder holder=new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int i) {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date  date=new Date(pList.get(i).getCommentTime());
        holder.ping_name.setText(pList.get(i).getCommentUserName());
        holder.ping_text.setText(pList.get(i).getCommentContent());
        holder.ping_data.setText(format.format(date));
        Glide.with(mContext).load(pList.get(i).getCommentHeadPic()).into(holder.ping_image);
        holder.ping_zan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClicke.setItem(i);
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
        return pList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private  ImageView ping_image,ping_zan;
        private  TextView ping_name,ping_text,ping_data;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ping_image = itemView.findViewById(R.id.ping_image);
            ping_name = itemView.findViewById(R.id.ping_name);
            ping_text = itemView.findViewById(R.id.ping_text);
            ping_data = itemView.findViewById(R.id.ping_data);
            ping_zan = itemView.findViewById(R.id.ping_zan);
        }
    }
}
