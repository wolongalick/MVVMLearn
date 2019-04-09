package com.alick.mvvmlearn.view;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.alick.mvvmlearn.R;
import com.alick.mvvmlearn.base.BaseActivity;
import com.alick.mvvmlearn.constant.IntentKey;
import com.alick.mvvmlearn.databinding.ActivityProjectListBinding;
import com.alick.mvvmlearn.model.Project;
import com.alick.mvvmlearn.viewbinder.ProjectViewBinder;
import com.alick.mvvmlearn.viewmodel.ProjectListViewModel;
import com.alick.mvvmlearn.widget.OnReloadListener;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * @author 崔兴旺
 * @package com.alick.mvvmlearn.view
 * @title:
 * @description: TODO
 * @date 2019/4/9 11:57
 */
public class ProjectListActivity extends BaseActivity<ActivityProjectListBinding> {
    private ProjectListViewModel projectListViewModel;
    private String username;
    private ProjectViewBinder projectViewBinder;
    private List<Project> projects;
    private MultiTypeAdapter adapter;
    private MutableLiveData<List<Project>> listMutableLiveData;

    /**
     * 获取布局ID
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_project_list;
    }

    /**
     * 初始化视图
     */
    @Override
    public void initViews() {
        username = getIntent().getStringExtra(IntentKey.USERNAME);
        projects = new ArrayList<>();
        projectViewBinder = new ProjectViewBinder();

        adapter = new MultiTypeAdapter();
        adapter.register(Project.class, projectViewBinder);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBinding.recyclerView.setAdapter(adapter);
    }

    /**
     * 初始化监听
     */
    @Override
    public void initListener() {
        mBinding.holderView.setOnReloadListener(new OnReloadListener() {
            @Override
            public void onReload() {
                projectListViewModel.getListMutableLiveData(username);
            }
        });
    }

    /**
     * 初始化数据
     */
    @Override
    public void initData() {
        projectListViewModel = new ProjectListViewModel();
        mBinding.holderView.showLoadingView();
        listMutableLiveData = projectListViewModel.getListMutableLiveData(username);
        listMutableLiveData.observe(this, new Observer<List<Project>>() {
            @Override
            public void onChanged(@Nullable List<Project> projects) {
                if(projects!=null){
                    if(!projects.isEmpty()){
                        adapter.setItems(projects);
                        adapter.notifyDataSetChanged();
                        mBinding.holderView.showRealContentView();
                    }else {
                        adapter.setItems(projects);
                        adapter.notifyDataSetChanged();
                        mBinding.holderView.showEmptyView();
                    }
                }else {
                    mBinding.holderView.showFailView();
                }
            }
        });
    }


}
