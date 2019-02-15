package com.bw.movie.utils;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * author:author${车文飞}
 * data:2019/1/23
 */
public interface MyApiService {
    @GET
    Observable<ResponseBody> get(@Url String url, @QueryMap HashMap<String,Object> map, @HeaderMap HashMap<String,Object> headmap);
    @POST
    Observable<ResponseBody> post(@Url String url, @QueryMap HashMap<String,Object> map, @HeaderMap HashMap<String,Object> headmap);
    @Multipart
    @POST
    Observable<ResponseBody> postFrom(@Url String url, @Part("commentId") int id, @HeaderMap HashMap<String,Object> headmap);
    @DELETE
    Observable<ResponseBody> delete(@Url String url, @QueryMap HashMap<String,Object> map, @HeaderMap HashMap<String,Object> headmap);
    @PUT
    Observable<ResponseBody> put(@Url String url, @QueryMap HashMap<String,Object> map, @HeaderMap HashMap<String,Object> headmap);
    @Multipart
    @POST
    Observable<ResponseBody> upload(@Url String url, @QueryMap HashMap<String,Object> map, @HeaderMap HashMap<String,Object> headMap, @Part MultipartBody.Part part);
    @FormUrlEncoded
    @POST
    Observable<ResponseBody> formPost(@Url String url, @FieldMap HashMap<String,Object> map,@HeaderMap HashMap<String,Object> headMap);


}
