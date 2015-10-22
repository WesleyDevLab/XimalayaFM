package ximalayafm.beiing.com.ximalayafm.utils;
/**
 * Created by Administrator on 2015/10/22.
 */

/**
 * Date:2015/10/22
 * Author:Beiing
 * Email:15764230067@163.com
 **/

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * 用于进行尺寸计算的
 */
public final  class DimensionUtil {
    private DimensionUtil(){

    }

    /**
     * 根据当前手机的屏幕密度，进行 dp 到 px 单位的换算
     * @param context
     * @param dp
     * @return
     */
    public static int dp2px(Context context, int dp){
        float ret = 0;
        Resources res = context.getResources();
        DisplayMetrics metrics = res.getDisplayMetrics();

        //px = dp * (dpi / 160)
        ret = dp * metrics.density;
        return (int) ret;
    }


}
