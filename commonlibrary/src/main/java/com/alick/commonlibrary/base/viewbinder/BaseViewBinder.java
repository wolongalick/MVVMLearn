package com.alick.commonlibrary.base.viewbinder;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import me.drakeet.multitype.ItemViewBinder;

/**
 * @author 崔兴旺
 * @package com.alick.commonlibrary.base.viewbinder
 * @title:
 * @description:
 * @date 2019/4/11 11:45
 */
public abstract class BaseViewBinder<Data, VH extends RecyclerView.ViewHolder, Binding extends ViewDataBinding> extends ItemViewBinder<Data, VH> {
    private OnBinderItemClickListener<Data, VH> onBinderItemClickListener;

    public interface OnBinderItemClickListener<Data, VH extends RecyclerView.ViewHolder> {
        void onBinderItemClick(@NonNull final VH holder, @NonNull final Data item, int position);
    }

    @Override
    protected final VH onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        Binding mBinding = DataBindingUtil.inflate(inflater, getItemLayoutId(), parent, false);
        Type type = getClass().getGenericSuperclass();

        Type viewHolderType = ((ParameterizedType) type).getActualTypeArguments()[1];
        try {
            Constructor<VH> constructor = ((Class) viewHolderType).getConstructor(View.class);
            return constructor.newInstance(mBinding.getRoot());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    protected final void onBindViewHolder(@NonNull final VH holder, @NonNull final Data item) {
        onBaseBindViewHolder((Binding) DataBindingUtil.getBinding(holder.itemView), holder, item, holder.getLayoutPosition());
        if (!isDisableItemClick()) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onBinderItemClickListener != null) {
                        onBinderItemClickListener.onBinderItemClick(holder, item, holder.getLayoutPosition());
                    }
                }
            });
        }
    }

    /**
     * 获取item布局id
     *
     * @return
     */
    protected abstract int getItemLayoutId();

    protected abstract void onBaseBindViewHolder(Binding binding, @NonNull final VH holder, @NonNull final Data item, int position);


    /**
     * 是否禁用条目点击功能
     *
     * @return
     */
    private boolean isDisableItemClick() {
        return false;
    }

    public void setOnBinderItemClickListener(OnBinderItemClickListener<Data, VH> onBinderItemClickListener) {
        this.onBinderItemClickListener = onBinderItemClickListener;
    }
}
