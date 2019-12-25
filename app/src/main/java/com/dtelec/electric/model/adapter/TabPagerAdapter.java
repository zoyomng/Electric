package com.dtelec.electric.model.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.dtelec.electric.view.HighClosetLayoutFragment;
import com.dtelec.electric.view.LowClosetLayoutFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (c) dtelec, Inc All Rights Reserved.
 */
public class TabPagerAdapter extends FragmentStateAdapter {

    private static final int LOW_CLOSET_LAYOUT = 0;
    private static final int HIGH_CLOSET_LAYOUT = 1;
    private Map<Integer, Fragment> tabFragments = new HashMap<Integer, Fragment>() {{
        put(LOW_CLOSET_LAYOUT, new LowClosetLayoutFragment());
        put(HIGH_CLOSET_LAYOUT, new HighClosetLayoutFragment());
    }};

    public TabPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return tabFragments.get(position);
    }

    @Override
    public int getItemCount() {
        return tabFragments.size();
    }
}
