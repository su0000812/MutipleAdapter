package com.JasonSu.mutipleadapter.provider;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.JasonSu.mutipleadapter.R;
import com.JasonSu.mutipleadapter.adapter.PicUrl;
import com.JasonSu.mutipleadapter.adapterItem.BaseProvider;
import com.JasonSu.mutipleadapter.adapterItem.Item;
import com.bumptech.glide.Glide;

/**
 * Created by Jason Su on 2015/11/13.
 */
public class TopProvider extends BaseProvider<TopProvider> {

    @Override
    public void render(@NonNull View view, @NonNull Item item) {
        super.render(view, item);
        ImageView one = (ImageView) view.findViewById(R.id.test_recyclerview_img_one);
        Glide.with(getContext()).load(PicUrl.Pic[0]).into(one);

        ImageView two = (ImageView) view.findViewById(R.id.test_recyclerview_img_two);
        Glide.with(getContext()).load(PicUrl.Pic[5]).into(two);

        ImageView three = (ImageView) view.findViewById(R.id.test_recyclerview_img_three);
        Glide.with(getContext()).load(PicUrl.Pic[6]).into(three);
    }
}
