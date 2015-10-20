package ximalayafm.beiing.com.ximalayafm.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import ximalayafm.beiing.com.ximalayafm.fragments.BaseFragment;

/**
 * Created by Administrator on 2015/10/20.
 */
public class CommonFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> fragments;

    public CommonFragmentPagerAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);

        this.fragments = fragments;
    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {

        return fragments == null ? 0 : fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        String ret = null;

        BaseFragment baseFragment = fragments.get(position);

        ret = baseFragment.getFragmentTitle();

        return ret;
    }
}




