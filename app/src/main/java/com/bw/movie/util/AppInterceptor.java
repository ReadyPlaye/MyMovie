package com.bw.movie.util;

import com.blankj.utilcode.util.SPUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class AppInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        Request.Builder builder = request.newBuilder();
        String string = SPUtils.getInstance().getString("userId");
                builder.addHeader("ak", "0110010010000");
                builder.addHeader("Content-Type", "application/x-www-form-urlencoded");
        if (string.isEmpty()){

        } else {
            builder.addHeader("userId", SPUtils.getInstance().getString("userId"));
            builder.addHeader("sessionId", SPUtils.getInstance().getString("sessionId"));
        }

        Request build = builder.build();
        Response proceed = chain.proceed(build);
        return proceed;
    }
}
