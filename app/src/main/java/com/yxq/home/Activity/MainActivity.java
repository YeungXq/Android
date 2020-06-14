package com.yxq.home.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.yxq.home.Adapter.MyFragmentAdapter;

import com.yxq.home.Fragment.HomePageFragment;
import com.yxq.home.Fragment.ListFragment;
import com.yxq.home.Fragment.DynamicFragment;
import com.yxq.home.Fragment.UserFragment;
import com.yxq.home.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private ViewPager mViewPager;
    private RadioGroup mTabRadioGroup;
    private List<Fragment> mFragments;
    private FragmentPagerAdapter mAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        //   find view
        mViewPager = (ViewPager) findViewById(R.id.fragment_vp);
        mTabRadioGroup = (RadioGroup) findViewById(R.id.tabs_rg);
        RadioButton homepage_tab = (RadioButton) findViewById(R.id.homepage_tab);
        RadioButton list_tab = (RadioButton) findViewById(R.id.list_tab);
        RadioButton mine_tab = (RadioButton) findViewById(R.id.mine_tab);
        RadioButton dynamic_tab = (RadioButton) findViewById(R.id.dynamic_tab);
        //  init fragment
        mFragments = new ArrayList<>(4);
        mFragments.add(new HomePageFragment());
        mFragments.add(new ListFragment());
        mFragments.add(new DynamicFragment());
        mFragments.add(new UserFragment());


        //修改RadioButton图片的大小
        Drawable homepage = getResources().getDrawable(R.drawable.tab_homepage_selector);
        Drawable list = getResources().getDrawable(R.drawable.tab_list_selector);
        Drawable dynamic = getResources().getDrawable(R.drawable.tab_dynamic_selector);
        Drawable mine = getResources().getDrawable(R.drawable.tab_mine_selector);
        //第一0是距左右边距离，第二0是距上下边距离，第三长度,第四宽度
        homepage.setBounds(0, 0, 80, 80);
        list.setBounds(0, 0, 80, 80);
        dynamic.setBounds(0, 0, 80, 80);
        mine.setBounds(0, 0, 80, 80);

        //图片只放上面
        homepage_tab.setCompoundDrawables(null, homepage, null, null);
        list_tab.setCompoundDrawables(null, list, null, null);
        dynamic_tab.setCompoundDrawables(null, dynamic, null, null);
        mine_tab.setCompoundDrawables(null, mine, null, null);
        // init viewpager
        mAdapter = new MyFragmentAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(mAdapter);
        //注册监听器
        mViewPager.addOnPageChangeListener(mPageChangeListener);
        mTabRadioGroup.setOnCheckedChangeListener(mOnCheckedChangeListener);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewPager.removeOnPageChangeListener(mPageChangeListener);
    }

    private ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            RadioButton radioButton = (RadioButton) mTabRadioGroup.getChildAt(position);
            radioButton.setChecked(true);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };
    private RadioGroup.OnCheckedChangeListener mOnCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            for (int i = 0; i < group.getChildCount(); i++) {
                if (group.getChildAt(i).getId() == checkedId) {
                    mViewPager.setCurrentItem(i);
                    return;
                }
            }
        }
    };



}
