package com.lws.zhiqu.api;

import com.lws.zhiqu.model.bean.ZhuDetailBean;
import com.lws.zhiqu.model.bean.ZhuListBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by song on 2018/2/10.
 */

public interface ZhuhuApi {
    public static final String HOST = "http://news-at.zhihu.com";

    @GET("/api/4/news/latest")
    Observable<ZhuListBean> getLatestList();

    @GET("/api/4/news/before/{date}")
    Observable<ZhuListBean> getBeforeList(@Path("date") String date);

    @GET("/api/4/news/{id}")
    Observable<ZhuDetailBean> getDetail(@Path("id") String id);
}
