package com.bw.movie.view;

/**
 * author:author${车文飞}
 * data:2019/1/23
 */
public interface IView<T> {
    void successData(T data);
    void errorMsg(T error);
}
