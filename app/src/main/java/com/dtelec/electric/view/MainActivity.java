package com.dtelec.electric.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.dtelec.electric.BR;
import com.dtelec.electric.R;
import com.dtelec.electric.databinding.ActivityMainBinding;
import com.dtelec.electric.viewModel.MainViewModel;

import com.dtelec.core.mvvm.base.BaseActivity;

import static androidx.navigation.Navigation.findNavController;


public class MainActivity extends BaseActivity<MainViewModel> {


    private ActivityMainBinding dataBinding;
    public NavController navController;

    @Override
    protected int getLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    protected int getViewModelId() {
        return BR.viewModel;
    }

    @Override
    protected int getContentLayoutId() {
        return R.id.contentLayout;
    }

    @Override
    public void initData() {
        dataBinding = (ActivityMainBinding) this.dataBinding;
        FragmentManager fragmentManager = getSupportFragmentManager();
        NavHostFragment navHostFragment = (NavHostFragment) fragmentManager.findFragmentById(R.id.fragment_nav_host);
        navController = navHostFragment.getNavController();
    }

    @Override
    public void initViewObservable() {

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
