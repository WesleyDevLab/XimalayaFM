package ximalayafm.beiing.com.ximalayafm.adapters;

/**
 * Created by Administrator on 2015/10/21.
 */


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.List;

import ximalayafm.beiing.com.ximalayafm.R;
import ximalayafm.beiing.com.ximalayafm.entity.discoverrecommend.DiscoverRecommendAlbums;
import ximalayafm.beiing.com.ximalayafm.entity.discoverrecommend.DiscoverRecommendColumns;
import ximalayafm.beiing.com.ximalayafm.entity.discoverrecommend.DiscoverRecommendItem;
import ximalayafm.beiing.com.ximalayafm.entity.discoverrecommend.DiscoverRecommendSpecial;

/**
 * 发现  推荐部分的ListView Adapter
 * 支持多布局复用
 */
public class DiscoverRecommendAdapter extends BaseAdapter {
    private LayoutInflater inflater;

    private List<DiscoverRecommendItem> items;

    public DiscoverRecommendAdapter(Context context, List<DiscoverRecommendItem> items) {
        inflater = LayoutInflater.from(context);
        this.items = items;
    }

    @Override
    public int getCount() {
        return items == null ? 0 : items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 3;//当前listVIew 显示三种不同布局 ：小编推荐/热门推荐 、 精品听单 、 发现新奇
    }

    @Override
    public int getItemViewType(int position) {
        int ret = 0;
        DiscoverRecommendItem item = items.get(position);

        if (item instanceof DiscoverRecommendAlbums) {//小编推荐/热门推荐
            ret = 0;
        } else if (item instanceof DiscoverRecommendColumns) {//发现新奇
            ret = 1;
        } else if (item instanceof DiscoverRecommendSpecial) {//精品听单
            ret = 2;
        }

        return ret;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View ret = null;
        DiscoverRecommendItem item = items.get(position);

        if (item instanceof DiscoverRecommendAlbums) {//小编推荐
            ret = bindAlbumsView(position, convertView, parent);
        } else if (item instanceof DiscoverRecommendColumns) {//发现新奇
            ret = bindColumnView(position, convertView, parent);
        } else if (item instanceof DiscoverRecommendSpecial) {//精品听单
            ret = bindSpecialView(position, convertView, parent);
        }

        return ret;
    }


    /**
     * 加载 专辑推荐的Item ： 现编推荐 和热门推荐
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    private View bindAlbumsView(int position, View convertView, ViewGroup parent) {
        View ret = null;

        if (convertView != null) {
            ret = convertView;
        } else {
            ret = inflater.inflate(R.layout.discover_recommend_album_item, parent, false);
        }

        //2.ViewHolder
        AlbumViewHolder holder = (AlbumViewHolder) ret.getTag();
        if(holder == null){
            holder = new AlbumViewHolder();
            holder.txtTitle = (TextView) ret.findViewById(R.id.recommend_album_title);
            holder.txtMore = (TextView) ret.findViewById(R.id.recommend_album_more);
            holder.albumIcons = new ImageView[3];
            holder.albumNames = new TextView[3];
            holder.trackNames = new TextView[3];

            for (int i = 0; i < 3; i++) {
                //给Holder设置数组的内容
                holder.albumIcons[i] = (ImageView) findView(ret, "recommend_album_icon_" + i);
                holder.albumNames[i] = (TextView) findView(ret,"recommend_album_name_" + i);
                holder.trackNames[i] = (TextView) findView(ret, "recommend_album_track_name_" + i);
            }

            ret.setTag(holder);
        }

        //3.获取数据 设置数据
        DiscoverRecommendAlbums albums = (DiscoverRecommendAlbums) items.get(position);
        String title = albums.getTitle();
        holder.txtTitle.setText(title);


        return ret;
    }

    /**
     * 加载 发现新奇
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    private View bindColumnView(int position, View convertView, ViewGroup parent) {

        return null;
    }

    /**
     * 加载 新品听单
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    private View bindSpecialView(int position, View convertView, ViewGroup parent) {

        return null;
    }

    /**
     * 根据名称获取ID(反射)
     * @param container
     * @param fieldName
     * @return
     */
    public static View findView(View container, String fieldName){
        View  ret = null;
        if (container != null && fieldName != null) {
            Class<R.id> idClass = R.id.class;
            Field field = null;
            try {
                field = idClass.getDeclaredField(fieldName);
                int id = field.getInt(idClass);
                //通过静态常量，获取int 值，
                ret = container.findViewById(id);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return ret;
    }

    /**
     * 小编推荐的 ViewHolder
     */
    private static class AlbumViewHolder{
        TextView txtTitle;
        TextView txtMore;
        ImageView[] albumIcons;//三张图片，在不同的RelativeLayout里面
        TextView[] albumNames;//三个专辑标题
        TextView[] trackNames;//三个曲目名称
    }



}





