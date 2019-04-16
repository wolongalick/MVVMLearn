package com.alick.mvvmlearn.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.alick.commonlibrary.base.bean.BaseResponse;
import com.alick.commonlibrary.utils.OkHttpUtils;
import com.alick.mvvmlearn.model.ProjectDetail;

/**
 * @author 崔兴旺
 * @package com.alick.mvvmlearn.viewmodel
 * @title:
 * @description:
 * @date 2019/4/16 14:47
 */
public class ProjectDetailViewModel extends ViewModel {

    public LiveData<BaseResponse<ProjectDetail>> getDetailLiveData(String url){
        final MutableLiveData<BaseResponse<ProjectDetail>> projectDetailMutableLiveData=new MutableLiveData<>();

        OkHttpUtils.getInstance().requestGet(url, null, new OkHttpUtils.OkCallback<ProjectDetail>() {
            @Override
            public void onSuccess(BaseResponse<ProjectDetail> baseResponse) {
                projectDetailMutableLiveData.postValue(baseResponse);
            }

            @Override
            public void onFail(Throwable throwable) {
                projectDetailMutableLiveData.postValue(new BaseResponse<ProjectDetail>(throwable.getMessage()));
            }
        });
        return projectDetailMutableLiveData;
    }
}
