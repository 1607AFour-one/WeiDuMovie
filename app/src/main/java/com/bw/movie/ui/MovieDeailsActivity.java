package com.bw.movie.ui;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.adapter.JZVideoAdapter;
import com.bw.movie.adapter.MovieCommentsAdapter;
import com.bw.movie.adapter.MovieStillAdapter;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.FindMovieCommentData;
import com.bw.movie.bean.MessageData;
import com.bw.movie.bean.MovieDetailData;
import com.bw.movie.bean.MovieMessage;
import com.bw.movie.bean.SpacesItemDecoration;
import com.bw.movie.presenter.PresenterImpl;
import com.bw.movie.utils.Contacts;
import com.bw.movie.utils.SpUtils;
import com.bw.movie.view.IView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.jzvd.JZVideoPlayerStandard;

public class MovieDeailsActivity extends BaseActivity implements IView {

    private ImageView detailImage;
    private TextView movieName;
    private RadioGroup radioGroup;
    private RadioButton detail;
    private RadioButton trailer;
    private RadioButton still;
    private RadioButton comments;
    TranslateAnimation animation;
    private View view;
    private MovieDetailData movieDetailData;
    private ImageView back;
    private PresenterImpl presenter;
    private PopupWindow popupWindow;
    private HashMap<String, Object> headmap;
    private FindMovieCommentData findMovieCommentData;
    private XRecyclerView commentXrecy;
    private CheckBox followCb;
    private HashMap<String, Object> map;
    private HashMap<String, Object> greatMap;
    private HashMap<String, Object> findCoomentsMap;
    List<FindMovieCommentData.ResultBean> result=new ArrayList<>();
    private MovieCommentsAdapter movieCommentsAdapter;

    @Override
    protected int initLayout() {
        return R.layout.activity_movie_deails;
    }

