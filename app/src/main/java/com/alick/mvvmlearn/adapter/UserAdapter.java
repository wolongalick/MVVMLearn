package com.alick.mvvmlearn.adapter;

import android.support.annotation.NonNull;

import com.alick.commonlibrary.base.adapter.BaseAdapter;
import com.alick.mvvmlearn.model.User;
import com.alick.mvvmlearn.viewbinder.UserViewBinder;

import java.util.List;

/**
 * @author 崔兴旺
 * @package com.alick.mvvmlearn.adapter
 * @title:
 * @description:
 * @date 2019/4/16 16:48
 */
public class UserAdapter extends BaseAdapter<User, UserViewBinder> {

    public UserAdapter(@NonNull List<User> items) {
        super(items);
    }
}
