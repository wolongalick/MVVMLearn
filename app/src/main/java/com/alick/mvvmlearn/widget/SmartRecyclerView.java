package com.alick.mvvmlearn.widget;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.alick.mvvmlearn.R;
import com.alick.mvvmlearn.databinding.LayoutSmartRecyclerViewBinding;

/**
 * @author 崔兴旺
 * @package com.alick.mvvmlearn.widget
 * @title:
 * @description:
 * @date 2019/4/10 18:51
 */
public class SmartRecyclerView extends WySmartRefreshLayout{
    private Context context;
    private RecyclerView recyclerView;

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
        mbinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.layout_smart_recycler_view, null, false);
        recyclerView=mbinding.recyclerView;
        recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(context));
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }
}
