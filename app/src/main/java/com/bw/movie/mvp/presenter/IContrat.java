package com.bw.movie.mvp.presenter;

import com.bw.movie.base.BaseContrat;
import com.bw.movie.bean.BeanRegister;
import com.bw.movie.mvp.model.IModelCallBack;
import com.bw.movie.mvp.model.Model;

import java.util.Map;


public interface IContrat {
    abstract class IPresenter extends BaseContrat.BasePresenter<IModel,IView>{
        @Override
        public IModel getModel() {
            return new Model();
        }
        public abstract void post(String url, Map<String, Object> map, Class aClass);

        public abstract void get(String utl, Map<String, Object> map, Class aClass);

        public abstract void put(String url, Map<String, Object> map, Class aClass);

        public abstract void delete(String utl, Map<String, Object> map, Class aClass);

        public abstract void getpost(String utl, Map<String, Object> map, Class aClass);
    }

    interface IModel extends BaseContrat.IBaseModel {
        void post(String url, Map<String, Object> map, Class aClass, IModelCallBack iModerCallBack);

        void get(String utl, Map<String, Object> map, Class aClass, IModelCallBack iModerCallBack);

        void put(String url, Map<String, Object> map, Class aClass, IModelCallBack iModerCallBack);

        void delete(String url, Map<String, Object> map, Class aClass, IModelCallBack iModerCallBack);

        void getpost(String url, Map<String, Object> map, Class aClass, IModelCallBack iModerCallBack);
    }

    interface IView extends BaseContrat.IBaseView {
    }
}
