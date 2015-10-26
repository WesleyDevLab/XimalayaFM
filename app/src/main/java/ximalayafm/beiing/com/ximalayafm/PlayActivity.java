package ximalayafm.beiing.com.ximalayafm;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Parcelable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import ximalayafm.beiing.com.ximalayafm.application.FMApplication;
import ximalayafm.beiing.com.ximalayafm.entity.album_detail.TrackBig;
import ximalayafm.beiing.com.ximalayafm.service.PlayService;

public class PlayActivity extends Activity implements View.OnClickListener {

    public static void startPlayActivity(Context context, ArrayList<TrackBig> trackBigs, int position){
        Intent intent = new Intent(context, PlayActivity.class);
        intent.putParcelableArrayListExtra(Constants.TRACK_LIST, trackBigs);
        intent.putExtra(Constants.INTENT_EXTRA_MUSIC_POSITION, position);
        context.startActivity(intent);
    }

    //当前播放列表
    private ArrayList<TrackBig> trackBigs;
    //当前播放位置
    private int curPlayPosition;

    //--一大波UI视图
    private ImageButton backIb;
    private TextView titletv;
    private TextView playTimesTv;
    private ImageButton alarmClockIb;
    private ImageView albumIconIv;
    private TextView playListTv;
    private TextView playHistoryTv;

    private ImageButton notifyPlayIb;
    private ImageButton notifyPreIb;
    private ImageButton notifyNextIb;


    SeekBar seekBar;// 进度条
    TextView nowTimeTv;//当前时间
    TextView totalTimeTv;//总时间

    private SimpleDateFormat dateFormat;
    private LocalBroadcastManager lbManager;// 本地广播管理器

    PrgReceiver prgReceiver;// 进度广播接收者

    private boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        //初始化控件
        initViews();

        dateFormat = new SimpleDateFormat("mm:ss", Locale.CHINA);
        prgReceiver = new PrgReceiver();
        lbManager = LocalBroadcastManager.getInstance(this);
        IntentFilter proFilter = new IntentFilter();
        proFilter.addAction(Constants.CAST_ACTION_MUSIC_PROGRESS);// 注册进度广播频道
        proFilter.addAction(Constants.CAST_ACTION_MUSIC_COMPLETE);// 播放完成的通知
        proFilter.addAction(Constants.CAST_ACTION_MUSIC_START);// 播放开始的广播
        lbManager.registerReceiver(prgReceiver, proFilter);



        //获取传来的数据
        Intent intent = getIntent();
        trackBigs = intent.getParcelableArrayListExtra(Constants.TRACK_LIST);
        curPlayPosition = intent.getIntExtra(Constants.INTENT_EXTRA_MUSIC_POSITION, -1);

        //播放音乐--
        playMusic(curPlayPosition, true);

        //更新UI
        updateOtherUI(curPlayPosition);
        Toast.makeText(this, "正在请求数据，稍后播放", Toast.LENGTH_SHORT).show();

    }

    private void initViews() {
        backIb = (ImageButton) findViewById(R.id.ac_play_back);
        titletv = (TextView) findViewById(R.id.ac_play_title);
        playTimesTv = (TextView) findViewById(R.id.ac_play_times_tv);
        alarmClockIb = (ImageButton) findViewById(R.id.ac_play_alarm_clock);
        albumIconIv = (ImageView) findViewById(R.id.ac_play_album_icon);
        playListTv = (TextView) findViewById(R.id.ac_play_list);
        playHistoryTv = (TextView) findViewById(R.id.ac_play_history);


        seekBar = (SeekBar) findViewById(R.id.ac_play_seekbar);
        nowTimeTv = (TextView) findViewById(R.id.a_play_nowtime_tv);
        totalTimeTv = (TextView) findViewById(R.id.a_play_totaltime_tv);


        notifyPlayIb = (ImageButton) findViewById(R.id.ac_notify_play_ib);
        notifyPreIb = (ImageButton) findViewById(R.id.ac_notify_pre_ib);
        notifyNextIb = (ImageButton) findViewById(R.id.ac_notify_next_ib);

        backIb.setOnClickListener(this);
        alarmClockIb.setOnClickListener(this);
        playListTv.setOnClickListener(this);
        playHistoryTv.setOnClickListener(this);


        notifyPlayIb.setOnClickListener(this);
        notifyPreIb.setOnClickListener(this);
        notifyNextIb.setOnClickListener(this);

    }



    /**
     * 进度条拖动事件
     */
    private void progEvent() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int cur = seekBar.getProgress();
                Intent intent = new Intent(
                        Constants.CAST_ACTION_SEEKBAR_PROCESS);
                intent.putExtra(Constants.INTENT_EXTRA_MUSIC_CUR_LEN, cur);
                lbManager.sendBroadcast(intent);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // 拖动进度条时，当前时间刷新
                nowTimeTv.setText(dateFormat.format(new Date(progress)));
            }
        });

    }



