package ximalayafm.beiing.com.ximalayafm.entity;

/**
 * Created by Administrator on 2015/10/21.
 */

/**
 * 精品听单 和 发现新奇 公共部分
 */


import org.json.JSONException;
import org.json.JSONObject;

/**
 * 发现新奇---->
 *"id": 1,
 "orderNum": 2,
 +++"title": "听友圈子",
 +++"subtitle": "给你获得一种超能力的机会，你选择什么超能力？",
 +++"coverPath": "http://fdfs.xmcdn.com/group9/M07/1C/8C/wKgDYlV3rd2zGc9PAAAgRAu1VLU052.png",
 +++"contentType": "xzone",
 "url": "",
 "sharePic": "",
 "enableShare": false,
 "contentUpdatedAt": 0
 * -----------------------------
 *
 *精品听单----------->
 * "columnType": 1,
 "specialId": 348,
 +++"title": "5个如雷贯耳的脱口秀，高智商必听！",
 +++"subtitle": "人生不应该只有眼前的苟且，还有诗和远方！\r\n生活从来就不应该只有简单的吃饭穿衣，安身立命，人生除了温饱，还应该有另一个精神的高度，最终决定这个高度的，一定是眼界和见识，以及你碰到一个什么样的人生导师",
 "footnote": "5张专辑",
 +++"coverPath": "http://fdfs.xmcdn.com/group14/M04/4E/68/wKgDY1WyIl_jgde2AAG79hZ3Ml4209_mobile_small.jpg",
 +++"contentType": "1"

 */
public class ColumnBasic {

    private String title;

    private String subtitle;

    private String coverPath;

    private String contentType;

    public void parseJson(JSONObject jsonObject) throws JSONException {
        if(jsonObject != null){
            title = jsonObject.getString("title");
            subtitle = jsonObject.getString("subtitle");
            coverPath = jsonObject.getString("coverPath");
            contentType = jsonObject.getString("contentType");
        }
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
