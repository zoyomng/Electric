package com.dtelec.electric.view;

import android.os.Bundle;

import com.dtelec.electric.BR;
import com.dtelec.electric.R;
import com.dtelec.electric.viewModel.MainViewModel;

import com.dtelec.core.mvvm.base.BaseActivity;

import static androidx.navigation.Navigation.findNavController;


public class MainActivity extends BaseActivity<MainViewModel> {


    @Override
    protected int getLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    protected int initViewModelId() {
        return BR.viewModel;
    }

    @Override
    protected int getContentLayoutId() {
        return R.id.contentLayout;
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onStatusRefresh() {
        super.onStatusRefresh();
        viewModel.request();
    }

    @Override
    public boolean onSupportNavigateUp() {
        return findNavController(this, R.id.fragment_nav_host).navigateUp();
    }
}
