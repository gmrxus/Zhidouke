package com.gmrxus.zhidouke.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by mac on 2017/4/23.
 */

public class MainViewPagerAdapter extends FragmentPagerAdapter {

    private static final String TAG = "MainViewPagerAdapter";
    private String[] titles = {"知乎日报", "豆瓣一颗", "果壳精选"};
    private List<Fragment> mFragments;

    public MainViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.mFragments = fragments;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);

    }


    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

}
