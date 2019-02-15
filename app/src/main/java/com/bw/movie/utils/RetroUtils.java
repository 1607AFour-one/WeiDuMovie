package com.bw.movie.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * author:author${车文飞}
 * data:2019/1/23
 */
public class RetroUtils {

    private final MyApiService myApiService;

    private RetroUtils() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .retryOnConnectionFailure(true)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Contacts.BASE_URL)
                .client(okHttpClient)
                .build();
        myApiService = retrofit.create(MyApiService.class);
    }

    private static class RetroHolder {
        private static final RetroUtils retrofitUtils = new RetroUtils();
    }

    public static RetroUtils getInstence() {
        return RetroHolder.retrofitUtils;
    }

    public void get(String url, HashMap<String, Object> map, HashMap<String, Object> headmap, final HttpListener httpListener) {
        Observer<ResponseBody> observer = new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                httpListener.onError(e.getMessage());

            }

            @Override
            public void onNext(ResponseBody responseBody) {
                if (httpListener != null) {
                    try {
                        httpListener.onSuccess(responseBody.string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        };
        myApiService.get(url, map, headmap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);


    }
    public void post(String url, HashMap<String,Object> map,HashMap<String,Object> headmap, final HttpListener httpListener){
        Observer<ResponseBody> observer=new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {

            }
            @Override
            public void onError(Throwable e) {

            }
            @Override
            public void onNext(ResponseBody responseBody) {
                if(httpListener!=null){
                    try {
                        httpListener.onSuccess(responseBody.string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        };
        myApiService.post(url,map,headmap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }

    public void postFrom(String url, int id,HashMap<String,Object> headmap, final HttpListener httpListener){
        Observer<ResponseBody> observer=new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {

            }
            @Override
            public void onError(Throwable e) {

            }
            @Override
            public void onNext(ResponseBody responseBody) {
                if(httpListener!=null){
                    try {
                        httpListener.onSuccess(responseBody.string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        };
        myApiService.postFrom(url,id,headmap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }
    public void delete(String url, HashMap<String,Object> map,HashMap<String,Object> headmap, final HttpListener httpListener){
        Observer<ResponseBody> observer=new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {

            }
            @Override
            public void onError(Throwable e) {

            }
            @Override
            public void onNext(ResponseBody responseBody) {
                if(httpListener!=null){
                    try {
                        httpListener.onSuccess(responseBody.string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        };
        myApiService.delete(url,map,headmap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }
    public void put(String url, HashMap<String,Object> map,HashMap<String,Object> headmap, final HttpListener httpListener){
        Observer<ResponseBody> observer=new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {

            }
            @Override
            public void onError(Throwable e) {

            }
            @Override
            public void onNext(ResponseBody responseBody) {
                if(httpListener!=null){
                    try {
                        httpListener.onSuccess(responseBody.string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        };
        myApiService.put(url,map,headmap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }
    public void upLoad(String url, HashMap<String,Object> map, HashMap<String,Object> headmap, MultipartBody.Part part, final HttpListener httpListener){
        Observer<ResponseBody> observer=new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if(httpListener!=null){
                    httpListener.onError(e.getMessage());
                }

            }

            @Override
            public void onNext(ResponseBody responseBody) {
                if(httpListener!=null){
                    try {
                        httpListener.onSuccess(responseBody.string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            }
        };
        myApiService.upload(url,map,headmap,part)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }
    public void formPost(String url, HashMap<String,Object> map,HashMap<String,Object> headmap, final HttpListener httpListener){
        Observer<ResponseBody> observer=new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {

            }
            @Override
            public void onError(Throwable e) {

            }
            @Override
            public void onNext(ResponseBody responseBody) {
                if(httpListener!=null){
                    try {
                        httpListener.onSuccess(responseBody.string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        };
        myApiService.formPost(url,map,headmap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }

    public interface HttpListener{
        void onSuccess(String jsonStr);
        void onError(String error);
    }
}