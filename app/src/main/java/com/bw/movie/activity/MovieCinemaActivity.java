package com.bw.movie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.bw.movie.R;
import com.bw.movie.adapter.MyMovieCinemaListAdapter;
import com.bw.movie.adapter.MyMovieCinemaSearchAdapter;
import com.bw.movie.adapter.MyMovieRegionAdapter;
import com.bw.movie.adapter.MyRegionListAdapter;
import com.bw.movie.base.BaseContrat;
import com.bw.movie.base.BaseMvpActivity;
import com.bw.movie.bean.BeanDatelist;
import com.bw.movie.bean.BeanMovieCinema;
import com.bw.movie.bean.BeanRegion;
import com.bw.movie.bean.BeanRegionList;
import com.bw.movie.event.MoviceCinemaEvent;
import com.bw.movie.mvp.presenter.IContrat;
import com.bw.movie.mvp.presenter.Presenter;
import com.bw.movie.util.CinemaUrl;
import com.bw.movie.util.FilmUrl;
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

public class MovieCinemaActivity extends BaseMvpActivity<IContrat.IModel, IContrat.IPresenter> implements IContrat.IView {
    @BindView(R.id.iv_movie_cieman_exit)
    ImageView ivMovieCiemanExit;
    @BindView(R.id.tv_movie_cieman_name)
    TextView tvMovieCiemanName;
    @BindView(R.id.tv_movie_cinema)
    TextView tvMovieCinema;
    @BindView(R.id.rlt_movie_cinema)
    RelativeLayout rltMovieCinema;
    @BindView(R.id.tv_movie_cinema_one)
    TextView tvMovieCinemaOne;
    @BindView(R.id.rlt_movie_cinema_one)
    RelativeLayout rltMovieCinemaOne;
    @BindView(R.id.tv_movie_cinema_two)
    TextView tvMovieCinemaTwo;
    @BindView(R.id.rlt_movie_cinema_two)
    RelativeLayout rltMovieCinemaTwo;
    @BindView(R.id.iv_movie_cinema_search)
    ImageView ivMovieCinemaSearch;
    @BindView(R.id.iv_search_movie_yes)
    ImageView ivSearchMovieYes;
    @BindView(R.id.et_movie_ciema)
    EditText etMovieCiema;
    @BindView(R.id.iv_search_movie_no)
    ImageView ivSearchMovieNo;
    @BindView(R.id.recy_movie_cinema)
    RecyclerView recyMovieCinema;
    @BindView(R.id.rlt_movie_cinema_search)
    RelativeLayout rltMovieCinemaSearch;
    @BindView(R.id.recy_movie_date)
    RecyclerView recyMovieDate;
    @BindView(R.id.rv_movie_regionName)
    RecyclerView rvMovieRegionName;
    @BindView(R.id.rv_mivie_name)
    RecyclerView rvMivieName;
    @BindView(R.id.llt_movie_cinema)
    LinearLayout lltMovieCinema;
    @BindView(R.id.recy_movie_cinema_search)
    RecyclerView recyMovieCinemaSearch;
    @BindView(R.id.llt_no_movie_cinema)
    LinearLayout lltNoMovieCinema;
    private String movieId;
    private String cinemaId;
    private int search = 0;
    private int price = 1;

    //    @Override
    //    protected void onCreate(Bundle savedInstanceState) {
    //        super.onCreate(savedInstanceState);
    //        setContentView(R.layout.activity_movie_cinema);
    //    }

