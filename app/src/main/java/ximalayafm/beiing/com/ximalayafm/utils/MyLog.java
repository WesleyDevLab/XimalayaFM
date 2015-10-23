package ximalayafm.beiing.com.ximalayafm.utils;

import android.util.Log;

import ximalayafm.beiing.com.ximalayafm.BuildConfig;

/**
 * Date:2015/10/23
 * Author:Beiing
 * Email:15764230067@163.com
 **/

public final class MyLog {

    public static final boolean LOG_ON = BuildConfig.DEBUG;

    private MyLog(){

    }

    public static void d(String tag, String msg){
        if(LOG_ON){
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg){
        if(LOG_ON){
            Log.i(tag, msg);
        }
    }




}







