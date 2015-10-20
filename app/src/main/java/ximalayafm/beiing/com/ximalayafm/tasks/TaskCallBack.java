package ximalayafm.beiing.com.ximalayafm.tasks;

/**
 * Created by Administrator on 2015/10/20.
 */

/**
 * 异步任务 回调接口
 * @see TaskResult
 */
public interface TaskCallBack {

    /**
     * 当一部任务执行完成的时候，会回调这个方法，将数据结果，传递给相应的实现类
     * @param result
     */
    void onTaskFinished(TaskResult result);
}
