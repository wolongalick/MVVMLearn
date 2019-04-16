package com.alick.mvvmlearn.repository;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.alick.commonlibrary.base.bean.BaseResponse;
import com.alick.mvvmlearn.model.Account;
import com.alick.mvvmlearn.repository.local.LocalUserDataSource;
import com.alick.mvvmlearn.repository.remote.RemoteUserDataSource;
import com.alick.commonlibrary.utils.NetworkUtils;

public class UserRepository {

    private static UserRepository instance = null;
    private Context context;

    public void init(Context context) {
        this.context = context;
    }

    private UserRepository() {

    }

    public static UserRepository getInstance() {
        if (instance == null) {
            synchronized (UserRepository.class) {
                if (instance == null) {
                    instance = new UserRepository();
                }
            }
        }
        return instance;
    }

    public LiveData<BaseResponse<Account>> getUser(String username) {
        if (NetworkUtils.isConnected(context)) {
            return RemoteUserDataSource.getInstance().queryUserByUsername(username);
        } else {
            return LocalUserDataSource.getInstance().queryUserByUsername(username);
        }
    }
}