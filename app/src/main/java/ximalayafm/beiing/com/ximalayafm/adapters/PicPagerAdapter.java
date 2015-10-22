package ximalayafm.beiing.com.ximalayafm.adapters;

/**
 * Date:2015/10/22
 * Author:Beiing
 * Email:15764230067@163.com
 **/

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import ximalayafm.beiing.com.ximalayafm.R;
import ximalayafm.beiing.com.ximalayafm.utils.DimensionUtil;

/**
 * 轮播海报的 ViewPager Adapter
 */
public class PicPagerAdapter extends PagerAdapter{

    private List picData;

    public PicPagerAdapter(List picData) {
        this.picData = picData;
    }

    @Override
    public int getCount() {
        int ret = 0;
        if(picData != null){
            if(!picData.isEmpty()){
                ret = Integer.MAX_VALUE;//使用整形最大值，拉描述一个假的循环
            }
        }
        return ret;
    }

    /**
     * 判断 View 是否是通过 或者 由 Object 创建出来的
     * 通常对于 获取 只返回View的PagerAdapter ，可以简写成（view == object）
     *
     * @param view      ViewPager 内部管理的一个View
     * @param object    由 instantiatateItem 返回的对象
     * @return
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    /**
     * 根据指定的位置 创建一个对象，这个对象可以就是View ，也可以是管理view的对象
     * 例如 Fragment
     * 最终在放回方法之前，一定要将实际的View，添加到container，
     * 并且 ，永远不要调用super的该方法
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Context context = container.getContext();

        //TODO 因为getCount返回了整形最大值，所以 实际的数据个数是有限的，
        //利用 position % 数据个数，从而映射成实际数据的索引
        int index = position % picData.size();

        //TODO 根据index 获取相应数据

        ImageView ret = new ImageView(context);
         ret.setImageResource(R.drawable.ic_launcher);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                DimensionUtil.dp2px(context, 150)
                );
        container.addView(ret);
        return ret;
    }

    /**
     * 销毁对象， 从容器删除视图
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}












