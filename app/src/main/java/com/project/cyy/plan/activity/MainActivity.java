package com.project.cyy.plan.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.project.cyy.plan.R;
import com.project.cyy.plan.adapter.MainViewPagerAdapter;
import com.project.cyy.plan.fragment.Tab1Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cyy
 * on 18-5-29下午5:43
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ViewPager vp_content;
    private List<Fragment> mFragmentList = new ArrayList<>();
    private BottomNavigationView navigation;
    private MenuItem menuItem;
    private ActionBar actionBar;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_life:
                    setTitle(R.string.title_life);
                    vp_content.setCurrentItem(0);
                    actionBar.setLogo(R.drawable.ic_life_white_24dp);
                    return true;
                case R.id.navigation_learn:
                    setTitle(R.string.title_learn);
                    vp_content.setCurrentItem(1);
                    actionBar.setLogo(R.drawable.ic_learn_white_24dp);
                    return true;
                case R.id.navigation_entertainment:
                    setTitle(R.string.title_entertainment);
                    vp_content.setCurrentItem(2);
                    actionBar.setLogo(R.drawable.ic_entertainment_white_24dp);
                    return true;
                case R.id.navigation_work:
                    setTitle(R.string.title_work);
                    vp_content.setCurrentItem(3);
                    actionBar.setLogo(R.drawable.ic_work_white_24dp);
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.title_life);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setLogo(R.drawable.ic_life_white_24dp);
            actionBar.setDisplayUseLogoEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
        navigation = findViewById(R.id.navigation);
        vp_content = findViewById(R.id.vp_content);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mFragmentList.add(new Tab1Fragment());
        mFragmentList.add(new Tab1Fragment());
        mFragmentList.add(new Tab1Fragment());
        mFragmentList.add(new Tab1Fragment());
        mainViewPagerAdapter.setFragmentList(mFragmentList);
        vp_content.setAdapter(mainViewPagerAdapter);
        vp_content.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    navigation.getMenu().getItem(0).setChecked(false);
                }
                menuItem = navigation.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


}
