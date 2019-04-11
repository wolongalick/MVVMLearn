package com.alick.commonlibrary.holder_view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.alick.commonlibrary.R;


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

    public void setOnReloadListener(final OnReloadListener onReloadListener) {
        rootView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onReloadListener.onReload();
            }
        });
    }
}
