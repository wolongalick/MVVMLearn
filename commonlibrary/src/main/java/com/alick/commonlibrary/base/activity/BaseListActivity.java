package com.alick.commonlibrary.base.activity;

import android.databinding.ViewDataBinding;

import com.alick.commonlibrary.R;
import com.alick.commonlibrary.base.adapter.BaseAdapter;
import com.alick.commonlibrary.base.viewbinder.BaseViewBinder;
import com.alick.commonlibrary.holder_view.ListHolerView;
import com.alick.commonlibrary.holder_view.WySmartRefreshLayout;
import com.alick.commonlibrary.utils.RefreshLoadMoreUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 崔兴旺
 * @package com.alick.mvvmlearn.base
 * @title:
 * @description:
 * @date 2019/4/11 13:32
 */
public abstract class BaseListActivity<Binding extends ViewDataBinding, Adapter extends BaseAdapter> extends BaseActivity<Binding> implements WySmartRefreshLayout.OnWyRefreshListener, WySmartRefreshLayout.OnWyLoadMoreListener {
    protected ListHolerView listHolerView;

    @Override
    public void initViews() {
        super.initViews();
        Type genericSuperclass = getClass().getGenericSuperclass();
        Type type = ((ParameterizedType) genericSuperclass).getActualTypeArguments()[1];
        try {
            //初始化adapter
            Adapter adapter = ((Class<Adapter>) type).getConstructor(List.class).newInstance(new ArrayList<>());
            //初始化listHolerView

            if (holderView instanceof ListHolerView) {
                listHolerView = (ListHolerView) holderView;
            } else {
                listHolerView = findViewById(R.id.holderView);
            }

            //将recyclerView与adapter关联
            listHolerView.getSmartRecyclerView().getRecyclerView().setAdapter(adapter);
            //设置下拉刷新监听
            if (!isDisableRefresh()) {
                listHolerView.getSmartRecyclerView().setOnWyRefreshListener(this);
            } else {
                listHolerView.getSmartRecyclerView().getWySmartRefreshLayout().setEnableRefresh(false);
            }
            //设置加载更多监听
            if (!isDisableLoadMore()) {
                listHolerView.getSmartRecyclerView().setOnWyLoadMoreListener(this);
            } else {
                listHolerView.getSmartRecyclerView().getWySmartRefreshLayout().setEnableLoadMore(false);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    //====================================================================
    protected <Data, VB extends BaseViewBinder> void updateData(List<Data> newData, boolean isSuccess) {
        WySmartRefreshLayout smartRefreshLayout = listHolerView.getSmartRecyclerView().getWySmartRefreshLayout();
        BaseAdapter<Data, VB> baseAdapter = (BaseAdapter<Data, VB>) listHolerView.getSmartRecyclerView().getRecyclerView().getAdapter();
        List<Data> allData = baseAdapter.getDatas();

        RefreshLoadMoreUtils.updateData(listHolerView, smartRefreshLayout, baseAdapter, allData, newData, isSuccess);
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

    /**
     * 是否禁用下拉刷新
     *
     * @return
     */
    protected boolean isDisableRefresh() {
        return false;
    }

    /**
     * 是否禁用加载更多
     *
     * @return
     */
    protected boolean isDisableLoadMore() {
        return false;
    }
}


