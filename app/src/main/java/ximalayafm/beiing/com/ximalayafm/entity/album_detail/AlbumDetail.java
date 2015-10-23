package ximalayafm.beiing.com.ximalayafm.entity.album_detail;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import ximalayafm.beiing.com.ximalayafm.entity.AlbumBasic;

/**
 * Date:2015/10/23
 * Author:Beiing
 * Email:15764230067@163.com
 **/

public class AlbumDetail extends AlbumBasic{


    /**
     * categoryId : 4
     * categoryName : 综艺娱乐
     * coverOrigin : http://fdfs.xmcdn.com/group5/M03/A6/D8/wKgDtlR1MD_T1DQHAANqZDyk48s720.jpg
     * coverSmall : http://fdfs.xmcdn.com/group5/M03/A6/D8/wKgDtlR1MD_T1DQHAANqZDyk48s720_mobile_small.jpg
     * coverWebLarge : http://fdfs.xmcdn.com/group5/M03/A6/D8/wKgDtlR1MD_T1DQHAANqZDyk48s720_web_large.jpg
     * createdAt : 1376650745000
     * updatedAt : 1435591762000
     * uid : 2629577
     * nickname : 枕边疯电台
     * isVerified : true
     * avatarPath : http://fdfs.xmcdn.com/group3/M00/64/F8/wKgDslJ68TnQpnVPAAU-A1zGGqI128_mobile_small.jpg
     * intro : 更多请关注微信公众账号： caicaifm
     * introRich : 更多请关注微信公众账号： caicaifm
     * shares : 0
     * hasNew : false
     * isFavorite : false
     * playTimes : 142941681
     * status : 1
     * serializeStatus : 0
     */

    private long categoryId;
    private String categoryName;
    private String coverOrigin;
    private String coverSmall;
    private String coverWebLarge;
    private long createdAt;
    private long updatedAt;
    private long uid;
    private String nickname;
    private boolean isVerified;
    private String avatarPath;
    private String intro;
    private String introRich;
    private int shares;
    private boolean hasNew;
    private boolean isFavorite;
    private long playTimes;
    private int status;
    private int serializeStatus;


    @Override
    public void parseJson(JSONObject json) throws JSONException {
        if (json != null) {
            super.parseJson(json);
            categoryId = json.getLong("categoryId");
            categoryName = json.getString("categoryName");
            coverOrigin = json.getString("coverOrigin");
            coverSmall = json.getString("coverSmall");
            coverWebLarge = json.getString("coverWebLarge");
            createdAt = json.getLong("createdAt");
            updatedAt = json.getLong("updatedAt");
            uid = json.getLong("uid");
            nickname = json.getString("nickname");
            isVerified = json.getBoolean("isVerified");
            avatarPath = json.getString("avatarPath");
            intro = json.getString("intro");
            introRich = json.getString("introRich");
            shares = json.getInt("shares");
            hasNew = json.getBoolean("hasNew");
            isFavorite = json.getBoolean("isFavorite");
            playTimes = json.getLong("playTimes");
            status = json.getInt("status");
            serializeStatus = json.getInt("serializeStatus");
        }
    }


    public long getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getCoverOrigin() {
        return coverOrigin;
    }

    public String getCoverSmall() {
        return coverSmall;
    }

    public String getCoverWebLarge() {
        return coverWebLarge;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public long getUid() {
        return uid;
    }

    public String getNickname() {
        return nickname;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public String getIntro() {
        return intro;
    }

    public String getIntroRich() {
        return introRich;
    }

    public int getShares() {
        return shares;
    }

    public boolean isHasNew() {
        return hasNew;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public long getPlayTimes() {
        return playTimes;
    }

    public int getStatus() {
        return status;
    }

    public int getSerializeStatus() {
        return serializeStatus;
    }

    @Override
    public String toString() {
        return "AlbumDetail{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", coverOrigin='" + coverOrigin + '\'' +
                ", coverSmall='" + coverSmall + '\'' +
                ", coverWebLarge='" + coverWebLarge + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", uid=" + uid +
                ", nickname='" + nickname + '\'' +
                ", isVerified=" + isVerified +
                ", avatarPath='" + avatarPath + '\'' +
                ", intro='" + intro + '\'' +
                ", introRich='" + introRich + '\'' +
                ", shares=" + shares +
                ", hasNew=" + hasNew +
                ", isFavorite=" + isFavorite +
                ", playTimes=" + playTimes +
                ", status=" + status +
                ", serializeStatus=" + serializeStatus +
                '}';
    }
}
