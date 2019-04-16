package com.alick.mvvmlearn.viewbinder;

import android.support.annotation.NonNull;
import android.view.View;

import com.alick.commonlibrary.base.viewbinder.BaseViewBinder;
import com.alick.commonlibrary.base.viewholder.BaseViewHolder;
import com.alick.mvvmlearn.R;
import com.alick.mvvmlearn.databinding.ItemProjectBinding;
import com.alick.mvvmlearn.model.Project;

/**
 * @author 崔兴旺
 * @package com.alick.mvvmlearn.adapter
 * @title:
 * @description:
 * @date 2019/4/9 16:10
 */
public class ProjectViewBinder extends BaseViewBinder<Project, ProjectViewBinder.ProjectViewHolder,ItemProjectBinding> {

    /**
     * 获取item布局id
     * @return
     */
    @Override
    protected int getItemLayoutId() {
        return R.layout.item_project;
    }

    @Override
    protected void onBaseBindViewHolder(ItemProjectBinding binding,@NonNull ProjectViewHolder holder, @NonNull Project project, int position) {
        binding.setProject(project);
    }

    public static class ProjectViewHolder extends BaseViewHolder {
        public ProjectViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }





}
