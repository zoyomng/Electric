package com.dtelec.electric.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.dtelec.core.common.constants.Constants;
import com.dtelec.core.mvvm.base.BaseFragment;
import com.dtelec.electric.BR;
import com.dtelec.electric.R;
import com.dtelec.electric.databinding.FragmentHighClosetLayoutBinding;
import com.dtelec.electric.viewModel.MainViewModel;

/**
 * Copyright (c) dtelec, Inc All Rights Reserved.
 */
public class HighClosetLayoutFragment extends BaseFragment<MainViewModel> implements View.OnClickListener, ConfirmDialogFragment.DialogHandler {


    private SwipeRefreshLayout swipeRefreshLayout;

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

        swipeRefreshLayout = dataBinding.swipeRefreshLayout;
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewModel.initHighClosetLayoutFragment();
            }
        });

        viewModel.statusValue.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (Constants.STAUTS_LOADING != integer) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(getContext(), HighElecDetailActivity.class));
    }

    @Override
    public void handler() {
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.initHighClosetLayoutFragment();
    }
}
