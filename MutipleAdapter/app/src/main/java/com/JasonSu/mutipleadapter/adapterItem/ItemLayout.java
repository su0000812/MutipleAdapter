package com.JasonSu.mutipleadapter.adapterItem;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Jason Su on 2015/10/30.
 */
public class ItemLayout extends FrameLayout implements Observer {

    private Item mItem;
    private boolean mObserves = false;
    private int mPosition;

    public ItemLayout(Context context) {
        super(context);
    }

    public ItemLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public ItemLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void build(final Item item, int position) {
        mItem = item;
        mPosition = position;
        if (!mObserves) {
            mItem.getProvider().addObserver(this);
            mObserves = true;
        }

        mItem.getProvider().render(this, item);
    }

    public Item getCard() {
        return mItem;
    }

    @Override
    public void update(final Observable observable, final Object data) {
        if (data == null) {
            build(mItem, mPosition);
            ((BaseProvider) observable).notifyDataSetChanged(getCard());
        }
    }
}

