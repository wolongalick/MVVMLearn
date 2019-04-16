package com.alick.mvvmlearn.view.fragment;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.alick.commonlibrary.base.activity.BaseListFragment;
import com.alick.commonlibrary.base.bean.BaseResponse;
import com.alick.commonlibrary.constatnt.CommonConstant;
import com.alick.mvvmlearn.R;
import com.alick.mvvmlearn.adapter.UserAdapter;
import com.alick.mvvmlearn.constant.IntentKey;
import com.alick.mvvmlearn.databinding.ItemUserBinding;
import com.alick.mvvmlearn.model.User;
import com.alick.mvvmlearn.viewmodel.StarViewModel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.List;

/**
 * @author 崔兴旺
 * @package com.alick.mvvmlearn.view.fragment
 * @title:
 * @description:
 * @date 2019/4/16 16:43
 */
public class StarListFragment extends BaseListFragment<ItemUserBinding, UserAdapter> {
    private StarViewModel starViewModel;
    private String url;


    public static StarListFragment getInstance(String url) {
        Bundle bundle = new Bundle();
        bundle.putString(IntentKey.STARS_URL, url);

        StarListFragment starListFragment = new StarListFragment();
        starListFragment.setArguments(bundle);
        return starListFragment;
    }

    /**
     * 获取布局ID
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_star;
    }


    /**
     * 初始化数据
     */
    @Override
    public void initData() {
        starViewModel = new StarViewModel();
        url = getArguments().getString(IntentKey.STARS_URL);
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
        starViewModel.getStarListLiveData().observe(this, new Observer<BaseResponse<List<User>>>() {
            @Override
            public void onChanged(@Nullable BaseResponse<List<User>> baseResponse) {
                List<User> users = baseResponse.getData();
                if (users != null) {
                    updateData(users, true);
                } else {
                    updateData(null, false);
                    Toast.makeText(getContext(), baseResponse.getErrorMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        mHolderView.showLoadingView();
        onReload();
    }

    /**
     * 页面加载失败后,点击页面重试的回调函数
     */
    @Override
    public void onReload() {
        getStars(CommonConstant.DEFAULT_FIRST_PAGE_NUM, CommonConstant.DEFAULT_PAGE_SIZE);
    }

    private void getStars(int page, int per_page) {
        starViewModel.getStarList(url, page, per_page);
    }

    /**
     * 下拉刷新的回调函数
     *
     * @param refreshLayout
     * @param pageSize
     */
    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout, int pageSize) {
        getStars(CommonConstant.DEFAULT_FIRST_PAGE_NUM,pageSize);
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
        getStars(pageNum,pageSize);
    }

}
