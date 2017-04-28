package com.gmrxus.zhidouke.homepage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmrxus.zhidouke.R;
import com.gmrxus.zhidouke.adapter.MainViewPagerAdapter;
import com.gmrxus.zhidouke.douban.DoubanFragment;
import com.gmrxus.zhidouke.guoke.GuokeFragment;
import com.gmrxus.zhidouke.zhihu.ZhihuFragment;
import com.gmrxus.zhidouke.zhihu.ZhihuPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac on 2017/4/22.
 */

public class MainFragment extends Fragment {
    private static final String TAG = "MainFragment";

    private ViewPager vp;
    private TabLayout tabLayout;
    private ZhihuFragment mZhihuFragment;
    private DoubanFragment mDoubanFragment;
    private GuokeFragment mGuokeFragment;
    private ZhihuPresenter mZhihuPresenter;
    private List<Fragment> mFragments = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mZhihuFragment = new ZhihuFragment();
        mDoubanFragment = new DoubanFragment();
        mGuokeFragment = new GuokeFragment();
        mFragments.add(mZhihuFragment);
        mFragments.add(mDoubanFragment);
        mFragments.add(mGuokeFragment);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        initView(rootView);
        return rootView;

    }

    private void initView(View rootView) {
        vp = (ViewPager) rootView.findViewById(R.id.vp);
        tabLayout = (TabLayout) rootView.findViewById(R.id.tabLayout);
        vp.setOffscreenPageLimit(3);
        vp.setAdapter(new MainViewPagerAdapter(getChildFragmentManager(), mFragments));
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tabLayout.setupWithViewPager(vp);
    }
}
