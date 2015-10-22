package ximalayafm.beiing.com.ximalayafm.entity.discoverrecommend;

import org.json.JSONException;
import org.json.JSONObject;

import ximalayafm.beiing.com.ximalayafm.entity.ColumnBasic;

/**
 * Created by Administrator on 2015/10/21.
 */

/**
 * *发现新奇---->
 "id": 1,
 "orderNum": 2,
 +++"title": "听友圈子",
 +++"subtitle": "给你获得一种超能力的机会，你选择什么超能力？",
 +++"coverPath": "http://fdfs.xmcdn.com/group9/M07/1C/8C/wKgDYlV3rd2zGc9PAAAgRAu1VLU052.png",
 +++"contentType": "xzone",
 "url": "",
 "sharePic": "",
 "enableShare": false,
 "contentUpdatedAt": 0
 */
public class DiscoveryRecommend extends ColumnBasic {

    private long id;
    private int orderNum;
    private String url;
    private String sharePic;
    private boolean enableShare;
    private int contentUpdatedAt;

    public void parseJson(JSONObject json) throws JSONException {
        super.parseJson(json);
        id = json.getLong("id");
        orderNum = json.getInt("orderNum");
        url = json.optString("url");
        sharePic = json.optString("sharePic");
        enableShare = json.getBoolean("enableShare");
        contentUpdatedAt = json.getInt("contentUpdatedAt");
    }


}
