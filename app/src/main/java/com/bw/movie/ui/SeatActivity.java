package com.bw.movie.ui;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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

import com.alipay.sdk.app.PayTask;
import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.AliBean;
import com.bw.movie.bean.OrderData;
import com.bw.movie.bean.PayBean;
import com.bw.movie.bean.PayResult;
import com.bw.movie.presenter.PresenterImpl;
import com.bw.movie.utils.Contacts;
import com.bw.movie.utils.MD5Utils;
import com.bw.movie.utils.SpUtils;
import com.bw.movie.utils.WXUtils;
import com.bw.movie.view.IView;
import com.bw.movie.weight.SeatTable;
import com.tencent.mm.opensdk.modelpay.PayReq;

import java.util.HashMap;
import java.util.Map;

public class SeatActivity extends BaseActivity implements IView {
    public SeatTable seatTableView;
    private ImageView seatTable_jiesuan, SeatTable_Clear;
    private PresenterImpl presenter;
    private int count = 1;
    private View view;
    private ImageView pop_to_pay_type_back;
    private RadioButton pop_wechat_rb;
    private RadioButton pop_pay_rb;
    private Button pop_to_pay_btn;
    private RelativeLayout alipy;
    private String orderId;
    private HashMap<String, Object> headmap;
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {

            @SuppressWarnings("unchecked")
            PayResult payResult = new PayResult((Map<String, String>) msg.obj);
            /**
             * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
             */
            String resultInfo = payResult.getResult();// 同步返回需要验证的信息
            String resultStatus = payResult.getResultStatus();
            // 判断resultStatus 为9000则代表支付成功
            if (TextUtils.equals(resultStatus, "9000")) {
                // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                //showAlert(PayDemoActivity.this, getString(R.string.pay_success) + payResult);
            } else {
                // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                //showAlert(PayDemoActivity.this, getString(R.string.pay_failed) + payResult);
            }

        }

        ;
    };

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
        pop_to_pay_btn.setOnClickListener(this);

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
        Intent intent = getIntent();
        final int id = intent.getIntExtra("id", 0);

        seatTableView = (SeatTable) findViewById(R.id.seatView);
        seatTable_jiesuan = findViewById(R.id.SeatTable_Jiesuan);
        SeatTable_Clear = findViewById(R.id.SeatTable_Clear);
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
                headmap = new HashMap<>();
                HashMap<String, Object> map = new HashMap<>();
                headmap.put("userId", userId);
                headmap.put("sessionId", sessionId);
                map.put("scheduleId", id);
                map.put("amount", count + "");
                String str = "";
                str += userId + "" + id + "1movie";
                Log.e("hh", headmap.toString() + "--" + id + "==" + str);
                map.put("sign", MD5Utils.MD5(str));
                presenter.requestFormPost(Contacts.ORDER_URL, map, headmap, OrderData.class);

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
                if (column == 2) {
                    return false;
                }
                return true;
            }

            @Override
            public boolean isSold(int row, int column) {
                if (row == 6 && column == 6) {
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
        seatTableView.setData(10, 15);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.pop_wechat_rb:
                if (pop_pay_rb.isChecked()) {
                    pop_pay_rb.setChecked(false);
                }

                break;
            case R.id.pop_pay_rb:
                if (pop_pay_rb.isChecked()) {
                    pop_wechat_rb.setChecked(false);
                }
                break;
            case R.id.pop_to_pay_btn:
                if (!pop_wechat_rb.isChecked() && !pop_pay_rb.isChecked()) {
                    showShort("请选择支付方式");
                } else {
                    if (pop_wechat_rb.isChecked()) {
                        //Toast.makeText(this, orderId, Toast.LENGTH_SHORT).show();
                        HashMap<String, Object> parms = new HashMap<>();
                        parms.put("payType", 1 + "");
                        parms.put("orderId", orderId);
                        presenter.requestFormPost(Contacts.URL_GO_TO_PAY, parms, headmap, PayBean.class);

                    } else {
                        //Toast.makeText(this, "zfb", Toast.LENGTH_SHORT).show();
                        HashMap<String, Object> aliparms = new HashMap<>();
                        aliparms.put("payType", 2 + "");
                        aliparms.put("orderId", orderId);

                        presenter.requestFormPost(Contacts.URL_GO_TO_PAY, aliparms, headmap, AliBean.class);

                    }
                }

                break;


        }

    }

    @Override
    public void successData(Object data) {

        if (data instanceof OrderData) {
            OrderData orderData = (OrderData) data;

            orderId = orderData.getOrderId();

            Toast.makeText(getApplicationContext(), orderData.getMessage(), Toast.LENGTH_SHORT).show();

        }
        if (data instanceof PayBean) {
            PayBean pay = (PayBean) data;
//            "appId":"wxb3852e6a6b7d9516",
//                    "message":"支付成功",
//                    "nonceStr":"WQ72A7mNfUaA05Gw",// 随机字符串
//                    "partnerId":"1510865081",// 微信支付分配的商户号
//                    "prepayId":"wx07084938610450dcaad697243945045542",// 微信返回的支付交易会话ID
//                    "sign":"94F2FD993AB97C002E6CE898B04B578A",// 签名
//                    "status":"0000",
//                    "timeStamp":"1533602976",// 时间戳
//                    "packageValue":"Sign=WXPay"// 扩展字段占时填固定

            if (pay != null && pay.getStatus().equals("0000")) {

                PayReq req = new PayReq();
                req.appId = pay.getAppId();
                req.nonceStr = pay.getNonceStr();
                req.partnerId = pay.getPartnerId();
                req.prepayId = pay.getPrepayId();
                req.sign = pay.getSign();
                req.timeStamp = pay.getTimeStamp();
                req.packageValue = pay.getPackageValue();
                //去调微信
                WXUtils.reg(this).sendReq(req);
            }


        } else if (data instanceof AliBean) {
            final AliBean aliBean = (AliBean) data;
            if (aliBean != null && aliBean.getStatus().equals("0000")) {
                Toast.makeText(SeatActivity.this, "2222", Toast.LENGTH_SHORT).show();
                Runnable payRunnable = new Runnable() {

                    @Override
                    public void run() {
                        PayTask alipay = new PayTask(SeatActivity.this);
                        HashMap<String, String> result = (HashMap<String, String>) alipay.payV2(aliBean.getResult(), true);

                        Message msg = new Message();
                        msg.what = SDK_PAY_FLAG;
                        msg.obj = result;
                        mHandler.sendMessage(msg);
                    }
                };
                // 必须异步调用
                Thread payThread = new Thread(payRunnable);
                payThread.start();

            }
        }

    }

    @Override
    public void errorMsg(Object error) {

    }
}
