package com.alick.mvvmlearn.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.alick.mvvmlearn.R;


/**
 * @author 崔兴旺
 * @package com.alick.mvvmlearn.widget
 * @title:
 * @description: TODO
 * @date 2019/4/8 17:49
 */
public class EmptyView extends LinearLayout {
    private Context context;
    private LinearLayout rootView;

    public EmptyView(Context context) {
        this(context, null);
    }

    public EmptyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmptyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }


    private void init() {
        rootView = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.layout_empty_view, this);
    }


}
