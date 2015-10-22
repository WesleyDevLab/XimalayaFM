package ximalayafm.beiing.com.ximalayafm.entity.discoverrecommend;

import org.json.JSONException;
import org.json.JSONObject;

import ximalayafm.beiing.com.ximalayafm.entity.ColumnBasic;

/**
 * Created by Administrator on 2015/10/21.
 */

/**
 * 精品听单----------->
 "columnType": 1,
 "specialId": 348,
 +++"title": "5个如雷贯耳的脱口秀，高智商必听！",
 +++"subtitle": "人生不应该只有眼前的苟且，还有诗和远方！\r\n生活从来就不应该只有简单的吃饭穿衣，安身立命，人生除了温饱，还应该有另一个精神的高度，最终决定这个高度的，一定是眼界和见识，以及你碰到一个什么样的人生导师",
 "footnote": "5张专辑",
 +++"coverPath": "http://fdfs.xmcdn.com/group14/M04/4E/68/wKgDY1WyIl_jgde2AAG79hZ3Ml4209_mobile_small.jpg",
 +++"contentType": "1"
 */
public class SpecialRecommend extends ColumnBasic {
    private int columnType;
    private long specialId;
    private String footnote;

    public void parseJson(JSONObject json) throws JSONException {
        super.parseJson(json);
        columnType = json.getInt("columnType");
        specialId = json.getLong("specialId");
        footnote = json.optString("footnote");
    }

    public int getColumnType() {
        return columnType;
    }

    public void setColumnType(int columnType) {
        this.columnType = columnType;
    }

    public long getSpecialId() {
        return specialId;
    }

    public void setSpecialId(long specialId) {
        this.specialId = specialId;
    }

    public String getFootnote() {
        return footnote;
    }

    public void setFootnote(String footnote) {
        this.footnote = footnote;
    }
}









