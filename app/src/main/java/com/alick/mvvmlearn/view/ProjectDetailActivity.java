package com.alick.mvvmlearn.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Toast;

import com.alick.commonlibrary.base.activity.BaseActivity;
import com.alick.commonlibrary.base.activity.BaseFragment;
import com.alick.commonlibrary.base.bean.BaseResponse;
import com.alick.mvvmlearn.R;
import com.alick.mvvmlearn.adapter.ProjectTabAdapter;
import com.alick.mvvmlearn.constant.IntentKey;
import com.alick.mvvmlearn.databinding.ActivityProjectDetailBinding;
import com.alick.mvvmlearn.model.ProjectDetail;
import com.alick.mvvmlearn.view.fragment.StarListFragment;
import com.alick.mvvmlearn.view.fragment.WatchListFragment;
import com.alick.mvvmlearn.viewmodel.ProjectDetailViewModel;
import com.alick.mvvmlearn.widget.ColorFlipPagerTitleView;

import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 崔兴旺
 * @package com.alick.mvvmlearn.view
 * @title:
 * @description:
 * @date 2019/4/16 14:36
 */
public class ProjectDetailActivity extends BaseActivity<ActivityProjectDetailBinding> {
    private ProjectDetailViewModel projectDetailViewModel;
    private String[] messageCenterTab = {"star", "watch"};
    private String projectDetailUrl;
    private ProjectTabAdapter projectTabAdapter;
    private List<BaseFragment> fragments;

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
        fragments=new ArrayList<>();
        fragments.add(StarListFragment.getInstance(getIntent().getStringExtra(IntentKey.STARS_URL)));
        fragments.add(WatchListFragment.getInstance(getIntent().getStringExtra(IntentKey.WATCH_URL)));
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
        initMagicIndicator();
    }


    /**
     * 页面加载失败后,点击页面重试的回调函数
     */
    @Override
    public void onReload() {

    }

    private void initMagicIndicator() {
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setEnablePivotScroll(true);//多指示器模式，可以滑动
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return messageCenterTab.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorFlipPagerTitleView(context);
                simplePagerTitleView.setPadding(10, 10, 10, 10);
                simplePagerTitleView.setText(messageCenterTab[index]);
                simplePagerTitleView.setNormalColor(getResources().getColor(R.color.color_9b9b9b));
                simplePagerTitleView.setTextColor(getResources().getColor(R.color.color_9b9b9b));
                simplePagerTitleView.setSelectedColor(getResources().getColor(R.color.color_4a4a4a));
                simplePagerTitleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                simplePagerTitleView.setBackgroundColor(Color.TRANSPARENT);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mBinding.viewPager.setCurrentItem(index);
                    }
                });


                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {

                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(UIUtil.dip2px(context, 2));
                indicator.setLineWidth(UIUtil.dip2px(context, 50));
                indicator.setRoundRadius(UIUtil.dip2px(context, 3));
                //indicator.setYOffset(DensityUtils.dp2px(getContext(), 3));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setColors(getResources().getColor(R.color.color_4a4a4a));

                return null;
            }
        });
        mBinding.magicIndicator.setNavigator(commonNavigator);
        mBinding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mBinding.magicIndicator.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            public void onPageSelected(int position) {
                mBinding.magicIndicator.onPageSelected(position);
            }

            public void onPageScrollStateChanged(int state) {

            }
        });
        projectTabAdapter = new ProjectTabAdapter(getSupportFragmentManager(), fragments);
        mBinding.viewPager.setAdapter(projectTabAdapter);
    }
}
