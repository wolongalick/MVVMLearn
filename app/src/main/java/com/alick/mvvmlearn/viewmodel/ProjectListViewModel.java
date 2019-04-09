package com.alick.mvvmlearn.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.alick.mvvmlearn.model.Project;
import com.alick.mvvmlearn.utils.OkHttpUtils;

import java.util.List;

/**
 * @author 崔兴旺
 * @package com.alick.mvvmlearn.viewmodel
 * @title:
 * @description: TODO
 * @date 2019/4/9 14:32
 */
public class ProjectListViewModel extends ViewModel {

    private MutableLiveData<List<Project>> listMutableLiveData;

    public MutableLiveData<List<Project>> getListMutableLiveData(String username) {
        if(listMutableLiveData==null){
            listMutableLiveData = new MutableLiveData<>();
        }

        OkHttpUtils.getInstance().requestGet(OkHttpUtils.BASE_URL + "users/" + username + "/repos", null, new OkHttpUtils.OkCallback<List<Project>>() {
            @Override
            public void onSuccess(List<Project> projects) {
                if(projects!=null){
                    listMutableLiveData.postValue(projects);
                }else {
                    listMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFail(Throwable throwable) {
                throwable.printStackTrace();
                listMutableLiveData.postValue(null);
            }
        });
        return listMutableLiveData;
    }

}
