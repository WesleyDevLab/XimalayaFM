package ximalayafm.beiing.com.ximalayafm.fragments.dicover;


import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ximalayafm.beiing.com.ximalayafm.Constants;
import ximalayafm.beiing.com.ximalayafm.R;
import ximalayafm.beiing.com.ximalayafm.adapters.DiscoverRecommendAdapter;
import ximalayafm.beiing.com.ximalayafm.adapters.PicPagerAdapter;
import ximalayafm.beiing.com.ximalayafm.entity.discoverrecommend.AlbumRecommend;
import ximalayafm.beiing.com.ximalayafm.entity.discoverrecommend.DiscoverRecommendAlbums;
import ximalayafm.beiing.com.ximalayafm.entity.discoverrecommend.DiscoverRecommendItem;
import ximalayafm.beiing.com.ximalayafm.fragments.BaseFragment;
import ximalayafm.beiing.com.ximalayafm.tasks.DiscoverRecommendTask;
import ximalayafm.beiing.com.ximalayafm.tasks.TaskCallBack;
import ximalayafm.beiing.com.ximalayafm.tasks.TaskResult;
import ximalayafm.beiing.com.ximalayafm.utils.DimensionUtil;

/**
 */
public class DiscoverRecommendFragment extends BaseFragment implements TaskCallBack, View.OnClickListener {


    private DiscoverRecommendAdapter adapter;
    private List<DiscoverRecommendItem> items;


    /**
     * 轮播海报
     */
    private ViewPager focusImagesPager;
    private PicPagerAdapter picPagerAdapter;


    public DiscoverRecommendFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        items = new LinkedList<>();
        adapter = new DiscoverRecommendAdapter(getActivity(), items);

    }

    /**
     * 因为每次进入 推荐的时候，都会出发内容的刷新
     * 所以网络加载 放到onResume（）中
     */
    @Override
    public void onResume() {
        super.onResume();
        DiscoverRecommendTask task = new DiscoverRecommendTask(this);
        task.execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ret = inflater.inflate(R.layout.fragment_discover_recommend, container, false);
        ListView listView = (ListView) ret.findViewById(R.id.discover_recommend_list);

        //创建ViewPager

        focusImagesPager = new ViewPager(getActivity());
        AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                DimensionUtil.dp2px(getActivity(), 150));
        focusImagesPager.setLayoutParams(lp);

        ArrayList picData = new ArrayList();
        //添加测试数据
        for(int i = 0; i < 5; i++){
            picData.add("" + i);
        }
        picPagerAdapter = new PicPagerAdapter(picData);
        focusImagesPager.setAdapter(picPagerAdapter);

        listView.addHeaderView(focusImagesPager);

        //设置专辑推荐事件
        adapter.setOnRecommendAlbumCLickListener(this);

        listView.setAdapter(adapter);

        focusImagesPager.setCurrentItem(Integer.MAX_VALUE >> 1);

        return ret;
    }


    @Override
    public String getFragmentTitle() {
        return "推荐";
    }

    @Override
    public void onTaskFinished(TaskResult result) {
        //　TODO　处理推荐列表
        if(result.action == Constants.TASK_ACTION_DISCOVER_RECOMMENDS){
            if(result.resultCode == Constants.TASK_RESULT_OK){
                Object data = result.data;
                if (data != null && data instanceof List) {
                    List list = (List) data;
                    //TODO 更新ListView Adapter

                    //只要数据来了，就清除adapter
                    items.clear();
                    for(Object o : list){
                        if(o instanceof  DiscoverRecommendItem){
                            items.add((DiscoverRecommendItem) o);
                        }
                    }
                    adapter.notifyDataSetChanged();
                } else if(data != null && data instanceof JSONObject){
                    // focusImages
                    //1.解析 轮播海报

                    JSONObject json = (JSONObject) data;
                    try {
                        json.getJSONObject("focusImages");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                // TODO 提示错误信息
            }
        }
    }

    @Override
    public void onClick(View view) {
        Object tag = view.getTag();
        if (tag != null) {
            if (tag instanceof AlbumRecommend) {

                //专辑推荐
                AlbumRecommend albumRecommend = (AlbumRecommend) tag;

                //专辑id
                long albumId = albumRecommend.getAlbumId();
                //曲目id
                long trackId = albumRecommend.getTrackId();

                Toast.makeText(getActivity(), "albumId :" + albumId + " ," + trackId, Toast.LENGTH_SHORT ).show();
            }
        }

    }
}



