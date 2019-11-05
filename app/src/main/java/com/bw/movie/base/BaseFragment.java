package com.bw.movie.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseFragment extends Fragment {
    private Unbinder bind;

    protected abstract void initData();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(initLayout(), container, false);
        bind = ButterKnife.bind(this,view);
        this.init(view);
        this.initData();
        return view;
    }

    protected abstract int initLayout();


    //初始化
    protected abstract void init(View view);

    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
