package com.alick.mvvmlearn.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;

import com.alick.mvvmlearn.constant.Constant;
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
public class WySmartRefreshLayout extends SmartRefreshLayout {


    private int firstNum = Constant.DEFAULT_FIRST_PAGE_NUM;

    private int pageNum = firstNum;
    private int pageSize = Constant.DEFAULT_PAGE_SIZE;

    public interface OnWyRefreshListener {
        void onRefresh(@NonNull RefreshLayout refreshLayout, int pageSize);
    }

    public interface OnWyLoadMoreListener{
        void onLoadMore(@NonNull RefreshLayout refreshLayout, int pageNum, int pageSize);
    }


    public WySmartRefreshLayout(Context context) {
        super(context);
    }

    public WySmartRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
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
