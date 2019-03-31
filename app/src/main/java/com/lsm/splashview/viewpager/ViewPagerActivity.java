package com.lsm.splashview.viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.lsm.splashview.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends AppCompatActivity {
    private int[] bgRes = {R.mipmap.viewpager1, R.mipmap.viewpager2, R.mipmap.viewpager3, R.mipmap.viewpager4};
    private ViewPagerAdapter adapter;
    private List<Fragment> fragments;
    private SplashLayout splashLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        splashLayout = findViewById(R.id.splash_layout);
        fragments = new ArrayList<>();

        for (int i = 0; i < bgRes.length; i++) {
            ContentFragment fragment;
            if (i == (bgRes.length - 1)) {
                fragment = ContentFragment.getInstance(bgRes[i], true);
            } else {
                fragment = ContentFragment.getInstance(bgRes[i], false);
            }
            fragments.add(fragment);
        }
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments);
        splashLayout.showSplash(adapter, fragments.size());
    }
}
