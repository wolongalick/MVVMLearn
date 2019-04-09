package com.alick.mvvmlearn.repository.local;

import android.arch.lifecycle.LiveData;

import com.alick.mvvmlearn.model.User;
import com.alick.mvvmlearn.repository.UserDataSource;
import com.alick.mvvmlearn.repository.local.service.UserService;
import com.alick.mvvmlearn.repository.local.service.UserServiceImpl;
import com.alick.mvvmlearn.utils.BLog;

public class LocalUserDataSource implements UserDataSource {

    private static LocalUserDataSource instance = null;

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


    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public LiveData<User> queryUserByUsername(String username) {
        BLog.i("从本地获取数据");
        return userService.queryByUsername(username);
    }

    public LiveData<Long> addUser(User user) {
        return userService.add(user);
    }
}