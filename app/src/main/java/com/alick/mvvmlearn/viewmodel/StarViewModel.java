package com.alick.mvvmlearn.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.alick.commonlibrary.base.bean.BaseResponse;
import com.alick.commonlibrary.utils.OkHttpUtils;
import com.alick.mvvmlearn.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 崔兴旺
 * @package com.alick.mvvmlearn.viewmodel
 * @title:
 * @description:
 * @date 2019/4/17 5:20
 */
public class StarViewModel extends ViewModel {

    private MutableLiveData<BaseResponse<List<User>>> listMutableLiveData;



    public MutableLiveData<BaseResponse<List<User>>> getStarListLiveData(){
        if(listMutableLiveData==null){
            listMutableLiveData=new MutableLiveData<>();
        }
        return listMutableLiveData;
    }

    public void getStarList(String url, int page, int per_page){
        Map<String,Object> paramsMap=new HashMap<>();
        paramsMap.put("page",page);
        paramsMap.put("per_page",per_page);
        OkHttpUtils.getInstance().requestGet(url, paramsMap, new OkHttpUtils.OkCallback<List<User>>() {
            @Override
            public void onSuccess(BaseResponse<List<User>> baseResponse) {
                listMutableLiveData.postValue(baseResponse);
            }

            @Override
            public void onFail(Throwable throwable) {
                listMutableLiveData.postValue(new BaseResponse<List<User>>(throwable.getMessage()));
            }
        });
    }



}
