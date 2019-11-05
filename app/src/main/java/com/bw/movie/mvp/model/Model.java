package com.bw.movie.mvp.model;

import android.util.Log;

import com.blankj.utilcode.util.ToastUtils;
import com.bw.movie.mvp.presenter.IContrat;
import com.bw.movie.util.HttpRetrofitUtil;
import com.bw.movie.util.WayApi;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;


public class Model implements IContrat.IModel {
    public void get(String url, Map<String, Object> map, Class aClass, IModelCallBack iModelCallBack) {
        HttpRetrofitUtil.get().create(WayApi.class).get(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String string = null;
                        try {
                            string = responseBody.string();
//                            Log.e("--------",string);
                            Gson gson = new Gson();
                            Object o = gson.fromJson(string, aClass);
                            iModelCallBack.successful(o);
                        }catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        iModelCallBack.failure(throwable.getMessage());
                    }
                });
    }

    public void post(String url, Map<String, Object> map, Class aClass, IModelCallBack iModelCallBack) {
        HttpRetrofitUtil.get().create(WayApi.class).post(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String string = null;
                        try {
                            string = responseBody.string();
//                            ToastUtils.showShort(string);
//                            Log.e("--------",string);
                            Gson gson = new Gson();
                            Object o = gson.fromJson(string, aClass);
                            iModelCallBack.successful(o);
                        }catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        iModelCallBack.failure(throwable.getMessage());
                    }
                });
    }
    public void put(String url, Map<String, Object> map, Class aClass, IModelCallBack iModelCallBack) {
        HttpRetrofitUtil.get().create(WayApi.class).put(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String string = null;
                        try {
                            string = responseBody.string();
 //                            Log.e("--------",string);
                            Gson gson = new Gson();
                            Object o = gson.fromJson(string, aClass);
                            iModelCallBack.successful(o);
                        }catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        iModelCallBack.failure(throwable.getMessage());
                    }
                });
    }
    public void delete(String url, Map<String, Object> map, Class aClass, IModelCallBack iModelCallBack) {
        HttpRetrofitUtil.get().create(WayApi.class).delete(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String string = null;
                        try {
                            string = responseBody.string();
 //                            Log.e("--------",string);
                            Gson gson = new Gson();
                            Object o = gson.fromJson(string, aClass);
                            iModelCallBack.successful(o);
                        }catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        iModelCallBack.failure(throwable.getMessage());
                    }
                });
    }

    @Override
    public void getpost(String url, Map<String, Object> map, Class aClass, IModelCallBack iModerCallBack) {
        HttpRetrofitUtil.get().create(WayApi.class).getpost(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String string = null;
                        try {
                            string = responseBody.string();
                            //                            Log.e("--------",string);
                            Gson gson = new Gson();
                            Object o = gson.fromJson(string, aClass);
                            iModerCallBack.successful(o);
                        }catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        iModerCallBack.failure(throwable.getMessage());
                    }
                });
    }
}
