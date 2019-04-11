package com.alick.commonlibrary.base.adapter;

import android.support.annotation.NonNull;

import com.alick.commonlibrary.base.viewbinder.BaseViewBinder;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import me.drakeet.multitype.MultiTypeAdapter;
import me.drakeet.multitype.TypePool;

/**
 * @author 崔兴旺
 * @package com.alick.commonlibrary.base.adapter
 * @title:
 * @description:
 * @date 2019/4/11 11:36
 */
public abstract class BaseAdapter<Data, VB extends BaseViewBinder> extends MultiTypeAdapter {
    private List<Data> datas;
    private VB binder;


    public BaseAdapter(@NonNull List<Data> items) {
        super(items);
        this.datas=items;
        init();
    }

    public BaseAdapter(@NonNull List<Data> items, int initialCapacity) {
        super(items, initialCapacity);
        this.datas=items;
        init();
    }

    public BaseAdapter(@NonNull List<Data> items, @NonNull TypePool pool) {
        super(items, pool);
        this.datas=items;
        init();
    }

    private void init(){
        Type[] actualTypeArguments = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments();
        Class <Data> dataClass = (Class <Data>) actualTypeArguments[0];
        Class <VB> vbClass= (Class<VB>) actualTypeArguments[1];
        setItems(datas);
        try {
            binder = vbClass.newInstance();
            register(dataClass, binder);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public List<Data> getDatas() {
        return datas;
    }

    public VB getBinder() {
        return binder;
    }

}
