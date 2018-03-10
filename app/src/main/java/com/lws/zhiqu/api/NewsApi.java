package com.lws.zhiqu.api;

import com.lws.zhiqu.model.bean.NewsBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by song on 2018/2/24.
 */

public interface NewsApi {
    public static final String HOST = "http://c.m.163.com";

    @GET("/nc/article/headline/T1348647909107/{id}-20.html")
    Observable<NewsBean> getNewsList(@Path("id") int id);

}

