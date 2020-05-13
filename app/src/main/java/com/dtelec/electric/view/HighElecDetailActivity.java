package com.dtelec.electric.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.dtelec.core.common.constants.Constants;
import com.dtelec.core.common.widget.toast.Toasty;
import com.dtelec.core.mvvm.base.BaseActivity;
import com.dtelec.electric.BR;
import com.dtelec.electric.R;
import com.dtelec.electric.databinding.ActivityDetailHighElecBinding;
import com.dtelec.electric.viewModel.MainViewModel;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.sql.SQLOutput;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Copyright (c) dtelec, Inc All Rights Reserved.
 */
public class HighElecDetailActivity extends BaseActivity<MainViewModel> implements RadioDialogFragment.DialogHandler {


    private SwipeRefreshLayout swipeRefreshLayout;
    private Disposable subscribe;

    @Override
    protected int getLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_detail_high_elec;
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
        try {
            switch (v.getId()) {
                case R.id.tv_breaker2:
                    //断容器合闸分闸
//                if (viewModel.highClosetBean.getValue().testStation){
//                    RadioDialogFragment.newInstance(viewModel.highClosetBean, viewModel.highClosetBean.getValue().breakerBitValue).show(getSupportFragmentManager(), "dialog");
//                    return;
//                }
                    if (Objects.requireNonNull(viewModel.highClosetBean.getValue()).groundKnifeOpen) {
                        //接地刀分闸
                        RadioDialogFragment.newInstance(viewModel.highClosetBean, viewModel.highClosetBean.getValue().breakerBitValue).show(getSupportFragmentManager(), "dialog");
                    } else {
                        Toasty.error(HighElecDetailActivity.this, "断路器合闸之前请确保接地刀处于分闸状态", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.tv_handcart:
                    //手车
                    if (Objects.requireNonNull(viewModel.highClosetBean.getValue()).breaker) {
                        Toasty.error(HighElecDetailActivity.this, "手车摇入摇出之前请确保断路器处于分闸状态", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (viewModel.highClosetBean.getValue().groundKnifeOpen) {
                        //接地刀合闸
                        RadioDialogFragment.newInstance(viewModel.highClosetBean, viewModel.highClosetBean.getValue().handcartBitValue).show(getSupportFragmentManager(), "dialog");
                    } else {
                        Toasty.error(HighElecDetailActivity.this, "手车摇入摇出之前请确保接地刀处于分闸状态", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.tv_knife:
                    //接地刀
                    if (Objects.requireNonNull(viewModel.highClosetBean.getValue()).testStation) {
                        RadioDialogFragment.newInstance(viewModel.highClosetBean, viewModel.highClosetBean.getValue().knifeBitValue).show(getSupportFragmentManager(), "dialog");
                    } else {
                        Toasty.error(HighElecDetailActivity.this, "接地刀合闸之前请确保断路器处于试验位置", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case R.id.tv_diagram:
                    startActivity(new Intent(HighElecDetailActivity.this, ElecDiagramActivity.class));
                    break;
                default:
                    break;
            }
        } catch (NullPointerException e) {
            Toasty.info(HighElecDetailActivity.this, "请在数据刷新后再尝试操作").show();
        }
    }

    @Override
    protected void onStatusRefresh() {
        super.onStatusRefresh();
        viewModel.initHighClosetLayoutFragment();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //轮询
        if (subscribe != null && !subscribe.isDisposed()) {
            subscribe.dispose();
        }

        subscribe = Observable.interval(0, 2, TimeUnit.SECONDS)
                .compose(this.<Long>bindUntilEvent(ActivityEvent.STOP))
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        viewModel.initHighClosetLayoutFragment();
                        System.out.println("========HighElecDetailActivity===========");
                    }
                });
    }

    @Override
    public void handler(int bitVaule) {
        viewModel.highElecWrite(bitVaule);
    }
}
