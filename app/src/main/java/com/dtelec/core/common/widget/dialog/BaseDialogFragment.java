package com.dtelec.core.common.widget.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

/**
 * 基类,需要被继承
 */
public abstract class BaseDialogFragment extends DialogFragment {

    private View mRootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutId(), container, false);
        }
        initialize();
        return mRootView;
    }


    /**
     * @return 布局资源id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化控件/数据
     */
    protected void initialize() {
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Material_Light_Dialog);
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

    public BaseDialogFragment setText(@IdRes int viewId, CharSequence content) {
        View view = findViewById(viewId);
        if (view instanceof TextView) {
            ((TextView) view).setText(content);
        }
        return this;
    }

    public BaseDialogFragment setDrawableRes(@IdRes int viewId, @DrawableRes int drawableRes) {
        View view = findViewById(viewId);
        if (view instanceof ImageView) {
            ((ImageView) view).setImageResource(drawableRes);
        }
        return this;
    }


    public BaseDialogFragment setOnViewClickListener(int viewId, final OnViewClickListener onViewClickListener) {
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
