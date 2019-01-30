package com.bw.movie.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.MessageData;
import com.bw.movie.presenter.PresenterImpl;
import com.bw.movie.utils.Contacts;
import com.bw.movie.utils.EncryptUtil;
import com.bw.movie.utils.RegexUtils;
import com.bw.movie.view.IView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisteredActivity extends BaseActivity implements IView {

    @BindView(R.id.Zhu_Name)
    EditText ZhuName;
    @BindView(R.id.Zhu_Sex)
    EditText ZhuSex;
    @BindView(R.id.Zhu_Date)
    EditText ZhuDate;
    @BindView(R.id.Zhu_Phone)
    EditText ZhuPhone;
    @BindView(R.id.Zhu_Mail)
    EditText ZhuMail;
    @BindView(R.id.Zhu_Pass)
    EditText ZhuPass;
    @BindView(R.id.Zhu_Btn)
    Button ZhuBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //etContentView(R.layout.activity_registered);
        ButterKnife.bind(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_registered;
    }

    @Override
    protected void initView() {

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
    public void successData(Object data) {
        MessageData messageData= (MessageData) data;
        showShort(messageData.getMessage());
        finish();
    }

    @Override
    public void errorMsg(Object error) {

    }

    @OnClick(R.id.Zhu_Btn)
    public void onViewClicked() {

        String name = ZhuName.getText().toString().trim();
        String sex = ZhuSex.getText().toString().trim();
        String date = ZhuDate.getText().toString().trim();
        String phone = ZhuPhone.getText().toString().trim();
        String mail = ZhuMail.getText().toString().trim();
        String pass = ZhuPass.getText().toString().trim();
        if(sex.equals("")||date.equals("")){
            showShort("性别不能为空");
        }
        else if(!RegexUtils.checkEmail(mail)||mail.equals("")){
            showShort("邮箱格式不正确");
            return;
        }else if(!RegexUtils.checkMobile(phone)||phone.equals("")){
            showShort("手机格式不正确");
            return;
        }else if(!RegexUtils.checkChinese(name)||name.equals("")){
            showShort("昵称只能为中文");
            return;
        }else if(!RegexUtils.checkDigit(pass)||pass.equals("")){
            showShort("密码格式不正确");
            return;
        }else{
            HashMap<String,Object>map=new HashMap<>();
            HashMap<String,Object>hashMap=new HashMap<>();
            String encrypt = EncryptUtil.encrypt(pass);
            map.put("pwd",encrypt);
            map.put("nickName",name);
            map.put("phone",phone);
            map.put("sex",sex);
            map.put("birthday",date);
            map.put("email",mail);
            map.put("pwd2",encrypt);
            PresenterImpl presenter=new PresenterImpl(this);
            presenter.requestFormPost(Contacts.ZHUCE_URL,map,hashMap,MessageData.class);

        }



    }
}
