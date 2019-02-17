package com.bw.movie.utils;

import android.content.Context;

import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * author:author${车文飞}
 * data:2019/2/16
 */
public class WXUtils {
    /**
     * APP_ID 替换为你的应用从官方网站申请到的合法appID
     */
    public static String APP_ID = "wxb3852e6a6b7d9516";

    public static IWXAPI reg(Context context) {
        if (context != null) {
            //AppConst.WEIXIN.APP_ID是指你应用在微信开放平台上的AppID，记得替换。
            IWXAPI wxapi = WXAPIFactory.createWXAPI(context, APP_ID, true);
            //注册到微信
            wxapi.registerApp(APP_ID);
            return wxapi;
        } else {
            return null;
        }
    }

    //判断是否安装过微信
    public static boolean success(Context context) {
        if (WXUtils.reg(context).isWXAppInstalled()) {
            return true;
        } else {
            return false;
        }
    }
}
