package com.alick.commonlibrary.holder_view;

/**
 * @author 崔兴旺
 * @package com.alick.mvvmlearn.widget
 * @title:
 * @description:
 * @date 2019/4/11 10:55
 */
public interface IWySmartRefreshLayout {
    /**
     * 设置下拉刷新监听
     * @param onWyRefreshListener
     * @return
     */
    WySmartRefreshLayout setOnWyRefreshListener(final WySmartRefreshLayout.OnWyRefreshListener onWyRefreshListener);

    /**
     * 设置加载更多监听
     * @param onWyLoadMoreListener
     * @return
     */
    WySmartRefreshLayout setOnWyLoadMoreListener(final WySmartRefreshLayout.OnWyLoadMoreListener onWyLoadMoreListener);
}
