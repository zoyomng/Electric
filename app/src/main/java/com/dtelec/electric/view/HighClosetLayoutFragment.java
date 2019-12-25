package com.dtelec.electric.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dtelec.core.mvvm.base.BaseFragment;
import com.dtelec.electric.BR;
import com.dtelec.electric.R;
import com.dtelec.electric.databinding.FragmentHighClosetLayoutBinding;
import com.dtelec.electric.viewModel.MainViewModel;

/**
 * Copyright (c) dtelec, Inc All Rights Reserved.
 */
public class HighClosetLayoutFragment extends BaseFragment<MainViewModel> implements View.OnClickListener, ConfirmDialogFragment.DialogHandler {


    @Override
    protected int getViewModelId() {
        return BR.viewModel;
    }

    @Override
    protected int getLayoutId(Bundle savedInstanceState) {
        return R.layout.fragment_high_closet_layout;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initViewObservable() {

        FragmentHighClosetLayoutBinding dataBinding = (FragmentHighClosetLayoutBinding) this.dataBinding;
        dataBinding.setClickListener(this);

    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(getContext(), HighElecDetailActivity.class));
    }

    @Override
    public void handler() {
    }

    @Override
    public void onStart() {
        super.onStart();
        viewModel.initHighClosetLayoutFragment();
    }
}
