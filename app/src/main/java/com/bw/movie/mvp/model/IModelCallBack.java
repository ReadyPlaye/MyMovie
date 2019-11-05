package com.bw.movie.mvp.model;


public interface IModelCallBack<T> {
    void successful(T object);
    void failure(String error);
}
