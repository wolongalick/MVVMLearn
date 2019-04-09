package com.alick.mvvmlearn.repository;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.alick.mvvmlearn.model.User;
import com.alick.mvvmlearn.repository.local.LocalUserDataSource;
import com.alick.mvvmlearn.repository.remote.RemoteUserDataSource;
import com.alick.mvvmlearn.utils.NetworkUtils;

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

    public LiveData<User> getUser(String username) {
        if (NetworkUtils.isConnected(context)) {
            return RemoteUserDataSource.getInstance().queryUserByUsername(username);
        } else {
            return LocalUserDataSource.getInstance().queryUserByUsername(username);
        }
    }
}