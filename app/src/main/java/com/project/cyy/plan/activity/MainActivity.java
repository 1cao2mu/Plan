package com.project.cyy.plan.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.project.cyy.plan.R;
import com.project.cyy.plan.View.BottomNavigationViewHelper;
import com.project.cyy.plan.adapter.MainViewPagerAdapter;
import com.project.cyy.plan.bean.Childs;
import com.project.cyy.plan.bean.Parents;
import com.project.cyy.plan.db.ChildDao;
import com.project.cyy.plan.db.ParentDao;
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
        BottomNavigationViewHelper.disableShiftMode(navigation);
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
        initData();
    }

    private void initData() {
        ParentDao parentDao = new ParentDao(getApplicationContext());
        ChildDao childDao = new ChildDao(getApplicationContext());

        Parents p1 = new Parents("001", "曹建华", "于晓红");
        parentDao.addParent(p1);

        Childs s1 = new Childs("猪小明", "男", "15");
        s1.setParents(p1);
        childDao.addChild(s1);

        Parents p2 = new Parents("002", "余正华", "谋女郎");
        parentDao.addParent(p2);

        Childs s2 = new Childs("闰土", "男", "20");
        s2.setParents(p2);
        childDao.addChild(s2);

        //id自增长默认从1开始
        Childs student1 = childDao.getChild(1);
        if (student1 != null) {
            //结果：Childs{id=1, sname='猪小明', sgender='男', sage='15', parents=Parents{id=1,fid='null' fname='null', mname='null'}}
            Log.i("liuw", student1.toString());
        }

        Childs student2 = childDao.getChildAndParent(2);
        if (student2 != null) {
            //结果：Childs{id=2, sname='闰土', sgender='男', sage='20', parents=Parents{id=2,fid='002' fname='余正华', mname='谋女郎'}}
            Log.i("liuw", student2.toString());
        }

        List<Childs> students = childDao.getChildByName("闰土");
        if (students != null && students.size() > 0) {
            Log.i("liuw", "数据大小为："+students.size());
        }

    }

}
