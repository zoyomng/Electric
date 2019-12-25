package com.dtelec.core.common.widget.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.dtelec.electric.view.ConfirmDialogFragment;

/**
 * 通用类,可直接创建使用
 */
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
        initialize();

        return mRootView;
    }

    public void initialize() {
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


    public CommonDialogFragment setText(@IdRes int viewId, CharSequence content) {
        View view = findViewById(viewId);
        if (view instanceof TextView) {
            ((TextView) view).setText(content);
        }
        return this;
    }

    public CommonDialogFragment setDrawableRes(@IdRes int viewId, @DrawableRes int drawableRes) {
        View view = findViewById(viewId);
        if (view instanceof ImageView) {
            ((ImageView) view).setImageResource(drawableRes);
        }
        return this;
    }


    public CommonDialogFragment setOnViewClickListener(int viewId, final OnViewClickListener onViewClickListener) {
        View view = findViewById(viewId);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onViewClickListener != null) {
                    onViewClickListener.onClick(v);
                }
            }
        });
        return this;
    }


    public interface OnViewClickListener {
        void onClick(View v);
    }
}
