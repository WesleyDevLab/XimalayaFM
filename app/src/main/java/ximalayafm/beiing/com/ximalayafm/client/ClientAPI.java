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
     * API地址：/mobile/discovery/v1/recommends?channel=and-f6&device=android&includeActivity=true&includeSpecial=true&scale=2&version=4.1.7.1
     *
     * @return JSONObject
     */
    public static JSONObject getDiscoverCategories() {
        JSONObject ret = null;
        byte[] data = HttpTools.doGet(API_POINT + "/mobile/discovery/v1/recommends?channel=and-f6&device=android&includeActivity=true&includeSpecial=true&scale=2&version=4.1.7.1");
        if(data != null){
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
