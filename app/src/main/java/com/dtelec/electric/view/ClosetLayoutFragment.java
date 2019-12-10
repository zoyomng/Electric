package com.dtelec.electric.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.dtelec.core.mvvm.base.BaseFragment;
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
public class ClosetLayoutFragment extends BaseFragment<MainViewModel> implements View.OnClickListener {


    @Override
    protected int getViewModelId() {
        return BR.viewModel;
    }

    @Override
    protected int getLayoutId(Bundle savedInstanceState) {
        return R.layout.fragment_closet_layout;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initViewObservable() {

        FragmentClosetLayoutBinding dataBinding = (FragmentClosetLayoutBinding) this.dataBinding;
        dataBinding.setClickListener(this);


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

    @Override
    public void onClick(View v) {
        startActivity(new Intent(getContext(),HighElecDetailActivity.class));
    }
}
