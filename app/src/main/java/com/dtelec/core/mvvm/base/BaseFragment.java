package com.dtelec.core.mvvm.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.dtelec.core.mvvm.utils.TypeUtil;
import com.trello.rxlifecycle2.components.support.RxFragment;


public abstract class BaseFragment<VM extends BaseViewModel> extends RxFragment implements IBaseView {

    protected ViewDataBinding dataBinding;
    public VM viewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.inflate(inflater, getLayoutId(savedInstanceState), container, false);
        return dataBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewDataBinding();
        initViewObservable();
        initData();
    }


    protected void initViewDataBinding() {
        int viewModelId = getViewModelId();
        viewModel = initViewModel();
        dataBinding.setVariable(viewModelId, viewModel);
        getLifecycle().addObserver(viewModel);
        dataBinding.setLifecycleOwner(this);
        viewModel.injectLifecycleProvider(this);
    }

    private VM initViewModel() {
        return (VM) ViewModelProviders.of(this).get(TypeUtil.getClassType(this, 0, BaseViewModel.class));
    }

    protected abstract int getViewModelId();

    protected abstract int getLayoutId(Bundle savedInstanceState);

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getLifecycle().removeObserver(viewModel);
        if (dataBinding != null) {
            dataBinding.unbind();
        }
    }
}
