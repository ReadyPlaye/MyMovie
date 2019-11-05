package com.bw.movie.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.activity.DetailsActivity;
import com.bw.movie.activity.FilmActivity;
import com.bw.movie.activity.MovieCinemaActivity;
import com.bw.movie.activity.SearchActivity;
import com.bw.movie.adapter.MyComingAdapter;
import com.bw.movie.adapter.MyHotAdapter;
import com.bw.movie.adapter.MyReleaseAdapter;
import com.bw.movie.base.BaseContrat;
import com.bw.movie.base.BaseMvpFragment;
import com.bw.movie.bean.BeamRelease;
import com.bw.movie.bean.BeanBanner;
import com.bw.movie.bean.BeanComing;
import com.bw.movie.bean.BeanHot;
import com.bw.movie.bean.BeanRegister;
import com.bw.movie.event.ComingFilmEvent;
import com.bw.movie.mvp.presenter.IContrat;
import com.bw.movie.mvp.presenter.Presenter;
import com.bw.movie.util.FilmUrl;
import com.bw.movie.util.ToolUrl;
import com.stx.xhb.xbanner.XBanner;

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
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilmFragment extends BaseMvpFragment<IContrat.IModel, IContrat.IPresenter> implements IContrat.IView {


    @BindView(R.id.xBanner)
    XBanner xBanner;
    Unbinder unbinder;
    @BindView(R.id.recy_reseale)
    RecyclerView recyReseale;
    @BindView(R.id.recy_coming)
    RecyclerView recyComing;
    @BindView(R.id.recy_hot)
    RecyclerView recyHot;
    @BindView(R.id.iv_image_film)
    ImageView ivImageFilm;
    @BindView(R.id.tv_film_1_name)
    TextView tvFilm1Name;
    @BindView(R.id.tv_film_1_score)
    TextView tvFilm1Score;
    @BindView(R.id.iv_locatione)
    ImageView ivLocatione;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                    Log.e("-------", "555");
                    //可在其中解析amapLocation获取相应内容。
                    //                    amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                    //                    amapLocation.getLatitude();//获取纬度
                    //                    amapLocation.getLongitude();//获取经度
                    //                    amapLocation.getAccuracy();//获取精度信息
                    //                    amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                    //                    amapLocation.getCountry();//国家信息
                    //                    amapLocation.getProvince();//省信息
                    String city = amapLocation.getCity();//城市信息
                    //                    amapLocation.getDistrict();//城区信息
                    //                    amapLocation.getStreet();//街道信息
                    //                    amapLocation.getStreetNum();//街道门牌号信息
                    //                    amapLocation.getCityCode();//城市编码
                    //                    amapLocation.getAdCode();//地区编码
                    //                    amapLocation.getAoiName();//获取当前定位点的AOI信息
                    //                    amapLocation.getBuildingId();//获取当前室内定位的建筑物Id
                    //                    amapLocation.getFloor();//获取当前室内定位的楼层
                    //                    amapLocation.getGpsAccuracyStatus();//获取GPS的当前状态
                    //获取定位时间
                    //                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    //                    Date date = new Date(amapLocation.getTime());
                    //                    df.format(date);
                    Log.e("-------", city + "263");
                    tvLocation.setText(city);
                    mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + amapLocation.getErrorCode() + ", errInfo:"
                            + amapLocation.getErrorInfo());
                }
            }
        }
    };
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.tv_film_head1)
    TextView tvFilmHead1;
    @BindView(R.id.tv_film_head2)
    TextView tvFilmHead2;
    @BindView(R.id.tv_film_head3)
    TextView tvFilmHead3;
    @BindView(R.id.btn_film_one)
    Button btnFilmOne;
    private Intent intenthead;
    private String movieId;
    private String name;

    @Override
    public void onDestroy() {
        super.onDestroy();
        //        mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
    }

    public FilmFragment() {
        // Required empty public constructor
    }


    //    @Override
    //    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    //                             Bundle savedInstanceState) {
    //        // Inflate the layout for this fragment
    //        return inflater.inflate(R.layout.fragment_film, container, false);
    //    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void asdfdsfg(ComingFilmEvent event) {
        int movieId = event.movieId;
        int tyer = event.tyer;
        Map<String, Object> map = new HashMap<>();
        map.put("movieId", movieId);
        basePresenter.getpost(FilmUrl.MOVIES_RESERVE_URL, map, BeanRegister.class);
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        intenthead = new Intent(getContext(), FilmActivity.class);


        Map<String, Object> map = new HashMap<>();
        basePresenter.get(ToolUrl.BANNER_URL, map, BeanBanner.class);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("page", "1");
        map1.put("count", "10");

        //正在上映
        basePresenter.get(FilmUrl.RELEASE_URL, map1, BeamRelease.class);
        //即将上映
        basePresenter.get(FilmUrl.COMING_URL, map1, BeanComing.class);
        //热门电影
        basePresenter.get(FilmUrl.HOT_URL, map1, BeanHot.class);
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_film;
    }

    @Override
    public BaseContrat.BasePresenter getPresenter() {
        return new Presenter();
    }

    @Override
    public void successful(Object object) {
        if (object != null) {
            //banner
            if (object instanceof BeanBanner) {
                BeanBanner bean = (BeanBanner) object;
                if (bean.getStatus().equals("0000")) {
                    List<BeanBanner.ResultBean> list = bean.getResult();
                    ArrayList<String> objects = new ArrayList<>();

                    for (int i = 0; i < list.size(); i++) {
                        String imageUrl = list.get(i).getImageUrl();
                        objects.add(imageUrl);
                    }
                    //加载广告图片
                    //                    xBanner.setBannerData(objects);
                    xBanner.setData(objects, null);
                    xBanner.loadImage(new XBanner.XBannerAdapter() {
                        @Override
                        public void loadBanner(XBanner banner, Object model, View view, int position) {

                            //1、此处使用的 Glide 加载图片，可自行替换自己项目中的图片加载框架
                            //2、返回的图片路径为 Object 类型，你只需要强转成你传输的类型就行，切记不要胡乱强转！
                            Glide.with(getActivity()).load(objects.get(position)).apply(RequestOptions.bitmapTransform(new RoundedCorners(8))).into((ImageView) view);
                        }
                    });
                    xBanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
                        @Override
                        public void onItemClick(XBanner banner, Object model, View view, int position) {
                            String jumpUrl = list.get(position).getJumpUrl();
                            if (jumpUrl != null) {
                                if (jumpUrl.indexOf("=") != -1) {
                                    String[] split = jumpUrl.split("=");
                                    String s = split[1];
                                    Intent intent = new Intent(getContext(), DetailsActivity.class);
                                    intent.putExtra("movieId", s);
                                    startActivity(intent);
                                }
                            }
                        }
                    });

                }
                //正在上映
            } else if (object instanceof BeamRelease) {
                BeamRelease bean = (BeamRelease) object;
                if (bean.getStatus().equals("0000")) {
                    List<BeamRelease.ResultBean> list = bean.getResult();
                    MyReleaseAdapter releaseAdapter = new MyReleaseAdapter(getActivity(), list);
                    recyReseale.setAdapter(releaseAdapter);
                    LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext());
                    layoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
                    recyReseale.setLayoutManager(layoutManager1);

                }

                //即将上映
            } else if (object instanceof BeanComing) {
                BeanComing bean = (BeanComing) object;
                if (bean.getStatus().equals("0000")) {
                    List<BeanComing.ResultBean> beanList = bean.getResult();
                    MyComingAdapter comingAdapter = new MyComingAdapter(getActivity(), beanList);
                    recyComing.setAdapter(comingAdapter);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyComing.setLayoutManager(layoutManager);
                    //                    comingAdapter.notifyDataSetChanged();
                }

                //热门电影
            } else if (object instanceof BeanHot) {
                BeanHot bean = (BeanHot) object;
                if (bean.getStatus().equals("0000")) {
                    List<BeanHot.ResultBean> list = bean.getResult();
                    Glide.with(getActivity()).load(list.get(0).getImageUrl()).apply(RequestOptions.bitmapTransform(new RoundedCorners(8))).into(ivImageFilm);
                    tvFilm1Name.setText(list.get(0).getName());
                    tvFilm1Score.setText(list.get(0).getScore() + "");
                    movieId = list.get(0).getMovieId() + "";
                    name = list.get(0).getName();
                    ivImageFilm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getContext(), DetailsActivity.class);
                            intent.putExtra("movieId", movieId);
                            startActivity(intent);
                        }
                    });
                    MyHotAdapter hotAdapter = new MyHotAdapter(getActivity(), list);
                    recyHot.setAdapter(hotAdapter);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
                    gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyHot.setLayoutManager(gridLayoutManager);
                }

            } else if (object instanceof BeanRegister) {
                BeanRegister beanRegister = (BeanRegister) object;
                if (beanRegister.getStatus().equals("0000")) {
                    Map<String, Object> map1 = new HashMap<>();
                    map1.put("page", "1");
                    map1.put("count", "10");
                    //即将上映
                    basePresenter.get(FilmUrl.COMING_URL, map1, BeanComing.class);
                }
                ToastUtils.showShort(beanRegister.getMessage());
            }
        }
    }

    @Override
    public void failure(String error) {
        Log.e("------", error);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.iv_locatione, R.id.iv_search, R.id.tv_film_head1, R.id.tv_film_head2, R.id.tv_film_head3, R.id.btn_film_one})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_film_head1:
                intenthead.putExtra("head", "0");
                startActivity(intenthead);
                break;
            case R.id.tv_film_head2:
                intenthead.putExtra("head", "1");
                startActivity(intenthead);
                break;
            case R.id.tv_film_head3:
                intenthead.putExtra("head", "2");
                startActivity(intenthead);
                break;
            case R.id.iv_locatione:
                Log.e("-------", "启动定位");
                //启动定位
                getlocation();
                break;
            case R.id.btn_film_one:
                Intent intent = new Intent(getContext(), MovieCinemaActivity.class);
                intent.putExtra("movieId",movieId);
                intent.putExtra("name",name);
                startActivity(intent);
                break;
            case R.id.iv_search:
                Intent intent2 = new Intent(getContext(), SearchActivity.class);
                startActivity(intent2);
                break;
        }
    }

    private void getlocation() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //        AMapLocationClientOption option = new AMapLocationClientOption();
        /**
         * 设置定位场景，目前支持三种场景（签到、出行、运动，默认无场景）
         */
        //        option.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
        //        AMapLocationClient locationClient = null;
        //        if (null != locationClient) {
        //            locationClient.setLocationOption(option);
        //            //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
        //            locationClient.stopLocation();
        //            locationClient.startLocation();
        //        }

        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);

        //获取一次定位结果：
        //该方法默认为false。
        mLocationOption.setOnceLocation(true);

        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        //        mLocationOption.setOnceLocationLatest(true);
        //设置是否返回地址信息（默认返回地址信息）
        //        mLocationOption.setNeedAddress(true);
        //设置是否允许模拟位置,默认为true，允许模拟位置
        //        mLocationOption.setMockEnable(true);

        //单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
        //        mLocationOption.setHttpTimeOut(20000);
        //关闭缓存机制
        mLocationOption.setLocationCacheEnable(false);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        mLocationClient.startLocation();
    }
}
