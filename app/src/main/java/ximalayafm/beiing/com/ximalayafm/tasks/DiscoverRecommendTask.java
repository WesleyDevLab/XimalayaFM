package ximalayafm.beiing.com.ximalayafm.tasks;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import ximalayafm.beiing.com.ximalayafm.Constants;
import ximalayafm.beiing.com.ximalayafm.TaskAction;
import ximalayafm.beiing.com.ximalayafm.client.ClientAPI;
import ximalayafm.beiing.com.ximalayafm.utils.EntityParseUtil;

/**
 * Created by Administrator on 2015/10/20.
 */
public class DiscoverRecommendTask extends BaseTask {

    public DiscoverRecommendTask(TaskCallBack callBack) {
        super(callBack);
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        TaskResult ret = new TaskResult();

        ret.action = TaskAction.TASK_ACTION_DISCOVER_RECOMMENDS;

        JSONObject jsonObject = ClientAPI.getDiscoverRecommend("and-f6", true, true);
        if(jsonObject != null){
            try {
                ret.resultCode = jsonObject.getInt("ret");

                //解析数据
                ret.data = EntityParseUtil.parseDiscoverRecommends(jsonObject);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }
}



