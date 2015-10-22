package ximalayafm.beiing.com.ximalayafm.entity.discoverrecommend;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ximalayafm.beiing.com.ximalayafm.entity.Parsable;

/**
 *小编推荐 和 热门推荐
 */
public class DiscoverRecommendAlbums extends DiscoverRecommendItem{
	/**
	  "ret": 0,
	 "title": "小编推荐",
	 "hasMore": true,
	 "list": []
	 */

	private int ret;

	private List<AlbumRecommend> albumRecommends;

	public void parseJson(JSONObject json) throws JSONException {
		//调用父类的解析，解析父类需要使用的数据
		//因为super中包含了title， hasMore
		if (json != null) {
			super.parseJson(json);

			ret = json.optInt("ret", 0);

			JSONArray array = json.getJSONArray("list");

			int len = array.length();

			albumRecommends = new ArrayList<>();
			if(len > 0){
				for (int i = 0; i < len; i++) {
					//解析内部的专辑推荐
					JSONObject jsonObject = array.getJSONObject(i);
					AlbumRecommend album = new AlbumRecommend();
					album.parseJson(jsonObject);
					albumRecommends.add(album);
				}
			}
		}
	}

	public int getRet() {
		return ret;
	}

	public List<AlbumRecommend> getAlbumRecommends() {
		return albumRecommends;
	}


}




