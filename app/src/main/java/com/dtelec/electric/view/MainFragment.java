package com.dtelec.electric.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;

import com.dtelec.core.mvvm.base.BaseFragment;
import com.dtelec.electric.BR;
import com.dtelec.electric.R;
import com.dtelec.electric.databinding.FragmentMainBinding;
import com.dtelec.electric.model.adapter.TabPagerAdapter;
import com.dtelec.electric.viewModel.MainViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class MainFragment extends BaseFragment<MainViewModel> {

    private static final String[] tabNames = {"低压柜", "高压柜"};

    @Override
    protected int getViewModelId() {
        return BR.viewModel;
    }

    @Override
    protected int getLayoutId(Bundle savedInstanceState) {
        return R.layout.fragment_main;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initViewObservable() {
        FragmentMainBinding dataBinding = (FragmentMainBinding) this.dataBinding;
        TabLayout tabLayout = dataBinding.tabLayout;
        ViewPager2 viewPager = dataBinding.viewPager;

        TabPagerAdapter tabPagerAdapter = new TabPagerAdapter(this);
        viewPager.setAdapter(tabPagerAdapter);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(getTitle(position));
            }
        });
        tabLayoutMediator.attach();
    }

    private CharSequence getTitle(int position) {
        return tabNames[position];
    }
}
