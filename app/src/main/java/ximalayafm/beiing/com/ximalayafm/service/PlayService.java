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

    ProReceiver proReceiver;

    @Override
    public void onCreate() {
        super.onCreate();
        mPlayer = new MediaPlayer();
        // 获取本地广播管理器
        lbManager = LocalBroadcastManager.getInstance(getApplicationContext());

        proReceiver = new ProReceiver();

        lbManager.registerReceiver(proReceiver, new IntentFilter(Constants.CAST_ACTION_SEEKBAR_PROCESS));

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 判断是否播放新的歌曲
        if (intent.getBooleanExtra(Constants.INTENT_EXTRA_CHANGE_MUSIC, false)) {
            // 获取播放路径
            String path = intent.getStringExtra(Constants.INTENT_EXTRA_MUSIC_PATH);

//            Toast.makeText(getApplicationContext(), "service - path :" + path, Toast.LENGTH_SHORT).show();

//            Log.w("------------------== :", path );

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
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            if (mPlayer.isPlaying()) {
                mPlayer.pause();// 暂停
            } else {
                mPlayer.start();// 播放
                new ProgressThread().start();//启动进度线程
            }
        }

        return super.onStartCommand(intent, flags, startId);
    }


    /**
     * 播放一首音乐结束的回调方法
     * @param mediaPlayer
     */
    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        // TODO 发送播放完毕的广播
        Intent intent = new Intent(Constants.CAST_ACTION_MUSIC_COMPLETE);
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

                    Thread.sleep(200);//200ms发一次 ，必须大于0，否则 ANR
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
            int cur = 0;//从播放界面发送过来的进度
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
