package com.JasonSu.mutipleadapter.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.JasonSu.mutipleadapter.TestFragment;

/**
 * Created by Jason Su on 2015/11/3.
 */
public class NormalVPAdapter extends FragmentPagerAdapter {

    private int[] CONTENT = null;
    private Context mContext;
    private int which = -1;

    public NormalVPAdapter(Context context, int[] content, FragmentManager fm) {
        super(fm);
        this.CONTENT = content;
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        return TestFragment.newInstance(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getString(CONTENT[position]);
    }

    @Override
    public int getCount() {
        return CONTENT.length;
    }
}
