package com.alick.mvvmlearn.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.alick.mvvmlearn.R;

import androidx.annotation.Nullable;

/**
 * @author 崔兴旺
 * @package com.alick.mvvmlearn.widget
 * @title:
 * @description: TODO
 * @date 2019/4/8 17:49
 */
public class FailView extends LinearLayout {
    private Context context;
    private LinearLayout rootView;

    public FailView(Context context) {
        this(context, null);
    }

    public FailView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FailView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }


    private void init() {
        rootView = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.layout_fail_view, this);
    }


}
