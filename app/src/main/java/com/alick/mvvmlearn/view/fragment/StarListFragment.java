package com.alick.mvvmlearn.view.fragment;

import android.support.annotation.NonNull;

import com.alick.commonlibrary.base.activity.BaseListFragment;
import com.alick.mvvmlearn.R;
import com.alick.mvvmlearn.adapter.UserAdapter;
import com.alick.mvvmlearn.databinding.ItemUserBinding;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

/**
 * @author 崔兴旺
 * @package com.alick.mvvmlearn.view.fragment
 * @title:
 * @description:
 * @date 2019/4/16 16:43
 */
public class StarListFragment extends BaseListFragment<ItemUserBinding, UserAdapter> {
    /**
     * 获取布局ID
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.item_user;
    }

    /**
     * 初始化视图
     */
    @Override
    public void initViews() {

    }

    /**
     * 初始化监听
     */
    @Override
    public void initListener() {

    }

    /**
     * 初始化数据
     */
    @Override
    public void initData() {

    }

    /**
     * 页面加载失败后,点击页面重试的回调函数
     */
    @Override
    public void onReload() {

    }

    /**
     * 下拉刷新的回调函数
     *
     * @param refreshLayout
     * @param pageSize
     */
    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout, int pageSize) {

    }

    /**
     * 加载更多的回调函数
     *
     * @param refreshLayout
     * @param pageNum
     * @param pageSize
     */
    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout, int pageNum, int pageSize) {

    }
}
