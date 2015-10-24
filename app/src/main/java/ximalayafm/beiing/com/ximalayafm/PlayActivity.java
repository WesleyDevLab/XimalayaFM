package ximalayafm.beiing.com.ximalayafm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import ximalayafm.beiing.com.ximalayafm.entity.album_detail.TrackBig;
import ximalayafm.beiing.com.ximalayafm.service.PlayService;

public class PlayActivity extends Activity implements View.OnClickListener {

    public static void startPlayActivity(Context context, ArrayList<TrackBig> trackBigs, int position){
        Intent intent = new Intent(context, PlayActivity.class);
        intent.putParcelableArrayListExtra(Constants.TRACK_LIST, trackBigs);
        intent.putExtra(Constants.CURRENT_PLAY_POSITION, position);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        initViews();


        //获取传来的数据
        Intent intent = getIntent();
        trackBigs = intent.getParcelableArrayListExtra(Constants.TRACK_LIST);
        curPlayPosition = intent.getIntExtra(Constants.CURRENT_PLAY_POSITION, -1);

        //测试结束后数据是否完整：测试ok
//        Log.d("---------==========", "trackBigs : " + trackBigs.toString());

        TrackBig trackBig = trackBigs.get(curPlayPosition);

        updateTopUI(trackBig);

        //播放音乐--

        Intent serviceIntent = new Intent(this, PlayService.class);
        serviceIntent.putExtra(Constants.INTENT_EXTRA_CHANGE_MUSIC, true);
        serviceIntent.putExtra(Constants.INTENT_EXTRA_MUSIC_PATH, trackBig.getPlayUrl64());
        startService(serviceIntent);

    }

    private void initViews() {
        backIb = (ImageButton) findViewById(R.id.ac_play_back);
        titletv = (TextView) findViewById(R.id.ac_play_title);
        playTimesTv = (TextView) findViewById(R.id.ac_play_times_tv);
        alarmClockIb = (ImageButton) findViewById(R.id.ac_play_alarm_clock);
        albumIconIv = (ImageView) findViewById(R.id.ac_play_album_icon);
        playListTv = (TextView) findViewById(R.id.ac_play_list);
        playHistoryTv = (TextView) findViewById(R.id.ac_play_history);

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

    private void updateTopUI(TrackBig trackBig){
        titletv.setText(trackBig.getTitle());
        playTimesTv.setText(String.valueOf(trackBig.getPlaytimes()));
        Picasso.with(this).load(trackBig.getCoverSmall()).into(albumIconIv);
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
                break;

            case R.id.ac_notify_pre_ib:
                // TODO 点击上一首按钮处理
                break;

            case R.id.ac_notify_next_ib:
                // TODO 点击下一首按钮处理
                break;
        }
    }
}
