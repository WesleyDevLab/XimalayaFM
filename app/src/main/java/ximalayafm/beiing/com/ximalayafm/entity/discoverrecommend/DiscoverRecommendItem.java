package ximalayafm.beiing.com.ximalayafm.entity.discoverrecommend;

import org.json.JSONException;
import org.json.JSONObject;

public class DiscoverRecommendItem {
	private String title;
	private boolean hasMore;

	public void parseJson(JSONObject json) throws JSONException {
		if(json != null){
			title = json.getString("title");
			hasMore = json.optBoolean("hasMore");
		}

	}



	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isHasMore() {
		return hasMore;
	}

	public void setHasMore(boolean hasMore) {
		this.hasMore = hasMore;
	}


}