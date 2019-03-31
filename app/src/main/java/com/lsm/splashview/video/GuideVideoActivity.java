package com.lsm.splashview.video;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.lsm.splashview.R;
import com.lsm.splashview.viewpager.SplashLayout;
import com.lsm.splashview.viewpager.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class GuideVideoActivity extends AppCompatActivity {
    private List<Fragment> fragments = new ArrayList<>();
    private int[] videoRes = new int[]{R.raw.guide1, R.raw.guide2, R.raw.guide3};
    private SplashLayout splashLayout;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_video);
        splashLayout = findViewById(R.id.splash_layout);
        for (int i = 0; i < videoRes.length; i++) {
            VideoFragment videoFragment;
            if (i == (videoRes.length - 1)) {
                videoFragment = VideoFragment.getInstance(videoRes[i], true);
            } else {
                videoFragment = VideoFragment.getInstance(videoRes[i], false);
            }

            fragments.add(videoFragment);
        }
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments);
        splashLayout.showSplash(adapter, fragments.size());
    }
}
