package ximalayafm.beiing.com.ximalayafm.widgets;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import ximalayafm.beiing.com.ximalayafm.R;
import ximalayafm.beiing.com.ximalayafm.entity.discoverrecommend.FocusImageItem;

/**
 * 用于播放广告的自定义控件-包含了ViewPager的控件
 */
public class TopView extends FrameLayout {

	ViewPager vPager;

	TextView titleTv;

	LinearLayout navLayout;

	private List<ImageView> imgViews;// viewpager中显示的

	List<FocusImageItem> focusImageItems;

	Handler mHandler = new Handler();

	private Context context;

	private int curPosition = 0;// 当前位置
	private PicPagerAdapter adapter;

	public TopView(Context context) {
		this(context, null, 0);
		this.context = context;
	}

	public TopView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public TopView(Context context, AttributeSet attrs, int style) {
		super(context, attrs, style);
		// 第二个参数表示：布局资源中跟标签内声明的布局参数参考父控件类型
		// 第三个参数：为true时代表将第一个参数中声明的子控件归属到第二个参数对象中，false不归属
		LayoutInflater.from(context).inflate(R.layout.ac_main_ad_view_top,
				this, true);

		initView();
	}

	private void initView() {
		// TODO 查找相关的UI控件
		vPager = (ViewPager) findViewById(R.id.viewPager);
		titleTv = (TextView) findViewById(R.id.titleId);
		navLayout = (LinearLayout) findViewById(R.id.navLayoutId);
	}

	public void setDatas(List<FocusImageItem> focusImageItems) {
		this.focusImageItems = focusImageItems;
		titleTv.setText(focusImageItems.get(0).getShortTitle());// 默认显示第一个广告
		createViews();
	}

	private void createViews() {
		// TODO 根据数据源创建View
		imgViews = new ArrayList<ImageView>();
		ImageView img = null;
		int len = focusImageItems.size();

		for (int i = 0; i < len; i++) {
			FocusImageItem item = focusImageItems.get(i);
			img = new ImageView(context);
			img.setScaleType(ScaleType.CENTER_CROP);
			img.setTag(item);
			imgViews.add(img);
		}

		// 设置viewpager适配器
		adapter = new PicPagerAdapter();
		vPager.setAdapter(adapter);

		vPager.setCurrentItem(curPosition);
		loadImage();
	}

	private void loadImage(){
		for (ImageView img : imgViews) {
			FocusImageItem item = (FocusImageItem) img.getTag();
			String url = item.getPic();
			//使用Picasso 加载网络图片
			Picasso.with(getContext()).load(url).resize(200, 120).into(img);
		}

		new PlayImgThread().start();
	}


	class PicPagerAdapter extends PagerAdapter{

		@Override
		public int getCount() {
//			int ret = 0;
//			if(imgViews != null){
//				if(!imgViews.isEmpty()){
//					ret = Integer.MAX_VALUE;//使用整形最大值，拉描述一个假的循环
//				}
//			}
//			return ret;
			return imgViews == null ? 0 : imgViews.size();
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
			//TODO 因为getCount返回了整形最大值，所以 实际的数据个数是有限的，
			//利用 position % 数据个数，从而映射成实际数据的索引
//			int index = position % imgViews.size();

			//TODO 根据index 获取相应数据

			ImageView ret = imgViews.get(position);
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
//			container.removeView(imgViews.get(position % imgViews.size()));

			container.removeView(imgViews.get(position));
		}
	}



	class PlayImgThread extends Thread {
		@Override
		public void run() {
			super.run();
			try {
				while (TopView.this != null) {

					Thread.sleep(3000);
					mHandler.post(new Runnable() {
						@Override
						public void run() {
							titleTv.setText(focusImageItems.get(curPosition).getShortTitle());
							curPosition = ++curPosition % imgViews.size();
							vPager.setCurrentItem(curPosition);
						}
					});
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
