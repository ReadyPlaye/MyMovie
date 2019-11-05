package com.bw.movie.activity.my;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bw.movie.R;
import com.bw.movie.adapter.MySearchAdapter;
import com.bw.movie.adapter.my.MyReserveMyAdapter;
import com.bw.movie.base.BaseContrat;
import com.bw.movie.base.BaseMvpActivity;
import com.bw.movie.bean.BeanMyReserve;
import com.bw.movie.mvp.presenter.IContrat;
import com.bw.movie.mvp.presenter.Presenter;
import com.bw.movie.util.UserUrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyReserveActivity extends BaseMvpActivity<IContrat.IModel, IContrat.IPresenter> implements IContrat.IView {

    @BindView(R.id.iv_my_reserve_exit)
    ImageView ivMyReserveExit;
    @BindView(R.id.recy_my_reserve)
    RecyclerView recyMyReserve;
    @BindView(R.id.llt_no_network_my_reserve)
    LinearLayout lltNoNetworkMyReserve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reserve);
        ButterKnife.bind(this);
    }

    @Override
    protected void initLogic() {

    }

    @Override
    protected void initDate() {
        getQuery();
    }

    private void getQuery() {
        Map<String, Object> map = new HashMap<>();
        basePresenter.get(UserUrl.USER_RESERVE_URL, map, BeanMyReserve.class);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_my_reserve;
    }

    @Override
    public BaseContrat.BasePresenter getPresenter() {
        return new Presenter();
    }

    @Override
    public void successful(Object object) {
        if (object != null) {
            if (object instanceof BeanMyReserve) {
                BeanMyReserve bean = (BeanMyReserve) object;
                if (bean.getStatus().equals("0000")) {
                    if (bean.getResult()!=null) {
                        List<BeanMyReserve.ResultBean> list = bean.getResult();
                        MyReserveMyAdapter mySearchAdapter = new MyReserveMyAdapter(MyReserveActivity.this, list);
                        recyMyReserve.setAdapter(mySearchAdapter);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyReserveActivity.this);
                        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        recyMyReserve.setLayoutManager(linearLayoutManager);
                        lltNoNetworkMyReserve.setVisibility(View.GONE);
                    } else {
                        lltNoNetworkMyReserve.setVisibility(View.VISIBLE);
                    }
                } else {
                    lltNoNetworkMyReserve.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    @Override
    public void failure(String error) {
        Log.e("------", error);
    }

    @OnClick(R.id.iv_my_reserve_exit)
    public void onViewClicked() {
        finish();
    }
}
