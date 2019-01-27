package com.bw.movie.ui;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.MovieDetailData;
import com.bw.movie.bean.MovieMessage;
import com.bw.movie.presenter.PresenterImpl;
import com.bw.movie.utils.Contacts;
import com.bw.movie.utils.SpUtils;
import com.bw.movie.view.IView;

import java.util.HashMap;

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
        detail.setOnClickListener(this);


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
        HashMap<String,Object> headmap=new HashMap<>();
        headmap.put("userId",userId);
        headmap.put("sessionId",sessionId);
        PresenterImpl presenter=new PresenterImpl(this);
        HashMap<String,Object> map=new HashMap<>();
        map.put("movieId",movieId);
        showShort(movieId+"");
        presenter.requestGEt(Contacts.MOVIE_DETAIL_URL,map,headmap,MovieDetailData.class);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.detail_rb:

                final PopupWindow popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.WRAP_CONTENT);
                popupWindow.setBackgroundDrawable(new ColorDrawable());
                popupWindow.setFocusable(true);
                // 设置点击popupwindow外屏幕其它地方消失
                popupWindow.setOutsideTouchable(true);
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
                deailDismiss.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });

                popupWindow.setAnimationStyle(R.style.popwin_anim_style);
                popupWindow.showAtLocation(MovieDeailsActivity.this.findViewById(R.id.deails_rela), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;

        }


    }


    @Override
    public void successData(Object data) {
        movieDetailData = (MovieDetailData) data;
        showShort(movieDetailData.getMessage());
        Glide.with(MovieDeailsActivity.this).load(movieDetailData.getResult().getImageUrl()).into(detailImage);
        movieName.setText(movieDetailData.getResult().getName());
    }

    @Override
    public void errorMsg(Object error) {

    }
}
