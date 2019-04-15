package com.alick.mvvmlearn.repository.remote;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.alick.commonlibrary.base.bean.BaseResponse;
import com.alick.mvvmlearn.model.User;
import com.alick.mvvmlearn.repository.UserDataSource;
import com.alick.mvvmlearn.repository.local.LocalUserDataSource;
import com.alick.commonlibrary.utils.BLog;
import com.alick.commonlibrary.utils.OkHttpUtils;

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
    public LiveData<BaseResponse<User>> queryUserByUsername(String username) {
        BLog.i("从网络获取数据");


        Map<String,Object> params=new HashMap<>();
        params.put("nickname","小鸡子");
        params.put("age","28");
        final MutableLiveData<BaseResponse<User>> userMutableLiveData = new MutableLiveData<>();
        OkHttpUtils.getInstance().requestGet(OkHttpUtils.BASE_URL + "users/"+username,params, new OkHttpUtils.OkCallback<User>() {
            @Override
            public void onSuccess(BaseResponse<User> baseResponse){
                User user = baseResponse.getData();
                if (user != null) {
                    //更新数据库缓存
                    LocalUserDataSource.getInstance().addUser(user);
                }
                userMutableLiveData.postValue(baseResponse);
            }

            @Override
            public void onFail(Throwable throwable) {
                throwable.printStackTrace();
                userMutableLiveData.postValue(new BaseResponse<User>(throwable.getMessage()));
            }
        });

        return userMutableLiveData;
    }
}
