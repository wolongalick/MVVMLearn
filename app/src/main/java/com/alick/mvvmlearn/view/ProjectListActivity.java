package com.alick.mvvmlearn.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.alick.commonlibrary.base.activity.BaseListActivity;
import com.alick.commonlibrary.base.bean.BaseResponse;
import com.alick.commonlibrary.base.viewbinder.BaseViewBinder;
import com.alick.commonlibrary.constatnt.CommonConstant;
import com.alick.mvvmlearn.R;
import com.alick.mvvmlearn.adapter.ProjectAdapter;
import com.alick.mvvmlearn.constant.IntentKey;
import com.alick.mvvmlearn.databinding.ActivityProjectListBinding;
import com.alick.mvvmlearn.model.Project;
import com.alick.mvvmlearn.viewbinder.ProjectViewBinder;
import com.alick.mvvmlearn.viewmodel.ProjectListViewModel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.List;

/**
 * @author 崔兴旺
 * @package com.alick.mvvmlearn.view
 * @title:
 * @description: TODO
 * @date 2019/4/9 11:57
 */
public class ProjectListActivity extends BaseListActivity<ActivityProjectListBinding, ProjectAdapter> {
    private ProjectListViewModel projectListViewModel;
    private String username;

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
     * 初始化数据
     */
    @Override
    public void initData() {
        username = getIntent().getStringExtra(IntentKey.USERNAME);
        projectListViewModel = ViewModelProviders.of(this).get(ProjectListViewModel.class);
    }

    /**
     * 初始化监听
     */
    @Override
    public void initListener() {

    }

    /**
     * 初始化视图
     */
    @Override
    public void initViews() {
        mListHolerView.showLoadingView();
        projectListViewModel.getProjectsLiveData(username, CommonConstant.DEFAULT_FIRST_PAGE_NUM, CommonConstant.DEFAULT_PAGE_SIZE).observe(this, new Observer<BaseResponse<List<Project>>>() {
            @Override
            public void onChanged(@Nullable BaseResponse<List<Project>> baseResponse) {
                List<Project> projects = baseResponse.getData();
                if (projects != null) {
                    updateData(projects, true);
                } else {
                    updateData(null, false);
                    Toast.makeText(getApplicationContext(), baseResponse.getErrorMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        ProjectViewBinder projectViewBinder = mAdapter.getBinder();
        projectViewBinder.setOnBinderItemClickListener(new BaseViewBinder.OnBinderItemClickListener<Project, ProjectViewBinder.ProjectViewHolder>() {
            @Override
            public void onBinderItemClick(@NonNull ProjectViewBinder.ProjectViewHolder holder, @NonNull Project project, int position) {
                startActivity(new Intent(getApplicationContext(), ProjectDetailActivity.class).putExtra(IntentKey.PROJECT_DETAIL_URL, project.getUrl()));
            }
        });
    }



    /**
     * 下拉刷新的回调函数
     *
     * @param refreshLayout
     * @param pageSize
     */
    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout, int pageSize) {
        projectListViewModel.getProjectsLiveData(username, CommonConstant.DEFAULT_FIRST_PAGE_NUM, pageSize);
    }

    /**
     * 加载更多的回调函数
     *
     * @param refreshLayout
     * @param pageNum
     * @param pageSize
     */
    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout, int pageNum, int pageSize) {
        projectListViewModel.getProjectsLiveData(username, pageNum, pageSize);
    }

    /**
     * 页面加载失败后,点击页面重试的回调函数
     */
    @Override
    public void onReload() {
        projectListViewModel.getProjectsLiveData(username, CommonConstant.DEFAULT_FIRST_PAGE_NUM, CommonConstant.DEFAULT_PAGE_SIZE);
    }


}