    @Override
    protected void initView() {
        view = View.inflate(MovieDeailsActivity.this,R.layout.detial_dialog,null);
        detailImage = findViewById(R.id.detail_image);
        movieName = findViewById(R.id.detail_name);
        radioGroup = findViewById(R.id.detail_rg);
        detail = findViewById(R.id.detail_rb);
        trailer = findViewById(R.id.trailer_rb);
        still = findViewById(R.id.still_rb);
        comments = findViewById(R.id.comments_rb);
        back = findViewById(R.id.detail_back);
        followCb = findViewById(R.id.movie_follow_cb);
        //---------------------------
        detail.setOnClickListener(this);
        trailer.setOnClickListener(this);
        back.setOnClickListener(this);
        still.setOnClickListener(this);
        comments.setOnClickListener(this);
        followCb.setOnClickListener(this);



    }
    @Override
    protected void setClicks() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void setProgress() {
        Intent intent=getIntent();
        String movieId = intent.getStringExtra("movieId");
        int userId = SpUtils.getInt("userId");
        String sessionId=SpUtils.getString("sessionId");
        //电影评论点赞的map;
        greatMap = new HashMap<>();
        //请求头map;
        headmap = new HashMap<>();
        headmap.put("userId",userId);
        headmap.put("sessionId",sessionId);
        presenter = new PresenterImpl(this);
        //查询详情的map
        map = new HashMap<>();
        map.put("movieId",movieId);
        presenter.requestGEt(Contacts.MOVIE_DETAIL_URL, map, headmap,MovieDetailData.class);

        //----------------------------------------------------------------------------
        //请求查看影片评论
        findCoomentsMap = new HashMap<>();
        findCoomentsMap.put("movieId",movieId);
        findCoomentsMap.put("page","1");
        findCoomentsMap.put("count","5");
        //点击弹出poppupWindow再请求网络
        /*presenter.requestGEt(Contacts.FINDMOVIE_COMMENT_URL, findCoomentsMap,headmap,FindMovieCommentData.class);*/


    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.detail_back:
                finish();
                break;
                //点击弹出详情
            case R.id.detail_rb:
                //查找控件
               ImageView detailImage= view.findViewById(R.id.details_img);
               TextView detailType=view.findViewById(R.id.detail_type);
               TextView detailTime=view.findViewById(R.id.details_time);
               TextView detailDirector=view.findViewById(R.id.detail_director);
               TextView detailAdrress=view.findViewById(R.id.details_address);
                TextView detailContent=view.findViewById(R.id.detail_content);
                ImageView deailDismiss=view.findViewById(R.id.deail_dismiss);
                TextView deailActorName=view.findViewById(R.id.detail_actor_name);
               Glide.with(MovieDeailsActivity.this).load(movieDetailData.getResult().getImageUrl()).into(detailImage);
                detailType.setText(movieDetailData.getResult().getMovieTypes());
                detailTime.setText(movieDetailData.getResult().getDuration());
                detailDirector.setText(movieDetailData.getResult().getDirector());
                detailAdrress.setText(movieDetailData.getResult().getPlaceOrigin());
                detailContent.setText(movieDetailData.getResult().getSummary());
                deailActorName.setText(movieDetailData.getResult().getStarring());
                setPopupWindow(view);
                deailDismiss.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });

                break;
                //点击弹出预告片
            case R.id.trailer_rb:
                View trailerView=View.inflate(MovieDeailsActivity.this,R.layout.pup_movie_trailer,null);
                RecyclerView traulerRecy=trailerView.findViewById(R.id.trailer_Recy);
                traulerRecy.setLayoutManager(new LinearLayoutManager(MovieDeailsActivity.this));
                List<MovieDetailData.ResultBean.ShortFilmListBean> shortFilmList=movieDetailData.getResult().getShortFilmList();
                JZVideoAdapter jzVideoAdapter=new JZVideoAdapter(MovieDeailsActivity.this,shortFilmList);
                traulerRecy.setAdapter(jzVideoAdapter);
                setPopupWindow(trailerView);
                trailerView.findViewById(R.id.deail_dismiss).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(popupWindow!=null&&popupWindow.isShowing()){
                            popupWindow.dismiss();
                        }
                    }
                });
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {

                        JZVideoPlayerStandard.releaseAllVideos();
                    }
                });
                break;
                //点击弹出剧照
            case R.id.still_rb:
                View stillView=View.inflate(MovieDeailsActivity.this,R.layout.pup_movie_still,null);
                RecyclerView stillRecy=stillView.findViewById(R.id.still_recy);
                stillRecy.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                stillRecy.setAdapter(new MovieStillAdapter(MovieDeailsActivity.this,movieDetailData.getResult().getPosterList()));
                SpacesItemDecoration decoration=new SpacesItemDecoration(8);
                stillRecy.addItemDecoration(decoration);
                setPopupWindow(stillView);
                stillView.findViewById(R.id.still_dismiss).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(popupWindow!=null&&popupWindow.isShowing()){
                            popupWindow.dismiss();
                        }
                    }
                });
                break;
                //评论
            case R.id.comments_rb:

                boolean isComments=true;
                View commentsView=View.inflate(MovieDeailsActivity.this,R.layout.pup_movie_comments,null);
                final RelativeLayout commentRelative=commentsView.findViewById(R.id.comment_Relative);
                final ImageView image=commentsView.findViewById(R.id.comments_btn_image);
                Button send=commentsView.findViewById(R.id.comments_btn_send);
                //评论的recycleView
                XRecyclerView commentXrecy= commentsView.findViewById(R.id.comments_xrecy);
                commentXrecy.setLayoutManager(new LinearLayoutManager(MovieDeailsActivity.this));
                movieCommentsAdapter = new MovieCommentsAdapter(MovieDeailsActivity.this,result);
                commentXrecy.setAdapter(movieCommentsAdapter);
                presenter.requestGEt(Contacts.FINDMOVIE_COMMENT_URL, findCoomentsMap,headmap,FindMovieCommentData.class);

                image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(true){
                            commentRelative.setVisibility(View.VISIBLE);
                            image.setVisibility(View.GONE);
                        }
                    }
                });
                send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        commentRelative.setVisibility(View.GONE);
                        image.setVisibility(View.VISIBLE);
                    }
                });
                //评论的适配器
                movieCommentsAdapter.setMovieCommentListener(new MovieCommentsAdapter.MovieCommentListener() {
                    @Override
                    public void getCommentPosition(int position) {
                        if(result.get(position).getIsGreat()==1){
                            greatMap.clear();
                            greatMap.put("commentId",result.get(position).getCommentId());
                            presenter.requestFormPost(Contacts.CINEMA_COMMENT_GREAT,greatMap,headmap,MessageData.class);
                        }
                    }
                });

                setPopupWindow(commentsView);

                break;
                //点击关注电影
            case R.id.movie_follow_cb:
                if(followCb.isChecked()){
                    presenter.requestGEt(Contacts.FOLLMOVIE_URL,map,headmap,MessageData.class);
                }else{
                    presenter.requestGEt(Contacts.CANCEL_FOLLOWMOVIE,map,headmap,MessageData.class);

                }
                break;

        }

    }
    //设置PopupWindow
    private void setPopupWindow(View v){
        popupWindow = new PopupWindow(v, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.setFocusable(true);
        // 设置点击popupwindow外屏幕其它地方消失
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(R.style.popwin_anim_style);
        popupWindow.showAtLocation(MovieDeailsActivity.this.findViewById(R.id.deails_rela), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

    }


    @Override
    public void successData(Object data) {
       if(data instanceof MovieDetailData){
           movieDetailData = (MovieDetailData) data;
           Glide.with(MovieDeailsActivity.this).load(movieDetailData.getResult().getImageUrl()).into(detailImage);
           movieName.setText(movieDetailData.getResult().getName());
           if(movieDetailData.getResult().isFollowMovie()==1){
               followCb.setChecked(true);
           }else{
               followCb.setChecked(false);
           }
       }
       //查询对影片的评论
       if(data instanceof FindMovieCommentData){
           findMovieCommentData = (FindMovieCommentData) data;
           result.addAll(findMovieCommentData.getResult());
           movieCommentsAdapter.notifyDataSetChanged();

       }
       if(data instanceof MessageData){
           MessageData messageData= (MessageData) data;
           showShort(messageData.getMessage());
           //movieCommentsAdapter.notifyDataSetChanged();

       }
    }

    @Override
    public void errorMsg(Object error) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
        popupWindow=null;
        popupWindow=null;



    }
}
