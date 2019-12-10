package com.dtelec.electric.view;

import android.os.Bundle;
import android.view.View;

import com.dtelec.core.mvvm.base.BaseActivity;
import com.dtelec.electric.BR;
import com.dtelec.electric.R;
import com.dtelec.electric.databinding.ActivityDetailHighElecBinding;
import com.dtelec.electric.viewModel.MainViewModel;

/**
 * Copyright (c) dtelec, Inc All Rights Reserved.
 */
public class HighElecDetailActivity extends BaseActivity<MainViewModel> {


    @Override
    protected int getLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_detail_high_elec;
    }

    @Override
    protected int getViewModelId() {
        return BR.viewModel;
    }


    @Override
    public void initData() {
    }

    @Override
    public void initViewObservable() {
        ActivityDetailHighElecBinding dataBinding = (ActivityDetailHighElecBinding) this.dataBinding;
        dataBinding.setClickListener(this);
    }
}
