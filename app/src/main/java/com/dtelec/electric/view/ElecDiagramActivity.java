package com.dtelec.electric.view;

import android.os.Bundle;

import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.dtelec.core.mvvm.base.BaseActivity;
import com.dtelec.electric.BR;
import com.dtelec.electric.R;
import com.dtelec.electric.databinding.ActivityElecDiagramBinding;
import com.dtelec.electric.databinding.ActivityMainBinding;
import com.dtelec.electric.viewModel.MainViewModel;

import static androidx.navigation.Navigation.findNavController;


public class ElecDiagramActivity extends BaseActivity<MainViewModel> {

    @Override
    protected int getLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_elec_diagram;
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
        ActivityElecDiagramBinding binding = (ActivityElecDiagramBinding) this.dataBinding;
        binding.setClicklistener(this);
    }

    @Override
    public void initViewObservable() {

    }

    @Override
    protected void onStatusRefresh() {
        super.onStatusRefresh();
        viewModel.initHighClosetLayoutFragment();
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewModel.initHighClosetLayoutFragment();
    }
}
