package com.JasonSu.mutipleadapter.provider;

import android.support.annotation.NonNull;
import android.view.View;

import com.JasonSu.mutipleadapter.adapterItem.BaseProvider;
import com.JasonSu.mutipleadapter.adapterItem.Item;

/**
 * Created by Jason Su on 2015/11/13.
 */
public class TopProvider extends BaseProvider<TopProvider> {

    @Override
    public void render(@NonNull View view, @NonNull Item item, int position) {
        super.render(view, item, position);
    }
}
