package com.alick.mvvmlearn.viewmodel;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.alick.commonlibrary.base.bean.BaseResponse;
import com.alick.mvvmlearn.model.Account;
import com.alick.mvvmlearn.repository.UserRepository;

public class UserViewModel extends ViewModel {
    private UserRepository userRepository = UserRepository.getInstance();
    private LiveData<BaseResponse<Account>> ldUser;
    private MutableLiveData<String> ldUsername;

    public LiveData<BaseResponse<Account>> getUser() {
        if (null == ldUser) {
            ldUsername = new MutableLiveData<>();
            //ldUsername中的value一旦发生变化就会回调apply()方法
            ldUser = Transformations.switchMap(ldUsername, new Function<String, LiveData<BaseResponse<Account>>>() {
                @Override
                public LiveData<BaseResponse<Account>> apply(String username) {
                    return userRepository.getUser(username);
                }
            });
        }
        return ldUser;
    }

    public void reload(String username) {
        ldUsername.setValue(username);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        ldUsername=null;
        ldUser=null;
    }
}