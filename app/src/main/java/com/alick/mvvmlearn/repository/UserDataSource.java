package com.alick.mvvmlearn.repository;

import android.arch.lifecycle.LiveData;

import com.alick.commonlibrary.base.bean.BaseResponse;
import com.alick.mvvmlearn.model.Account;

public interface UserDataSource {
    LiveData<BaseResponse<Account>> queryUserByUsername(String username);
}