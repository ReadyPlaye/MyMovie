package com.bw.movie.activity.my;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.adapter.MyCinemaSearchAdapter;
import com.bw.movie.adapter.MySearchAdapter;
import com.bw.movie.base.BaseContrat;
import com.bw.movie.base.BaseMvpActivity;
import com.bw.movie.bean.BeanCearch;
import com.bw.movie.bean.BeanCearchCinema;
import com.bw.movie.bean.BeanRegister;
import com.bw.movie.mvp.presenter.IContrat;
import com.bw.movie.mvp.presenter.Presenter;
import com.bw.movie.util.UserUrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyFollowActivity extends BaseMvpActivity<IContrat.IModel, IContrat.IPresenter> implements IContrat.IView {
    @BindView(R.id.iv_my_follow_exit)
    ImageView ivMyFollowExit;
    @BindView(R.id.tv_my_follow_one)
    TextView tvMyFollowOne;
    @BindView(R.id.rlt_my_follow_one)
    RelativeLayout rltMyFollowOne;
    @BindView(R.id.tv_my_follow_two)
    TextView tvMyFollowTwo;
    @BindView(R.id.rlt_my_follow_two)
    RelativeLayout rltMyFollowTwo;
    @BindView(R.id.recy_my_follow)
    RecyclerView recyMyFollow;
    @BindView(R.id.llt_no_network_my_comment)
    LinearLayout lltNoNetworkMyComment;

    //    @Override
    //    protected void onCreate(Bundle savedInstanceState) {
    //        super.onCreate(savedInstanceState);
    //        setContentView(R.layout.activity_my_follow);
    //
    //    }

    @Override
    protected void initLogic() {

    }

    @Override
    protected void initDate() {
        getQuery();
    }

    private void getQuery() {
        tvMyFollowOne.setVisibility(View.VISIBLE);
        tvMyFollowTwo.setVisibility(View.GONE);
        Map<String, Object> map = new HashMap<>();
        map.put("page","1");
        map.put("count","100");
        basePresenter.get(UserUrl.USER_MOVIE_URL, map, BeanCearch.class);
    }
    private void getQuerytwo() {
        tvMyFollowOne.setVisibility(View.GONE);
        tvMyFollowTwo.setVisibility(View.VISIBLE);
        Map<String, Object> map = new HashMap<>();
        map.put("page","1");
        map.put("count","100");
        basePresenter.get(UserUrl.USER_CINEMA_URL, map, BeanCearchCinema.class);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_my_follow;
    }

    @Override
    public BaseContrat.BasePresenter getPresenter() {
        return new Presenter();
    }

    @Override
    public void successful(Object object) {
        if (object != null) {
            if (object instanceof BeanCearch) {
                BeanCearch bean = (BeanCearch) object;
                if (bean.getStatus().equals("0000")) {
                    if (bean.getResult()!=null){
                        List<BeanCearch.ResultBean> list = bean.getResult();
                        MySearchAdapter mySearchAdapter = new MySearchAdapter(this,list);
                        recyMyFollow.setAdapter(mySearchAdapter);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        recyMyFollow.setLayoutManager(linearLayoutManager);
                        lltNoNetworkMyComment.setVisibility(View.GONE);
                    } else {
                        lltNoNetworkMyComment.setVisibility(View.VISIBLE);
                    }
                } else {
                    lltNoNetworkMyComment.setVisibility(View.VISIBLE);
                }
            } else if (object instanceof BeanCearchCinema) {
                BeanCearchCinema bean = (BeanCearchCinema) object;
                if (bean.getStatus().equals("0000")) {
                    if (bean.getResult()!=null){
                        List<BeanCearchCinema.ResultBean> list = bean.getResult();
                        MyCinemaSearchAdapter mySearchAdapter = new MyCinemaSearchAdapter(MyFollowActivity.this, list, 0);
                        recyMyFollow.setAdapter(mySearchAdapter);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyFollowActivity.this);
                        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        recyMyFollow.setLayoutManager(linearLayoutManager);
                        lltNoNetworkMyComment.setVisibility(View.GONE);
                    } else {
                        lltNoNetworkMyComment.setVisibility(View.VISIBLE);
                    }
                } else {
                    lltNoNetworkMyComment.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    @Override
    public void failure(String error) {
        Log.e("------", error);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_my_follow_exit, R.id.rlt_my_follow_one, R.id.rlt_my_follow_two})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_my_follow_exit:
                finish();
                break;
            case R.id.rlt_my_follow_one:
                getQuery();
                break;
            case R.id.rlt_my_follow_two:
                getQuerytwo();
                break;
        }
    }
}
