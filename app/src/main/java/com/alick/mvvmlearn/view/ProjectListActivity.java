package com.alick.mvvmlearn.view;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.alick.mvvmlearn.R;
import com.alick.mvvmlearn.base.BaseActivity;
import com.alick.mvvmlearn.constant.Constant;
import com.alick.mvvmlearn.constant.IntentKey;
import com.alick.mvvmlearn.databinding.ActivityProjectListBinding;
import com.alick.mvvmlearn.model.Project;
import com.alick.mvvmlearn.utils.RefreshLoadMoreUtils;
import com.alick.mvvmlearn.viewbinder.ProjectViewBinder;
import com.alick.mvvmlearn.viewmodel.ProjectListViewModel;
import com.alick.mvvmlearn.widget.OnReloadListener;
import com.alick.mvvmlearn.widget.WrapContentLinearLayoutManager;
import com.alick.mvvmlearn.widget.WySmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

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
    private List<Project> allProjects;
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
        allProjects = new ArrayList<>();
        projectViewBinder = new ProjectViewBinder();

        adapter = new MultiTypeAdapter();
        adapter.register(Project.class, projectViewBinder);
        adapter.setItems(allProjects);
        mBinding.recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(this));
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
                projectListViewModel.getListMutableLiveData(username, Constant.DEFAULT_FIRST_PAGE_NUM, Constant.DEFAULT_PAGE_SIZE);
            }
        });

        mBinding.refreshLayout.setOnWyRefreshListener(new WySmartRefreshLayout.OnWyRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout, int pageSize) {
                projectListViewModel.getListMutableLiveData(username, Constant.DEFAULT_FIRST_PAGE_NUM, pageSize);
            }
        }).setOnWyLoadMoreListener(new WySmartRefreshLayout.OnWyLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout, int pageNum, int pageSize) {
                projectListViewModel.getListMutableLiveData(username, pageNum, pageSize);
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
        listMutableLiveData = projectListViewModel.getListMutableLiveData(username, Constant.DEFAULT_FIRST_PAGE_NUM, Constant.DEFAULT_PAGE_SIZE);
        listMutableLiveData.observe(this, new Observer<List<Project>>() {
            @Override
            public void onChanged(@Nullable List<Project> projects) {
                RefreshLoadMoreUtils.updateData(mBinding.holderView,mBinding.refreshLayout,adapter,allProjects,projects,projects!=null);
                /*if (projects != null) {
                    if (!projects.isEmpty()) {
                        if (mBinding.refreshLayout.getState() == RefreshState.Loading) {
                            allProjects.addAll(projects);
                            adapter.notifyItemRangeChanged(allProjects.size() - projects.size(), projects.size());
                            mBinding.refreshLayout.finishLoadMore();//此时状态:Loading
                            mBinding.holderView.showRealContentView();
                        } else {
                            allProjects.clear();
                            allProjects.addAll(projects);
                            adapter.notifyItemRangeChanged(0, allProjects.size());
                            mBinding.refreshLayout.finishRefresh();//此时状态:Refreshing
                            mBinding.holderView.showRealContentView();
                        }
                    } else {
                        adapter.notifyDataSetChanged();
                        mBinding.holderView.showEmptyView();
                        if (mBinding.refreshLayout.getState() == RefreshState.Loading) {
                            mBinding.refreshLayout.finishLoadMore();
                        } else {
                            mBinding.refreshLayout.finishRefresh();
                        }
                    }
                } else {
                    mBinding.holderView.showFailView();
                    if (mBinding.refreshLayout.getState() == RefreshState.Loading) {
                        mBinding.refreshLayout.finishLoadMore(false);
                    } else {
                        mBinding.refreshLayout.finishRefresh(false);
                    }
                }*/
            }
        });
    }


}
