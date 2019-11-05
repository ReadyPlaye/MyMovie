package com.bw.movie.activity.my;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.blankj.utilcode.util.ToastUtils;
import com.bw.movie.R;
import com.bw.movie.base.BaseContrat;
import com.bw.movie.base.BaseMvpActivity;
import com.bw.movie.bean.BeanRegister;
import com.bw.movie.mvp.presenter.IContrat;
import com.bw.movie.mvp.presenter.Presenter;
import com.bw.movie.util.HttpRetrofitUtil;
import com.bw.movie.util.UserUrl;

import java.util.HashMap;
import java.util.Map;

public class MyCommentActivity extends BaseMvpActivity<IContrat.IModel, IContrat.IPresenter> implements IContrat.IView {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_my_comment);
//    }

    @Override
    protected void initLogic() {

    }

    @Override
    protected void initDate() {
//        getQuery();
//        getNetwork();
    }

    private void getNetwork() {
        boolean isnetwork = HttpRetrofitUtil.get().isnetwork(this);
        if (!isnetwork){
            //显示
            ToastUtils.showShort("没有网络");
        } else {
            //隐藏


        }
    }

    private void getQuery() {
        Map<String,Object> map = new HashMap<>();
        basePresenter.get(UserUrl.BASE_URL,map, BeanRegister.class);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_my_comment;
    }

    @Override
    public BaseContrat.BasePresenter getPresenter() {
        return new Presenter();
    }

    @Override
    public void successful(Object object) {
        if (object!=null){
            if (object instanceof BeanRegister){
                BeanRegister bean = (BeanRegister) object;
                if (bean.getStatus().equals("0000")){

                }
            }
        }

    }

    @Override
    public void failure(String error) {
        Log.e("------", error);
    }
}
