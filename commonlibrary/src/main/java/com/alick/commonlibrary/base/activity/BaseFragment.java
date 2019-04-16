package com.alick.commonlibrary.base.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alick.commonlibrary.R;
import com.alick.commonlibrary.base.IViewHelper;
import com.alick.commonlibrary.holder_view.HolderView;
import com.alick.commonlibrary.holder_view.OnReloadListener;

/**
 * @author 崔兴旺
 * @package com.alick.commonlibrary.base.activity
 * @title:
 * @description:
 * @date 2019/4/16 11:33
 */
public abstract class BaseFragment<Binding extends ViewDataBinding> extends Fragment implements IViewHelper, OnReloadListener {
    protected Binding mBinding;
    protected HolderView mHolderView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mHolderView = mBinding.getRoot().findViewById(R.id.holderView);
        if (mHolderView != null) {
            mHolderView.setOnReloadListener(this);
        }

        init();
        initData();
        initListener();
        initViews();
    }

    protected void init(){

    }

    /**
     * 获取布局ID
     *
     * @return
     */
    protected abstract int getLayoutId();


}
