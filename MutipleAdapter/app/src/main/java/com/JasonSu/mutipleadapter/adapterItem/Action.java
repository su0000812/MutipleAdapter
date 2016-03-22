package com.JasonSu.mutipleadapter.adapterItem;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

public abstract class Action {
    private final Context mContext;
    private BaseProvider mProvider;

    public Action(@NonNull final Context context) {
        mContext = context;
    }

    void setProvider(BaseProvider provider) {
        mProvider = provider;
    }

    protected Context getContext() {
        return mContext;
    }

    protected void notifyActionChanged() {
        if (mProvider != null) {
            mProvider.notifyDataSetChanged();
        }
    }

    protected abstract void onRender(@NonNull final View view, @NonNull final Item item);
}
