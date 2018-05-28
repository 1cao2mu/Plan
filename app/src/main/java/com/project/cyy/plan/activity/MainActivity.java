package com.project.cyy.plan.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.project.cyy.plan.R;
import com.project.cyy.plan.adapter.MainViewPagerAdapter;
import com.project.cyy.plan.fragment.BlankFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager vp_content;
    private List<Fragment> mFragmentList = new ArrayList<>();
    private BottomNavigationView navigation;
    private MenuItem menuItem;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_life:
                    setTitle(R.string.title_life);
                    vp_content.setCurrentItem(0);
                    return true;
                case R.id.navigation_learn:
                    setTitle(R.string.title_learn);
                    vp_content.setCurrentItem(1);
                    return true;
                case R.id.navigation_entertainment:
                    setTitle(R.string.title_entertainment);
                    vp_content.setCurrentItem(2);
                    return true;
                case R.id.navigation_work:
                    setTitle(R.string.title_work);
                    vp_content.setCurrentItem(3);
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
        navigation = findViewById(R.id.navigation);
        vp_content = findViewById(R.id.vp_content);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mFragmentList.add(BlankFragment.newInstance(getString(R.string.title_life)));
        mFragmentList.add(BlankFragment.newInstance(getString(R.string.title_learn)));
        mFragmentList.add(BlankFragment.newInstance(getString(R.string.title_work)));
        mFragmentList.add(BlankFragment.newInstance(getString(R.string.title_entertainment)));
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
