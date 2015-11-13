package com.JasonSu.mutipleadapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Jason Su on 2015/11/13.
 */
public class TestFragment extends Fragment {
    public static TestFragment newInstance(int position) {
        TestFragment testFragment = new TestFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", position);
        testFragment.setArguments(bundle);
        return testFragment;
    }

    private View main;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (main == null) {
            main = inflater.inflate(R.layout.test_layout, null);
            TextView tv = (TextView) main.findViewById(R.id.test_text);
            tv.setText(getClass().getSimpleName() + getArguments().getInt("id"));
        }
        ViewGroup p = (ViewGroup) main.getParent();
        if (p != null) {
            p.removeAllViews();
        }
        return main;
    }

}
