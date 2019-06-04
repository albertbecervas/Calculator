package com.abecerra.calculator.core.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class CustomPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> fragments = new ArrayList<>();
    private final List<String> fragmentsName = new ArrayList<>();

    public CustomPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    // Our custom method that populates this Adapter with Fragments
    public void addFragments(Fragment fragment, String name) {
        if (!fragments.contains(fragment)) {
            fragments.add(fragment);
            fragmentsName.add(name);
        }
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentsName.get(position);
    }

    public void clearItems() {
        fragments.clear();
    }

}