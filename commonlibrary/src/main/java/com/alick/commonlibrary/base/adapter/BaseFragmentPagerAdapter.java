package com.alick.commonlibrary.base.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * 功能:
 * 作者: 崔兴旺
 * 日期: 2017/8/4
 * 备注:
 */
public abstract class BaseFragmentPagerAdapter<W extends Fragment> extends FragmentPagerAdapter {


    protected List<W> fragments;
    private FragmentManager fragmentManager;

    public BaseFragmentPagerAdapter(FragmentManager fm, List<W> fragments) {
        super(fm);
        this.fragmentManager=fm;
        this.fragments = fragments;
    }

    @Override
    public abstract CharSequence getPageTitle(int position);

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public abstract Fragment getItem(int position);


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        fragmentManager.beginTransaction().show(fragment).commitAllowingStateLoss();
        return fragment;
    }

    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Fragment fragment = fragments.get(position);
        fragmentManager.beginTransaction().hide(fragment).commitAllowingStateLoss();
    }

    public List<W> getFragments() {
        return fragments;
    }
}
