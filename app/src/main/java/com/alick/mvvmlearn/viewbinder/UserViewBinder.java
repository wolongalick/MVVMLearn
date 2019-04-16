package com.alick.mvvmlearn.viewbinder;

import android.support.annotation.NonNull;
import android.view.View;

import com.alick.commonlibrary.base.viewbinder.BaseViewBinder;
import com.alick.commonlibrary.base.viewholder.BaseViewHolder;
import com.alick.mvvmlearn.R;
import com.alick.mvvmlearn.databinding.ItemUserBinding;
import com.alick.mvvmlearn.model.User;

/**
 * @author 崔兴旺
 * @package com.alick.mvvmlearn.viewbinder
 * @title:
 * @description:
 * @date 2019/4/16 16:48
 */
public class UserViewBinder extends BaseViewBinder<User, UserViewBinder.UserViewHolder, ItemUserBinding> {

    @Override
    protected void onBaseBindViewHolder(ItemUserBinding binding,@NonNull UserViewHolder holder, @NonNull User user, int position) {
        binding.setUser(user);
    }

    /**
     * 获取item布局id
     * @return
     */
    @Override
    protected int getItemLayoutId() {
        return R.layout.item_user;
    }

    public static class UserViewHolder extends BaseViewHolder {
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
