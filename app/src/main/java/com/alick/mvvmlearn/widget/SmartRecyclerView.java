package com.alick.mvvmlearn.widget;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.alick.mvvmlearn.R;
import com.alick.mvvmlearn.databinding.LayoutSmartRecyclerViewBinding;

/**
 * @author 崔兴旺
 * @package com.alick.mvvmlearn.widget
 * @title:
 * @description:
 * @date 2019/4/10 18:51
 */
public class SmartRecyclerView extends FrameLayout implements IWySmartRefreshLayout{
    private Context context;
    private RecyclerView recyclerView;
    private WySmartRefreshLayout wySmartRefreshLayout;

    private LayoutSmartRecyclerViewBinding mbinding;

    public SmartRecyclerView(Context context) {
        this(context,null);
    }

    public SmartRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SmartRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        init();
    }

    private void init(){
        mbinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.layout_smart_recycler_view, this,true);
        recyclerView=mbinding.recyclerView;
        wySmartRefreshLayout=mbinding.refreshLayout;
        recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(context));
    }

    @Override
    public WySmartRefreshLayout setOnWyRefreshListener(WySmartRefreshLayout.OnWyRefreshListener onWyRefreshListener) {
        return mbinding.refreshLayout.setOnWyRefreshListener(onWyRefreshListener);
    }

    @Override
    public WySmartRefreshLayout setOnWyLoadMoreListener(WySmartRefreshLayout.OnWyLoadMoreListener onWyLoadMoreListener) {
        return mbinding.refreshLayout.setOnWyLoadMoreListener(onWyLoadMoreListener);
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public WySmartRefreshLayout getWySmartRefreshLayout() {
        return wySmartRefreshLayout;
    }
}
