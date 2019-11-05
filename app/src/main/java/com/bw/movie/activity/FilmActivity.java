package com.bw.movie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.bw.movie.R;
import com.bw.movie.adapter.MyComingAdapter;
import com.bw.movie.adapter.MyFilmSearchAdapter;
import com.bw.movie.adapter.MyHotAdapter;
import com.bw.movie.adapter.MySearchAdapter;
import com.bw.movie.base.BaseContrat;
import com.bw.movie.base.BaseMvpActivity;
import com.bw.movie.bean.BeamRelease;
import com.bw.movie.bean.BeanCearch;
import com.bw.movie.bean.BeanComing;
import com.bw.movie.bean.BeanHot;
import com.bw.movie.mvp.presenter.IContrat;
import com.bw.movie.mvp.presenter.Presenter;
import com.bw.movie.util.FilmUrl;
import com.bw.movie.util.HttpRetrofitUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FilmActivity extends BaseMvpActivity<IContrat.IModel, IContrat.IPresenter> implements IContrat.IView {
    @BindView(R.id.tv_film_exit)
    ImageView tvFilmExit;
    @BindView(R.id.et_h)
    EditText etH;
    @BindView(R.id.iv_film_search)
    ImageView ivFilmSearch;
    @BindView(R.id.tv_film_release)
    TextView tvFilmRelease;
    @BindView(R.id.rlt_film_release)
    RelativeLayout rltFilmRelease;
    @BindView(R.id.tv_film_coming)
    TextView tvFilmComing;
    @BindView(R.id.rlt_film_coming)
    RelativeLayout rltFilmComing;
    @BindView(R.id.tv_film_hot)
    TextView tvFilmHot;
    @BindView(R.id.rlt_film_hot)
    RelativeLayout rltFilmHot;
    @BindView(R.id.recy_search_film)
    RecyclerView recySearchFilm;
    @BindView(R.id.llt_no_network_film)
    LinearLayout lltNoNetworkFilm;
    @BindView(R.id.llt_no_search_film)
    LinearLayout lltNoSearchFilm;

    //    @Override
    //    protected void onCreate(Bundle savedInstanceState) {
    //        super.onCreate(savedInstanceState);
    //        setContentView(R.layout.activity_film);
    //    }

    @Override
    protected void initLogic() {
        etH.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String s1 = s.toString();
                Log.e("--------","3"+s1);
                if (s1.isEmpty()){
                    etH.setVisibility(View.GONE);
                    ivFilmSearch.setVisibility(View.VISIBLE);
                    recySearchFilm.setVisibility(View.VISIBLE);
                    lltNoSearchFilm.setVisibility(View.GONE);
                    lltNoNetworkFilm.setVisibility(View.GONE);
                    estimateTwort("");
                } else {
                    estimateTwort(s1);
                }
            }
        });


    }
    private void estimateTwort(String s) {
        boolean isnetwork = HttpRetrofitUtil.get().isnetwork(this);
        if (!isnetwork) {
//            etH.setVisibility(View.GONE);
            recySearchFilm.setVisibility(View.GONE);
            lltNoSearchFilm.setVisibility(View.GONE);
            lltNoNetworkFilm.setVisibility(View.VISIBLE);
            ToastUtils.showShort("请链接网络！");
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("keyword", s);
            map.put("page", "1");
            map.put("count", "10");
            basePresenter.get(FilmUrl.MOVIES_KETWORD_URL, map, BeanCearch.class);
        }
    }
    private void estimateYESSearch() {
//        etSearch.setVisibility(View.VISIBLE);
        recySearchFilm.setVisibility(View.VISIBLE);
        lltNoSearchFilm.setVisibility(View.GONE);
        lltNoNetworkFilm.setVisibility(View.GONE);
    }

    private void estimateNOSearch() {
//        etSearch.setVisibility(View.VISIBLE);
        recySearchFilm.setVisibility(View.GONE);
        lltNoSearchFilm.setVisibility(View.VISIBLE);
        lltNoNetworkFilm.setVisibility(View.GONE);
    }

    @Override
    protected void initDate() {
        etH.setVisibility(View.GONE);
        Intent intent = getIntent();
        String head = intent.getStringExtra("head");
        getDate(head);

    }

    private void getDate(String head) {
        boolean isnetwork = HttpRetrofitUtil.get().isnetwork(this);
        if (!isnetwork) {
            recySearchFilm.setVisibility(View.GONE);
            lltNoSearchFilm.setVisibility(View.GONE);
            lltNoNetworkFilm.setVisibility(View.VISIBLE);
            ToastUtils.showShort("请链接网络！");
        } else {
            recySearchFilm.setVisibility(View.VISIBLE);
            lltNoSearchFilm.setVisibility(View.GONE);
            lltNoNetworkFilm.setVisibility(View.GONE);
            Map<String, Object> map1 = new HashMap<>();
            map1.put("page", "1");
            map1.put("count", "10");
            if (head.indexOf("0") != -1) {
                tvFilmRelease.setVisibility(View.VISIBLE);
                tvFilmComing.setVisibility(View.GONE);
                tvFilmHot.setVisibility(View.GONE);
                //正在上映
                basePresenter.get(FilmUrl.RELEASE_URL, map1, BeamRelease.class);
            } else if (head.indexOf("1") != -1) {
                tvFilmRelease.setVisibility(View.GONE);
                tvFilmComing.setVisibility(View.VISIBLE);
                tvFilmHot.setVisibility(View.GONE);
                //即将上映
                basePresenter.get(FilmUrl.COMING_URL, map1, BeanComing.class);
            } else if (head.indexOf("2") != -1) {
                tvFilmRelease.setVisibility(View.GONE);
                tvFilmComing.setVisibility(View.GONE);
                tvFilmHot.setVisibility(View.VISIBLE);
                //热门电影
                basePresenter.get(FilmUrl.HOT_URL, map1, BeanHot.class);
            }
        }

    }

    @Override
    protected int initLayout() {
        return R.layout.activity_film;
    }

    @Override
    public BaseContrat.BasePresenter getPresenter() {
        return new Presenter();
    }

    @Override
    public void successful(Object object) {
        if (object != null) {
            //正在上映
            if (object instanceof BeamRelease) {
                BeamRelease bean = (BeamRelease) object;
                if (bean.getStatus().equals("0000")) {
                    List<BeamRelease.ResultBean> list = bean.getResult();
                    MyFilmSearchAdapter mySearchAdapter = new MyFilmSearchAdapter(this, list);
                    recySearchFilm.setAdapter(mySearchAdapter);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recySearchFilm.setLayoutManager(linearLayoutManager);

                }

                //即将上映
            } else if (object instanceof BeanComing) {
                BeanComing bean = (BeanComing) object;
                if (bean.getStatus().equals("0000")) {
                    List<BeanComing.ResultBean> list = bean.getResult();
                    MyComingAdapter comingAdapter = new MyComingAdapter(this, list);
                    recySearchFilm.setAdapter(comingAdapter);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recySearchFilm.setLayoutManager(layoutManager);

                }

                //热门电影
            } else if (object instanceof BeanHot) {
                BeanHot bean = (BeanHot) object;
                if (bean.getStatus().equals("0000")) {
                    List<BeanHot.ResultBean> list = bean.getResult();
                    MyHotAdapter hotAdapter = new MyHotAdapter(this, list);
                    recySearchFilm.setAdapter(hotAdapter);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
                    gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recySearchFilm.setLayoutManager(gridLayoutManager);
                }

            } else if (object instanceof BeanCearch) {
                BeanCearch bean = (BeanCearch) object;
                if (bean.getStatus().equals("0000")) {
                    String message = bean.getMessage();
                    int index = message.indexOf("查询成功");
                    if (index==-1){
                        estimateNOSearch();
                    } else {
                        estimateYESSearch();
                        List<BeanCearch.ResultBean> list = bean.getResult();
                        MySearchAdapter mySearchAdapter = new MySearchAdapter(this,list);
                        recySearchFilm.setAdapter(mySearchAdapter);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        recySearchFilm.setLayoutManager(linearLayoutManager);
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

    @OnClick({R.id.tv_film_exit, R.id.iv_film_search, R.id.rlt_film_release, R.id.rlt_film_coming, R.id.rlt_film_hot})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_film_exit:
                finish();
                break;
            case R.id.iv_film_search:
                ivFilmSearch.setVisibility(View.GONE);
                etH.setVisibility(View.VISIBLE);
                break;
            case R.id.rlt_film_release:
                etH.setText("");
                etH.setVisibility(View.GONE);
                ivFilmSearch.setVisibility(View.VISIBLE);
                getDate("0");
                break;
            case R.id.rlt_film_coming:
                etH.setText("");
                etH.setVisibility(View.GONE);
                ivFilmSearch.setVisibility(View.VISIBLE);
                getDate("1");
                break;
            case R.id.rlt_film_hot:
                etH.setText("");
                etH.setVisibility(View.GONE);
                ivFilmSearch.setVisibility(View.VISIBLE);
                getDate("2");
                break;
        }
    }
}
