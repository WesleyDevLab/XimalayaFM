package ximalayafm.beiing.com.ximalayafm.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

import ximalayafm.beiing.com.ximalayafm.R;
import ximalayafm.beiing.com.ximalayafm.adapters.CommonFragmentPagerAdapter;
import ximalayafm.beiing.com.ximalayafm.fragments.dicover.DiscoverAnchorFragment;
import ximalayafm.beiing.com.ximalayafm.fragments.dicover.DiscoverCatergoryFragment;
import ximalayafm.beiing.com.ximalayafm.fragments.dicover.DiscoverLiveFragment;
import ximalayafm.beiing.com.ximalayafm.fragments.dicover.DiscoverRatingFragment;
import ximalayafm.beiing.com.ximalayafm.fragments.dicover.DiscoverRecommendFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverFragment extends Fragment implements TabLayout.OnTabSelectedListener {


    ViewPager pager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ret = inflater.inflate(R.layout.fragment_discover, container, false);

//       //1.创建 tab
        TabLayout tabLayout = (TabLayout) ret.findViewById(R.id.discover_tab_bar);
//        TabLayout.Tab tab = tabLayout.newTab().setText("推荐");
//        tabLayout.addTab(tab);
//
//        tab = tabLayout.newTab().setText("分类");
//        tabLayout.addTab(tab);
//
//        tab = tabLayout.newTab().setText("直播");
//        tabLayout.addTab(tab);
//
//        tab = tabLayout.newTab().setText("榜单");
//        tabLayout.addTab(tab);
//
//        tab = tabLayout.newTab().setText("主播");
//        tabLayout.addTab(tab);


        // 2. ViewPager 加载
        pager = (ViewPager) ret.findViewById(R.id.discover_view_pager);

        List<BaseFragment> fragments = new LinkedList<BaseFragment>();

        fragments.add(new DiscoverRecommendFragment());
        fragments.add(new DiscoverCatergoryFragment());
        fragments.add(new DiscoverLiveFragment());
        fragments.add(new DiscoverRatingFragment());
        fragments.add(new DiscoverAnchorFragment());

        CommonFragmentPagerAdapter pagerAdapter =
                new CommonFragmentPagerAdapter(getChildFragmentManager(), fragments);

        pager.setAdapter(pagerAdapter);

        // ViewPager 滑动与TabLayout绑定
        pager.addOnPageChangeListener(
                new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(this);

        //封装了TabLayout与ViewPager的联动
        //需要ViewPager内部指定的Adapter必须重写 getPageTitle（）方法
        tabLayout.setupWithViewPager(pager);

        return ret;
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        //pager切换
        pager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
