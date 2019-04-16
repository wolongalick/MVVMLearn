package com.alick.mvvmlearn.repository;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.alick.commonlibrary.base.bean.BaseResponse;
import com.alick.mvvmlearn.model.Account;
import com.alick.mvvmlearn.repository.local.LocalUserDataSource;
import com.alick.mvvmlearn.repository.remote.RemoteUserDataSource;
import com.alick.commonlibrary.utils.NetworkUtils;

public class UserRepository {
    private RemoteUserDataSource remoteUserDataSource;

    public UserRepository() {
        remoteUserDataSource = new RemoteUserDataSource();
    }

    public LiveData<BaseResponse<Account>> getUser(Context context,String username) {
        if (NetworkUtils.isConnected(context)) {
            return remoteUserDataSource.queryUserByUsername(username);
        } else {
            return LocalUserDataSource.getInstance().queryUserByUsername(username);
        }
    }
}