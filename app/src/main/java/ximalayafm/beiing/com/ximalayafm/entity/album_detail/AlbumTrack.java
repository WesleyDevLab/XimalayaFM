package ximalayafm.beiing.com.ximalayafm.entity.album_detail;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ximalayafm.beiing.com.ximalayafm.utils.MyLog;

/**
 * Date:2015/10/23
 * Author:Beiing
 * Email:15764230067@163.com
 **/

public class AlbumTrack {

    /**
     * 专辑详情
     */
    private AlbumDetail albumDetail;

    /**
     * 曲目列表
     */
    private List<TrackBig> trackBigs;

    /**
     * 曲目总数
     */
    private int totalCount;


    public void parseJson(JSONObject json) throws JSONException {
        if (json != null) {
            JSONObject j_album = json.getJSONObject("album");
            albumDetail = new AlbumDetail();
            albumDetail.parseJson(j_album);

            trackBigs = new ArrayList<>();

            JSONObject j_track = json.getJSONObject("tracks");
            JSONArray ja_track = j_track.getJSONArray("list");
            int len = ja_track.length();
            for (int i = 0; i < len; i++) {
                JSONObject jj = ja_track.getJSONObject(i);
                TrackBig trackBig = new TrackBig();
                trackBig.parseJson(jj);
                trackBigs.add(trackBig);
            }

            totalCount = j_track.getInt("totalCount");
        }

    }


    public AlbumDetail getAlbumDetail() {
        return albumDetail;
    }

    public void setAlbumDetail(AlbumDetail albumDetail) {
        this.albumDetail = albumDetail;
    }

    public List<TrackBig> getTrackBigs() {
        return trackBigs;
    }

    public void setTrackBigs(List<TrackBig> trackBigs) {
        this.trackBigs = trackBigs;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
