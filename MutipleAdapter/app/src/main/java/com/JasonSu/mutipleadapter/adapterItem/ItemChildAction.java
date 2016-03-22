package com.JasonSu.mutipleadapter.adapterItem;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

public class ItemChildAction extends Action {
    @Nullable
    private OnActionClickListener mListener;

    public ItemChildAction(@NonNull Context context) {
        super(context);
    }

    @Nullable
    public OnActionClickListener getListener() {
        return mListener;
    }

    public ItemChildAction setListener(@Nullable final OnActionClickListener listener) {
        this.mListener = listener;
        notifyActionChanged();
        return this;
    }

    @Override
    protected void onRender(@NonNull final View view, @NonNull final Item item) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onActionClicked(view, item);
                }
            }
        });
    }
}
