package com.bw.movie.presenter;

import java.util.HashMap;

import okhttp3.RequestBody;

/**
 * author:author${车文飞}
 * data:2019/1/23
 */
public interface IPresenter {
    void requestPost(String url, HashMap<String,Object> map, HashMap<String, Object> headmap, Class clazz);
    void requestGEt(String url, HashMap<String,Object> map,HashMap<String, Object> headmap,Class clazz);
    void requestDelete(String url, HashMap<String,Object> map,HashMap<String, Object> headmap,Class clazz);
    void requestPut(String url, HashMap<String,Object> map,HashMap<String, Object> headmap,Class clazz);
    void requestUpload(String url, HashMap<String,Object> map, HashMap<String,Object>headmap,HashMap<String, RequestBody>part, Class clas);
    void requestFormPost(String url, HashMap<String,Object> formMap,HashMap<String, Object> headmap,Class clazz);
}
