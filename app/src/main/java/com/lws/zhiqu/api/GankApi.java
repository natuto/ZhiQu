package com.lws.zhiqu.api;

import com.lws.zhiqu.model.bean.GankBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;



/**
 * Created by song on 2018/2/26.
 */

public interface GankApi {

    public static final  String HOST = "http://gank.io";
    @GET("/api/data/{type}/{pageSize}/{page}")
    Observable<GankBean> getGankList(@Path("type") String type, @Path("pageSize") int pageSize, @Path("page") int page);


}
