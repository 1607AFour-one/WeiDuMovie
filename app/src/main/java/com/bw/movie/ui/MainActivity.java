package com.bw.movie.ui;


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

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.LoginData;
import com.bw.movie.presenter.PresenterImpl;
import com.bw.movie.utils.Contacts;
import com.bw.movie.utils.EncryptUtil;
import com.bw.movie.view.IView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements IView {
    @BindView(R.id.login_ed_phone)
    EditText loginEdPhone;
    @BindView(R.id.login_ed_pwd)
    EditText loginEdPwd;
    @BindView(R.id.login_cb_rember)
    CheckBox loginCbRember;
    @BindView(R.id.login_cb_auto)
    CheckBox loginCbAuto;
    @BindView(R.id.regist_tv)
    TextView registTv;
    @BindView(R.id.login_lin1)
    RelativeLayout loginLin1;
    @BindView(R.id.login_btn_login)
    Button loginBtnLogin;
    @BindView(R.id.login_sanfang)
    TextView loginSanfang;
    @BindView(R.id.login_image_weixin)
    ImageView loginImageWeixin;

    //你好我不好

    @Override
    protected int initLayout() {

        return R.layout.activity_main;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void initView() {

        getWindow().setEnterTransition(new Explode().setDuration(3000));
        getWindow().setExitTransition(new Explode().setDuration(3000));
    }

    @Override
    protected void setClicks() {


    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void setProgress() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.login_cb_rember, R.id.login_cb_auto, R.id.regist_tv, R.id.login_lin1, R.id.login_btn_login, R.id.login_image_weixin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_cb_rember:
                break;
            case R.id.login_cb_auto:
                break;
            case R.id.regist_tv:
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

    }

    @Override
    public void errorMsg(Object error) {

    }
}
