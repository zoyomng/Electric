package com.dtelec.electric.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.dtelec.core.common.constants.Constants;
import com.dtelec.core.mvvm.base.BaseActivity;
import com.dtelec.electric.BR;
import com.dtelec.electric.R;
import com.dtelec.electric.databinding.ActivityDetailHighElecBinding;
import com.dtelec.electric.viewModel.MainViewModel;

/**
 * Copyright (c) dtelec, Inc All Rights Reserved.
 */
public class HighElecDetailActivity extends BaseActivity<MainViewModel> implements RadioDialogFragment.DialogHandler {


    private SwipeRefreshLayout swipeRefreshLayout;

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
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_breaker2:
                //断容器合闸分闸
                RadioDialogFragment.newInstance(viewModel.highClosetBean.getValue().breakerBitValue).show(getSupportFragmentManager(), "dialog");
                break;
            case R.id.tv_handcart:
                //断容器合闸分闸
                RadioDialogFragment.newInstance(viewModel.highClosetBean.getValue().handcartBitValue).show(getSupportFragmentManager(), "dialog");
                break;
            case R.id.tv_knife:
                //断容器合闸分闸
                RadioDialogFragment.newInstance(viewModel.highClosetBean.getValue().knifeBitValue).show(getSupportFragmentManager(), "dialog");
                break;

            case R.id.tv_diagram:
                startActivity(new Intent(HighElecDetailActivity.this, ElecDiagramActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    protected void onStatusRefresh() {
        super.onStatusRefresh();
        viewModel.initHighClosetLayoutFragment();
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.initHighClosetLayoutFragment();
    }

    @Override
    public void handler(int bitVaule) {
        viewModel.highElecWrite(bitVaule);
    }
}
