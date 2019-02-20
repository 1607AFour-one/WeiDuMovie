package com.bw.movie.wxapi;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.bw.movie.R;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXPayEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {
    private IWXAPI iwxapi;
    private static String APP_ID = "wxb3852e6a6b7d9516";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        // 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //接收到分享以及登录的intent传递handleIntent方法，处理结果
        iwxapi = WXAPIFactory.createWXAPI(this, APP_ID, false);
        iwxapi.handleIntent(getIntent(), this);

    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            switch (baseResp.errCode) {
                case BaseResp.ErrCode.ERR_OK:
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(WXPayEntryActivity.this, "成功", Toast.LENGTH_SHORT).show();
                            Log.e("dj", "支付成功");
                            WXPayEntryActivity.this.finish();
                        }
                    });
                    break;
                case BaseResp.ErrCode.ERR_USER_CANCEL:
                    Toast.makeText(WXPayEntryActivity.this, "取消支付", Toast.LENGTH_SHORT).show();
                    Log.e("dj", "用户取消支付");
                    WXPayEntryActivity.this.finish();
                    break;

                default:
                    break;
            }
        }
    }


}
