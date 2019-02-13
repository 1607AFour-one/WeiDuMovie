package com.bw.movie.ui;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.MyInfoData;
import com.bw.movie.bean.UpdataInfoData;
import com.bw.movie.presenter.PresenterImpl;
import com.bw.movie.utils.Contacts;
import com.bw.movie.utils.RegexUtils;
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
    private PresenterImpl presenter;
    private RelativeLayout updata_name;
    private RelativeLayout updata_sex;
    private RelativeLayout updata_date;
    private RelativeLayout updata_phone;
    private RelativeLayout updta_email;
    private RelativeLayout updta_pwd;
    private EditText edName;
    private EditText ed_email;
    private HashMap<String, Object> headmap;

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

        updata_name=findViewById(R.id.updata_name);
        updata_sex=findViewById(R.id.updata_sex);
        updata_date=findViewById(R.id.updata_date);
        updata_phone=findViewById(R.id.updata_phone);
        updta_email=findViewById(R.id.updta_email);
        updta_pwd=findViewById(R.id.updta_pwd);

        updata_name.setOnClickListener(this);
        updata_sex.setOnClickListener(this);
        updata_date.setOnClickListener(this);
        updata_phone.setOnClickListener(this);
        updta_email.setOnClickListener(this);
        updta_pwd.setOnClickListener(this);


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
        String sessionId = SpUtils.getString("sessionId");
        headmap = new HashMap<>();
        headmap.put("userId", userId);
        headmap.put("sessionId", sessionId);
        presenter = new PresenterImpl(this);
        HashMap<String, Object> map = new HashMap<>();
        presenter.requestGEt(Contacts.USERINFO_URL, map, headmap, MyInfoData.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //点击修改昵称
            case R.id.updata_name:
                View updata_name_view=View.inflate(MyInfoActivity.this,R.layout.alert_updat_name,null);
                AlertDialog.Builder builder=new AlertDialog.Builder(MyInfoActivity.this);
                builder.setView(updata_name_view);
                edName = updata_name_view.findViewById(R.id.ed_name);
                edName.setText(name.getText().toString());

                final AlertDialog dialog=builder.create();
                updata_name_view.findViewById(R.id.btn_queding).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        name.setText(edName.getText().toString());
                        dialog.dismiss();
                    }
                });
                updata_name_view.findViewById(R.id.btn_cancle).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                break;
            case R.id.updata_sex:
                View updata_sex_view=View.inflate(MyInfoActivity.this,R.layout.alert_updata_sex,null);
                AlertDialog.Builder sexBuilder=new AlertDialog.Builder(MyInfoActivity.this);
                sexBuilder.setView(updata_sex_view);
                RadioButton rb_boy=updata_sex_view.findViewById(R.id.rb_boy);
                RadioButton rb_girl=updata_sex_view.findViewById(R.id.rb_girl);
                final AlertDialog sexDialog=sexBuilder.create();
                if(sex.getText().toString().equals("男")){
                    rb_boy.setChecked(true);
                }else{
                    rb_girl.setChecked(true);
                }
                RadioGroup sex_rg=updata_sex_view.findViewById(R.id.sex_rg);
                sex_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId){
                            case R.id.rb_boy:
                                sex.setText("男");
                                sexDialog.dismiss();
                                break;
                            case R.id.rb_girl:
                                sex.setText("女");
                                sexDialog.dismiss();
                                break;
                        }
                    }
                });
                sexDialog.show();
                break;
            case R.id.updta_email:
                View updata_email_view=View.inflate(MyInfoActivity.this,R.layout.alert_updata_email,null);
                AlertDialog.Builder emailBuilder=new AlertDialog.Builder(MyInfoActivity.this);
                emailBuilder.setView(updata_email_view);
                final AlertDialog emailDialog = emailBuilder.create();

                ed_email = updata_email_view.findViewById(R.id.ed_email);
                ed_email.setText(youxiang.getText().toString());
                updata_email_view.findViewById(R.id.btn_queding).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(RegexUtils.checkEmail(ed_email.getText().toString())){
                            youxiang.setText(ed_email.getText().toString());
                            emailDialog.dismiss();
                        }else{
                            Toast.makeText(MyInfoActivity.this, "邮箱格式不正确", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                updata_email_view.findViewById(R.id.btn_cancle).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        emailDialog.dismiss();
                    }
                });
                emailDialog.show();
                break;
        }

    }

    /**
     * 转换时间的
     *
     * @param lo
     * @return
     */
    //将long类型的数字转换成日期格式的方法
    public static String getloToDate(long lo) {
        Date date = new Date(lo);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        return sd.format(date);
    }

    @Override
    public void successData(Object data) {
        if (data instanceof MyInfoData) {
            MyInfoData myInfoData = (MyInfoData) data;
            name.setText(myInfoData.getResult().getNickName());
            date.setText(getloToDate(myInfoData.getResult().getBirthday()));
            phone.setText(myInfoData.getResult().getPhone());
            RequestOptions requestOptions = RequestOptions.circleCropTransform();
            Glide.with(MyInfoActivity.this).load(myInfoData.getResult().getHeadPic()).apply(requestOptions).into(headImage);
            if (myInfoData.getResult().getSex() == 1) {
                sex.setText("男");
            } else {
                sex.setText("女");
            }
          //  youxiang.setText(myInfoData.getResult().get);
        }
        if(data instanceof UpdataInfoData){
            UpdataInfoData updataInfoData= (UpdataInfoData) data;
            Toast.makeText(MyInfoActivity.this, updataInfoData.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void errorMsg(Object error) {

    }

    @Override
    protected void onDestroy() {
        HashMap<String,Object> infoMap=new HashMap<>();
        infoMap.put("nickName",name.getText().toString());
        if(sex.getText().toString().equals("男")){
            infoMap.put("sex",1);
        }else{
            infoMap.put("sex",2);
        }
        infoMap.put("email",youxiang.getText().toString());
        presenter.requestFormPost(Contacts.UPDATE_INFO_URL,infoMap,headmap,UpdataInfoData.class);
        presenter.onDetach();
        super.onDestroy();
    }


}
