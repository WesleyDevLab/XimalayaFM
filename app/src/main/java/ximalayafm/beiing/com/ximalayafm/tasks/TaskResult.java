package ximalayafm.beiing.com.ximalayafm.tasks;

import java.util.Objects;

/**
 * Created by Administrator on 2015/10/20.
 */

/**
 *
 * 异步任务回调接口定义的数据,其中包含的action 表示处理请求的task，
 * 让回调接口处理类能够检测到这个数据从哪里来
 */
public class TaskResult {
    /**
     * 异步任务唯一标识,每一个异步任务标识都不相同
     */
    public int action;

    /**
     * 服务器返回的 ret 值，0代表成功
     */
    public int resultCode = -1;

    /**
     * 任意数据类型，只要接口实现类支持即可
     */
    public Object data;





}















