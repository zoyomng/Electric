package com.dtelec.electric.view;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.dtelec.core.mvvm.base.BaseFragment;
import com.dtelec.core.mvvm.utils.ScreenSizeUtil;
import com.dtelec.electric.BR;
import com.dtelec.electric.R;
import com.dtelec.electric.databinding.FragmentClosetLayoutBinding;
import com.dtelec.electric.model.adapter.BaseAdapter;
import com.dtelec.electric.model.bean.ItemBean;
import com.dtelec.electric.viewModel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) dtelec, Inc All Rights Reserved.
 */
public class ClosetLayoutFragment extends BaseFragment<MainViewModel> {


    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }

    @Override
    protected int getLayoutId(Bundle savedInstanceState) {
        return R.layout.fragment_closet_layout;
    }

    @Override
    protected void initViewObservable() {
        FragmentClosetLayoutBinding dataBinding = (FragmentClosetLayoutBinding) this.dataBinding;
        final SwipeRefreshLayout swipeRefreshLayout = dataBinding.swipeRefreshLayout;
        View includeLowElecCloset = dataBinding.includeLowElecCloset;
        View includeHighElecCloset = dataBinding.includeHighElecCloset;

        RecyclerView recyclerView = includeLowElecCloset.findViewById(R.id.recyclerView);
        List<ItemBean> list = new ArrayList<>();
        ItemBean itemBean = new ItemBean("a", 0);
        list.add(itemBean);
        list.add(itemBean);
        list.add(itemBean);
        list.add(itemBean);
        list.add(itemBean);
        list.add(itemBean);
        list.add(itemBean);
        list.add(itemBean);
        list.add(itemBean);
        list.add(itemBean);
        list.add(itemBean);
        list.add(itemBean);
        list.add(itemBean);

        recyclerView.setAdapter(new BaseAdapter(R.layout.item_closet, list, BR.model));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.stopNestedScroll();
            }
        });
    }
}
