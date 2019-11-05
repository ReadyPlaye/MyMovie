package com.bw.movie.util;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;


public interface WayApi {
    @GET
    Observable<ResponseBody> get(@Url String url, @QueryMap Map<String,Object> map);
    @FormUrlEncoded
    @POST
    Observable<ResponseBody> post(@Url String url,@FieldMap Map<String,Object> map);
    @DELETE
    Observable<ResponseBody> delete(@Url String url,@QueryMap Map<String,Object> map);
    @PUT
    Observable<ResponseBody> put(@Url String url,@QueryMap Map<String,Object> map);
    @POST
    Observable<ResponseBody> getpost(@Url String url,@QueryMap Map<String,Object> map);
}
