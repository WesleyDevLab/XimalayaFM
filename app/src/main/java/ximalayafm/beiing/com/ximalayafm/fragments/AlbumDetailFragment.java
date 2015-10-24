package ximalayafm.beiing.com.ximalayafm.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.squareup.picasso.Picasso;

import ximalayafm.beiing.com.ximalayafm.Constants;
import ximalayafm.beiing.com.ximalayafm.R;
import ximalayafm.beiing.com.ximalayafm.TaskAction;
import ximalayafm.beiing.com.ximalayafm.adapters.AlbumDetailAdapter;
import ximalayafm.beiing.com.ximalayafm.entity.album_detail.AlbumDetail;
import ximalayafm.beiing.com.ximalayafm.entity.album_detail.AlbumTrack;
import ximalayafm.beiing.com.ximalayafm.tasks.AlbumDetailTask;
import ximalayafm.beiing.com.ximalayafm.tasks.TaskCallBack;
import ximalayafm.beiing.com.ximalayafm.tasks.TaskResult;
import ximalayafm.beiing.com.ximalayafm.widgets.MaxHeightListView;

/**
 * Date:2015/10/23
 * Author:Beiing
 * Email:15764230067@163.com
 **/

public class AlbumDetailFragment extends Fragment implements TaskCallBack, View.OnClickListener {


    public static AlbumDetailFragment newInstance(long albumId, long trackId){
        AlbumDetailFragment adf = new AlbumDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(Constants.KEY_ALBUMID, albumId);
        bundle.putLong(Constants.KEY_TRACKID, trackId);
        adf.setArguments(bundle);
        return adf;
    }

    /**
     * 专辑详情 url
     * http://mobile.ximalaya.com/mobile/others/ca/album/track/203355/true/1/20
     */
    static final String URL = "http://mobile.ximalaya.com/mobile/others/ca/album/track/%d/true/%d/20";

    private long trackId;

    private long albumId;

    private int curPage;

//    private AlbumTrack albumTrack;


    private PullToRefreshScrollView ptrScrollView;
    private TextView trackCountTv;
    private TextView sortTv;//排序按钮album_detail_page_select_tv
    private TextView pageSelectTv;

    private MaxHeightListView mhListview;

    private AlbumDetailAdapter adapter;


    //---------专辑详情顶部部分
    TextView albumTitleTv;
    ImageButton backIb;
    ImageButton moreIb;
    ImageView albumIcon;
    TextView playCountTv;
    ImageView artistHead;
    TextView artistName;
    TextView albumIntroduction;
    TextView albumTag;
    TextView collectTv;
    TextView downloadTv;
    TextView relatedTv;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getArguments();
        albumId = arguments.getLong(Constants.KEY_ALBUMID);
        trackId = arguments.getLong(Constants.KEY_TRACKID);
    }

    @Override
    public void onResume() {
        super.onResume();
        AlbumDetailTask task = new AlbumDetailTask(this);
        String url = getUrl();
        task.execute(url);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View ret = inflater.inflate(R.layout.fragment_album_detail, container, false);
        ptrScrollView = (PullToRefreshScrollView) ret.findViewById(R.id.fm_album_detail_scoll_view);
        trackCountTv = (TextView) ret.findViewById(R.id.album_detail_track_count_tv);
        sortTv = (TextView) ret.findViewById(R.id.album_detail_sort_tv);
        pageSelectTv = (TextView) ret.findViewById(R.id.album_detail_page_select_tv);

        mhListview = (MaxHeightListView) ret.findViewById(R.id.album_detail_track_list);
        adapter = new AlbumDetailAdapter(getActivity());
        mhListview.setAdapter(adapter);

        //----------专辑顶部
        albumTitleTv = (TextView) ret.findViewById(R.id.album_detail_title);
        backIb = (ImageButton) ret.findViewById(R.id.album_detail_back);
        backIb.setOnClickListener(this);
        moreIb = (ImageButton) ret.findViewById(R.id.album_detail_more_ib);
        albumIcon = (ImageView) ret.findViewById(R.id.album_detail_album_icon);
        playCountTv = (TextView) ret.findViewById(R.id.album_detail_play_count_tv);
        artistHead = (ImageView) ret.findViewById(R.id.album_detail_artist_head);
        artistName = (TextView) ret.findViewById(R.id.album_detail_artist_name);
        albumIntroduction = (TextView) ret.findViewById(R.id.album_detail_album_introduction);
        albumTag = (TextView) ret.findViewById(R.id.album_detail_album_tags);
        collectTv = (TextView) ret.findViewById(R.id.album_detail_collect_tv);
        downloadTv = (TextView) ret.findViewById(R.id.album_detail_download_tv);
        relatedTv = (TextView) ret.findViewById(R.id.album_detail_related_tv);

        return ret;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ptrScrollView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        ptrScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
                AlbumDetailTask task = new AlbumDetailTask(AlbumDetailFragment.this);
                task.execute(getUrl());
            }
        });

    }

    private String getUrl(){
        return String.format(URL, albumId, ++curPage);
    }

    @Override
    public void onTaskFinished(TaskResult result) {
        if(result != null && result.action == TaskAction.TASK_ACTION_ALBUM_DETAIL){
            if(result.resultCode == Constants.TASK_RESULT_OK){
                if(result.data instanceof AlbumTrack){
                    AlbumTrack at = (AlbumTrack) result.data;
                    adapter.addData(at.getTrackBigs());

                    updateUI(at.getAlbumDetail(), at.getTotalCount());
                }
            }

            ptrScrollView.onRefreshComplete();
        }
    }


    private void updateUI(AlbumDetail detail, int count){
        albumTitleTv.setText(detail.getTitle());
        playCountTv.setText(detail.getPlayTimes() + "");
        artistName.setText(detail.getNickname());
        albumIntroduction.setText(detail.getIntro());

        albumTag.setText(detail.getTags());

        trackCountTv.setText("共" + count + "集");

        Picasso.with(getActivity()).load(detail.getAvatarPath()).into(artistHead);
        Picasso.with(getActivity()).load(detail.getCoverSmall()).into(albumIcon);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.album_detail_back :
                getActivity().finish();
                break;
        }
    }
}









