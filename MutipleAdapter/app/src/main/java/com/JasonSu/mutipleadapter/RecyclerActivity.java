package com.JasonSu.mutipleadapter;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.JasonSu.mutipleadapter.adapter.BaseAdapter;
import com.JasonSu.mutipleadapter.adapterItem.Item;
import com.JasonSu.mutipleadapter.bean.DataBean;
import com.JasonSu.mutipleadapter.provider.BottomProvider;
import com.JasonSu.mutipleadapter.provider.MiddleProvider;
import com.JasonSu.mutipleadapter.provider.TopProvider;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.main_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(RecyclerActivity.this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(getAdapter(getItemList()));
    }

    private List<Item> getItemList() {
        List<Item> mList = new ArrayList<>();
        mList.add(new Item.Builder(RecyclerActivity.this)
                .withProvider(new TopProvider())
                .setLayout(R.layout.activity_recycler_top)
                .setData(new DataBean())
                .endConfig()
                .build());

        mList.add(new Item.Builder(RecyclerActivity.this)
                .withProvider(new MiddleProvider())
                .setLayout(R.layout.activity_recycler_middle)
                .setData(new DataBean())
                .endConfig()
                .build());

        mList.add(new Item.Builder(RecyclerActivity.this)
                .withProvider(new BottomProvider())
                .setLayout(R.layout.activity_recycler_bottom)
                .setData(new DataBean())
                .endConfig()
                .build());

        return mList;
    }

    private BaseAdapter getAdapter(List<Item> list) {
        return new BaseAdapter(list);
    }
}
