package com.dtelec.core.common.widget.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class CommonDialogFragment extends DialogFragment {

    private View mRootView;
    private int layoutResId;

    public CommonDialogFragment(@LayoutRes int layoutResId) {
        this.layoutResId = layoutResId;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mRootView == null) {
            mRootView = inflater.inflate(layoutResId, container, false);
        }
        return mRootView;
    }


    /**
     * 通用findViewById
     *
     * @param id  控件Id
     * @param <T> 控件类型
     * @return 控件对象
     */
    @NonNull
    public <T extends View> T findViewById(@IdRes int id) {
        return mRootView.findViewById(id);
    }

    public CommonDialogFragment setOnViewClickListener(int viewId, final OnViewClickListener onViewClickListener) {
        View view = findViewById(viewId);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onViewClickListener != null) {
                    onViewClickListener.onClick();
                }
            }
        });
        return this;
    }


    public interface OnViewClickListener {
        void onClick();
    }
}
