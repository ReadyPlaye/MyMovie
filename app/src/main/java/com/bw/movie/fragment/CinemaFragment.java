package com.bw.movie.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.blankj.utilcode.util.ToastUtils;
import com.bw.movie.R;
import com.bw.movie.adapter.MyCinemaSearchAdapter;
import com.bw.movie.adapter.MyRegionAdapter;
import com.bw.movie.adapter.MyRegionListAdapter;
import com.bw.movie.base.BaseContrat;
import com.bw.movie.base.BaseMvpFragment;
import com.bw.movie.bean.BeanCearchCinema;
import com.bw.movie.bean.BeanRegion;
import com.bw.movie.bean.BeanRegionList;
import com.bw.movie.event.MoviceCinemaEvent;
import com.bw.movie.mvp.presenter.IContrat;
import com.bw.movie.mvp.presenter.Presenter;
import com.bw.movie.util.CinemaUrl;
import com.bw.movie.util.HttpRetrofitUtil;
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
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class CinemaFragment extends BaseMvpFragment<IContrat.IModel, IContrat.IPresenter> implements IContrat.IView {


    @BindView(R.id.iv_cinema_location)
    ImageView ivCinemaLocation;
    @BindView(R.id.tv_cinema_location)
    TextView tvCinemaLocation;
    @BindView(R.id.et_cinema_search)
    EditText etCinemaSearch;
    @BindView(R.id.iv_cinema_search)
    ImageView ivCinemaSearch;
    @BindView(R.id.tv_cinema_recommend)
    TextView tvCinemaRecommend;
    @BindView(R.id.rlt_cinema_recommend)
    RelativeLayout rltCinemaRecommend;
    @BindView(R.id.tv_cinema_nearby)
    TextView tvCinemaNearby;
    @BindView(R.id.rlt_cinema_nearby)
    RelativeLayout rltCinemaNearby;
    @BindView(R.id.tv_cinema_region)
    TextView tvCinemaRegion;
    @BindView(R.id.rlt_cinema_region)
    RelativeLayout rltCinemaRegion;
    @BindView(R.id.llt_no_search_cinema)
    LinearLayout lltNoSearchCinema;
    Unbinder unbinder;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    @BindView(R.id.rv_regionName)
    RecyclerView rvRegionName;
    @BindView(R.id.rv_name)
    RecyclerView rvName;
    @BindView(R.id.llt_cin_regin)
    LinearLayout lltCinRegin;
    @BindView(R.id.recy_search_cinema)
    RecyclerView recySearchCinema;
    @BindView(R.id.recy_cinema_search_list)
    RecyclerView recyCinemaSearchList;
    private double latitude;
    private double longitude;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                    Log.e("-------", "555");
                    //可在其中解析amapLocation获取相应内容。
                    //                    amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                    //获取纬度
                    latitude = amapLocation.getLatitude();
                    //获取经度
                    longitude = amapLocation.getLongitude();
                    Log.e("--------", "纬度：" + latitude + "经度：" + longitude);
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
                    tvCinemaLocation.setText(city);
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
    private int stey;
    private int search=0;

    public CinemaFragment() {
        // Required empty public constructor
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        //搜索放大镜
        ivCinemaSearch.setVisibility(View.VISIBLE);
        //输入框
        etCinemaSearch.setVisibility(View.GONE);
        //推荐影院
        tvCinemaRecommend.setVisibility(View.VISIBLE);
        //附近影院
        tvCinemaNearby.setVisibility(View.GONE);
        //区域影院
        tvCinemaRegion.setVisibility(View.GONE);
        //地区列表
        lltCinRegin.setVisibility(View.GONE);
        //搜索列表
        recyCinemaSearchList.setVisibility(View.GONE);
        //无搜索内容
        lltNoSearchCinema.setVisibility(View.GONE);
        getlocation();
        getQueryRecommend();
        etCinemaSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String s1 = s.toString();
                if (s1.isEmpty()){
                    //输入框
                    etCinemaSearch.setVisibility(View.GONE);
                    //搜索列表
                    recyCinemaSearchList.setVisibility(View.GONE);
                    //无搜索内容
                    lltNoSearchCinema.setVisibility(View.GONE);
                    //搜索放大镜
                    ivCinemaSearch.setVisibility(View.VISIBLE);
                } else {
                    //搜索列表
                    recyCinemaSearchList.setVisibility(View.VISIBLE);
                    Map<String, Object> map = new HashMap<>();
                    map.put("cinemaName", s1);
                    map.put("page", "1");
                    map.put("count", "10");
                    basePresenter.get(CinemaUrl.CINEMA_ALL_URL, map, BeanCearchCinema.class);
                }
            }
        });
        lltNoSearchCinema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShort("快点去搜索吧！");
            }
        });
        lltCinRegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShort("选择区域");
            }
        });
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void asdzvcvfg(MoviceCinemaEvent event) {
        String regionId = event.cinemaId;
        Map<String, Object> map4 = new HashMap<>();
        map4.put("regionId", regionId);
        basePresenter.get(CinemaUrl.CINEMA_REGION_URL, map4, BeanRegion.class);

    }

    //    @Override
    //    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    //                             Bundle savedInstanceState) {
    //        // Inflate the layout for this fragment
    //        return inflater.inflate(R.layout.fragment_cinema, container, false);
    //    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_cinema;
    }

    @Override
    public BaseContrat.BasePresenter getPresenter() {
        return new Presenter();
    }

    @Override
    public void successful(Object object) {
        if (object != null) {
            if (object instanceof BeanCearchCinema) {
                BeanCearchCinema bean = (BeanCearchCinema) object;
                if (bean.getStatus().equals("0000")) {
                    if (search==1){
                        Log.e("------","sdf"+bean.getResult().toString()+"sdf5sfd"+bean.getResult().size());
                        if (bean.getResult()==null||bean.getResult().size()==0){
                            //无搜索内容
                            lltNoSearchCinema.setVisibility(View.VISIBLE);

                        } else {
                            //无搜索内容
                            lltNoSearchCinema.setVisibility(View.GONE);
                            List<BeanCearchCinema.ResultBean> list = bean.getResult();
                            MyCinemaSearchAdapter mySearchAdapter = new MyCinemaSearchAdapter(getContext(), list, stey);
                            recyCinemaSearchList.setAdapter(mySearchAdapter);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            recyCinemaSearchList.setLayoutManager(linearLayoutManager);
                        }
                    } else {

                        List<BeanCearchCinema.ResultBean> list = bean.getResult();
                        MyCinemaSearchAdapter mySearchAdapter = new MyCinemaSearchAdapter(getContext(), list, stey);
                        recySearchCinema.setAdapter(mySearchAdapter);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        recySearchCinema.setLayoutManager(linearLayoutManager);
                    }
                }
            } else if (object instanceof BeanRegionList) {
                BeanRegionList bean = (BeanRegionList) object;
                if (bean.getStatus().equals("0000")) {
                    //地区列表
                    lltCinRegin.setVisibility(View.VISIBLE);
                    List<BeanRegionList.ResultBean> beanList = bean.getResult();
                    MyRegionListAdapter adapter = new MyRegionListAdapter(getContext(), beanList);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    rvRegionName.setAdapter(adapter);
                    rvRegionName.setLayoutManager(layoutManager);
                }
            } else if (object instanceof BeanRegion) {
                BeanRegion bean = (BeanRegion) object;
                if (bean.getStatus().equals("0000")) {
                    if (bean.getResult().size()==0){

                    } else {

                        List<BeanRegion.ResultBean> beanList = bean.getResult();
                        MyRegionAdapter adapter = new MyRegionAdapter(getContext(), beanList);
                        LinearLayoutManager layonager = new LinearLayoutManager(getContext());
                        layonager.setOrientation(LinearLayoutManager.VERTICAL);
                        rvName.setAdapter(adapter);
                        rvName.setLayoutManager(layonager);
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
    }

    @OnClick({R.id.iv_cinema_location, R.id.iv_cinema_search, R.id.rlt_cinema_recommend, R.id.rlt_cinema_nearby, R.id.rlt_cinema_region})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_cinema_location:
                getlocation();
                break;
            case R.id.iv_cinema_search:
                //搜索放大镜
                ivCinemaSearch.setVisibility(View.GONE);
                //输入框
                etCinemaSearch.setVisibility(View.VISIBLE);

                search=1;
                stey=0;
                break;
            case R.id.rlt_cinema_recommend:
                search=0;
                stey=0;
                //搜索放大镜
                ivCinemaSearch.setVisibility(View.VISIBLE);
                //输入框
                etCinemaSearch.setVisibility(View.GONE);
                //推荐影院
                tvCinemaRecommend.setVisibility(View.VISIBLE);
                //附近影院
                tvCinemaNearby.setVisibility(View.GONE);
                //区域影院
                tvCinemaRegion.setVisibility(View.GONE);
                //地区列表
                lltCinRegin.setVisibility(View.GONE);
                //搜索列表
                recyCinemaSearchList.setVisibility(View.GONE);
                //无搜索内容
                lltNoSearchCinema.setVisibility(View.GONE);
                getQueryRecommend();
                break;
            case R.id.rlt_cinema_nearby:
                search=0;
                stey=1;
                //搜索放大镜
                ivCinemaSearch.setVisibility(View.VISIBLE);
                //输入框
                etCinemaSearch.setVisibility(View.GONE);
                //推荐影院
                tvCinemaRecommend.setVisibility(View.GONE);
                //附近影院
                tvCinemaNearby.setVisibility(View.VISIBLE);
                //区域影院
                tvCinemaRegion.setVisibility(View.GONE);
                //地区列表
                lltCinRegin.setVisibility(View.GONE);
                //搜索列表
                recyCinemaSearchList.setVisibility(View.GONE);
                //无搜索内容
                lltNoSearchCinema.setVisibility(View.GONE);
                getQueryNearby();
                break;
            case R.id.rlt_cinema_region:
                search=0;
                stey=0;
                //搜索放大镜
                ivCinemaSearch.setVisibility(View.VISIBLE);
                //输入框
                etCinemaSearch.setVisibility(View.GONE);
                //推荐影院
                tvCinemaRecommend.setVisibility(View.GONE);
                //附近影院
                tvCinemaNearby.setVisibility(View.GONE);
                //区域影院
                tvCinemaRegion.setVisibility(View.VISIBLE);
                //地区列表
                lltCinRegin.setVisibility(View.GONE);
                //搜索列表
                recyCinemaSearchList.setVisibility(View.GONE);
                //无搜索内容
                lltNoSearchCinema.setVisibility(View.GONE);
                getQueryRegion();
                break;
        }
    }

    private void getQueryRegion() {
        Map<String, Object> map3 = new HashMap<>();
        basePresenter.get(ToolUrl.REGION_LIST_URL, map3, BeanRegionList.class);
    }

    private void getQueryNearby() {
        //附近影院
        Map<String, Object> map2 = new HashMap<>();
        map2.put("page", "1");
        map2.put("count", "10");
        map2.put("longitude", longitude + "");
        map2.put("latitude", latitude + "");
        basePresenter.get(CinemaUrl.CINEMA_NEARBY_URL, map2, BeanCearchCinema.class);
    }

    private void getQueryRecommend() {
        //推荐影院
        Map<String, Object> map1 = new HashMap<>();
        map1.put("page", "1");
        map1.put("count", "10");
        basePresenter.get(CinemaUrl.CINEMA_RECOMMEND_URL, map1, BeanCearchCinema.class);

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
