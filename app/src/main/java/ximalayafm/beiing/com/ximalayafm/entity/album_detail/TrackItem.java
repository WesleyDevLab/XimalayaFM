package ximalayafm.beiing.com.ximalayafm.entity.album_detail;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import ximalayafm.beiing.com.ximalayafm.entity.Parsable;
import ximalayafm.beiing.com.ximalayafm.utils.MyLog;

/**
 * Date:2015/10/23
 * Author:Beiing
 * Email:15764230067@163.com
 **/

public class TrackItem{


    /**
     * trackId : 7996526
     * uid : 2629577
     * playUrl64 : http://fdfs.xmcdn.com/group8/M08/52/5B/wKgDYFW2envSiMSyATE0a1NxlgI220.mp3
     * playUrl32 : http://fdfs.xmcdn.com/group10/M07/52/53/wKgDZ1W2ekPBgmC_AJiaTKE52lI991.mp3
     * downloadUrl : http://download.xmcdn.com/group10/M07/52/50/wKgDaVW2elHirDiwAJ5aSy_6VKU544.aac
     * playPathAacv164 : http://audio.xmcdn.com/group10/M07/52/51/wKgDaVW2emmhQ0h1ATSDth0VxeI658.m4a
     * playPathAacv224 : http://audio.xmcdn.com/group10/M07/52/53/wKgDZ1W2ejeTU5ytAHXCHrwQF60039.m4a
     * downloadAacUrl : http://audio.xmcdn.com/group10/M07/52/53/wKgDZ1W2ejeTU5ytAHXCHrwQF60039.m4a
     * title : 段子来了丨飞机一日千百里，电梯一屁几十米50728（采采）
     * duration : 2500.21
     * processState : 2
     * createdAt : 1438022270000
     * coverSmall : http://fdfs.xmcdn.com/group7/M07/53/26/wKgDWlW26MGQ8SKQAAFrgB20PU8213_mobile_small.jpg
     * coverLarge : http://fdfs.xmcdn.com/group7/M07/53/26/wKgDWlW26MGQ8SKQAAFrgB20PU8213_mobile_large.jpg
     * nickname : 枕边疯电台
     * smallLogo : http://fdfs.xmcdn.com/group3/M00/64/F8/wKgDslJ68TnQpnVPAAU-A1zGGqI128_mobile_small.jpg
     * userSource : 1
     * albumId : 203355
     * albumTitle : 段子来了
     * albumImage : http://fdfs.xmcdn.com/group5/M03/A6/D8/wKgDtlR1MD_T1DQHAANqZDyk48s720_mobile_small.jpg
     * orderNum : 99999999
     * opType : 1
     * isPublic : true
     * likes : 1126
     * playtimes : 80978
     * comments : 695
     * shares : 0
     * status : 1
     * downloadSize : 10377803
     * downloadAacSize : 7717406
     */

    private long trackId;
    private long uid;
    private String playUrl64;
    private String playUrl32;
    private String downloadUrl;
    private String playPathAacv164;
    private String playPathAacv224;
    private String downloadAacUrl;
    private String title;
    private double duration;
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
    private boolean isPublic;
    private int likes;
    private int playtimes;
    private int comments;
    private int shares;
    private int status;
    private int downloadSize;
    private int downloadAacSize;


//    public void parseJson(TrackItem item, JSONObject json){
//        if(json != null){
//            Gson gson = new Gson();;
//            item = gson.fromJson(json.toString(), TrackItem.class);
//
//            MyLog.d("++++++++++++++++++TrackItem++++++++++++++++++", "item :" + item.toString());
//        }
//    }


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

    public String getDownloadAacUrl() {
        return downloadAacUrl;
    }

    public String getTitle() {
        return title;
    }

    public double getDuration() {
        return duration;
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

    @Override
    public String toString() {
        return "TrackItem{" +
                "trackId=" + trackId +
                ", uid=" + uid +
                ", playUrl64='" + playUrl64 + '\'' +
                ", playUrl32='" + playUrl32 + '\'' +
                ", downloadUrl='" + downloadUrl + '\'' +
                ", playPathAacv164='" + playPathAacv164 + '\'' +
                ", playPathAacv224='" + playPathAacv224 + '\'' +
                ", downloadAacUrl='" + downloadAacUrl + '\'' +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                ", processState=" + processState +
                ", createdAt=" + createdAt +
                ", coverSmall='" + coverSmall + '\'' +
                ", coverLarge='" + coverLarge + '\'' +
                ", nickname='" + nickname + '\'' +
                ", smallLogo='" + smallLogo + '\'' +
                ", userSource=" + userSource +
                ", albumId=" + albumId +
                ", albumTitle='" + albumTitle + '\'' +
                ", albumImage='" + albumImage + '\'' +
                ", orderNum=" + orderNum +
                ", opType=" + opType +
                ", isPublic=" + isPublic +
                ", likes=" + likes +
                ", playtimes=" + playtimes +
                ", comments=" + comments +
                ", shares=" + shares +
                ", status=" + status +
                ", downloadSize=" + downloadSize +
                ", downloadAacSize=" + downloadAacSize +
                '}';
    }
}





















