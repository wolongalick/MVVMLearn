package com.alick.mvvmlearn.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * @author 崔兴旺
 * @package com.alick.mvvmlearn.base
 * @title:
 * @description: TODO
 * @date 2019/4/9 12:00
 */
public abstract class BaseActivity<Binding extends ViewDataBinding> extends AppCompatActivity implements IViewHelper{

    protected Binding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, getLayoutId());

        initViews();
        initListener();
        initData();
    }

    /**
     * 获取布局ID
     * @return
     */
    protected abstract int getLayoutId();
}
