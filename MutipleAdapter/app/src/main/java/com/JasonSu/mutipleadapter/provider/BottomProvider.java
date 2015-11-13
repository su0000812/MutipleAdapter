package com.JasonSu.mutipleadapter.provider;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.JasonSu.mutipleadapter.R;
import com.JasonSu.mutipleadapter.adapterItem.BaseProvider;
import com.JasonSu.mutipleadapter.adapterItem.Item;
import com.JasonSu.mutipleadapter.bean.DataBean;
import com.bumptech.glide.Glide;

/**
 * Created by Jason Su on 2015/11/13.
 */
public class BottomProvider extends BaseProvider<BottomProvider> {

    @Override
    public void render(@NonNull View view, @NonNull Item item, int position) {
        super.render(view, item, position);
        DataBean data = (DataBean) item.getProvider().getDataList().get(position);
        ImageView pic = (ImageView) view.findViewById(R.id.test_pic);
        Glide.with(item.getProvider().getContext()).load(Uri.parse(data.getUrl())).into(pic);
    }
}
