package com.bw.movie.ui;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.transition.Explode;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.LoginData;
import com.bw.movie.presenter.PresenterImpl;
import com.bw.movie.utils.Contacts;
import com.bw.movie.utils.EncryptUtil;
import com.bw.movie.utils.SpUtils;
import com.bw.movie.view.IView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements IView {
    /*@BindView(R.id.login_ed_phone)*/
    EditText loginEdPhone;
    /*@BindView(R.id.login_ed_pwd)*/
    EditText loginEdPwd;
    /*@BindView(R.id.login_cb_rember)*/
    CheckBox loginCbRember;
   /* @BindView(R.id.login_cb_auto)*/
    CheckBox loginCbAuto;
    /*@BindView(R.id.regist_tv)*/
    TextView registTv;
   /* @BindView(R.id.login_lin1)*/
    RelativeLayout loginLin1;
   /* @BindView(R.id.login_btn_login)*/
    Button loginBtnLogin;
    /*@BindView(R.id.login_sanfang)*/
    TextView loginSanfang;
    /*@BindView(R.id.login_image_weixin)*/
    ImageView loginImageWeixin;

    @Override
    protected int initLayout() {

        return R.layout.activity_main;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void initView() {

        //getWindow().setEnterTransition(new Explode().setDuration(3000));
        //getWindow().setExitTransition(new Explode().setDuration(3000));
        loginEdPhone=findViewById(R.id.login_ed_phone);
        loginEdPwd=findViewById(R.id.login_ed_pwd);
        loginBtnLogin=findViewById(R.id.login_btn_login);
        loginCbRember=findViewById(R.id.login_cb_rember);
        loginCbAuto=findViewById(R.id.login_cb_auto);
        registTv=findViewById(R.id.regist_tv);
        loginImageWeixin=findViewById(R.id.login_image_weixin);

        loginBtnLogin.setOnClickListener(this);
        loginImageWeixin.setOnClickListener(this);
        registTv.setOnClickListener(this);
        loginCbRember.setOnClickListener(this);
        loginCbAuto.setOnClickListener(this);



    }

    @Override
    protected void setClicks() {
//        SpUtils.getPreferneces()
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void setProgress() {
        //Toast.makeText(this,SpUtils.getPreferneces().getInt("one",11)+"", Toast.LENGTH_SHORT).show();
        boolean isRember = SpUtils.getPreferneces().getBoolean("isRember", false);
        boolean isAuto = SpUtils.getPreferneces().getBoolean("isAuto", false);
        loginCbRember.setChecked(isRember);
        loginCbAuto.setChecked(isAuto);

        if(isRember){
            String phone = SpUtils.getPreferneces().getString("phone", "");
            String pwd = SpUtils.getPreferneces().getString("pwd", "");
            loginEdPhone.setText(phone);
            loginEdPwd.setText(pwd);
        }
        if(isAuto){
            String phone=loginEdPhone.getText().toString().trim();
            String pwd=loginEdPwd.getText().toString().trim();
            HashMap<String,Object> map=new HashMap<>();
            HashMap<String,Object>headmap=new HashMap<>();
            String encrypt = EncryptUtil.encrypt(pwd);
            map.put("phone",phone);
            map.put("pwd",encrypt);
            PresenterImpl presenter=new PresenterImpl(this);
            presenter.requestFormPost(Contacts.LOGIN_URL,map,headmap,LoginData.class);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_cb_rember:
                if(!loginCbRember.isChecked()){
                    loginCbAuto.setChecked(false);
                }

                break;
            case R.id.login_cb_auto:
                if(loginCbAuto.isChecked()){
                    loginCbRember.setChecked(true);
                }
                break;
            case R.id.regist_tv:
                openActivity(RegisteredActivity.class);
                break;
            case R.id.login_lin1:
                break;
            case R.id.login_btn_login:
                String phone=loginEdPhone.getText().toString().trim();
                String pwd=loginEdPwd.getText().toString().trim();
                HashMap<String,Object> map=new HashMap<>();
                HashMap<String,Object>headmap=new HashMap<>();
                String encrypt = EncryptUtil.encrypt(pwd);
                map.put("phone",phone);
                map.put("pwd",encrypt);
                PresenterImpl presenter=new PresenterImpl(this);
                presenter.requestFormPost(Contacts.LOGIN_URL,map,headmap,LoginData.class);
                break;
            case R.id.login_image_weixin:
                break;
        }

    }



    @Override
    public void successData(Object data) {
        LoginData loginData= (LoginData) data;
        showShort(loginData.getMessage());
        SpUtils.putInt("userId",loginData.getResult().getUserId());
        SpUtils.putString("sessionId",loginData.getResult().getSessionId());
        if(loginCbRember.isChecked()){
            SpUtils.putBoolean("isRember",true);
            SpUtils.putString("phone",loginEdPhone.getText().toString().trim());
            SpUtils.putString("pwd",loginEdPwd.getText().toString().trim());
        }else {
            SpUtils.putBoolean("isRember",false);
        }
        if(loginCbAuto.isChecked()){
            SpUtils.putBoolean("isAuto",true);
        }else{
            SpUtils.putBoolean("isAuto",false);
        }
        if(loginData.getStatus().equals("0000")){
            openActivity(HomeActivity.class);
            finish();
        }

    }

    @Override
    public void errorMsg(Object error) {

    }
}
