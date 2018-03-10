package com.lws.zhiqu.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.lws.zhiqu.App;

/**
 * Created by song on 2018/2/4.
 */

public class DisplayUtils {
    public  static int getDisplayWidth() {
        WindowManager wm = (WindowManager)App.getContext() .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;

    }
}
