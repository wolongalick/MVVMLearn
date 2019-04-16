package com.alick.mvvmlearn.repository.local.service;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.alick.commonlibrary.base.bean.BaseResponse;
import com.alick.mvvmlearn.model.Account;
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
    public LiveData<Long> add(final Account account) {
        final MutableLiveData<Long> data = new MutableLiveData<>();
        new Thread() {
            @Override
            public void run() {
                Long result = userDao.add(account);
                data.postValue(result);
            }
        }.start();
        return data;
    }

    @Override
    public LiveData<BaseResponse<Account>> queryByUsername(final String username) {
        final MutableLiveData<BaseResponse<Account>> data = new MutableLiveData<>();
        new Thread() {
            @Override
            public void run() {
                Account account = userDao.queryByUsername(username);
                data.postValue(new BaseResponse<>(account));
            }
        }.start();
        return data;
    }
}
