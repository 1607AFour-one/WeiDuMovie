package com.bw.movie.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.MyInfoData;
import com.bw.movie.presenter.PresenterImpl;
import com.bw.movie.utils.Contacts;
import com.bw.movie.utils.SpUtils;
import com.bw.movie.view.IView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class MyInfoActivity extends BaseActivity implements IView {


    private ImageView headImage;
    private TextView name;
    private TextView sex;
    private TextView date;
    private TextView phone;
    private TextView youxiang;
    private ImageView chongzhi;

    @Override
    protected int initLayout() {
        return R.layout.activity_my_info;
    }


    @Override
    protected void initView() {
        headImage = findViewById(R.id.touxiang);
        name = findViewById(R.id.name);
        sex = findViewById(R.id.sex);
        date = findViewById(R.id.info_date);
        phone = findViewById(R.id.phone);
        youxiang = findViewById(R.id.youxiang);
        chongzhi = findViewById(R.id.chongzhi);


    }

    @Override
    protected void setClicks() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void setProgress() {
        int userId = SpUtils.getInt("userId");
        String sessionId=SpUtils.getString("sessionId");
        HashMap<String,Object>headmap = new HashMap<>();
        headmap.put("userId",userId);
        headmap.put("sessionId",sessionId);
        PresenterImpl presenter=new PresenterImpl(this);
        HashMap<String,Object>map=new HashMap<>();
        presenter.requestGEt(Contacts.USERINFO_URL,map,headmap,MyInfoData.class);
    }

    @Override
    public void onClick(View v) {

    }
    /**
     * 转换时间的
     * @param lo
     * @return
     */
    public static String getloToDate(long lo) {
        Date date = new Date(lo);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        return sd.format(date);
    }

    @Override
    public void successData(Object data) {
        if(data instanceof MyInfoData){
            MyInfoData myInfoData= (MyInfoData) data;
            name.setText(myInfoData.getResult().getNickName());
            date.setText(getloToDate(myInfoData.getResult().getBirthday()));
            phone.setText(myInfoData.getResult().getPhone());
            RequestOptions requestOptions = RequestOptions.circleCropTransform();
            Glide.with(MyInfoActivity.this).load(myInfoData.getResult().getHeadPic()).apply(requestOptions).into(headImage);
            if(myInfoData.getResult().getSex()==1){
                sex.setText("男");
            }else{
                sex.setText("女");
            }
        }

    }


    @Override
    public void errorMsg(Object error) {

    }
}
