package com.alick.mvvmlearn.repository.remote;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.alick.commonlibrary.base.bean.BaseResponse;
import com.alick.mvvmlearn.model.Account;
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
    private MutableLiveData<BaseResponse<Account>> userMutableLiveData;

    @Override
    public LiveData<BaseResponse<Account>> queryUserByUsername(String username) {
        BLog.i("从网络获取数据");
        if(userMutableLiveData==null){
            userMutableLiveData=new MutableLiveData<>();
        }

        Map<String,Object> params=new HashMap<>();
        params.put("nickname","小鸡子");
        params.put("age","28");
        OkHttpUtils.getInstance().requestGet(OkHttpUtils.BASE_URL + "users/"+username,params, new OkHttpUtils.OkCallback<Account>() {
            @Override
            public void onSuccess(BaseResponse<Account> baseResponse){
                Account account = baseResponse.getData();
                if (account != null) {
                    //更新数据库缓存
                    LocalUserDataSource.getInstance().addUser(account);
                }
                userMutableLiveData.postValue(baseResponse);
            }

            @Override
            public void onFail(Throwable throwable) {
                throwable.printStackTrace();
                userMutableLiveData.postValue(new BaseResponse<Account>(throwable.getMessage()));
            }
        });

        return userMutableLiveData;
    }
}
