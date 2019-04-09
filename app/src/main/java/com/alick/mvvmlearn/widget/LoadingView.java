package com.alick.mvvmlearn.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.alick.mvvmlearn.R;

import androidx.annotation.Nullable;

/**
 * @author 崔兴旺
 * @package com.alick.mvvmlearn.widget
 * @title:
 * @description: TODO
 * @date 2019/4/8 17:56
 */
public class LoadingView extends FrameLayout {
    private FrameLayout rootView;
    private Context context;

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        rootView = (FrameLayout) LayoutInflater.from(context).inflate(R.layout.layout_loading_view, this);
    }


}
