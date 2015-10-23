package ximalayafm.beiing.com.ximalayafm.tasks;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import ximalayafm.beiing.com.ximalayafm.TaskAction;
import ximalayafm.beiing.com.ximalayafm.client.ClientAPI;
import ximalayafm.beiing.com.ximalayafm.utils.EntityParseUtil;

/**
 * Date:2015/10/23
 * Author:Beiing
 * Email:15764230067@163.com
 **/

public class AlbumDetailTask extends BaseTask {

    public AlbumDetailTask(TaskCallBack callBack) {
        super(callBack);
    }

    @Override
    protected TaskResult doInBackground(String... strings) {

        TaskResult ret = new TaskResult();
        ret.action = TaskAction.TASK_ACTION_ALBUM_DETAIL;

        if(strings != null && strings.length > 0){
            //得到下载网址
            String url = strings[0];

            JSONObject detailJSON = ClientAPI.getAlbumDetail(url);

            Log.d("----------------", "detailJSON = " + detailJSON.toString());

            if (detailJSON != null) {
                try {
                    ret.resultCode = detailJSON.getInt("ret");

                    //解析数据
                    ret.data = EntityParseUtil.parseAlbumDetail(detailJSON);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return ret;
    }
}









