package ximalayafm.beiing.com.ximalayafm.client;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import ximalayafm.beiing.com.ximalayafm.utils.HttpTools;

/**
 * Created by Administrator on 2015/10/20.
 */
public final class ClientAPI {

    /**
     * 单独提取服务器地址的部分，避免以后更换服务器地址
     */
    private static final String API_POINT = "http://mobile.ximalaya.com";


    private ClientAPI() {

    }

    //---------------------------------
    //接口12:

    /**
     * 获取发现分类<br/>
     * API地址：http://mobile.ximalaya.com/mobile/discovery/v1/categories?device=android&picVersion=10&scale=2
     *
     * @return JSONObject
     */
    public static JSONObject getDiscoverCategories() {
        JSONObject ret = null;
        byte[] data = HttpTools.doGet(API_POINT + "/mobile/discovery/v1/categories?device=android&picVersion=10&scale=2");
        if (data != null) {
            try {
                String str = new String(data, "utf-8");
                ret = new JSONObject(str);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    //---------------------------------

    //接口11

    /**发现部分，推荐列表的内容
     * 获取
     * 请求网址 ： /mobile/discovery/v1/recommends?channel=and-f6&device=android&includeActivity=true&includeSpecial=true&scale=2&version=4.1.7.1
     * @param channel               软件发布渠道
     * @param includeActivity       是否包含活动
     * @param includeSpecial        是否包含“精品名单”
     * @return
     */
    public static JSONObject getDiscoverRecommend(String channel, boolean includeActivity,
                                    boolean includeSpecial) {
        JSONObject ret = null;
        if(channel == null){
            channel = "and-f6";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(API_POINT).append("/mobile/discovery/v1/recommends").append("?channel=" + channel)
                .append("&device=android").append("&includeActivity=" + includeActivity)
                .append("&includeSpecial=" + includeSpecial).append("&scale=2&version=4.1.7.1");
        String url = sb.toString();
        byte[] data = HttpTools.doGet(url);
        if (data != null) {
            try {
                String str = new String(data, "utf-8");
                ret = new JSONObject(str);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
//        String url = API_POINT + "/mobile/discovery/v1/recommends"
//                + "?channel=" + channel
//                +"&device=android"
//                +"&includeActivity=" + includeActivity
//                +"&includeSpecial=" + includeSpecial
//                +"&scale=2&version=4.1.7.1";
        return ret;
    }


    //---------------------------------
    //---------------------------------
    //---------------------------------
    //---------------------------------
    //---------------------------------
    //---------------------------------
    //---------------------------------
    //---------------------------------
    //---------------------------------
    //---------------------------------


}
