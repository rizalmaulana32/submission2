package com.example.submission2.Adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.submission2.Fragment.FollowerFragment;
import com.example.submission2.Fragment.FollowingFragment;
import com.example.submission2.R;

public class PageAdapter extends FragmentPagerAdapter {

    private final Context context;

    public PageAdapter(@NonNull Context context, FragmentManager fm ) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.context = context;
    }

    @StringRes
    private final int[] TAB_TITLES = new int[]{
            R.string.title_follower,
            R.string.title_following
    };

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return context.getResources().getString(TAB_TITLES[position]);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new FollowerFragment();
                break;
            case 1:
                fragment = new FollowingFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
