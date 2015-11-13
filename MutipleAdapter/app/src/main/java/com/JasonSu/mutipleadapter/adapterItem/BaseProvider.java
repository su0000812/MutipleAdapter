package com.JasonSu.mutipleadapter.adapterItem;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

/**
 * Created by Jason Su on 2015/11/13.
 */
public class BaseProvider<T extends BaseProvider> extends Observable {

    private Context mContext;
    private Item.Builder builder;
    private final Map<Integer, Action> mActionMapping = new HashMap<>();
    private int layoutResId;
    private Object data;
    private List<Object> dataList;


    protected void setBuilder(Item.Builder builder){
        this.builder = builder;
    }

    public Context getContext() {
        return mContext;
    }

    protected void setContext(Context mContext) {
        this.mContext = mContext;
    }

    public int getLayoutResId() {
        return layoutResId;
    }

    public T setLayoutResId(int layoutResId) {
        this.layoutResId = layoutResId;
        return (T) this;
    }

    public Object getData() {
        return data;
    }

    public T setData(Object data) {
        this.data = data;
        return (T) this;
    }

    public List<Object> getDataList() {
        return dataList;
    }

    public T setDataList(List<Object> dataList) {
        this.dataList = dataList;
        return (T) this;
    }

    protected void notifyDataSetChanged() {
        notifyDataSetChanged(null);
    }

    protected void notifyDataSetChanged(@Nullable final Object object) {
        setChanged();
        notifyObservers(object);
    }

    public Item.Builder endConfig(){
        return builder;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public T addAction(@IdRes final int actionViewId, @NonNull final Action action) {
        mActionMapping.put(actionViewId, action);
        return (T) this;
    }

    @Nullable
    public Action getAction(@IdRes final int actionViewId) {
        return mActionMapping.get(actionViewId);
    }

    public void render(@NonNull final View view, @NonNull final Item item) {
        // Actions
        for (final Map.Entry<Integer, Action> entry : mActionMapping.entrySet()) {
            final View actionViewRaw = findViewById(view, entry.getKey(), View.class);
            if (actionViewRaw != null) {
                final Action action = entry.getValue();
                action.setProvider(this);
                action.onRender(actionViewRaw, item, -1);
            }
        }
    }

    public void render(@NonNull final View view, @NonNull final Item item, int position) {
        // Actions
        for (final Map.Entry<Integer, Action> entry : mActionMapping.entrySet()) {
            final View actionViewRaw = findViewById(view, entry.getKey(), View.class);
            if (actionViewRaw != null) {
                final Action action = entry.getValue();
                action.setProvider(this);
                action.onRender(actionViewRaw, item, position);
            }
        }
    }

    @Nullable
    protected <V extends View> V findViewById(@NonNull final View view, @IdRes final int id, @NonNull final Class<V> type) {
        final View viewById = view.findViewById(id);
        if (viewById != null) {
            return type.cast(viewById);
        } else {
            return null;
        }
    }
}
