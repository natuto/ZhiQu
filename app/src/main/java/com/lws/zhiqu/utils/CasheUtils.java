package com.lws.zhiqu.utils;

import com.lws.zhiqu.App;

import java.io.File;

import okhttp3.Cache;

/**
 * Created by song on 2018/2/10.
 */

public class CasheUtils {
    public static Cache getCache() {
        File fileCacheDirectory = new File(App.getContext().getCacheDir(),"zhiqucashe");
        int cacheSize = 10 * 1024 * 1024;
        return new Cache(fileCacheDirectory,cacheSize);
    }
}
