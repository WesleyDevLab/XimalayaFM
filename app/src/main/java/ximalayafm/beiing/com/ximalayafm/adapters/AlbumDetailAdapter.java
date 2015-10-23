package ximalayafm.beiing.com.ximalayafm.adapters;

import android.content.Context;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ximalayafm.beiing.com.ximalayafm.R;
import ximalayafm.beiing.com.ximalayafm.entity.album_detail.TrackItem;

/**
 * Date:2015/10/23
 * Author:Beiing
 * Email:15764230067@163.com
 **/

public class AlbumDetailAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;

    private List<TrackItem> trackItems;

    public AlbumDetailAdapter(Context context ){
        this.context = context;
        inflater = LayoutInflater.from(context);
        trackItems = new ArrayList<>();
    }

    public void addData(List<TrackItem> items){
        trackItems.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return trackItems == null ? 0 : trackItems.size();
    }

    @Override
    public Object getItem(int i) {
        return trackItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        View ret = null;
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_album_track, parent, false);
            holder.trackTitle = (TextView) convertView.findViewById(R.id.item_album_track_title);
            holder.trackTime = (TextView) convertView.findViewById(R.id.item_album_track_time);
            holder.playCountTv = (TextView) convertView.findViewById(R.id.item_album_play_count_tv);
            holder.allTimeTv = (TextView) convertView.findViewById(R.id.item_album_alltime_tv);
            holder.commentsCount = (TextView) convertView.findViewById(R.id.item_album_comments_tv);
            holder.downloadIv = (ImageView) convertView.findViewById(R.id.item_album_download_iv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        TrackItem item = trackItems.get(position);
        holder.trackTitle.setText(item.getTitle());
        holder.trackTime.setText((System.currentTimeMillis() - item.getCreatedAt()) + "");
        holder.playCountTv.setText(item.getPlaytimes() + "");
        holder.allTimeTv.setText(item.getDuration() + "");
        holder.commentsCount.setText(item.getComments() + "");



        return convertView;
    }

    private static class ViewHolder{
        TextView trackTitle;
        TextView trackTime;
        TextView playCountTv;
        TextView allTimeTv;
        TextView commentsCount;
        ImageView downloadIv;
    }
}




















