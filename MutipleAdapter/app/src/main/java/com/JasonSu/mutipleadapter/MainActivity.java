package com.JasonSu.mutipleadapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.JasonSu.mutipleadapter.adapter.BaseAdapter;
import com.JasonSu.mutipleadapter.adapter.NormalVPAdapter;
import com.JasonSu.mutipleadapter.adapter.PicUrl;
import com.JasonSu.mutipleadapter.adapterItem.BaseProvider;
import com.JasonSu.mutipleadapter.adapterItem.Item;
import com.JasonSu.mutipleadapter.bean.DataBean;
import com.JasonSu.mutipleadapter.provider.AnotherBottom;
import com.JasonSu.mutipleadapter.provider.MiddleProvider;
import com.JasonSu.mutipleadapter.provider.TopProvider;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ScrollingActivity.class));
            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.test_recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(manager);


        BaseProvider itemProvider = new Item.Builder(MainActivity.this).setItemClickCallback(false)
                .withProvider(new TopProvider())
                .setLayoutResId(R.layout.test);

        BaseProvider middleProvider = new Item.Builder(MainActivity.this).setItemClickCallback(false)
                .withProvider(new MiddleProvider())
                .setLayoutResId(R.layout.test2);

        BaseProvider anotherBottom = new Item.Builder(MainActivity.this).setItemClickCallback(false)
                .withProvider(new AnotherBottom())
                .setLayoutResId(R.layout.test3);

        BaseAdapter adapter = new BaseAdapter(itemProvider.endConfig().build(), middleProvider.endConfig().build(), anotherBottom.endConfig().build());
        recyclerView.setAdapter(adapter);

        TabLayout tab = (TabLayout) findViewById(R.id.tab_layout);

        ViewPager vp = (ViewPager) findViewById(R.id.test_vp);
        vp.setAdapter(new NormalVPAdapter(MainActivity.this, new int[]{R.string.test1, R.string.test2, R.string.test3, R.string.test4, R.string.test5}, getSupportFragmentManager()));
        tab.setupWithViewPager(vp);
    }

    private List<Object> getList() {
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            DataBean data = new DataBean();
            data.setUrl(PicUrl.Pic[i]);
            list.add(data);
        }
        return list;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camara) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
