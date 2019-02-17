package com.bw.movie.wxapi;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.bean.WXBean;
import com.bw.movie.presenter.PresenterImpl;
import com.bw.movie.ui.HomeActivity;
import com.bw.movie.utils.Contacts;
import com.bw.movie.utils.SpUtils;
import com.bw.movie.view.IView;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.HashMap;
import java.util.Map;

public class WXEntryActivity extends AppCompatActivity implements IWXAPIEventHandler, IView {

    private IWXAPI iwxapi;
    private String unionid;
    private String openid;
    private static String APP_ID = "wxb3852e6a6b7d9516";
    private PresenterImpl presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().hide();
         presenter = new PresenterImpl(this);
//        // 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //接收到分享以及登录的intent传递handleIntent方法，处理结果
        iwxapi = WXAPIFactory.createWXAPI(this, APP_ID, false);
        iwxapi.handleIntent(getIntent(), this);

    }

    @Override
    public void successData(Object data) {
        if (data instanceof WXBean) {
            WXBean bean = (WXBean) data;
            Toast.makeText(WXEntryActivity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
            if (bean != null && bean.getStatus().equals("0000")) {
                Log.e("WXBean", bean.toString());
                SpUtils.putInt("userId",bean.getResult().getUserId());
                SpUtils.putString("sessionId",bean.getResult().getSessionId());
                SpUtils.putString("nickName",bean.getResult().getUserInfo().getNickName());
                SpUtils.putString("headPic",bean.getResult().getUserInfo().getHeadPic());

                startActivity(new Intent(WXEntryActivity.this,HomeActivity.class));
                finish();
            }
        }

    }

    @Override
    public void errorMsg(Object error) {
        Toast.makeText(this, error+"", Toast.LENGTH_SHORT).show();
        //ToastUtils.show(this, error);
        //Toast.makeText(WXEntryActivity.this, error, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onReq(BaseReq baseReq) {
        Log.e("onReq", "onError");

    }

    @Override
    public void onResp(BaseResp baseResp) {
        //登录回调
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                String code = ((SendAuth.Resp) baseResp).code;
                HashMap<String,Object> map=new HashMap<>();
                HashMap<String,Object> headMap=new HashMap<>();
                map.put("code", code);
                Log.e("code",code);
                presenter.requestFormPost(Contacts.WECHAT_LOGIN,map,headMap,WXBean.class);
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED://用户拒绝授权
                finish();
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL://用户取消
                finish();
                break;
            default:
                finish();
                break;
        }
    }

}
