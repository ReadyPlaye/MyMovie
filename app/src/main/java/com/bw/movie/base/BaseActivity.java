package com.bw.movie.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.jaeger.library.StatusBarUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseActivity extends AppCompatActivity {
    private Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());

        bind = ButterKnife.bind(this);
        init();
        initDate();
        initLogic();
    }

    //初始化
    protected abstract void init();

    //逻辑
    protected abstract void initLogic();

    //数据
    protected abstract void initDate();

    //布局
    protected abstract int initLayout();

    //退出
    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
