package com.bw.movie.ui;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.OrderData;
import com.bw.movie.presenter.PresenterImpl;
import com.bw.movie.utils.Contacts;
import com.bw.movie.utils.MD5Utils;
import com.bw.movie.utils.SpUtils;
import com.bw.movie.view.IView;
import com.bw.movie.weight.SeatTable;

import java.util.HashMap;

public class SeatActivity extends BaseActivity implements IView {
    public SeatTable seatTableView;
    private ImageView seatTable_jiesuan,SeatTable_Clear;
    private PresenterImpl presenter;
    private int count=3;
    private View view;
    private ImageView pop_to_pay_type_back;
    private RadioButton pop_wechat_rb;
    private RadioButton pop_pay_rb;
    private Button pop_to_pay_btn;
    private RelativeLayout alipy;
    @Override
    protected int initLayout() {
        return R.layout.activity_seat;
    }
    @Override
    protected void initView() {
        alipy = findViewById(R.id.alipy);
        view = View.inflate(SeatActivity.this, R.layout.alipay_item, null);

        pop_to_pay_type_back = view.findViewById(R.id.pop_to_pay_type_back);
        pop_wechat_rb = view.findViewById(R.id.pop_wechat_rb);
        pop_pay_rb = view.findViewById(R.id.pop_pay_rb);
        pop_to_pay_btn = view.findViewById(R.id.pop_to_pay_btn);
        pop_wechat_rb.setOnClickListener(this);
        pop_pay_rb.setOnClickListener(this);

    }

    @Override
    protected void setClicks() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void setProgress() {
        //接受排期表id
        Intent intent=getIntent();
        final int id = intent.getIntExtra("id", 0);

        seatTableView = (SeatTable) findViewById(R.id.seatView);
        seatTable_jiesuan = findViewById(R.id.SeatTable_Jiesuan);
        SeatTable_Clear=findViewById(R.id.SeatTable_Clear);
        seatTableView.setScreenName("8号厅荧幕");//设置屏幕名称
        seatTableView.setMaxSelected(6);//设置最多选中
        presenter = new PresenterImpl(this);
        seatTable_jiesuan.setOnClickListener(this);
        initView();
        seatTable_jiesuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int userId = SpUtils.getInt("userId");
                String sessionId = SpUtils.getString("sessionId");
                HashMap<String, Object> headmap = new HashMap<>();
                HashMap<String, Object> map = new HashMap<>();
                headmap.put("userId", userId);
                headmap.put("sessionId", sessionId);
                map.put("scheduleId",id);
                map.put("amount",count+"");
                String str="";
                str+=userId+""+id+"3movie";
                Log.e("hh",headmap.toString()+"--"+id+"=="+str);
                map.put("sign", MD5Utils.MD5(str));
                presenter.requestFormPost(Contacts.ORDER_URL,map,headmap,OrderData.class);

                final PopupWindow popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.WRAP_CONTENT);
                popupWindow.setBackgroundDrawable(new ColorDrawable());
                popupWindow.setFocusable(true);
                // 设置点击popupwindow外屏幕其它地方消失
                popupWindow.setOutsideTouchable(true);
                pop_to_pay_type_back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                popupWindow.setAnimationStyle(R.style.popwin_anim_style);
                // popupWindow.showAsDropDown(view);
                popupWindow.showAtLocation(SeatActivity.this.findViewById(R.id.alipy), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);


            }
        });
        SeatTable_Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        seatTableView.setSeatChecker(new SeatTable.SeatChecker() {

            @Override
            public boolean isValidSeat(int row, int column) {
                if(column==2) {
                    return false;
                }
                return true;
            }

            @Override
            public boolean isSold(int row, int column) {
                if(row==6&&column==6){
                    return true;
                }
                return false;
            }

            @Override
            public void checked(int row, int column) {

            }

            @Override
            public void unCheck(int row, int column) {

            }

            @Override
            public String[] checkedSeatTxt(int row, int column) {
                return null;
            }

        });
        seatTableView.setData(10,15);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.pop_wechat_rb:
               if(pop_pay_rb.isChecked()){
                   pop_pay_rb.setChecked(false);
               }

                break;
            case R.id.pop_pay_rb:
                if(pop_pay_rb.isChecked()){
                    pop_wechat_rb.setChecked(false);
                }
                break;

        }

    }

    @Override
    public void successData(Object data) {

        if(data instanceof OrderData){
            OrderData orderData= (OrderData) data;
            Toast.makeText(getApplicationContext(),orderData.getMessage(),Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void errorMsg(Object error) {

    }
}
