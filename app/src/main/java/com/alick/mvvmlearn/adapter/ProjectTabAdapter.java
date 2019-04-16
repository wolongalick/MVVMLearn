package com.alick.mvvmlearn.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.alick.commonlibrary.base.adapter.BaseFragmentPagerAdapter;

import java.util.List;

/**
 * Created by hackware on 2016/9/10.
 */

public class ProjectTabAdapter<W extends Fragment> extends BaseFragmentPagerAdapter<W> {

    public ProjectTabAdapter(FragmentManager fm, List<W> dataList) {
        super(fm,dataList);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return getFragments().get(position).hashCode()+"";
    }

    @Override
    public Fragment getItem(int position) {
        return getFragments().get(position);
    }

}
