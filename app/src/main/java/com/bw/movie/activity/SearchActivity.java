package com.bw.movie.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.bw.movie.R;
import com.bw.movie.adapter.MySearchAdapter;
import com.bw.movie.base.BaseContrat;
import com.bw.movie.base.BaseMvpActivity;
import com.bw.movie.base.EncryptUtil;
import com.bw.movie.bean.BeanCearch;
import com.bw.movie.bean.BeanLogin;
import com.bw.movie.mvp.presenter.IContrat;
import com.bw.movie.mvp.presenter.Presenter;
import com.bw.movie.util.FilmUrl;
import com.bw.movie.util.HttpRetrofitUtil;
import com.bw.movie.util.UserUrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends BaseMvpActivity<IContrat.IModel, IContrat.IPresenter> implements IContrat.IView {
    @BindView(R.id.tv_search_exit)
    ImageView tvSearchExit;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.recy_search)
    RecyclerView recySearch;
    @BindView(R.id.llt_no_network)
    LinearLayout lltNoNetwork;
    @BindView(R.id.llt_no_search)
    LinearLayout lltNoSearch;

    //    @Override
    //    protected void onCreate(Bundle savedInstanceState) {
    //        super.onCreate(savedInstanceState);
    //        setContentView(R.layout.activity_search);
    //
    //    }

    @Override
    protected void initLogic() {
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.e("--------","1"+s.toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.e("--------","2"+s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

                String s1 = s.toString();
                Log.e("--------","3"+s1);
                if (s1.isEmpty()){
                    estimateNOSearch();
                } else {
                    estimateTwort(s1);
                }
            }
        });
    }

    @Override
    protected void initDate() {
        estimateTwort("");
//        estimateTwort();
//        estimateNOSearch();
//        estimateYESSearch();



    }

    private void estimateYESSearch() {
        etSearch.setVisibility(View.VISIBLE);
        recySearch.setVisibility(View.VISIBLE);
        lltNoSearch.setVisibility(View.GONE);
        lltNoNetwork.setVisibility(View.GONE);
    }

    private void estimateNOSearch() {
        etSearch.setVisibility(View.VISIBLE);
        recySearch.setVisibility(View.GONE);
        lltNoSearch.setVisibility(View.VISIBLE);
        lltNoNetwork.setVisibility(View.GONE);
    }

    private void estimateTwort(String s) {
        boolean isnetwork = HttpRetrofitUtil.get().isnetwork(this);
        if (!isnetwork) {
            etSearch.setVisibility(View.GONE);
            recySearch.setVisibility(View.GONE);
            lltNoSearch.setVisibility(View.GONE);
            lltNoNetwork.setVisibility(View.VISIBLE);
            ToastUtils.showShort("请链接网络！");
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("keyword", s);
            map.put("page", "1");
            map.put("count", "10");
            basePresenter.get(FilmUrl.MOVIES_KETWORD_URL, map, BeanCearch.class);
        }
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_search;
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
                    String message = bean.getMessage();
                    int index = message.indexOf("查询成功");
                    if (index==-1){
                        estimateNOSearch();
                    } else {
                        estimateYESSearch();
                        List<BeanCearch.ResultBean> list = bean.getResult();
                        MySearchAdapter mySearchAdapter = new MySearchAdapter(this,list);
                        recySearch.setAdapter(mySearchAdapter);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        recySearch.setLayoutManager(linearLayoutManager);
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

    @OnClick(R.id.tv_search_exit)
    public void onViewClicked() {
        finish();
    }
}
