package ximalayafm.beiing.com.ximalayafm.entity;

/**
 * Created by Administrator on 2015/10/22.
 */

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 用于 可以通过 JSON / XML, Cursor 来解析的数据
 */
public interface Parsable {

    /**
     * 实体类 解析JSON，更新内部的数据
     * @param json
     * @throws JSONException
     */
    void parseJson(JSONObject json) throws JSONException;

}
