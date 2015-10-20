package ximalayafm.beiing.com.ximalayafm.tasks;

/**
 * Created by Administrator on 2015/10/20.
 */

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import ximalayafm.beiing.com.ximalayafm.Constants;
import ximalayafm.beiing.com.ximalayafm.client.ClientAPI;
import ximalayafm.beiing.com.ximalayafm.utils.EntityParseUtil;

/**
 * 发现部分 分类 的数据加载异步任务
 */
public class DiscoverCategoryTask extends BaseTask{


    public DiscoverCategoryTask(TaskCallBack callBack) {
        super(callBack);
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        TaskResult ret = new TaskResult();
        //唯一
        ret.action = Constants.TASK_ACTION_DISCOVER_CATEGORIES;

        JSONObject jsonObject = ClientAPI.getDiscoverCategories();

        if(jsonObject != null){
            try {
               ret.resultCode =  jsonObject.getInt("ret");
               ret.data = EntityParseUtil.parseDiscoverCategories(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

}






