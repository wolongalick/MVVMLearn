package com.alick.mvvmlearn.repository.remote;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.alick.mvvmlearn.model.User;
import com.alick.mvvmlearn.repository.UserDataSource;
import com.alick.mvvmlearn.repository.local.LocalUserDataSource;
import com.alick.mvvmlearn.utils.BLog;
import com.alick.mvvmlearn.utils.OkHttpUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 崔兴旺
 * @package com.alick.mvvmlearn.repository.remote
 * @title:
 * @description: TODO
 * @date 2019/4/8 15:30
 */
public class RemoteUserDataSource implements UserDataSource {

    private static RemoteUserDataSource instance = null;

    private RemoteUserDataSource() {
    }

    public static RemoteUserDataSource getInstance() {
        if (instance == null) {
            synchronized (RemoteUserDataSource.class) {
                if (instance == null) {
                    instance = new RemoteUserDataSource();
                }
            }
        }
        return instance;
    }

    @Override
    public LiveData<User> queryUserByUsername(String username) {
        BLog.i("从网络获取数据");
        final MutableLiveData<User> userMutableLiveData = new MutableLiveData<>();


        Map<String,Object> params=new HashMap<>();
        params.put("nickname","小鸡子");
        params.put("age","28");

        OkHttpUtils.getInstance().requestGet(OkHttpUtils.BASE_URL + "/users/wolongalick",params, new OkHttpUtils.OkCallback<User>() {
            @Override
            public void onSuccess(User user) {
                if (user != null) {
                    userMutableLiveData.postValue(user);
                    //更新数据库缓存
                    LocalUserDataSource.getInstance().addUser(user);
                }
            }

            @Override
            public void onFail(Throwable throwable) {
                throwable.printStackTrace();
            }
        });

        return userMutableLiveData;
    }
}
