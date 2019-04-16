package com.alick.mvvmlearn.viewmodel;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;

import com.alick.commonlibrary.base.bean.BaseResponse;
import com.alick.mvvmlearn.model.Account;
import com.alick.mvvmlearn.repository.UserRepository;

public class UserViewModel extends AndroidViewModel {
    private UserRepository userRepository;
    private LiveData<BaseResponse<Account>> ldUser;
    private MutableLiveData<String> ldUsername;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository();
    }

    public LiveData<BaseResponse<Account>> getUser() {
        if (null == ldUser) {
            ldUsername = new MutableLiveData<>();
            //ldUsername中的value一旦发生变化就会回调apply()方法
            ldUser = Transformations.switchMap(ldUsername, new Function<String, LiveData<BaseResponse<Account>>>() {
                @Override
                public LiveData<BaseResponse<Account>> apply(String username) {
                    return userRepository.getUser(getApplication(),username);
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