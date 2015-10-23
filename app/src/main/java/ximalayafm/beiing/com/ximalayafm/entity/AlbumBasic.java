package ximalayafm.beiing.com.ximalayafm.entity;

/**
 * Created by Administrator on 2015/10/21.
 */

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 专辑基本信息(小编推荐 / 热门推荐 公用)
 * @see ximalayafm.beiing.com.ximalayafm.entity.discoverrecommend.AlbumRecommend
 */
public class AlbumBasic {

    /**
     * "albumId": 344497,
     "coverLarge": "http://fdfs.xmcdn.com/group6/M02/35/45/wKgDhFTg4w_SDkc9AAT-fXngGBY184_mobile_large.jpg",
     "title": "黑先生在麦田咖啡馆",
     "tags": "民谣,80后,文艺",
     "tracks": 117,
     */

    private long albumId;
    private String coverLarge;
    private String title;
    private String tags = "";
    /**
     * 曲目数
     */
    private long tracks;

    public void parseJson(JSONObject json) throws JSONException {
        if(json != null){
            albumId = json.getLong("albumId");
            coverLarge = json.getString("coverLarge");
            title = json.getString("title");
            tags = json.optString("tags");//这个可能没有，所以需要使用opt
            tracks = json.getLong("tracks");
        }
    }

    public long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }

    public String getCoverLarge() {
        return coverLarge;
    }

    public void setCoverLarge(String coverLarge) {
        this.coverLarge = coverLarge;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public long getTracks() {
        return tracks;
    }

    public void setTracks(long tracks) {
        this.tracks = tracks;
    }
}




