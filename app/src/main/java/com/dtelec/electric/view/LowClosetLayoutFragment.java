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
import com.dtelec.electric.databinding.FragmentLowClosetLayoutBinding;
import com.dtelec.electric.model.bean.ItemBean;
import com.dtelec.electric.viewModel.MainViewModel;

/**
 * Copyright (c) dtelec, Inc All Rights Reserved.
 */
public class LowClosetLayoutFragment extends BaseFragment<MainViewModel> implements View.OnClickListener, ConfirmDialogFragment.DialogHandler {


    private ItemBean itemBean;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected int getViewModelId() {
        return BR.viewModel;
    }

    @Override
    protected int getLayoutId(Bundle savedInstanceState) {
        return R.layout.fragment_low_closet_layout;
    }

    @Override
    public void initData() {
        viewModel.initLowClosetLayoutFragment();
    }

    @Override
    public void initViewObservable() {

        FragmentLowClosetLayoutBinding dataBinding = (FragmentLowClosetLayoutBinding) this.dataBinding;
        dataBinding.setClickListener(this);

        swipeRefreshLayout = dataBinding.swipeRefreshLayout;
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewModel.request();
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
        viewModel.showDialog.observe(this, new Observer<ItemBean>() {
            @Override
            public void onChanged(ItemBean itemBean) {
                if (itemBean != null) {
                    LowClosetLayoutFragment.this.itemBean = itemBean;
                    ConfirmDialogFragment dialogFragment = ConfirmDialogFragment.newInstance(itemBean);
                    dialogFragment.show(getChildFragmentManager(), "dialog");
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
        viewModel.write(itemBean);
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.request();
    }
}
