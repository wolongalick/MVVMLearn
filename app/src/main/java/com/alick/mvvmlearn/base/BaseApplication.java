package com.alick.mvvmlearn.base;

import android.app.Application;

import com.alick.mvvmlearn.repository.UserRepository;
import com.alick.mvvmlearn.repository.local.db.DBHelper;

/**
 * @author 崔兴旺
 * @package com.alick.mvvmlearn.base
 * @title:
 * @description: TODO
 * @date 2019/4/8 15:17
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DBHelper.getInstance().init(this);
        UserRepository.getInstance().init(this);
    }
}
