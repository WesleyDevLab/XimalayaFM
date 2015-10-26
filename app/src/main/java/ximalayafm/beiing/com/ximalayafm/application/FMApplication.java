package ximalayafm.beiing.com.ximalayafm.application;

import android.app.Application;

import java.util.ArrayList;

import ximalayafm.beiing.com.ximalayafm.entity.album_detail.TrackBig;

/**
 * Date:2015/10/24
 * Author:Beiing
 * Email:15764230067@163.com
 **/

public class FMApplication extends Application {


    public static FMApplication INSTANCE;


    /**
     * 全局播放列表
     */
    private ArrayList<TrackBig> playList;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }

    public ArrayList<TrackBig> getPlayList() {
        return playList;
    }

    public void setPlayList(ArrayList<TrackBig> playList) {
        this.playList = playList;
    }



}

















