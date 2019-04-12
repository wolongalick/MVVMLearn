package com.alick.mvvmlearn.repository.local.service;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.alick.commonlibrary.base.bean.BaseResponse;
import com.alick.mvvmlearn.model.User;
import com.alick.mvvmlearn.repository.local.dao.UserDao;
import com.alick.mvvmlearn.repository.local.db.DBHelper;

/**
 * @author 崔兴旺
 * @package com.alick.mvvmlearn.service
 * @title:
 * @description: TODO
 * @date 2019/4/8 15:19
 */
public class UserServiceImpl implements UserService {
    private static final UserServiceImpl instance = new UserServiceImpl();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    private UserDao userDao = DBHelper.getInstance().getDb().getUserDao();

    @Override
    public LiveData<Long> add(final User user) {
        final MutableLiveData<Long> data = new MutableLiveData<>();
        new Thread() {
            @Override
            public void run() {
                Long result = userDao.add(user);
                data.postValue(result);
            }
        }.start();
        return data;
    }

    @Override
    public LiveData<BaseResponse<User>> queryByUsername(final String username) {
        final MutableLiveData<BaseResponse<User>> data = new MutableLiveData<>();
        new Thread() {
            @Override
            public void run() {
                User user = userDao.queryByUsername(username);
                data.postValue(new BaseResponse<>(user));
            }
        }.start();
        return data;
    }
}
