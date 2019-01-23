package com.bw.movie.modle;

import com.bw.movie.callback.MyCallBack;

import java.util.HashMap;

import okhttp3.RequestBody;

/**
 * author:author${车文飞}
 * data:2019/1/23
 */
public interface IModle {
    void getPostData(String url, HashMap<String,Object> map, HashMap<String, Object> headmap, Class clazz, MyCallBack callBack);
    void getGetData(String url, HashMap<String,Object>map,HashMap<String, Object> headmap, Class clazz, MyCallBack callBack);
    void getDeleteData(String url, HashMap<String,Object>map,HashMap<String, Object> headmap, Class clazz, MyCallBack callBack);
    void getPutData(String url, HashMap<String,Object>map,HashMap<String, Object> headmap, Class clazz, MyCallBack callBack);
    void getUpLoadData(String url, HashMap<String,Object> map,HashMap<String,Object>headmap, HashMap<String, RequestBody> part, Class clas, MyCallBack callBack);
}
