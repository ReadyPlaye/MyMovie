package com.bw.movie.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class HttpRetrofitUtil {
    private static HttpRetrofitUtil util;
    private Retrofit retrofit;
    //日志
    private HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    //okhttp拦截器
    private OkHttpClient okHttpClient(){
        return new OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5,TimeUnit.SECONDS)
                .connectTimeout(5,TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                //添加请求头
                .addNetworkInterceptor(new AppInterceptor())
                .addNetworkInterceptor(loggingInterceptor)
                .build();
    }
    //网络判断
    public boolean isnetwork(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo!=null){
            return activeNetworkInfo.isAvailable();
        }
        return false;
    }

    //双层检验
    public static HttpRetrofitUtil get(){
        if (util==null){
            synchronized (HttpRetrofitUtil.class){
                if (util==null){
                    util = new HttpRetrofitUtil();
                }
            }
        }
        return util;
    }
    //retrofit请求网络
    private HttpRetrofitUtil(){

            retrofit = new Retrofit.Builder()
                    .baseUrl(UserUrl.BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient())
                    .build();
    }

    public <T>T create(Class<T> aClass){
        return retrofit.create(aClass);
    }

}
