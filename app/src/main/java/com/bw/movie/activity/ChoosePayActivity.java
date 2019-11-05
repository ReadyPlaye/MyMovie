package com.bw.movie.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bw.movie.R;
import com.bw.movie.adapter.MyChooseHallAdapter;
import com.bw.movie.adapter.MyScheduleRowAdapter;
import com.bw.movie.base.BaseContrat;
import com.bw.movie.base.BaseMvpActivity;
import com.bw.movie.base.EncryptUtil;
import com.bw.movie.bean.BeanDetails;
import com.bw.movie.bean.BeanMovieSchedule;
import com.bw.movie.bean.BeanMovieTickets;
import com.bw.movie.bean.BeanSchedue;
import com.bw.movie.event.ChooseHallEvent;
import com.bw.movie.event.MovieTicketsEvent;
import com.bw.movie.mvp.presenter.IContrat;
import com.bw.movie.mvp.presenter.Presenter;
import com.bw.movie.util.FilmUrl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChoosePayActivity extends BaseMvpActivity<IContrat.IModel, IContrat.IPresenter> implements IContrat.IView {
    @BindView(R.id.iv_choose_pay_exit)
    ImageView ivChoosePayExit;
    @BindView(R.id.tv_choose_pay_name)
    TextView tvChoosePayName;
    @BindView(R.id.tv_choose_hall_nul)
    TextView tvChooseHallNul;
    @BindView(R.id.recy_choose_hallId)
    RecyclerView recyChooseHallId;
    @BindView(R.id.tv_choose_no_movie)
    TextView tvChooseNoMovie;
    @BindView(R.id.btn_choose_price)
    Button btnChoosePrice;
    @BindView(R.id.recy_choose_schedule)
    RecyclerView recyChooseSchedule;
    private String cinemaId;
    private String movieId;
    private boolean bto = false;
    private String s = "";
    ArrayList<String> strings = new ArrayList<>();
    private boolean rfgh = false;
    private String scheduleId;
    private double fare;
    private String seatstring;
    private String smd5;
    private double v;
    //
    //    @Override
    //    protected void onCreate(Bundle savedInstanceState) {
    //        super.onCreate(savedInstanceState);
    //        setContentView(R.layout.activity_choose_pay);
    //    }

    @Override
    protected void initLogic() {


    }

    @Override
    protected void initDate() {
        EventBus.getDefault().register(this);
        tvChooseNoMovie.setVisibility(View.GONE);

        Intent intent = getIntent();
        cinemaId = intent.getStringExtra("cinemaId");
        movieId = intent.getStringExtra("movieId");
        Map<String, Object> map = new HashMap<>();
        map.put("movieId", movieId);
        basePresenter.get(FilmUrl.MOVIES_DETAIL_URL, map, BeanDetails.class);
        getQuery();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void sfdggesdgjn(ChooseHallEvent event) {
        strings.clear();
        s="";
        btnChoosePrice.setText("请先选座");
        btnChoosePrice.setBackgroundColor(Color.parseColor("#ff72a1"));
        String hallId = event.hallId;
        scheduleId = event.scheduleId;
        fare = event.fare;
        Map<String, Object> map1 = new HashMap<>();
        map1.put("hallId", hallId);
        basePresenter.get(FilmUrl.SEATINFO_URL, map1, BeanMovieSchedule.class);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void sfdgggjn(MovieTicketsEvent event) {
        String seat = event.seat;
        String stor = event.stor;
        if (stor.equals("3")){
            s=seat+","+s;
//            Log.e("--------3s",s+"s");
            strings.clear();
            String[] split = s.split(",");
//            Log.e("--------1split.length",split.length+"split");
            for (int i = 0; i < split.length; i++) {
                strings.add(i,split[i]);
//                Log.e("--------list",strings.get(i));
            }
        } else if (stor.equals("1")){
//            Log.e("--------1s",s+"s");
            strings.clear();
            String[] split = s.split(",");
//            Log.e("--------1split.length",split.length+"split");
            for (int i = 0; i < split.length; i++) {
                strings.add(i,split[i]);
//                Log.e("--------list",strings.get(i));
            }
//            Log.e("--------strings.size()",strings.size()+"k");
            for (int i = 0; i < strings.size(); i++) {
                if (strings.get(i).equals(seat)){
                    strings.remove(i);
                }
            }
//            strings.remove(seat);
            s="";
            for (int i = 0; i < strings.size(); i++) {
//                Log.e("--------strings.get(i)",strings.get(i)+"s");
                s=strings.get(i)+","+s;
            }
        }
        if (s==""){
            Log.e("--------sss",strings.size()+"没有了");
            Log.e("--------sss",s+"没有了");
            rfgh=false;
            btnChoosePrice.setText("请先选座");
            btnChoosePrice.setBackgroundColor(Color.parseColor("#ff72a1"));
        } else {
            rfgh=true;
            String userId = SPUtils.getInstance().getString("userId");
            String usmmd5 = userId+"" + scheduleId + "movie";
//            Log.e("-----------usmmd5",usmmd5);
            smd5 = EncryptUtil.MD5(usmmd5);
//            Log.e("--------smd5",smd5+"smd5了");
//            Log.e("--------scheduleId",scheduleId+"了");
            seatstring = this.s.substring(0, this.s.length() - 1);
//            Log.e("--------sss",strings.size()+"有");
//            Log.e("--------sss", this.s +"sss");
//            Log.e("--------seatstring",seatstring+"seatstring");

            v = fare * strings.size();
            btnChoosePrice.setText("￥"+ v);
            btnChoosePrice.setBackgroundColor(Color.parseColor("#e8185e"));


        }
    }

    private void getQuery() {
        Map<String, Object> map1 = new HashMap<>();
        map1.put("movieId", movieId);
        map1.put("cinemaId", cinemaId);
        basePresenter.get(FilmUrl.MOVIE_SCHEDULE_URL, map1, BeanSchedue.class);

    }

    @Override
    protected int initLayout() {
        return R.layout.activity_choose_pay;
    }

    @Override
    public BaseContrat.BasePresenter getPresenter() {
        return new Presenter();
    }

    @Override
    public void successful(Object object) {
        if (object != null) {
            if (object instanceof BeanSchedue) {
                BeanSchedue schedue = (BeanSchedue) object;
                if (schedue.getStatus().equals("0000")) {
                    if (schedue.getResult() == null || schedue.getResult().size() == 0) {
                        tvChooseHallNul.setText("选择影厅和时间（0）");
                        tvChooseNoMovie.setVisibility(View.VISIBLE);
                    } else {
                        tvChooseHallNul.setText("选择影厅和时间（" + schedue.getResult().size() + "）");
                        tvChooseNoMovie.setVisibility(View.GONE);
                        List<BeanSchedue.ResultBean> list = schedue.getResult();
                        MyChooseHallAdapter adapter = new MyChooseHallAdapter(ChoosePayActivity.this, list);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ChoosePayActivity.this);
                        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                        recyChooseHallId.setAdapter(adapter);
                        recyChooseHallId.setLayoutManager(linearLayoutManager);
                        if (!bto){
                            String hall = list.get(0).getHallId()+"";
                            Map<String, Object> map1 = new HashMap<>();
                            map1.put("hallId", hall);
                            basePresenter.get(FilmUrl.SEATINFO_URL, map1, BeanMovieSchedule.class);
                            bto=true;
                        }
                    }
                }
            } else if (object instanceof BeanDetails) {
                BeanDetails bean = (BeanDetails) object;
                if (bean.getStatus().equals("0000")) {
                    String name = bean.getResult().getName();
                    String imageUrl = bean.getResult().getShortFilmList().get(0).getImageUrl();
                    String videoUrl = bean.getResult().getShortFilmList().get(0).getVideoUrl();
                    tvChoosePayName.setText(name);


                }
            } else if (object instanceof BeanMovieSchedule) {
                BeanMovieSchedule bean = (BeanMovieSchedule) object;
                if (bean.getStatus().equals("0000")) {
//                    Log.e("---------","列+++--+-");
                    int size = bean.getResult().size();
//                    Log.e("---------","562列"+size);
                    String row = bean.getResult().get(size-1).getRow();
                    int parseInt = Integer.parseInt(row);
//                    Log.e("---------","列"+row);
                    List<BeanMovieSchedule.ResultBean> list = bean.getResult();
                    MyScheduleRowAdapter adapter = new MyScheduleRowAdapter(ChoosePayActivity.this,list,parseInt);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ChoosePayActivity.this);
                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyChooseSchedule.setLayoutManager(linearLayoutManager);
                    recyChooseSchedule.setAdapter(adapter);
                }
            } else if (object instanceof BeanMovieTickets) {
                BeanMovieTickets bean = (BeanMovieTickets) object;
                if (bean.getStatus().equals("0000")) {
                    String orderId = bean.getOrderId();
//                    btnChoosePrice.setText("微信支付"+v+"元");


                }
                ToastUtils.showShort(bean.getMessage());
            }
        }

    }

    @Override
    public void failure(String error) {
        Log.e("------", error);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_choose_pay_exit, R.id.btn_choose_price})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_choose_pay_exit:
                finish();
                break;
            case R.id.btn_choose_price:
                if (rfgh){
                    Map<String,Object> map = new HashMap<>();
                    map.put("scheduleId",scheduleId);
                    map.put("seat",seatstring);
                    map.put("sign",smd5);
                    basePresenter.post(FilmUrl.MOVIES_TICKETS_URL,map, BeanMovieTickets.class);

                } else {
                    ToastUtils.showShort("请先选座");
                }
                break;
        }
    }
}
