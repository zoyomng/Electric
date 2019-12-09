package com.dtelec.electric.model.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.dtelec.electric.view.ClosetLayoutFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (c) dtelec, Inc All Rights Reserved.
 */
public class TabPagerAdapter extends FragmentStateAdapter {

    private static final int CLOSET_LAYOUT = 0;
    private static final int OPERATE_RECORD = 1;
    private Map<Integer, Fragment> tabFragments = new HashMap<Integer, Fragment>() {{
        put(CLOSET_LAYOUT, new ClosetLayoutFragment());
//        put(OPERATE_RECORD, new OperateRecordFragment());
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
