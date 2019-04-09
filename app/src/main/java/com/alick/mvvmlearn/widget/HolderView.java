package com.alick.mvvmlearn.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.alick.mvvmlearn.R;

/**
 * @author 崔兴旺
 * @package com.alick.mvvmlearn.widget
 * @title:
 * @description: TODO
 * @date 2019/4/8 17:44
 */
public class HolderView extends FrameLayout {
    private Context context;
    private FrameLayout rootView;

    private EmptyView emptyView;
    private FailView failView;
    private LoadingView loadingView;
    private View realContentView;

    public HolderView(@NonNull Context context) {
        this(context, null);
    }

    public HolderView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HolderView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        this.setId(R.id.holderView);
        loadingView = new LoadingView(context);
        failView = new FailView(context);
        emptyView = new EmptyView(context);


    }

    protected View getRealContentView() {
        //这是本类默认的方法:取第一个子view
        return getChildAt(0);
    }

    private void fillLayout(){
        realContentView = getRealContentView();
        try {
            addView(realContentView);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        addView(loadingView);
        addView(failView);
        addView(emptyView);
    }


    public void showLoadingView() {
        loadingView.setVisibility(VISIBLE);
        failView.setVisibility(GONE);
        emptyView.setVisibility(GONE);
        realContentView.setVisibility(GONE);
    }

    public void showFailView() {
        loadingView.setVisibility(GONE);
        failView.setVisibility(VISIBLE);
        emptyView.setVisibility(GONE);
        realContentView.setVisibility(GONE);
    }

    public void showEmptyView() {
        loadingView.setVisibility(GONE);
        failView.setVisibility(GONE);
        emptyView.setVisibility(VISIBLE);
        realContentView.setVisibility(GONE);
    }

    public void showRealContentView() {
        loadingView.setVisibility(GONE);
        failView.setVisibility(GONE);
        emptyView.setVisibility(GONE);
        realContentView.setVisibility(VISIBLE);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        fillLayout();
        showRealContentView();
    }

    public void setOnReloadListener(final OnReloadListener onReloadListener){
        failView.setOnReloadListener(new OnReloadListener() {
            @Override
            public void onReload() {
                showLoadingView();
                onReloadListener.onReload();
            }
        });
    }

}
