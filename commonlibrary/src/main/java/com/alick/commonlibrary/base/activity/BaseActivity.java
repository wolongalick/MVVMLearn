package com.alick.commonlibrary.base.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alick.commonlibrary.R;
import com.alick.commonlibrary.base.IViewHelper;
import com.alick.commonlibrary.holder_view.HolderView;
import com.alick.commonlibrary.holder_view.OnReloadListener;


/**
 * @author 崔兴旺
 * @package com.alick.mvvmlearn.base
 * @title:
 * @description: TODO
 * @date 2019/4/9 12:00
 */
public abstract class BaseActivity<Binding extends ViewDataBinding> extends AppCompatActivity implements IViewHelper, OnReloadListener {

    protected Binding mBinding;
    protected HolderView mHolderView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, getLayoutId());

        mHolderView = findViewById(R.id.holderView);
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
