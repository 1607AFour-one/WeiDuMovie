package com.bw.movie.modle;

import com.bw.movie.callback.MyCallBack;
import com.bw.movie.utils.RetroUtils;
import com.google.gson.Gson;

import java.util.HashMap;

import okhttp3.RequestBody;

/**
 * author:author${车文飞}
 * data:2019/1/23
 */
public class ModleImpl implements IModle {
    @Override
    public void getPostData(String url, HashMap<String, Object> map, HashMap<String, Object> headmap, final Class clazz, final MyCallBack callBack) {
        RetroUtils.getInstence().post(url, map, headmap, new RetroUtils.HttpListener() {
            @Override
            public void onSuccess(String jsonStr) {
                Gson gson=new Gson();
                Object o = gson.fromJson(jsonStr, clazz);
                if(callBack!=null){
                    callBack.setData(o);
                }
            }

            @Override
            public void onError(String error) {
                if(callBack!=null){
                    callBack.setError(error);
                }

            }
        });
    }

    @Override
    public void getGetData(String url, HashMap<String, Object> map, HashMap<String, Object> headmap, final Class clazz, final MyCallBack callBack) {
        RetroUtils.getInstence().get(url, map, headmap, new RetroUtils.HttpListener() {
            @Override
            public void onSuccess(String jsonStr) {
                Gson gson=new Gson();
                Object o = gson.fromJson(jsonStr, clazz);
                if(callBack!=null){
                    callBack.setData(o);
                }
            }

            @Override
            public void onError(String error) {
                if(callBack!=null){
                    callBack.setError(error);
                }

            }
        });

    }

    @Override
    public void getDeleteData(String url, HashMap<String, Object> map, HashMap<String, Object> headmap, final Class clazz, final MyCallBack callBack) {
        RetroUtils.getInstence().delete(url, map, headmap, new RetroUtils.HttpListener() {
            @Override
            public void onSuccess(String jsonStr) {
                Gson gson=new Gson();
                Object o = gson.fromJson(jsonStr, clazz);
                if(callBack!=null){
                    callBack.setData(o);
                }
            }

            @Override
            public void onError(String error) {
                if(callBack!=null){
                    callBack.setError(error);
                }

            }
        });

    }

    @Override
    public void getPutData(String url, HashMap<String, Object> map, HashMap<String, Object> headmap, final Class clazz, final MyCallBack callBack) {
        RetroUtils.getInstence().put(url, map, headmap, new RetroUtils.HttpListener() {
            @Override
            public void onSuccess(String jsonStr) {
                Gson gson=new Gson();
                Object o = gson.fromJson(jsonStr, clazz);
                if(callBack!=null){
                    callBack.setData(o);
                }
            }

            @Override
            public void onError(String error) {
                if(callBack!=null){
                    callBack.setError(error);
                }

            }
        });


    }

    @Override
    public void getUpLoadData(String url, HashMap<String, Object> map, HashMap<String, Object> headmap, HashMap<String, RequestBody> part, final Class clas, final MyCallBack callBack) {
        RetroUtils.getInstence().upLoad(url, map, headmap,part, new RetroUtils.HttpListener() {
            @Override
            public void onSuccess(String jsonStr) {
                Gson gson=new Gson();
                Object o = gson.fromJson(jsonStr, clas);
                if(callBack!=null){
                    callBack.setData(o);
                }

            }

            @Override
            public void onError(String error) {
                if(callBack!=null){
                    callBack.setError(error);
                }

            }
        });


    }
}
