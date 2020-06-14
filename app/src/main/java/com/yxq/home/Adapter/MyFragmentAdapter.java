package com.yxq.home.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 管理主界面碎片  滑动的适配器
 */

public class MyFragmentAdapter extends FragmentPagerAdapter {

    //所有碎片
    List<Fragment> fragmentList;

    //构造函数
    public MyFragmentAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.fragmentList = list;
    }

    //根据Item的位置返回对应位置的Fragment，绑定item和Fragment
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    //返回数量
    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
