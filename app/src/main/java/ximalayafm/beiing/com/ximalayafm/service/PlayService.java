package ximalayafm.beiing.com.ximalayafm.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import ximalayafm.beiing.com.ximalayafm.Constants;
import ximalayafm.beiing.com.ximalayafm.application.FMApplication;
import ximalayafm.beiing.com.ximalayafm.entity.album_detail.TrackBig;

/**
 * Date:2015/10/24
 * Author:Beiing
 * Email:15764230067@163.com
 **/

public class PlayService extends Service implements MediaPlayer.OnCompletionListener {


    /**
     * 音乐播放组件
     */
    private MediaPlayer mPlayer;

    /*
     * 本地广播管理者
     */
    private LocalBroadcastManager lbManager;

    private int sumLen;// 总时长

    private ProReceiver proReceiver;

    private int curPosition = 0;//当前播放位置

    @Override
    public void onCreate() {
        super.onCreate();
        mPlayer = new MediaPlayer();
        // 获取本地广播管理器
        lbManager = LocalBroadcastManager.getInstance(getApplicationContext());

        proReceiver = new ProReceiver();

        lbManager.registerReceiver(proReceiver, new IntentFilter(Constants.CAST_ACTION_SEEKBAR_PROCESS));

    }


    /**
     * 播放音乐
     * @param position
     */
    private void playMusic(int position){
        if(position >= 0 && position < FMApplication.INSTANCE.getPlayList().size()){
            String path = FMApplication.INSTANCE.getPlayList().get(position).getPlayUrl64();
            //播放新歌曲 - reset
            mPlayer.reset();
            try {
                mPlayer.setDataSource(path);
                mPlayer.prepareAsync();
                mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        if (mPlayer.isPlaying()) {
                            mPlayer.pause();// 暂停
                        } else {
                            mPlayer.start();// 播放
                            mPlayer.setOnCompletionListener(PlayService.this);

                            new ProgressThread().start();//启动进度线程

                            // TODO 发送开始播放广播给 PlayActivity
                            Intent intent = new Intent(Constants.CAST_ACTION_MUSIC_COMPLETE);
                            intent.putExtra(Constants.INTENT_EXTRA_MUSIC_POSITION, curPosition);
                            lbManager.sendBroadcast(intent);
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 判断是否播放新的歌曲
        if (intent.getBooleanExtra(Constants.INTENT_EXTRA_CHANGE_MUSIC, false)) {
            // 获取播放路径
//            String path = intent.getStringExtra(Constants.INTENT_EXTRA_MUSIC_PATH);
            curPosition = intent.getIntExtra(Constants.INTENT_EXTRA_MUSIC_POSITION, -1);
            if(curPosition != -1){
                playMusic(curPosition);
            }
        }else {
            if (mPlayer.isPlaying()) {
                mPlayer.pause();// 暂停
            } else {
                mPlayer.start();// 播放
                new ProgressThread().start();//启动进度线程
            }
        }

//            Toast.makeText(getApplicationContext(), "service - path :" + path, Toast.LENGTH_SHORT).show();

        return super.onStartCommand(intent, flags, startId);
    }


    /**
     * 播放一首音乐结束的回调方法
     * @param mediaPlayer
     */
    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        if(++curPosition == FMApplication.INSTANCE.getPlayList().size())
            curPosition = 0;
        playMusic(curPosition);

        // TODO 发送播放完毕的广播
        Intent intent = new Intent(Constants.CAST_ACTION_MUSIC_START);
        lbManager.sendBroadcast(intent);
    }


    /**
     * 计算播放进度的线程
     */
    class ProgressThread extends Thread {
        @Override
        public void run() {
            try {
                while (mPlayer != null && mPlayer.isPlaying()) {
                    sumLen = mPlayer.getDuration();
                    int currentPosition = mPlayer.getCurrentPosition();
                    // 准备发送进度广播
                    Intent intent = new Intent(Constants.CAST_ACTION_MUSIC_PROGRESS);
                    intent.putExtra(Constants.INTEXT_EXTRA_MUSIC_TOTAL_LEN, sumLen);
                    intent.putExtra(Constants.INTENT_EXTRA_MUSIC_CUR_LEN, currentPosition);
                    lbManager.sendBroadcast(intent);

                    Thread.sleep(500);//200ms发一次 ，必须大于0，否则 ANR
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 进度广播接收者
     */
    class ProReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int cur = intent.getIntExtra(Constants.INTENT_EXTRA_MUSIC_CUR_LEN, 0);//从播放界面发送过来的进度

            mPlayer.seekTo(cur);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        lbManager.unregisterReceiver(proReceiver);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
