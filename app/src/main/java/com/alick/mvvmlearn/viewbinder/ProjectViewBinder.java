package com.alick.mvvmlearn.viewbinder;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alick.mvvmlearn.R;
import com.alick.mvvmlearn.databinding.ItemProjectBinding;
import com.alick.mvvmlearn.model.Project;

import me.drakeet.multitype.ItemViewBinder;

/**
 * @author 崔兴旺
 * @package com.alick.mvvmlearn.adapter
 * @title:
 * @description: TODO
 * @date 2019/4/9 16:10
 */
public class ProjectViewBinder extends ItemViewBinder<Project, ProjectViewBinder.ProjectViewHolder> {
    @Override
    protected ProjectViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        ItemProjectBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_project, parent, false);

        return new ProjectViewHolder(binding.getRoot());
    }

    @Override
    protected void onBindViewHolder(@NonNull ProjectViewHolder holder, @NonNull Project project) {
        ItemProjectBinding binding = DataBindingUtil.getBinding(holder.itemView);
        binding.setProject(project);
    }

    public static class ProjectViewHolder extends RecyclerView.ViewHolder {

        public ProjectViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


}