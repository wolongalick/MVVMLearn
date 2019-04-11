package com.alick.mvvmlearn.adapter;

import android.support.annotation.NonNull;

import com.alick.commonlibrary.base.adapter.BaseAdapter;
import com.alick.mvvmlearn.model.Project;
import com.alick.mvvmlearn.viewbinder.ProjectViewBinder;

import java.util.List;

/**
 * @author 崔兴旺
 * @package com.alick.mvvmlearn.adapter
 * @title:
 * @description:
 * @date 2019/4/11 11:41
 */
public class ProjectAdapter extends BaseAdapter<Project, ProjectViewBinder> {
    public ProjectAdapter(@NonNull List<Project> items) {
        super(items);
    }
}
