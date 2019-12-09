package com.dtelec.electric.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.navigation.Navigation;

import com.dtelec.core.mvvm.base.BaseActivity;
import com.dtelec.electric.BR;
import com.dtelec.electric.R;
import com.dtelec.electric.viewModel.MainViewModel;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

import static androidx.navigation.Navigation.findNavController;


public class SplasActivity extends BaseActivity<MainViewModel> {


    @Override
    protected int getLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_splash;
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
    public void onStart() {
        super.onStart();
        Observable.timer(2, TimeUnit.SECONDS)
                .compose(this.<Long>bindUntilEvent(ActivityEvent.DESTROY))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        startActivity(new Intent(SplasActivity.this, MainActivity.class));
                        finish();
                    }
                });
    }
}
