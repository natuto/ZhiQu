package com.lws.zhiqu.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.lws.zhiqu.theme.Theme;
import com.lws.zhiqu.theme.ThemeColor;


/**
 * Created by song on 2018/1/27.
 */

public class SpUtils {

    private  static SharedPreferences getSharedPreferences(final Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public  static int getInt( final  Context context, final String key, final  int defaultValue){
        return  SpUtils.getSharedPreferences(context).getInt(key,defaultValue);
    }

    public  static boolean putInt(final Context context, final String key, final  int value){
        final  SharedPreferences.Editor editor = SpUtils.getSharedPreferences(context).edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    public static long getLong(final Context context, final  String key, final long defaultValue){
        return SpUtils.getSharedPreferences(context).getLong(key, defaultValue);
    }

    private static boolean putLong(final Context context, final String key, final long value){
        SharedPreferences.Editor editor = SpUtils.getSharedPreferences(context).edit();
        editor.putLong(key, value);
        return editor.commit();
    }
    public  static boolean getBoolean(final  Context context, final String key, final boolean defaultValue ){
       return SpUtils.getSharedPreferences(context).getBoolean(key, defaultValue);
    }
    public  static boolean putBoolean(final Context context, final String key, final boolean value){
        SharedPreferences.Editor editor = SpUtils.getSharedPreferences(context).edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }
    public static String getString(final Context context, final String key, final String defaultValue) {
        return SpUtils.getSharedPreferences(context).getString(key, defaultValue);
    }

    public static boolean putString(final Context context, final String key, final String value) {
        final SharedPreferences.Editor editor = SpUtils.getSharedPreferences(context).edit();
        editor.putString(key, value);
        return editor.commit();
    }
    public static boolean remove(final Context context, final String key) {
        final SharedPreferences.Editor editor = SpUtils.getSharedPreferences(context).edit();
        editor.remove(key);
        return editor.commit();
    }



}
