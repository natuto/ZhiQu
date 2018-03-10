package com.lws.zhiqu;

import android.app.Application;
import android.content.Context;

/**
 * Created by song on 2018/1/26.
 */

public class App extends Application {
    public static  final String WEI_XIN_KEY = "cb6eebedf4fbda3e5d7428d4b9995295";

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }
}
