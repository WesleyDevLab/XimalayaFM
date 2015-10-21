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
import ximalayafm.beiing.com.ximalayafm.entity.discoverrecommend.DiscoverRecommendAlbums;
import ximalayafm.beiing.com.ximalayafm.entity.discoverrecommend.DiscoverRecommendItem;

/**
 * 实体类解析工具类
 */
public final class EntityParseUtil {
    private EntityParseUtil(){

    }

    /**
     * 解析发现-分类
     * @param json
     * @return
     */
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

    /**
     * 解析 "发现"模块中 推荐 栏目中的数据结构
     * @param jsonObject
     * @return
     */
    public static List<DiscoverRecommendItem> parseDiscoverRecommends(JSONObject jsonObject) {
        List<DiscoverRecommendItem> ret = null;

        if (jsonObject != null) {
            try {
                int code  = jsonObject.getInt("ret");
                if(code == Constants.TASK_RESULT_OK){
                    ret = new LinkedList<>();

                    //---小编推荐
                    JSONObject object = jsonObject.getJSONObject("editorRecommendAlbums");
                    DiscoverRecommendAlbums editorRecommend = new DiscoverRecommendAlbums();
                    editorRecommend.parseJson(object);
                    ret.add(editorRecommend);

                    //----------------------------------------------

                    //TODO 解析 精品听单

                    //TODO 解析 发现新奇




                    //解析热门推荐
                    JSONObject  hotRecommendsObject = jsonObject.getJSONObject("hotRecommends");
                    JSONArray hotArray = hotRecommendsObject.getJSONArray("list");
                    int len = hotArray.length();
                    for (int i = 0; i < len; i++) {
                        //获取热门推荐内部List中每一个推荐信息
                        JSONObject jj = hotArray.getJSONObject(i);
                        DiscoverRecommendAlbums hotAlbums = new DiscoverRecommendAlbums();
                        hotAlbums.parseJson(jj);
                        ret.add(hotAlbums);
                    }

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        return ret;
    }
}






