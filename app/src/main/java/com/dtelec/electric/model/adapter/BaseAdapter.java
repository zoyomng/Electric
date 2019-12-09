package com.dtelec.electric.model.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.dtelec.electric.databinding.ItemClosetBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) dtelec, Inc All Rights Reserved.
 */
public class BaseAdapter<T> extends RecyclerView.Adapter {
    private List<T> list;
    private int layoutRes;
    private int variableId;

    public BaseAdapter(@LayoutRes int layoutRes, List<T> list, @NonNull int variableId) {
        this.layoutRes = layoutRes;
        this.list = list;
        this.variableId = variableId;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemClosetBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), layoutRes, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((BaseAdapter.ViewHolder) holder).getBinding().setVariable(variableId, list.get(position));
    }

    @Override
    public int getItemCount() {
        if (list == null) {
            list = new ArrayList<>();
        }
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemClosetBinding binding;

        public ViewHolder(@NonNull ItemClosetBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public ItemClosetBinding getBinding() {
            return binding;
        }

        public void setBinding(ItemClosetBinding binding) {
            this.binding = binding;
        }

    }
}
