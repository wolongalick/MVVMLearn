package com.alick.mvvmlearn.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.alick.mvvmlearn.R;

/**
 * @author 崔兴旺
 * @package com.alick.mvvmlearn.widget
 * @title:
 * @description:
 * @date 2019/4/10 18:51
 */
public class SmartRecyclerView extends WySmartRefreshLayout{
    private Context context;
    private WySmartRefreshLayout view;

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
        view = (WySmartRefreshLayout) LayoutInflater.from(context).inflate(R.layout.layout_smart_recycler_view, this);
    }




}
