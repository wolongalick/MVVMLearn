package com.alick.mvvmlearn.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.alick.commonlibrary.base.bean.BaseResponse;
import com.alick.mvvmlearn.model.Project;
import com.alick.commonlibrary.utils.OkHttpUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 崔兴旺
 * @package com.alick.mvvmlearn.viewmodel
 * @title:
 * @description: TODO
 * @date 2019/4/9 14:32
 */
public class ProjectListViewModel extends ViewModel {

    private MutableLiveData<BaseResponse<List<Project>>> listMutableLiveData;

    public MutableLiveData<BaseResponse<List<Project>>> getProjectsLiveData(String username, int page, int per_page) {
        if(listMutableLiveData==null){
            listMutableLiveData = new MutableLiveData<>();
        }

        Map<String,Object> paramsMap=new HashMap<>();
        paramsMap.put("page",page);
        paramsMap.put("per_page",per_page);

        OkHttpUtils.getInstance().requestGet(OkHttpUtils.BASE_URL + "users/" + username + "/repos", paramsMap, new OkHttpUtils.OkCallback<List<Project>>() {
            @Override
            public void onSuccess(BaseResponse<List<Project>> baseResponse) {
                listMutableLiveData.postValue(baseResponse);
            }

            @Override
            public void onFail(Throwable throwable) {
                throwable.printStackTrace();
                listMutableLiveData.postValue(new BaseResponse<List<Project>>(throwable.getMessage()));
            }
        });
        return listMutableLiveData;
    }

}
