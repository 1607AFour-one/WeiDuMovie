package com.bw.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.bean.ComingSoonData;
import com.bw.movie.bean.HotMovieData;
import com.bw.movie.bean.ReleaseMovieData;
import com.bw.movie.ui.MovieDeailsActivity;
import com.bw.movie.utils.GlideUtils;

import java.util.List;

/**
 * author:author${车文飞}
 * data:2019/1/26
 */
public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {
    private Context mContext;
    private int type;
    List<HotMovieData.ResultBean> mHotList ;
    List<ReleaseMovieData.ResultBean> mReleaseList;
    List<ComingSoonData.ResultBean> mComingList;
    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }
    public void setType(int type) {
        this.type = type;
    }

    public void setmHotList(List<HotMovieData.ResultBean> mHotList) {
        this.mHotList = mHotList;
    }

    public void setmReleaseList(List<ReleaseMovieData.ResultBean> mReleaseList) {
        this.mReleaseList = mReleaseList;
    }

    public void setmComingList(List<ComingSoonData.ResultBean> mComingList) {
        this.mComingList = mComingList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.movie_list_item,viewGroup,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        switch (type){
            case 0:
                GlideUtils.setDefaultImage(mContext,mHotList.get(i).getImageUrl(),viewHolder.image);
                viewHolder.title.setText(mHotList.get(i).getName());
                viewHolder.content.setText(mHotList.get(i).getSummary());
                //判断是否给关注checkbox赋值
                if(mHotList.get(i).isFollowMovie()==1){
                    viewHolder.dianZanCb.setChecked(true);
                }else{
                    viewHolder.dianZanCb.setChecked(false);
                }
                //给checkBox设置点击事件
                viewHolder.dianZanCb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //如果为1(已关注)设置成2(未关注)
                        //把movie和状态值(1或2)传给请求网络的页面(MovieListActivity);
                        if(mHotList.get(i).isFollowMovie()==1){
                            mHotList.get(i).setFollowMovie(2);
                        }else{
                            mHotList.get(i).setFollowMovie(1);
                        }
                        if(movieListAdapterListener!=null){
                            movieListAdapterListener.getMovieId(mHotList.get(i).getId(),mHotList.get(i).isFollowMovie());
                        }
                    }
                });
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(mContext,MovieDeailsActivity.class);
                        intent.putExtra("movieId",mHotList.get(i).getId()+"");
                        mContext.startActivity(intent);
                    }
                });
                break;
            case 1:
                GlideUtils.setDefaultImage(mContext,mReleaseList.get(i).getImageUrl(),viewHolder.image);
                viewHolder.title.setText(mReleaseList.get(i).getName());
                viewHolder.content.setText(mReleaseList.get(i).getSummary());
                //判断是否给关注checkbox赋值
                if(mReleaseList.get(i).isFollowMovie()==1){
                    viewHolder.dianZanCb.setChecked(true);
                }else{
                    viewHolder.dianZanCb.setChecked(false);
                }
                //给checkBox设置点击事件
                viewHolder.dianZanCb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //如果为1(已关注)设置成2(未关注)
                        //把movie和状态值(1或2)传给请求网络的页面(MovieListActivity);
                        if(mReleaseList.get(i).isFollowMovie()==1){
                            mReleaseList.get(i).setFollowMovie(2);
                        }else{
                            mReleaseList.get(i).setFollowMovie(1);
                        }
                        if(movieListAdapterListener!=null){

                            movieListAdapterListener.getMovieId(mReleaseList.get(i).getId(),mReleaseList.get(i).isFollowMovie());
                        }
                    }
                });
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(mContext,MovieDeailsActivity.class);
                        intent.putExtra("movieId",mReleaseList.get(i).getId()+"");
                        mContext.startActivity(intent);
                    }
                });
                break;
            case 2:
                GlideUtils.setDefaultImage(mContext,mComingList.get(i).getImageUrl(),viewHolder.image);
                viewHolder.title.setText(mComingList.get(i).getName());
                viewHolder.content.setText(mComingList.get(i).getSummary());
                //判断是否给关注checkbox赋值
                if(mComingList.get(i).isFollowMovie()==1){
                    viewHolder.dianZanCb.setChecked(true);
                }else{
                    viewHolder.dianZanCb.setChecked(false);
                }
                viewHolder.dianZanCb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //如果为1(已关注)设置成2(未关注)
                        //把movie和状态值(1或2)传给请求网络的页面(MovieListActivity);
                        if(mComingList.get(i).isFollowMovie()==1){
                            mComingList.get(i).setFollowMovie(2);
                        }else{
                            mComingList.get(i).setFollowMovie(1);
                        }
                        if(movieListAdapterListener!=null){
                            movieListAdapterListener.getMovieId(mComingList.get(i).getId(),mComingList.get(i).isFollowMovie());
                        }
                    }
                });
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(mContext,MovieDeailsActivity.class);
                        intent.putExtra("movieId",mComingList.get(i).getId()+"");
                        mContext.startActivity(intent);
                    }
                });

                break;
        }


    }
    @Override
    public int getItemCount() {
        if(type==0){
            return mHotList.size();
        }else if(type==1){
            return mReleaseList.size();
        }else {
          return mComingList.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        CheckBox dianZanCb;
        TextView title,content;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.movie_image);
            title=itemView.findViewById(R.id.movie_title);
            dianZanCb=itemView.findViewById(R.id.movie_dianzan);
            content=itemView.findViewById(R.id.movie_content);
        }
    }
    MovieListAdapterListener movieListAdapterListener;

    public interface MovieListAdapterListener{
        void getMovieId(int movieId,int isFollowMovie);
    }
    public void setMovieListAdapterListener(MovieListAdapterListener movieListAdapterListener) {
        this.movieListAdapterListener = movieListAdapterListener;
    }
}
