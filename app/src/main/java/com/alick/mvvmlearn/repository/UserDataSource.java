package com.alick.mvvmlearn.repository;

import android.arch.lifecycle.LiveData;

import com.alick.commonlibrary.base.bean.BaseResponse;
import com.alick.mvvmlearn.model.User;

public interface UserDataSource {
    LiveData<BaseResponse<User>> queryUserByUsername(String username);
}