package ximalayafm.beiing.com.ximalayafm.entity.discoverrecommend;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * 精品听单
 */

public class DiscoverRecommendSpecial extends DiscoverRecommendItem {
    private int ret;

    private List<SpecialRecommend> specialRecommends;

    public void parseJson(JSONObject json) throws JSONException {
        if (json != null) {
            super.parseJson(json);
            ret = json.optInt("ret", 0);
            JSONArray array = json.getJSONArray("list");
            int len = array.length();
            specialRecommends = new LinkedList<>();
            if(len > 0){
                for (int i = 0; i < len; i++) {
                    JSONObject object = array.getJSONObject(i);
                    SpecialRecommend specialRecommend = new SpecialRecommend();
                    specialRecommend.parseJson(object);
                    specialRecommends.add(specialRecommend);
                }
            }
        }
    }

    public int getRet() {
        return ret;
    }

    public List<SpecialRecommend> getSpecialRecommends() {
        return specialRecommends;
    }

}