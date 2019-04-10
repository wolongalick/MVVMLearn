package com.alick.mvvmlearn.view;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.alick.mvvmlearn.R;
import com.alick.mvvmlearn.base.BaseActivity;
import com.alick.mvvmlearn.constant.IntentKey;
import com.alick.mvvmlearn.databinding.ActivityProjectListBinding;
import com.alick.mvvmlearn.model.Project;
import com.alick.mvvmlearn.viewbinder.ProjectViewBinder;
import com.alick.mvvmlearn.viewmodel.ProjectListViewModel;
import com.alick.mvvmlearn.widget.OnReloadListener;
import com.alick.mvvmlearn.widget.WrapContentLinearLayoutManager;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.jetbrains.annotations.NotNull;

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

    private boolean isLoadmoring;


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

        mBinding.refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NotNull RefreshLayout refreshlayout) {
                isLoadmoring=false;
                projectListViewModel.getListMutableLiveData(username);
            }
        });
        mBinding.refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NotNull RefreshLayout refreshlayout) {
                isLoadmoring=true;
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
                if (projects != null) {
                    if (!projects.isEmpty()) {
                        if(isLoadmoring){
                            allProjects.addAll(projects);
                            adapter.notifyItemRangeChanged(allProjects.size()-projects.size(),projects.size());
                            mBinding.refreshLayout.finishLoadMore(1500);
                            mBinding.holderView.showRealContentView();
                        }else {
                            allProjects.clear();
                            allProjects.addAll(projects);
                            adapter.notifyItemRangeChanged(0,allProjects.size());
                            mBinding.refreshLayout.finishRefresh(1500);
                            mBinding.holderView.showRealContentView();
                        }
                    } else {
                        adapter.notifyDataSetChanged();
                        mBinding.holderView.showEmptyView();
                        if(isLoadmoring){
                            mBinding.refreshLayout.finishLoadMore(1500);
                        }else {
                            mBinding.refreshLayout.finishRefresh(1500);
                        }
                    }
                } else {
                    mBinding.holderView.showFailView();
                    if(isLoadmoring){
                        mBinding.refreshLayout.finishLoadMore(false);
                    }else {
                        mBinding.refreshLayout.finishRefresh(false);
                    }
                }
            }
        });
    }


}
