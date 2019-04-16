package com.alick.mvvmlearn.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.alick.commonlibrary.base.activity.BaseActivity;
import com.alick.commonlibrary.base.bean.BaseResponse;
import com.alick.mvvmlearn.R;
import com.alick.mvvmlearn.constant.IntentKey;
import com.alick.mvvmlearn.databinding.ActivityProjectDetailBinding;
import com.alick.mvvmlearn.model.ProjectDetail;
import com.alick.mvvmlearn.viewmodel.ProjectDetailViewModel;

/**
 * @author 崔兴旺
 * @package com.alick.mvvmlearn.view
 * @title:
 * @description:
 * @date 2019/4/16 14:36
 */
public class ProjectDetailActivity extends BaseActivity<ActivityProjectDetailBinding> {
    private ProjectDetailViewModel projectDetailViewModel;

    private String projectDetailUrl;

    /**
     * 获取布局ID
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_project_detail;
    }

    /**
     * 初始化数据
     */
    @Override
    public void initData() {
        projectDetailUrl=getIntent().getStringExtra(IntentKey.PROJECT_DETAIL_URL);
        projectDetailViewModel = ViewModelProviders.of(this).get(ProjectDetailViewModel.class);
    }

    /**
     * 初始化监听
     */
    @Override
    public void initListener() {
        projectDetailViewModel.getDetailLiveData(projectDetailUrl).observe(this, new Observer<BaseResponse<ProjectDetail>>() {
            @Override
            public void onChanged(@Nullable BaseResponse<ProjectDetail> projectDetailBaseResponse) {
                ProjectDetail projectDetail = projectDetailBaseResponse.getData();

                if(projectDetail!=null){
                    mBinding.setProjectDetail(projectDetail);
                    mHolderView.showRealContentView();
                }else {
                    mHolderView.showFailView();
                    Toast.makeText(getApplicationContext(),projectDetailBaseResponse.getErrorMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 初始化视图
     */
    @Override
    public void initViews() {

    }


    /**
     * 页面加载失败后,点击页面重试的回调函数
     */
    @Override
    public void onReload() {

    }


}
