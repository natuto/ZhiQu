package com.lws.zhiqu.api;

import com.lws.zhiqu.model.bean.GirlBean;
import com.lws.zhiqu.model.bean.JiandanBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by song on 2018/3/5.
 */

public interface FuliApi {
    public static  final  String HOST = "http://i.jandan.net";
    public  static  final  String MZITU_HOST = "http://www.mzitu.com";

    @GET("/api/data/福利/10/{page}")
    Observable<GirlBean> getGirllist(@Path("page") int page);

    @GET("/?oxwlxojflwblxbsapi=jandan.get_ooxx_comments")
    Observable<JiandanBean> getJiandan(@Query("page") int page);

    @GET("/zipai/comment-page-{page}/#comments")
    Observable<ResponseBody> getZipailist(@Path("page") int page);
}
