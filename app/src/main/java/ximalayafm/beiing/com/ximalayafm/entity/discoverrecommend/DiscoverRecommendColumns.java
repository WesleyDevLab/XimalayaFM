package ximalayafm.beiing.com.ximalayafm.entity.discoverrecommend;

/**
 * 发现新奇
 */

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * "ret": 0,
 "title": "发现新奇",
 "list": []
 */
public class DiscoverRecommendColumns extends DiscoverRecommendItem {
    private int ret;

    private List<DiscoveryRecommend> discoveryRecommends;

    public void parseJson(JSONObject json) throws JSONException {
        if(json != null){
            super.parseJson(json);
            ret = json.optInt("ret");

            JSONArray array = json.getJSONArray("list");
            int len = array.length();
            discoveryRecommends = new LinkedList<>();
            if(len > 0){
                for (int i = 0; i < len; i++) {
                    JSONObject object = array.getJSONObject(i);
                    DiscoveryRecommend disRecommend = new DiscoveryRecommend();
                    disRecommend.parseJson(object);
                    discoveryRecommends.add(disRecommend);
                }
            }
        }
    }

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public List<DiscoveryRecommend> getDiscoveryRecommends() {
        return discoveryRecommends;
    }

    public void setDiscoveryRecommends(List<DiscoveryRecommend> discoveryRecommends) {
        this.discoveryRecommends = discoveryRecommends;
    }
}





