package com.bw.movie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.base.BaseContrat;
import com.bw.movie.base.BaseMvpActivity;
import com.bw.movie.bean.BeanDetails;
import com.bw.movie.bean.BeanLogin;
import com.bw.movie.mvp.presenter.IContrat;
import com.bw.movie.mvp.presenter.Presenter;
import com.bw.movie.util.FilmUrl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailsActivity extends BaseMvpActivity<IContrat.IModel, IContrat.IPresenter> implements IContrat.IView {
    @BindView(R.id.tv_details_moviescore)
    TextView tvDetailsMoviescore;
    @BindView(R.id.tv_details_moviecommentnum)
    TextView tvDetailsMoviecommentnum;
    @BindView(R.id.check_whether_follow)
    CheckBox checkWhetherFollow;
    @BindView(R.id.tv_details_moviename)
    TextView tvDetailsMoviename;
    @BindView(R.id.tv_details_movietype)
    TextView tvDetailsMovietype;
    @BindView(R.id.tv_details_movietime)
    TextView tvDetailsMovietime;
    @BindView(R.id.btn_moviecomment)
    Button btnMoviecomment;
    @BindView(R.id.btn_buy_ticket)
    Button btnBuyTicket;
    @BindView(R.id.tv_whether_follow)
    TextView tvWhetherFollow;
    @BindView(R.id.iv_details_backdrop)
    ImageView ivDetailsBackdrop;
    @BindView(R.id.tv_details_exit)
    ImageView tvDetailsExit;
    private String movieId;
    private String name;

    //    @Override
    //    protected void onCreate(Bundle savedInstanceState) {
    //        super.onCreate(savedInstanceState);
    //        setContentView(R.layout.activity_details);
    //    }

    @Override
    protected void initLogic() {


    }

    @Override
    protected void initDate() {
        Intent intent = getIntent();
        movieId = intent.getStringExtra("movieId");
        //        Log.e("-----", "++++" + movieId);
        //        ToastUtils.showShort("++" + movieId);
        Map<String, Object> map = new HashMap<>();
        map.put("movieId", movieId);
        basePresenter.get(FilmUrl.MOVIES_DETAIL_URL, map, BeanDetails.class);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_details;
    }

    @Override
    public BaseContrat.BasePresenter getPresenter() {
        return new Presenter();
    }

    @Override
    public void successful(Object object) {
        if (object != null) {
            if (object instanceof BeanDetails) {
                BeanDetails bean = (BeanDetails) object;
                if (bean.getStatus().equals("0000")) {
                    int commentNum = bean.getResult().getCommentNum();
                    String imageUrl = bean.getResult().getImageUrl();
                    String duration = bean.getResult().getDuration();
                    String movieType = bean.getResult().getMovieType();
                    name = bean.getResult().getName();
                    double score = bean.getResult().getScore();
                    long releaseTime = bean.getResult().getReleaseTime();
                    int whetherFollow = bean.getResult().getWhetherFollow();
                    String format = "yyyy-MM-dd";
                    SimpleDateFormat dateFormat = new SimpleDateFormat(format);
                    String time = dateFormat.format(releaseTime);
                    String placeOrigin = bean.getResult().getPlaceOrigin();
                    tvDetailsMoviename.setText(name);
                    tvDetailsMoviecommentnum.setText("评论  " + commentNum + "条");
                    tvDetailsMoviescore.setText("评分  " + score + "分");
                    tvDetailsMovietype.setText(movieType + "  " + duration);
                    tvDetailsMovietime.setText(time + "  " + placeOrigin);
                    Glide.with(this).load(imageUrl).into(ivDetailsBackdrop);
                    if (whetherFollow == 1) {
                        checkWhetherFollow.setChecked(true);
                        tvWhetherFollow.setText("已关注");
                    } else {
                        checkWhetherFollow.setChecked(false);
                        tvWhetherFollow.setText("关注");
                    }
                }
            } else  if (object instanceof BeanLogin) {
                BeanLogin bean = (BeanLogin) object;
                if (bean.getStatus().equals("0000")) {
                    checkWhetherFollow.setChecked(checkWhetherFollow.isChecked());
                } else {
                    checkWhetherFollow.setChecked(!checkWhetherFollow.isChecked());
                }
                ToastUtils.showShort(bean.getMessage());
                if (checkWhetherFollow.isChecked()) {
                    tvWhetherFollow.setText("已关注");
               } else {
                    tvWhetherFollow.setText("关注");
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

    @OnClick({R.id.check_whether_follow, R.id.btn_moviecomment, R.id.btn_buy_ticket, R.id.tv_details_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.check_whether_follow:
                if (checkWhetherFollow.isChecked()) {
//                    tvWhetherFollow.setText("已关注");
                    Map<String, Object> map = new HashMap<>();
                    map.put("movieId", movieId);
                    basePresenter.get(FilmUrl.MOVIES_FOLLOW_URL, map, BeanLogin.class);
                } else {
//                    tvWhetherFollow.setText("关注");
                    Map<String, Object> map = new HashMap<>();
                    map.put("movieId", movieId);
                    basePresenter.get(FilmUrl.MOVIES_NO_FOLLOW_URL, map, BeanLogin.class);
                }
                break;
            case R.id.btn_moviecomment:
                break;
            case R.id.btn_buy_ticket:
                Intent intent = new Intent(DetailsActivity.this, MovieCinemaActivity.class);
                intent.putExtra("movieId",movieId);
                intent.putExtra("name",name);
                startActivity(intent);
                break;
            case R.id.tv_details_exit:
                finish();
                break;
        }
    }
}
