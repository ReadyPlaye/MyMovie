package com.bw.movie.util;

import android.app.Application;
import android.content.Context;

import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import static android.provider.UserDictionary.Words.APP_ID;


public class App extends Application {
    public static IWXAPI mWxApi;
    public static final String APP_ID = "wxb3852e6a6b7d9516";
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        registerToWX();
        context = getApplicationContext();
    }
    private void registerToWX() {
        //第二个参数是应用在微信开放平台上的AppID
        mWxApi = WXAPIFactory.createWXAPI(this, APP_ID, false);
        // 将该app注册到微信
        mWxApi.registerApp(APP_ID);
    }
}