    @Override
    protected void initLogic() {
        lltNoMovieCinema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShort("快点去搜索吧！");
            }
        });
        lltMovieCinema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShort("选择区域");
            }
        });
        rltMovieCinemaSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void initDate() {
        EventBus.getDefault().register(this);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        movieId = intent.getStringExtra("movieId");

        tvMovieCiemanName.setText(name);
        //地区
        tvMovieCinema.setVisibility(View.GONE);
        //时间
        tvMovieCinemaOne.setVisibility(View.GONE);
        //价格
        tvMovieCinemaTwo.setVisibility(View.VISIBLE);
        //输入框
        rltMovieCinemaSearch.setVisibility(View.GONE);
        //时间列表
        recyMovieDate.setVisibility(View.GONE);
        //地区列表
        lltMovieCinema.setVisibility(View.GONE);
        //无搜索结果
        lltNoMovieCinema.setVisibility(View.GONE);
        //搜索的列表
        recyMovieCinemaSearch.setVisibility(View.GONE);
        getQueryTwo();

    }

    private void getQueryone() {
        Map<String, Object> map4 = new HashMap<>();
        map4.put("movieId", movieId);
        map4.put("date", cinemaId);
        map4.put("page", "1");
        map4.put("count", "10");
        basePresenter.get(FilmUrl.MOVIES_CINEMA_INFOBY_FATE_URL, map4, BeanMovieCinema.class);

    }

    private void getQueryTwo() {
        Map<String, Object> map4 = new HashMap<>();
        map4.put("movieId", movieId);
        map4.put("page", "1");
        map4.put("count", "10");
        basePresenter.get(FilmUrl.MOVIES_CINEMA_INFOBY_PRICE_URL, map4, BeanMovieCinema.class);

    }


    private void getQueryTTT(String moviename) {

        //搜索的列表
        recyMovieCinemaSearch.setVisibility(View.VISIBLE);
        Map<String, Object> map = new HashMap<>();
        map.put("cinemaName", moviename);
        map.put("page", "1");
        map.put("count", "10");
        basePresenter.get(CinemaUrl.CINEMA_ALL_URL, map, BeanMovieCinema.class);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void asdfsdfg(MoviceCinemaEvent event) {
        cinemaId = event.cinemaId;
        String tyer = event.tyer;
        if (tyer.equals("1")) {
            getQueryone();
            //时间列表
            recyMovieDate.setVisibility(View.GONE);
            //输入框
            rltMovieCinemaSearch.setVisibility(View.GONE);
        } else if (tyer.equals("2")) {
            Map<String, Object> map4 = new HashMap<>();
            map4.put("regionId", cinemaId);
            basePresenter.get(CinemaUrl.CINEMA_REGION_URL, map4, BeanRegion.class);
            //输入框
            rltMovieCinemaSearch.setVisibility(View.GONE);
        } else if (tyer.equals("3")) {
            Intent intent = new Intent(MovieCinemaActivity.this, ChoosePayActivity.class);
            intent.putExtra("movieId", movieId);
            intent.putExtra("cinemaId", cinemaId);
            startActivity(intent);
        }
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_movie_cinema;
    }

    @Override
    public BaseContrat.BasePresenter getPresenter() {
        return new Presenter();
    }

    @Override
    public void successful(Object object) {
        if (object != null) {
            if (object instanceof BeanRegionList) {
                BeanRegionList bean = (BeanRegionList) object;
                if (bean.getStatus().equals("0000")) {

                    List<BeanRegionList.ResultBean> beanList = bean.getResult();
                    MyRegionListAdapter adapter = new MyRegionListAdapter(MovieCinemaActivity.this, beanList);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(MovieCinemaActivity.this);
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    rvMovieRegionName.setAdapter(adapter);
                    rvMovieRegionName.setLayoutManager(layoutManager);
                }
            } else if (object instanceof BeanRegion) {
                BeanRegion bean = (BeanRegion) object;
                if (bean.getStatus().equals("0000")) {
                    List<BeanRegion.ResultBean> beanList = bean.getResult();
                    MyMovieRegionAdapter adapter = new MyMovieRegionAdapter(MovieCinemaActivity.this, beanList);
                    LinearLayoutManager layonager = new LinearLayoutManager(MovieCinemaActivity.this);
                    layonager.setOrientation(LinearLayoutManager.VERTICAL);
                    rvMivieName.setAdapter(adapter);
                    rvMivieName.setLayoutManager(layonager);
                }
            } else if (object instanceof BeanMovieCinema) {
                BeanMovieCinema bean = (BeanMovieCinema) object;
                if (bean.getStatus().equals("0000")) {
                    if (search == 1) {
                        if (bean.getResult() == null || bean.getResult().size() == 0) {
                            //无搜索结果
                            lltNoMovieCinema.setVisibility(View.VISIBLE);
                        } else {
                            //无搜索结果
                            lltNoMovieCinema.setVisibility(View.GONE);
                            List<BeanMovieCinema.ResultBean> list = bean.getResult();
                            MyMovieCinemaSearchAdapter mySearchAdapter = new MyMovieCinemaSearchAdapter(MovieCinemaActivity.this, list, price);
                            recyMovieCinemaSearch.setAdapter(mySearchAdapter);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MovieCinemaActivity.this);
                            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            recyMovieCinemaSearch.setLayoutManager(linearLayoutManager);
                        }
                    } else {
                        List<BeanMovieCinema.ResultBean> list = bean.getResult();
                        MyMovieCinemaSearchAdapter mySearchAdapter = new MyMovieCinemaSearchAdapter(MovieCinemaActivity.this, list, price);
                        recyMovieCinema.setAdapter(mySearchAdapter);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MovieCinemaActivity.this);
                        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        recyMovieCinema.setLayoutManager(linearLayoutManager);
                    }
                }
            } else if (object instanceof BeanDatelist) {
                BeanDatelist bean = (BeanDatelist) object;
                if (bean.getStatus().equals("0000")) {

                    List<String> list = bean.getResult();
                    MyMovieCinemaListAdapter mySearchAdapter = new MyMovieCinemaListAdapter(MovieCinemaActivity.this, list);
                    recyMovieDate.setAdapter(mySearchAdapter);
                    GridLayoutManager linearManager = new GridLayoutManager(MovieCinemaActivity.this, 2);
                    linearManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyMovieDate.setLayoutManager(linearManager);
                }
            }
        }

    }



    @Override
    public void failure(String error) {
        Log.e("-------", error);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_movie_cieman_exit, R.id.rlt_movie_cinema, R.id.rlt_movie_cinema_one, R.id.rlt_movie_cinema_two, R.id.iv_movie_cinema_search, R.id.iv_search_movie_yes, R.id.iv_search_movie_no})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_movie_cieman_exit:
                finish();
                break;
            case R.id.rlt_movie_cinema:
                search = 0;
                price = 0;
                //地区
                tvMovieCinema.setVisibility(View.VISIBLE);
                //时间
                tvMovieCinemaOne.setVisibility(View.GONE);
                //价格
                tvMovieCinemaTwo.setVisibility(View.GONE);
                //时间列表
                recyMovieDate.setVisibility(View.GONE);
                //地区列表
                lltMovieCinema.setVisibility(View.VISIBLE);
                Map<String, Object> map3 = new HashMap<>();
                basePresenter.get(ToolUrl.REGION_LIST_URL, map3, BeanRegionList.class);
                break;
            case R.id.rlt_movie_cinema_one:
                search = 0;
                price = 0;
                //地区
                tvMovieCinema.setVisibility(View.GONE);
                //时间
                tvMovieCinemaOne.setVisibility(View.VISIBLE);
                //价格
                tvMovieCinemaTwo.setVisibility(View.GONE);
                //时间列表
                recyMovieDate.setVisibility(View.VISIBLE);
                //地区列表
                lltMovieCinema.setVisibility(View.GONE);
                Map<String, Object> map = new HashMap<>();
                basePresenter.get(ToolUrl.DATELIST_URL, map, BeanDatelist.class);
                break;
            case R.id.rlt_movie_cinema_two:
                search = 0;
                price = 1;
                //地区
                tvMovieCinema.setVisibility(View.GONE);
                //时间
                tvMovieCinemaOne.setVisibility(View.GONE);
                //价格
                tvMovieCinemaTwo.setVisibility(View.VISIBLE);
                //时间列表
                recyMovieDate.setVisibility(View.GONE);
                //地区列表
                lltMovieCinema.setVisibility(View.GONE);
                getQueryTwo();
                break;
            case R.id.iv_movie_cinema_search:
                //输入框
                rltMovieCinemaSearch.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_search_movie_yes:
                search = 1;
                price = 0;
                String moviename = etMovieCiema.getText().toString().trim();
                if (moviename.isEmpty()) {
                    ToastUtils.showShort("请输入搜索内容！");
                } else {
                    getQueryTTT(moviename);
                }
                break;
            case R.id.iv_search_movie_no:
                search = 0;
                etMovieCiema.setText("");
                //输入框
                rltMovieCinemaSearch.setVisibility(View.GONE);
                //搜索的列表
                recyMovieCinemaSearch.setVisibility(View.GONE);
                //无搜索结果
                lltNoMovieCinema.setVisibility(View.GONE);
                break;
        }
    }
}
