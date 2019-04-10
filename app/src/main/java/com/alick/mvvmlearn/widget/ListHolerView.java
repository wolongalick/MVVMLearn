package com.alick.mvvmlearn.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author 崔兴旺
 * @package com.alick.mvvmlearn.widget
 * @title:
 * @description: TODO
 * @date 2019/4/10 18:44
 */
public class ListHolerView extends HolderView {
    private Context context;
    private SmartRecyclerView smartRecyclerView;

    public ListHolerView(@NonNull Context context) {
        this(context,null);
    }

    public ListHolerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ListHolerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
    }

    protected void init(){
        super.init();
        smartRecyclerView =new SmartRecyclerView(context);
    }

    @Override
    protected View getRealContentView() {
        return smartRecyclerView;
    }

    public SmartRecyclerView getSmartRecyclerView() {
        return smartRecyclerView;
    }
}
