package com.alick.mvvmlearn.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.alick.commonlibrary.base.activity.BaseActivity;
import com.alick.commonlibrary.base.bean.BaseResponse;
import com.alick.mvvmlearn.R;
import com.alick.mvvmlearn.constant.IntentKey;
import com.alick.mvvmlearn.databinding.ActivityMainBinding;
import com.alick.mvvmlearn.model.Account;
import com.alick.mvvmlearn.viewmodel.UserViewModel;

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
     * 初始化数据
     */
    @Override
    public void initData() {
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
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
                mHolderView.showLoadingView();
            }
        });

        mBinding.btnGoProjectList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //将用户名传给项目列表页面
                startActivity(new Intent(MainActivity.this,ProjectListActivity.class).putExtra(IntentKey.USERNAME,mBinding.getAccount().getLogin()));
            }
        });

        userViewModel.getUser().observe(this, new Observer<BaseResponse<Account>>() {
            @Override
            public void onChanged(@Nullable BaseResponse<Account> baseResponse) {
                Account account =baseResponse.getData();
                if (account != null) {
                    mBinding.setAccount(account);
                    mHolderView.showRealContentView();
                }else {
                    mHolderView.showFailView();
                    Toast.makeText(getApplicationContext(),baseResponse.getErrorMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    /**
     * 初始化视图
     */
    @Override
    public void initViews() {

    }

    @Override
    public void onReload() {
        searchUser();
    }
}
