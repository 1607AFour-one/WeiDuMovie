package com.bw.movie.presenter;

import com.bw.movie.callback.MyCallBack;
import com.bw.movie.modle.ModleImpl;
import com.bw.movie.view.IView;

import java.util.HashMap;

import okhttp3.RequestBody;

/**
 * author:author${车文飞}
 * data:2019/1/23
 */
public class PresenterImpl implements IPresenter {
    private ModleImpl modle;
    private IView iView;

    public PresenterImpl(IView iView) {
        this.iView = iView;
        modle=new ModleImpl();
    }

    @Override
    public void requestPost(String url, HashMap<String, Object> map, HashMap<String, Object> headmap, Class clazz) {
        modle.getPostData(url, map,headmap ,clazz, new MyCallBack() {
            @Override
            public void setData(Object data) {
                iView.successData(data);
            }
            @Override
            public void setError(Object error) {
                iView.errorMsg(error);

            }
        });


    }

    @Override
    public void requestGEt(String url, HashMap<String, Object> map, HashMap<String, Object> headmap, Class clazz) {
        modle.getGetData(url, map,headmap, clazz, new MyCallBack() {
            @Override
            public void setData(Object data) {
                iView.successData(data);
            }

            @Override
            public void setError(Object error) {
                iView.errorMsg(error);

            }
        });

    }

    @Override
    public void requestDelete(String url, HashMap<String, Object> map, HashMap<String, Object> headmap, Class clazz) {
        modle.getDeleteData(url, map,headmap, clazz, new MyCallBack() {
            @Override
            public void setData(Object data) {
                iView.successData(data);
            }

            @Override
            public void setError(Object error) {
                iView.errorMsg(error);

            }
        });

    }

    @Override
    public void requestPut(String url, HashMap<String, Object> map, HashMap<String, Object> headmap, Class clazz) {
        modle.getPutData(url, map,headmap, clazz, new MyCallBack() {
            @Override
            public void setData(Object data) {
                iView.successData(data);
            }

            @Override
            public void setError(Object error) {
                iView.errorMsg(error);

            }
        });

    }

    @Override
    public void requestUpload(String url, HashMap<String, Object> map, HashMap<String, Object> headmap, HashMap<String, RequestBody> part, Class clas) {
        modle.getUpLoadData(url, map, headmap,part, clas, new MyCallBack() {
            @Override
            public void setData(Object data) {
                iView.successData(data);
            }

            @Override
            public void setError(Object error) {
                iView.errorMsg(error);

            }
        });
    }
    public void onDetach(){
        if(modle!=null){
            modle=null;
        }
        if(iView!=null){
            iView=null;
        }
    }
}
