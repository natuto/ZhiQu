package com.lws.zhiqu.api;

import com.lws.zhiqu.model.bean.WeiXinBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;



/**
 * Created by song on 2018/2/20.
 */

public interface  WeixinApi {
    public static final String HOST = "http://v.juhe.cn";
    @GET("/weixin/query")
    Observable<WeiXinBean> getWeixinList(@Query("pno") int pno, @Query("ps") int
            ps, @Query("dtype") String dtype,@Query("key") String key );

}