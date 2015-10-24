package ximalayafm.beiing.com.ximalayafm.entity.album_detail;

/**
 * Date:2015/10/24
 * Author:Beiing
 * Email:15764230067@163.com
 **/

import org.json.JSONException;
import org.json.JSONObject;

import ximalayafm.beiing.com.ximalayafm.entity.Parsable;

/**
 * 接口20返回的曲目列表--
 */
public class TrackItem  implements Parsable{

    /**
     * trackId : 9221232
     * uid : 25806497
     * playUrl64 : http://fdfs.xmcdn.com/group12/M00/82/90/wKgDW1Ybjo2hwib4ADeoVolKYOI229.mp3
     * playUrl32 : http://fdfs.xmcdn.com/group12/M00/82/90/wKgDW1YbjouwMwDzABvUQrOWnMg240.mp3
     * downloadUrl : http://download.xmcdn.com/group7/M03/83/41/wKgDX1YbjoWDLVTTABzhAeMwf8c523.aac
     * playPathAacv164 : http://audio.xmcdn.com/group14/M0A/82/F5/wKgDZFYbjomTrQJjADhHybU7yOQ129.m4a
     * playPathAacv224 : http://audio.xmcdn.com/group7/M03/83/37/wKgDWlYbjofCwuZwABV957pwkik461.m4a
     * title : 专车身份证
     * duration : 455.92
     */

    private long trackId;
    private long uid;
    private String playUrl64;
    private String playUrl32;
    private String downloadUrl;
    private String playPathAacv164;
    private String playPathAacv224;
    private String title;
    private double duration;


    @Override
    public void parseJson(JSONObject json) throws JSONException {
        if (json != null) {
            trackId = json.getLong("trackId");
            uid = json.getLong("uid");
            playUrl64 = json.getString("playUrl64");
            playUrl32 = json.getString("playUrl32");
            downloadUrl = json.getString("downloadUrl");
            playPathAacv164 = json.getString("playPathAacv164");
            playPathAacv224 = json.getString("playPathAacv224");
            title = json.getString("title");
            duration = json.getDouble("duration");
        }
    }

    public long getTrackId() {
        return trackId;
    }

    public long getUid() {
        return uid;
    }

    public String getPlayUrl64() {
        return playUrl64;
    }

    public String getPlayUrl32() {
        return playUrl32;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public String getPlayPathAacv164() {
        return playPathAacv164;
    }

    public String getPlayPathAacv224() {
        return playPathAacv224;
    }

    public String getTitle() {
        return title;
    }

    public double getDuration() {
        return duration;
    }
}
