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

    @Override
    protected int initLayout() {
        return R.layout.activity_movie_deails;
    }

    @Override
    protected void initView() {
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
                view = View.inflate(MovieDeailsActivity.this,R.layout.detial_dialog,null);
                final PopupWindow popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.WRAP_CONTENT);
                popupWindow.setBackgroundDrawable(new ColorDrawable());
                popupWindow.setFocusable(true);
                // 设置点击popupwindow外屏幕其它地方消失
                popupWindow.setOutsideTouchable(true);

                popupWindow.setAnimationStyle(R.style.popwin_anim_style);
//                // 平移动画相对于手机屏幕的底部开始，X轴不变，Y轴从1变0
//                animation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0,
//                        Animation.RELATIVE_TO_PARENT, 1,Animation.RELATIVE_TO_PARENT  , 0);
//                animation.setInterpolator(new AccelerateInterpolator());
//                animation.setDuration(500);
//                view.startAnimation(animation);
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        if(popupWindow.isShowing()){
//                            animation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, Animation.RELATIVE_TO_PARENT,0 , Animation.RELATIVE_TO_PARENT,
//                                    Animation.RELATIVE_TO_PARENT, Animation.RELATIVE_TO_PARENT, 1 , Animation.RELATIVE_TO_PARENT);
//                            animation.setInterpolator(new AccelerateInterpolator());
//                            animation.setDuration(500);
//                            view.startAnimation(animation);
                            popupWindow.dismiss();
                        }
                        showShort(" eefsddsf");


                    }
                });

                popupWindow.showAtLocation(MovieDeailsActivity.this.findViewById(R.id.deails_rela), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;

        }


    }


    @Override
    public void successData(Object data) {
        MovieDetailData movieDetailData= (MovieDetailData) data;
        showShort(movieDetailData.getMessage());
        Glide.with(MovieDeailsActivity.this).load(movieDetailData.getResult().getImageUrl()).into(detailImage);
        movieName.setText(movieDetailData.getResult().getName());
    }

    @Override
    public void errorMsg(Object error) {

    }
}
