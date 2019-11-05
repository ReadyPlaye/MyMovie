package com.bw.movie.base;

import android.view.View;
import android.widget.Toast;

import com.bw.movie.util.HttpRetrofitUtil;


public abstract class BaseMvpFragment<M extends BaseContrat.IBaseModel,P extends BaseContrat.BasePresenter> extends BaseFragment implements BaseContrat.IBaseView {
    public P basePresenter;
    public M baseModel;


    @Override
    protected void init(View view) {
        boolean isnetwork = HttpRetrofitUtil.get().isnetwork(view.getContext());
        if (!isnetwork){
            Toast.makeText(view.getContext(), "未连接网络", Toast.LENGTH_SHORT).show();
        }
        basePresenter = (P) getPresenter();
        if (basePresenter!=null){
            baseModel = (M) basePresenter.getModel();
            if (baseModel!=null){
                basePresenter.Attach(baseModel,this);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        basePresenter.onDestroy();
    }
}
