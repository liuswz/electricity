package com.electricity.hasee.electricity.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;



public class TabFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments;

    private List<String> mTitles;



    public TabFragmentPagerAdapter(FragmentManager fm, List<Fragment> framents, List<String> titles) {

        super(fm);

        mFragments = framents;

        mTitles = titles;

    }



    @Override

    public Fragment getItem(int position) {

        return mFragments.get(position);

    }



    @Override

    public int getCount() {

        return mFragments == null ? 0 : mFragments.size();

    }



    @Override

    public CharSequence getPageTitle(int position) {

        return mTitles.get(position);

    }

}
