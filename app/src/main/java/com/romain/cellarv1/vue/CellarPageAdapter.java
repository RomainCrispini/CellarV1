package com.romain.cellarv1.vue;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.ArrayList;


public class CellarPageAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragmentArrayList;
    private ArrayList<String> fragmentTitleArrayList;

    public CellarPageAdapter(FragmentManager fm, int behavior) {
        super(fm, behavior);
        fragmentArrayList = new ArrayList<>();
        fragmentTitleArrayList = new ArrayList<>();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }

    public void addFragment(Fragment fragment, String title) {
        fragmentArrayList.add(fragment);
        fragmentTitleArrayList.add(title);
    }

    // This method is precaution for androidX, while tabs text not displaying android 9+
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitleArrayList.get(position);
    }


}

