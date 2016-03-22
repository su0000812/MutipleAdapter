package com.JasonSu.mutipleadapter.adapterItem;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

/**
 * Created by Jason Su on 2015/10/30.
 */
public class BaseProvider<T extends BaseProvider> extends Observable {
	
    private Context mContext;
    private Item.Builder mBuilder;

    private final Map<Integer, Action> mActionMapping = new HashMap<Integer, Action>();

    private int mLayoutId;

    private Object data;
    private List<?> dataList;

    public List<?> getDataList() {
        return dataList;
    }

    public T setDataList(List<?> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
        return (T) this;
    }

    public Object getData() {
        return data;
    }

    public T setData(Object data) {
        this.data = data;
        notifyDataSetChanged();
        return (T) this;
    }
    
    void setContext(Context context) {
        mContext = context;
        onCreated();
    }
    
    void setBuilder(Item.Builder builder) {
        mBuilder = builder;
    }

    protected void onCreated() {
    }

    protected Context getContext() {
        return mContext;
    }

    public Item.Builder endConfig() {
        return mBuilder;
    }

    protected void notifyDataSetChanged() {
        notifyDataSetChanged(null);
    }

    protected void notifyDataSetChanged(@Nullable final Object object) {
        setChanged();
        notifyObservers(object);
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public T setLayout(@LayoutRes final int layoutId) {
        mLayoutId = layoutId;
        return (T) this;
    }

    @LayoutRes
    public int getLayout() {
        return mLayoutId;
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

    @SuppressWarnings("unchecked")
    public void render(@NonNull final View view, @NonNull final Item item) {
        // Actions
        for (final Map.Entry<Integer, Action> entry : mActionMapping.entrySet()) {
            final View actionViewRaw = findViewById(view, entry.getKey(), View.class);
            if (actionViewRaw != null) {
                final Action action = entry.getValue();
                action.setProvider(this);
                action.onRender(actionViewRaw, item);
            }
        }
    }

//    public void render(@NonNull final View view, @NonNull final Item item, int position) {
//        // Actions
//        for (final Map.Entry<Integer, Action> entry : mActionMapping.entrySet()) {
//            final View actionViewRaw = findViewById(view, entry.getKey(), View.class);
//            if (actionViewRaw != null) {
//                final Action action = entry.getValue();
//                action.setProvider(this);
//                action.onRender(actionViewRaw, item, position);
//            }
//        }
//    }

    @Nullable
    protected <V extends View> V findViewById(@NonNull final View view, @IdRes final int id,
                                              @NonNull final Class<V> type) {
        final View viewById = view.findViewById(id);
        if (viewById != null) {
            return type.cast(viewById);
        } else {
            return null;
        }
    }
}