//    /**
//     * 更新UI
//     * @param position
//     */
//    private void updateTopUI(int position, boolean playNew){
//        if(playNew){
//            if(position != -1){
//                TrackBig trackBig = FMApplication.INSTANCE.getPlayList().get(position);
//                titletv.setText(trackBig.getTitle());
//                playTimesTv.setText(String.valueOf(trackBig.getPlaytimes()));
//                Picasso.with(this).load(trackBig.getCoverSmall()).into(albumIconIv);
//                //播放新的音乐时，
//                seekBar.setProgress(0);
//            }
//            //设置播放按钮变为 暂停
//            notifyPlayIb.setBackgroundResource(R.drawable.notify_pause_selector);
//            Toast.makeText(this, "updateTopUI : 暂停按钮", Toast.LENGTH_SHORT).show();
//        } else {
//            //设置播放按钮变为 播放
//            notifyPlayIb.setBackgroundResource(R.drawable.notify_play_selector);
//            Toast.makeText(this, "updateTopUI : 播放按钮", Toast.LENGTH_SHORT).show();
//        }
//    }

    /**
     * 更新除了播放按钮之外的UI控件
     */
    private void updateOtherUI(int position){
        if(position > -1 && position < FMApplication.INSTANCE.getPlayList().size()){
            TrackBig trackBig = FMApplication.INSTANCE.getPlayList().get(position);
            titletv.setText(trackBig.getTitle());
            playTimesTv.setText(String.valueOf(trackBig.getPlaytimes()));
            Picasso.with(this).load(trackBig.getCoverSmall()).into(albumIconIv);
            //播放新的音乐时，
            seekBar.setProgress(0);
        }
    }

    /**
     * 更新播放按钮
     * @param isPlay
     */
    private void updatePlayUI(boolean isPlay){
        if(isPlay){
            //设置播放按钮变为 暂停
            notifyPlayIb.setBackgroundResource(R.drawable.notify_pause_selector);
            Toast.makeText(this, "updateTopUI : 暂停按钮", Toast.LENGTH_SHORT).show();
        } else {
            //设置播放按钮变为 播放
            notifyPlayIb.setBackgroundResource(R.drawable.notify_play_selector);
            Toast.makeText(this, "updateTopUI : 播放按钮", Toast.LENGTH_SHORT).show();
        }
    }




    /**
     *  通知服务播放对应位置的音乐
     * @param position
     */
    private void playMusic(int position, boolean playNew){
        Intent serviceIntent = new Intent(this, PlayService.class);
        serviceIntent.putExtra(Constants.INTENT_EXTRA_CHANGE_MUSIC, playNew);
        if(playNew){
            serviceIntent.putExtra(Constants.INTENT_EXTRA_MUSIC_POSITION, position);
        }
        startService(serviceIntent);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ac_play_back:
                finish();
                break;

            case R.id.ac_play_alarm_clock:
                break;

            case R.id.ac_play_list:
                break;

            case R.id.ac_play_history:
                break;

            case R.id.ac_notify_play_ib:
                // TODO 点击播放按钮处理
                isPlaying = ! isPlaying;
                Toast.makeText(this, "isPlaying : " + isPlaying, Toast.LENGTH_SHORT).show();
                playMusic(-1, isPlaying);
                //更新playUI
                updatePlayUI(isPlaying);
                break;

            case R.id.ac_notify_pre_ib:
                // TODO 点击上一首按钮处理
                if(--curPlayPosition == -1)
                    curPlayPosition = FMApplication.INSTANCE.getPlayList().size() - 1;
                playMusic(curPlayPosition, true);
                Toast.makeText(this, "上一首", Toast.LENGTH_SHORT).show();

                updateOtherUI(curPlayPosition);
                break;

            case R.id.ac_notify_next_ib:
                // TODO 点击下一首按钮处理
                if(++curPlayPosition == FMApplication.INSTANCE.getPlayList().size())
                    curPlayPosition = 0;
                playMusic(curPlayPosition, true);
                Toast.makeText(this, "下一首", Toast.LENGTH_SHORT).show();

                updateOtherUI(curPlayPosition);
                break;
        }
    }

    /**
     * 自定义广播接受者
     */
    class PrgReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO 接收播放服务组件中发送的进度广播
            String action = intent.getAction();
            if (action.equals(Constants.CAST_ACTION_MUSIC_PROGRESS)) {
                int max = intent.getIntExtra(
                       Constants.INTEXT_EXTRA_MUSIC_TOTAL_LEN, 0);
                int cur = intent.getIntExtra(
                        Constants.INTENT_EXTRA_MUSIC_CUR_LEN, 0);
                seekBar.setMax(max);
                seekBar.setProgress(cur);

                nowTimeTv.setText(dateFormat.format(new Date(cur)));
                totalTimeTv.setText(dateFormat.format(new Date(max)));
            } else if (action.equals(Constants.CAST_ACTION_MUSIC_COMPLETE)) {
                // 接收service发的播放完成的通知
                notifyPlayIb.setBackgroundResource(R.drawable.notify_play_selector);

                // 正常播放完成,直接跳到下一首
                curPlayPosition = intent.getIntExtra(Constants.INTENT_EXTRA_MUSIC_POSITION, -1);
                //更新UI
                updateOtherUI(curPlayPosition);

            } else if(action.equals(Constants.CAST_ACTION_MUSIC_START)){
                // TODO 开始播放
                notifyPlayIb.setBackgroundResource(R.drawable.notify_pause_selector);
                isPlaying = true;
                updatePlayUI(isPlaying);
            }
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        lbManager.unregisterReceiver(prgReceiver);
    }

}
