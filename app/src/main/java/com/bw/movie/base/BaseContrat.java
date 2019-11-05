package com.bw.movie.base;

import java.lang.ref.WeakReference;


public interface BaseContrat {

    abstract class BasePresenter<M,V>{

        public M baseModel;
        public V baseView;
        private WeakReference<V> weakReference;

        public abstract M getModel();

        public void Attach(M baseModel, V baseView){
            this.baseModel=baseModel;
            weakReference = new WeakReference<>(baseView);
            this.baseView=weakReference.get();

        }
        //清除
        public void onDestroy(){
            if (weakReference!=null){
                weakReference.clear();
                weakReference=null;
                baseView=null;
            }
        }
    }
    //view接口
    interface IBaseView<T>{
        BasePresenter getPresenter();
        void successful(T object);
        void failure(String error);
    }
    //model接口
    interface IBaseModel{

    }
}
