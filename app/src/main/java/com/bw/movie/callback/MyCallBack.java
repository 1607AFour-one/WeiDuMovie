package com.bw.movie.callback;

/**
 * author:author${车文飞}
 * data:2019/1/23
 */
public interface MyCallBack<T> {
    void setData(T data);
    void setError(T error);
}
