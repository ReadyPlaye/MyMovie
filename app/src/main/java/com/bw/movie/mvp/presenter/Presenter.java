package com.bw.movie.mvp.presenter;

import com.bw.movie.mvp.model.IModelCallBack;

import java.util.Map;


public class Presenter extends IContrat.IPresenter {



    public void get(String url, Map<String, Object> map, Class aClass) {
        baseModel.get(url,map,aClass, new IModelCallBack() {
            @Override
            public void successful(Object object) {
                baseView.successful(object);
            }

            @Override
            public void failure(String error) {
                baseView.failure(error);
            }

        });
    }
    @Override
    public void post(String url, Map<String, Object> map, Class aClass) {
        baseModel.post(url,map,aClass, new IModelCallBack() {
            @Override
            public void successful(Object object) {
                baseView.successful(object);
            }

            @Override
            public void failure(String error) {
                baseView.failure(error);
            }

        });

    }

    @Override
    public void put(String url, Map<String, Object> map, Class aClass) {
        baseModel.put(url,map,aClass, new IModelCallBack() {
            @Override
            public void successful(Object object) {
                baseView.successful(object);
            }

            @Override
            public void failure(String error) {
                baseView.failure(error);
            }

        });
    }

    @Override
    public void delete(String url, Map<String, Object> map, Class aClass) {
        baseModel.delete(url,map,aClass, new IModelCallBack() {
            @Override
            public void successful(Object object) {
                baseView.successful(object);
            }

            @Override
            public void failure(String error) {
                baseView.failure(error);
            }

        });
    }

    @Override
    public void getpost(String url, Map<String, Object> map, Class aClass) {
        baseModel.getpost(url,map,aClass, new IModelCallBack() {
            @Override
            public void successful(Object object) {
                baseView.successful(object);
            }

            @Override
            public void failure(String error) {
                baseView.failure(error);
            }

        });
    }
}
