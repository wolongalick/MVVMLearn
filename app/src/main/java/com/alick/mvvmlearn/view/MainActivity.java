package com.alick.mvvmlearn.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.alick.mvvmlearn.R;
import com.alick.mvvmlearn.base.BaseActivity;
import com.alick.mvvmlearn.constant.IntentKey;
import com.alick.mvvmlearn.databinding.ActivityMainBinding;
import com.alick.mvvmlearn.model.User;
import com.alick.mvvmlearn.viewmodel.UserViewModel;
import com.alick.mvvmlearn.widget.OnReloadListener;

public class MainActivity extends BaseActivity<ActivityMainBinding> {


    private UserViewModel userViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    /**
     * 搜索用户
     */
    private void searchUser() {
        String username = mBinding.etUsername.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(getApplicationContext(), "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }
        userViewModel.reload(username);
    }


    /**
     * 初始化视图
     */
    @Override
    public void initViews() {

    }

    /**
     * 初始化监听
     */
    @Override
    public void initListener() {
        mBinding.btnSerach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchUser();
                mBinding.holderView.showLoadingView();
            }
        });

        mBinding.holderView.setOnReloadListener(new OnReloadListener() {
            @Override
            public void onReload() {
                searchUser();
            }
        });

        mBinding.btnGoProjectList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ProjectListActivity.class).putExtra(IntentKey.USERNAME,mBinding.getUser().getLogin()));
            }
        });
    }

    /**
     * 初始化数据
     */
    @Override
    public void initData() {
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                if (user != null) {
                    mBinding.setUser(user);
                    mBinding.holderView.showRealContentView();
                }else {
                    mBinding.holderView.showFailView();
                }
            }
        });
    }
}
