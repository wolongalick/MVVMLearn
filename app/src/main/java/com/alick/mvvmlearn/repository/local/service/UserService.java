package com.alick.mvvmlearn.repository.local.service;

import android.arch.lifecycle.LiveData;

import com.alick.mvvmlearn.model.User;

public interface UserService {
    LiveData<Long> add(User user);

    LiveData<User> queryByUsername(String username);
}