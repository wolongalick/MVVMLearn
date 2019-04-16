package com.alick.mvvmlearn.repository.local;

import android.arch.lifecycle.LiveData;

import com.alick.commonlibrary.base.bean.BaseResponse;
import com.alick.commonlibrary.utils.BLog;
import com.alick.mvvmlearn.model.Account;
import com.alick.mvvmlearn.repository.UserDataSource;
import com.alick.mvvmlearn.repository.local.service.UserService;
import com.alick.mvvmlearn.repository.local.service.UserServiceImpl;

public class LocalUserDataSource implements UserDataSource {

    private static LocalUserDataSource instance = null;

    private UserService userService = UserServiceImpl.getInstance();

    private LocalUserDataSource() {
    }

    public static LocalUserDataSource getInstance() {
        if (instance == null) {
            synchronized (LocalUserDataSource.class) {
                if (instance == null) {
                    instance = new LocalUserDataSource();
                }
            }
        }
        return instance;
    }


    @Override
    public LiveData<BaseResponse<Account>> queryUserByUsername(String username) {
        BLog.i("从本地获取数据");
        return userService.queryByUsername(username);
    }

    public void addUser(Account account) {
        userService.add(account);
    }
}