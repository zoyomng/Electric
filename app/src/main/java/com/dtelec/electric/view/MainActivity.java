package com.dtelec.electric.view;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.dtelec.electric.R;
import com.dtelec.electric.viewModel.MainViewModel;
import com.zoyo.core.mvvm.base.BaseActivity;


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
}
