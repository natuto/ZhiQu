package com.lws.zhiqu.api;

import com.lws.zhiqu.model.bean.DouDetailBean;
import com.lws.zhiqu.model.bean.DoubanBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by song on 2018/2/28.
 */

public interface DoubanApi {
    public final static String HOST = "Https://api.douban.com/";


    @GET("v2/movie/in_theaters")
    Observable<DoubanBean> getMovieList();
    @GET("v2/movie/subject/{id}")
    Observable<DouDetailBean> getDouDetail(@Path("id") String id);
}
