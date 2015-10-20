package ximalayafm.beiing.com.ximalayafm.utils;

/**
 * Created by Administrator on 2015/10/20.
 */

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

import ximalayafm.beiing.com.ximalayafm.Constants;
import ximalayafm.beiing.com.ximalayafm.entity.DiscoverCategory;

/**
 * 实体类解析工具类
 */
public final class EntityParseUtil {
    private EntityParseUtil(){

    }

    public static List<DiscoverCategory> parseDiscoverCategories(JSONObject json){
        List<DiscoverCategory> ret = null;
        if(json != null){
            try {
                int code = json.getInt("ret");

                //获取数据成功
                if(code == Constants.TASK_RESULT_OK){
                    ret = new LinkedList<DiscoverCategory>();
                    JSONArray array = json.getJSONArray("list");
                    int len = array.length();
                    if(len > 0){
                        for (int i = 0; i < len; i++){
                            JSONObject jsonObject = array.getJSONObject(i);
                            DiscoverCategory category = new DiscoverCategory();;
                            //利用实体类内部实现JSON解析，外部代码只用调用方法即可
                            category.parseJson(jsonObject);

                            ret.add(category);
                        }
                    }

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

}
