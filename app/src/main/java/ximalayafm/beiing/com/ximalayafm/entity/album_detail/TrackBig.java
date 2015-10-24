package ximalayafm.beiing.com.ximalayafm.entity.album_detail;

/**
 * Date:2015/10/23
 * Author:Beiing
 * Email:15764230067@163.com
 **/


import org.json.JSONException;
import org.json.JSONObject;

import ximalayafm.beiing.com.ximalayafm.entity.Parsable;

/**
 * 接口 17 返回的 一条曲目的详情：内容比较多--- 包含所有接口20返回的一条曲目的字段
 * ---所以以接口20得到的一条曲目作为父类
 */
public class TrackBig extends TrackItem {


    /**
     * downloadAacUrl : http://audio.xmcdn.com/group16/M06/4A/D8/wKgDalWuZi7xSOUHAFV7hb3I8KE615.m4a
     * processState : 2
     * createdAt : 1437492991000
     * coverSmall : http://fdfs.xmcdn.com/group7/M04/4B/38/wKgDX1WuZcOT5mfGAAGbzY7dvWQ896_mobile_small.jpg
     * coverLarge : http://fdfs.xmcdn.com/group7/M04/4B/38/wKgDX1WuZcOT5mfGAAGbzY7dvWQ896_mobile_large.jpg
     * nickname : 糗事播报
     * smallLogo : http://fdfs.xmcdn.com/group5/M05/90/97/wKgDtlRsQJmxnPW6AABmeagRyv0922_mobile_small.png
     * userSource : 1
     * albumId : 203355
     * albumTitle : 段子来了
     * albumImage : http://fdfs.xmcdn.com/group5/M03/A6/D8/wKgDtlR1MD_T1DQHAANqZDyk48s720_mobile_small.jpg
     * orderNum : 99999999
     * opType : 2
     * refUid : 2629577
     * refNickname : 枕边疯电台
     * refSmallLogo : http://fdfs.xmcdn.com/group3/M00/64/F8/wKgDslJ68TnQpnVPAAU-A1zGGqI128_mobile_small.jpg
     * isPublic : true
     * likes : 5042
     * playtimes : 486814
     * comments : 1206
     * shares : 0
     * status : 1
     * downloadSize : 7533046
     * downloadAacSize : 5602181
     */

    private String downloadAacUrl;
    private int processState;
    private long createdAt;
    private String coverSmall;
    private String coverLarge;
    private String nickname;
    private String smallLogo;
    private int userSource;
    private long albumId;
    private String albumTitle;
    private String albumImage;
    private int orderNum;
    private int opType;
    private long refUid;
    private String refNickname;
    private String refSmallLogo;
    private boolean isPublic;
    private int likes;
    private int playtimes;
    private int comments;
    private int shares;
    private int status;
    private int downloadSize;
    private int downloadAacSize;


    @Override
    public void parseJson(JSONObject json) throws JSONException {
        if (json != null) {
            super.parseJson(json);
            downloadAacUrl = json.getString("downloadAacUrl");
            processState = json.getInt("processState");
            createdAt = json.getLong("createdAt");
            coverSmall = json.getString("coverSmall");
            coverLarge = json.getString("coverLarge");
            nickname = json.getString("nickname");
            smallLogo = json.getString("smallLogo");
            userSource = json.getInt("userSource");
            albumId = json.getLong("albumId");
            albumTitle = json.getString("albumTitle");
            albumImage = json.getString("albumImage");
            orderNum = json.getInt("orderNum");
            opType = json.getInt("opType");
            refUid = json.optLong("refUid");
            refNickname = json.optString("refNickname");
            refSmallLogo = json.optString("refSmallLogo");
            isPublic = json.getBoolean("isPublic");
            likes = json.getInt("likes");
            playtimes = json.getInt("playtimes");
            comments = json.getInt("comments");
            shares = json.getInt("shares");
            status = json.getInt("status");
            downloadSize = json.getInt("downloadSize");
            downloadAacSize = json.getInt("downloadAacSize");
        }
    }


    public String getDownloadAacUrl() {
        return downloadAacUrl;
    }

    public int getProcessState() {
        return processState;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public String getCoverSmall() {
        return coverSmall;
    }

    public String getCoverLarge() {
        return coverLarge;
    }

    public String getNickname() {
        return nickname;
    }

    public String getSmallLogo() {
        return smallLogo;
    }

    public int getUserSource() {
        return userSource;
    }

    public long getAlbumId() {
        return albumId;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public String getAlbumImage() {
        return albumImage;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public int getOpType() {
        return opType;
    }

    public long getRefUid() {
        return refUid;
    }

    public String getRefNickname() {
        return refNickname;
    }

    public String getRefSmallLogo() {
        return refSmallLogo;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public int getLikes() {
        return likes;
    }

    public int getPlaytimes() {
        return playtimes;
    }

    public int getComments() {
        return comments;
    }

    public int getShares() {
        return shares;
    }

    public int getStatus() {
        return status;
    }

    public int getDownloadSize() {
        return downloadSize;
    }

    public int getDownloadAacSize() {
        return downloadAacSize;
    }
}





















