package com.bw.movie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.adapter.MyCinemaListlistAdapter;
import com.bw.movie.adapter.MyCinemalistAdapter;
import com.bw.movie.base.BaseContrat;
import com.bw.movie.base.BaseMvpActivity;
import com.bw.movie.bean.BeanCearch;
import com.bw.movie.bean.BeanCinemaComment;
import com.bw.movie.bean.BeanDatelist;
import com.bw.movie.event.CinemaListEvent;
import com.bw.movie.mvp.presenter.IContrat;
import com.bw.movie.mvp.presenter.Presenter;
import com.bw.movie.util.CinemaUrl;
import com.bw.movie.util.ToolUrl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CinemaSchedActivity extends BaseMvpActivity<IContrat.IModel, IContrat.IPresenter> implements IContrat.IView {
    @BindView(R.id.iv_cinema_sched_exit)
    ImageView ivCinemaSchedExit;
    @BindView(R.id.recy_cinema_sched)
    RecyclerView recyCinemaSched;
    @BindView(R.id.recy_cinema_schedtwo)
    RecyclerView recyCinemaSchedtwo;
    private String cinemaId;

    //    @Override
    //    protected void onCreate(Bundle savedInstanceState) {
    //        super.onCreate(savedInstanceState);
    //        setContentView(R.layout.activity_cinema_sched);
    //    }

    @Override
    protected void initLogic() {

    }

    @Override
    protected void initDate() {
        EventBus.getDefault().register(this);
        Intent intent = getIntent();
        cinemaId = intent.getStringExtra("cinemaId");
        getQuery();
        getQuerytwo();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void sdfgerfdgf(CinemaListEvent event){
        String tyer = event.tyer;
        if (tyer.equals("时间1")){
            getQuerytwo();
        } else {
            Intent intent = new Intent(CinemaSchedActivity.this, ChoosePayActivity.class);
            intent.putExtra("movieId",tyer);
            intent.putExtra("cinemaId",cinemaId);
            startActivity(intent);
        }
    }

    private void getQuerytwo() {
        Map<String, Object> map = new HashMap<>();
        map.put("cinemaId", cinemaId);
        map.put("page", "1");
        map.put("count", "10");
        basePresenter.get(CinemaUrl.CINEMA_CCHEDULE_URL, map, BeanCearch.class);
    }

    private void getQuery() {
        Map<String, Object> map = new HashMap<>();
        basePresenter.get(ToolUrl.DATELIST_URL, map, BeanDatelist.class);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_cinema_sched;
    }

    @Override
    public BaseContrat.BasePresenter getPresenter() {
        return new Presenter();
    }

    @Override
    public void successful(Object object) {
        if (object != null) {
            if (object instanceof BeanDatelist) {
                BeanDatelist bean = (BeanDatelist) object;
                if (bean.getStatus().equals("0000")) {
                    List<String> list = bean.getResult();
                    MyCinemaListlistAdapter mySearchAdapter = new MyCinemaListlistAdapter(CinemaSchedActivity.this, list);
                    recyCinemaSched.setAdapter(mySearchAdapter);
                    LinearLayoutManager linearManager = new LinearLayoutManager(CinemaSchedActivity.this);
                    linearManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                    recyCinemaSched.setLayoutManager(linearManager);
                }
            } else if (object instanceof BeanCearch) {
                BeanCearch bean = (BeanCearch) object;
                if (bean.getStatus().equals("0000")) {
                    List<BeanCearch.ResultBean> list = bean.getResult();
                    if (list.size()!=0){

                        MyCinemalistAdapter mySearchAdapter = new MyCinemalistAdapter(CinemaSchedActivity.this, list);
                        recyCinemaSchedtwo.setAdapter(mySearchAdapter);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CinemaSchedActivity.this);
                        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        recyCinemaSchedtwo.setLayoutManager(linearLayoutManager);
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

    @OnClick(R.id.iv_cinema_sched_exit)
    public void onViewClicked() {
        finish();
    }
}
