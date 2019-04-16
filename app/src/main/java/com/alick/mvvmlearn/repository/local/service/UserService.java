package com.alick.mvvmlearn.repository.local.service;

import android.arch.lifecycle.LiveData;

import com.alick.commonlibrary.base.bean.BaseResponse;
import com.alick.mvvmlearn.model.Account;

public interface UserService {
    LiveData<Long> add(Account account);

    LiveData<BaseResponse<Account>> queryByUsername(String username);
}