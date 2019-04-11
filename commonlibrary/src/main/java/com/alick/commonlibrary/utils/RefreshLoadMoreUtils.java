package com.alick.commonlibrary.utils;

import android.widget.Toast;

import com.alick.commonlibrary.constatnt.CommonConstant;
import com.alick.commonlibrary.holder_view.HolderView;
import com.alick.commonlibrary.holder_view.WySmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * @author 崔兴旺
 * @package com.alick.commonlibrary.utils
 * @title:
 * @description: 用于处理下拉刷新和加载更多数据的工具类
 * @date 2019/4/10 15:53
 */
public class RefreshLoadMoreUtils {
    public static <Data> void updateData(HolderView holderView, WySmartRefreshLayout smartRefreshLayout, MultiTypeAdapter multiTypeAdapter, List<Data> allData, List<Data> newData, boolean isSuccess) {
        //1.如果是下拉刷新结束
        if (smartRefreshLayout.getState() == RefreshState.Refreshing || smartRefreshLayout.getState() == RefreshState.None) {
            //1.1.如果下拉刷新成功
            if (isSuccess) {
                smartRefreshLayout.finishRefresh();//【记录刷新成功】
                //1.1.1.请求数据不为空时,清空全部旧数据并添加新数据,最后刷新列表
                if (newData != null && !newData.isEmpty()) {
                    if (allData == null) {
                        allData = new ArrayList<>();
                    } else if (!allData.isEmpty()) {
                        allData.clear();
                    }
                    allData.addAll(newData);
                    multiTypeAdapter.notifyItemRangeChanged(0, allData.size());
                    holderView.showRealContentView();
                } else {
                    //1.1.2.请求数据为空时,判断当前页面是否有数据
                    //1.1.2.1.如果当前页面有数据,则只弹出空数据的toast,
                    if (allData != null && !allData.isEmpty()) {
                        Toast.makeText(holderView.getContext(), "没有新数据", Toast.LENGTH_SHORT).show();
                        holderView.showRealContentView();
                    } else {
                        //1.1.2.2.否则显示空页面
                        holderView.showEmptyView();
                    }
                }
            } else {
                //1.2.如果刷新失败,判断当前页面是否有数据
                smartRefreshLayout.finishRefresh(false);//【记录刷新失败】
                if (allData != null && !allData.isEmpty()) {
                    //1.2.1.如果有数据,则只弹出刷新失败的toast
                    Toast.makeText(holderView.getContext(), "刷新失败", Toast.LENGTH_SHORT).show();
                } else {
                    //1.2.2.否则显示失败页面
                    holderView.showFailView();
                }
            }
        } else {
            //2.如果是加载更多结束
            //2.1.如果加载更多成功
            if (isSuccess) {
                allData.addAll(newData);
                multiTypeAdapter.notifyItemRangeChanged(allData.size() - newData.size(), newData.size());
                if (isNoMoreData(CommonConstant.DEFAULT_PAGE_SIZE, newData)) {
                    smartRefreshLayout.finishLoadMoreWithNoMoreData();//【记录加载更多成功(且没有更多数据)】
                } else {
                    smartRefreshLayout.finishLoadMore();//【记录加载更多成功】
                }
            } else {
                //2.2.如果加载更多失败
                smartRefreshLayout.finishLoadMore(false);//【记录加载更多失败】
            }
        }
    }

    /**
     * 是否没有更多数据了
     *
     * @param pageSize
     * @param newData
     * @param <Date>
     * @return
     */
    private static <Date> boolean isNoMoreData(int pageSize, List<Date> newData) {
        return newData == null || newData.size() < pageSize;
    }

}
