package com.alick.commonlibrary.holder_view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;

import com.alick.commonlibrary.constatnt.CommonConstant;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

/**
 * @author 崔兴旺
 * @package com.alick.mvvmlearn.widget
 * @title:
 * @description:
 * @date 2019/4/10 15:55
 */
public class WySmartRefreshLayout extends SmartRefreshLayout implements IWySmartRefreshLayout{


    private int firstNum = CommonConstant.DEFAULT_FIRST_PAGE_NUM;

    private int pageNum = firstNum;
    private int pageSize = CommonConstant.DEFAULT_PAGE_SIZE;

    public interface OnWyRefreshListener {
        /**
         * 下拉刷新的回调函数
         * @param refreshLayout
         * @param pageSize
         */
        void onRefresh(@NonNull RefreshLayout refreshLayout, int pageSize);
    }

    public interface OnWyLoadMoreListener{
        /**
         * 加载更多的回调函数
         * @param refreshLayout
         * @param pageNum
         * @param pageSize
         */
        void onLoadMore(@NonNull RefreshLayout refreshLayout, int pageNum, int pageSize);
    }


    public WySmartRefreshLayout(Context context) {
        this(context,null);
    }

    public WySmartRefreshLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public WySmartRefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public SmartRefreshLayout finishRefresh(int delayed, boolean success) {
        if (success) {
            pageNum++;
        }
        return super.finishRefresh(delayed, success);
    }

    public SmartRefreshLayout finishLoadMore(int delayed, final boolean success, final boolean noMoreData) {
        if (success) {
            pageNum++;
        }
        return super.finishLoadMore(delayed, success, noMoreData);
    }

    /**
     * 设置下拉刷新监听
     * @param onWyRefreshListener
     * @return
     */
    @Override
    public WySmartRefreshLayout setOnWyRefreshListener(final OnWyRefreshListener onWyRefreshListener) {
        super.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNum = firstNum;
                onWyRefreshListener.onRefresh(refreshLayout,pageSize);
            }
        });

        return this;
    }

    /**
     * 设置加载更多监听
     * @param onWyLoadMoreListener
     * @return
     */
    @Override
    public WySmartRefreshLayout setOnWyLoadMoreListener(final OnWyLoadMoreListener onWyLoadMoreListener) {
        super.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                onWyLoadMoreListener.onLoadMore(refreshLayout,pageNum,pageSize);
            }
        });

        return this;
    }


    /*=====================set/get方法-begin=====================*/
    public int getFirstNum() {
        return firstNum;
    }

    public void setFirstNum(int firstNum) {
        this.firstNum = firstNum;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    /*=====================set/get方法-end=====================*/

}
