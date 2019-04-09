package com.alick.mvvmlearn.repository;

import android.arch.lifecycle.LiveData;

import com.alick.mvvmlearn.model.User;

public interface UserDataSource {
    LiveData<User> queryUserByUsername(String username);
}