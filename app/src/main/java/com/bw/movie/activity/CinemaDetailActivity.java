package com.bw.movie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.bw.movie.R;
import com.bw.movie.adapter.MyCinemaAdapter;
import com.bw.movie.adapter.MyCinemaLabelAdapter;
import com.bw.movie.base.BaseContrat;
import com.bw.movie.base.BaseMvpActivity;
import com.bw.movie.bean.BeanCinemaComment;
import com.bw.movie.bean.BeanCinemaInfo;
import com.bw.movie.bean.BeanRegister;
import com.bw.movie.mvp.presenter.IContrat;
import com.bw.movie.mvp.presenter.Presenter;
import com.bw.movie.util.CinemaUrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CinemaDetailActivity extends BaseMvpActivity<IContrat.IModel, IContrat.IPresenter> implements IContrat.IView {
    @BindView(R.id.iv_cinema_detail_exit)
    ImageView ivCinemaDetailExit;
    @BindView(R.id.tv_cinema_detail_name)
    TextView tvCinemaDetailName;
    @BindView(R.id.check_cinema_detail)
    CheckBox checkCinemaDetail;
    @BindView(R.id.rv_cinema_detail_label)
    RecyclerView rvCinemaDetailLabel;
    @BindView(R.id.tv_cinema_detail_detail)
    TextView tvCinemaDetailDetail;
    @BindView(R.id.rlt_cinema_detail_detail)
    RelativeLayout rltCinemaDetailDetail;
    @BindView(R.id.tv_cinema_detail_estimate)
    TextView tvCinemaDetailEstimate;
    @BindView(R.id.rlt_inema_detail_estimate)
    RelativeLayout rltInemaDetailEstimate;
    @BindView(R.id.tv_cinema_detail_city)
    TextView tvCinemaDetailCity;
    @BindView(R.id.tv_cinema_detail_phone)
    TextView tvCinemaDetailPhone;
    @BindView(R.id.tv_cinema_detail)
    TextView tvCinemaDetail;
    @BindView(R.id.rv_cinema_detail)
    RecyclerView rvCinemaDetail;
    @BindView(R.id.btn_cinema_detail)
    Button btnCinemaDetail;
    private String cinemaId;

    //    @Override
    //    protected void onCreate(Bundle savedInstanceState) {
    //        super.onCreate(savedInstanceState);
    //        setContentView(R.layout.activity_cinema_detail);
    //    }

    @Override
    protected void initLogic() {

    }

    @Override
    protected void initDate() {
        rvCinemaDetail.setVisibility(View.GONE);
        tvCinemaDetailDetail.setVisibility(View.VISIBLE);
        tvCinemaDetailEstimate.setVisibility(View.GONE);
        Intent intent = getIntent();
        cinemaId = intent.getStringExtra("cinemaId");
        Map<String, Object> map = new HashMap<>();
        map.put("cinemaId", cinemaId);
        basePresenter.get(CinemaUrl.CINEMA_INFO_URL, map, BeanCinemaInfo.class);
        getQuery();
    }

    private void getQuery() {
        Map<String, Object> map = new HashMap<>();
        map.put("cinemaId", cinemaId);
        map.put("page", "1");
        map.put("count", "10");
        basePresenter.get(CinemaUrl.CINEMA_COMMENT_URL, map, BeanCinemaComment.class);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_cinema_detail;
    }

    @Override
    public BaseContrat.BasePresenter getPresenter() {
        return new Presenter();
    }

    @Override
    public void successful(Object object) {
        if (object != null) {
            if (object instanceof BeanCinemaInfo) {
                BeanCinemaInfo bean = (BeanCinemaInfo) object;
                if (bean.getStatus().equals("0000")) {
                    String name = bean.getResult().getName();
                    String label = bean.getResult().getLabel();
                    int followCinema = bean.getResult().getFollowCinema();
                    String address = bean.getResult().getAddress();
                    String phone = bean.getResult().getPhone();
                    String vehicleRoute = bean.getResult().getVehicleRoute();
                    tvCinemaDetailName.setText(name);
                    tvCinemaDetailCity.setText(address);
                    tvCinemaDetailPhone.setText(phone);
                    tvCinemaDetail.setText(vehicleRoute);
                    String[] split = label.split(",");
                    List<String> list = new ArrayList<>();
                    for (int i = 0; i < split.length; i++) {
                        list.add(split[i]);
                    }
                    MyCinemaLabelAdapter adapter = new MyCinemaLabelAdapter(CinemaDetailActivity.this,list);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CinemaDetailActivity.this);
                    linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                    rvCinemaDetailLabel.setLayoutManager(linearLayoutManager);
                    rvCinemaDetailLabel.setAdapter(adapter);
                    if (followCinema==1){
                        checkCinemaDetail.setChecked(true);
                    } else {
                        checkCinemaDetail.setChecked(false);
                    }
                }
            } else if (object instanceof BeanRegister) {
                BeanRegister bean = (BeanRegister) object;
                if (bean.getStatus().equals("0000")) {
                    checkCinemaDetail.setChecked(!checkCinemaDetail.isChecked());

                }
                ToastUtils.showShort(bean.getMessage());
            } else if (object instanceof BeanCinemaComment) {
                BeanCinemaComment bean = (BeanCinemaComment) object;
                if (bean.getStatus().equals("0000")) {
                    if (bean.getResult()!=null){

                        List<BeanCinemaComment.ResultBean> result = bean.getResult();
                        MyCinemaAdapter adapter = new MyCinemaAdapter(CinemaDetailActivity.this,result);
                        LinearLayoutManager linearManager = new LinearLayoutManager(CinemaDetailActivity.this);
                        linearManager.setOrientation(LinearLayoutManager.VERTICAL);
                        rvCinemaDetail.setLayoutManager(linearManager);
                        rvCinemaDetail.setAdapter(adapter);
                    }
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

    @OnClick({R.id.iv_cinema_detail_exit, R.id.check_cinema_detail, R.id.rlt_cinema_detail_detail, R.id.rlt_inema_detail_estimate, R.id.btn_cinema_detail})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_cinema_detail_exit:
                finish();
                break;
            case R.id.check_cinema_detail:
                if (checkCinemaDetail.isChecked()){
                    getNOFollow();
                    checkCinemaDetail.setChecked(!checkCinemaDetail.isChecked());
                } else {
                    getFollow();
                    checkCinemaDetail.setChecked(!checkCinemaDetail.isChecked());
                }
                break;
            case R.id.rlt_cinema_detail_detail:
                rvCinemaDetail.setVisibility(View.GONE);
                tvCinemaDetailDetail.setVisibility(View.VISIBLE);
                tvCinemaDetailEstimate.setVisibility(View.GONE);
                break;
            case R.id.rlt_inema_detail_estimate:
                rvCinemaDetail.setVisibility(View.VISIBLE);
                tvCinemaDetailDetail.setVisibility(View.GONE);
                tvCinemaDetailEstimate.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_cinema_detail:
                Intent intent = new Intent(CinemaDetailActivity.this, CinemaSchedActivity.class);
                intent.putExtra("cinemaId",cinemaId);
                startActivity(intent);
                break;
        }
    }

    private void getNOFollow() {
        Map<String, Object> map = new HashMap<>();
        map.put("cinemaId", cinemaId);
        basePresenter.get(CinemaUrl.CINEMA_FOLLOW_URL, map, BeanRegister.class);
    }

    private void getFollow() {
        Map<String, Object> map = new HashMap<>();
        map.put("cinemaId", cinemaId);
        basePresenter.get(CinemaUrl.CINEMA_NOFOLLOW_URL, map, BeanRegister.class);
    }
}
