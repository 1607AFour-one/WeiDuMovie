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
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.bean.FindMovieCommentData;
import com.bw.movie.utils.GlideUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * author:author${车文飞}
 * data:2019/1/28
 */
public class MovieCommentsAdapter extends XRecyclerView.Adapter<MovieCommentsAdapter.ViewHolder> {
    private Context mContext;
    private List<FindMovieCommentData.ResultBean> list;

    public MovieCommentsAdapter(Context mContext, List<FindMovieCommentData.ResultBean> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.movie_comments_item,viewGroup,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        RequestOptions requestOptions = RequestOptions.circleCropTransform();
        Glide.with(mContext).load(list.get(i).getCommentHeadPic()).apply(requestOptions).into(viewHolder.image);
        viewHolder.userName.setText(list.get(i).getCommentUserName());
        viewHolder.content.setText(list.get(i).getMovieComment());
        viewHolder.greatNum.setText(list.get(i).getGreatNum()+"");
        viewHolder.wirteNum.setText(list.get(i).getReplyNum()+"");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView wirteNum,greatNum,date,content,userName;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            image=itemView.findViewById(R.id.comments_head_image);
            userName=itemView.findViewById(R.id.comments_userName);
            content=itemView.findViewById(R.id.comments_content);
            date=itemView.findViewById(R.id.comment_date);
            greatNum=itemView.findViewById(R.id.comment_greatNum_tv);
            wirteNum=itemView.findViewById(R.id.comment_wirteNum_tv);
        }
    }
}
