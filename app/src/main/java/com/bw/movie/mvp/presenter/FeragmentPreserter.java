package com.bw.movie.mvp.presenter;

import com.bw.movie.mvp.model.IModelCallBack;
import com.bw.movie.mvp.model.Model;

import java.util.Map;


public class FeragmentPreserter {
    private IContrat.IView iview;
    private final IContrat.IModel molder;

    public FeragmentPreserter(IContrat.IView iview){
        this.iview=iview;
        molder = new Model();
    }

    public void onDestroyView() {
        if (iview!=null){
            iview=null;
        }
    }

    public void get(String utl, Map<String, Object> map, Class aClass) {
        molder.get(utl,map,aClass, new IModelCallBack() {
            @Override
            public void successful(Object object) {
                iview.successful(object);
            }

            @Override
            public void failure(String error) {
                iview.failure(error);
            }
        });
    }

    public void post(String url, Map<String, Object> map, Class aClass) {
        molder.post(url,map,aClass, new IModelCallBack() {
            @Override
            public void successful(Object object) {
                iview.successful(object);
            }

            @Override
            public void failure(String error) {
                iview.failure(error);
            }
        });
    }
}
