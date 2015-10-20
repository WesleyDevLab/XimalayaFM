package ximalayafm.beiing.com.ximalayafm.tasks;

import android.os.AsyncTask;

/**
 * Created by Administrator on 2015/10/20.
 */
public abstract class BaseTask extends AsyncTask<String, Void, TaskResult> {

    private TaskCallBack callBack;

    public BaseTask(TaskCallBack callBack) {
        this.callBack = callBack;
    }

    /**
     * 子类只需要重写该方法
     * @param strings
     * @return
     */
    @Override
    protected TaskResult doInBackground(String... strings) {
        return null;
    }


    @Override
    protected void onPostExecute(TaskResult result) {
        if(result != null)
            callBack.onTaskFinished(result);
    }
}
