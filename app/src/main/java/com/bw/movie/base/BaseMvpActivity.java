package com.bw.movie.base;

import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.bw.movie.util.HttpRetrofitUtil;



public abstract class BaseMvpActivity<M extends BaseContrat.IBaseModel,P extends BaseContrat.BasePresenter> extends BaseActivity implements BaseContrat.IBaseView {

    public P basePresenter;
    public M baseModel;

    //绑定
    @Override
    public void init() {
        boolean isnetwork = HttpRetrofitUtil.get().isnetwork(this);
        if (!isnetwork){
            ToastUtils.showShort("未连接网络");
        }
        basePresenter = (P) getPresenter();
        if (basePresenter!=null){
            baseModel = (M) basePresenter.getModel();
            if (baseModel!=null){
                basePresenter.Attach(baseModel,this);
            }
        }
    }
    //清除
    @Override
    protected void onDestroy() {
        super.onDestroy();
        basePresenter.onDestroy();
    }
}
