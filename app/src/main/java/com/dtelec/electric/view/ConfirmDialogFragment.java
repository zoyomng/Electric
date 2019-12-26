package com.dtelec.electric.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.dtelec.core.common.widget.dialog.BaseDialogFragment;
import com.dtelec.core.mvvm.utils.ScreenSizeUtil;
import com.dtelec.electric.R;
import com.dtelec.electric.model.bean.ItemBean;

/**
 * Copyright (c) dtelec, Inc All Rights Reserved.
 */
public class ConfirmDialogFragment extends BaseDialogFragment {

    public static ConfirmDialogFragment newInstance(ItemBean itemBean) {
        ConfirmDialogFragment dialogFragment = new ConfirmDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putCharSequence("title", "您确认要" + (itemBean.bitValue == 0 ? "合闸" : "分闸"));
        bundle.putBoolean("isOpen", itemBean.bitValue != 0);
        dialogFragment.setArguments(bundle);
        return dialogFragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.dialog_confirm;
    }

    @Override
    protected void initialize() {
        super.initialize();
        Bundle arguments = getArguments();
        setText(R.id.tv_title, arguments.getString("title"));
        TextView tv_cancle = (TextView) findViewById(R.id.tv_cancle);
        TextView tv_confirm = (TextView) findViewById(R.id.tv_confirm);
        ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
        ImageView imageView3 = (ImageView) findViewById(R.id.imageView3);

        boolean isOpen = arguments.getBoolean("isOpen", false);
        imageView2.setImageResource(isOpen ? R.drawable.ic_switch_on : R.drawable.ic_switch_off);
        imageView3.setImageResource(isOpen ? R.drawable.ic_switch_off : R.drawable.ic_switch_on);
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = getParentFragment();
                if (fragment instanceof DialogHandler) {
                    DialogHandler dialogHandler = (DialogHandler) fragment;
                    dialogHandler.handler();
                }
                dismiss();
            }
        });

    }

    public interface DialogHandler {
        void handler();
    }


    @Override
    public void onResume() {
        super.onResume();

        Window window = getDialog().getWindow();
//        window.setBackgroundDrawable(new ColorDrawable(0x80000000));
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = ScreenSizeUtil.dp2px(getContext(), 300);
        params.height = ScreenSizeUtil.dp2px(getContext(), 200);
        window.setAttributes(params);

    }
}
