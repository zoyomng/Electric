package com.dtelec.electric.view;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.dtelec.core.common.widget.dialog.BaseDialogFragment;
import com.dtelec.core.mvvm.utils.ScreenSizeUtil;
import com.dtelec.electric.R;
import com.dtelec.electric.model.bean.HighClosetResponse;

/**
 * Copyright (c) dtelec, Inc All Rights Reserved.
 */
public class RadioDialogFragment extends BaseDialogFragment {

    private int bitVaule;
    private RadioButton radioButton1;
    private RadioButton radioButton2;

    public static RadioDialogFragment newInstance(int bitVaule) {
        RadioDialogFragment dialogFragment = new RadioDialogFragment();
        Bundle bundle = new Bundle();
//        bundle.putCharSequence("title", title);
//        bundle.putCharSequence("radioButton1", radioButton[0]);
//        bundle.putCharSequence("radioButton2", radioButton[1]);
        bundle.putInt("bitVaule", bitVaule);
        dialogFragment.setArguments(bundle);
        return dialogFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_handcart;
    }

    @Override
    protected void initialize() {
        super.initialize();
        Bundle arguments = getArguments();
//        setText(R.id.tv_title, arguments.getString("title"));
        TextView tv_title = (TextView) findViewById(R.id.textView);
        TextView tv_confirm = (TextView) findViewById(R.id.tv_confirm);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        radioButton1 = (RadioButton) findViewById(R.id.radioButton);
        radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
//        radioButton1.setText(arguments.getString("radioButton1"));
//        radioButton2.setText(arguments.getString("radioButton2"));

        bitVaule = arguments.getInt("bitVaule");
        switch (bitVaule) {
            case 0:
            case 1:
                tv_title.setText("接地刀");
                radioButton1.setText("合闸");
                radioButton1.setTag(0);
                radioButton2.setText("分闸");
                radioButton2.setTag(1);
                break;
            case 2:
            case 3:
                tv_title.setText("手车");
                radioButton1.setText("摇入");
                radioButton1.setTag(2);
                radioButton2.setText("摇出");
                radioButton2.setTag(3);
                break;
            case 5:
            case 6:
                tv_title.setText("断容器");
                radioButton1.setText("合闸");
                radioButton1.setTag(5);
                radioButton2.setText("分闸");
                radioButton2.setTag(6);
                break;

            default:
                break;
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton:
                        bitVaule= (int) radioButton1.getTag();
                        break;
                    case R.id.radioButton2:
                        bitVaule= (int) radioButton2.getTag();
                        break;
                    default:
                        break;
                }
            }
        });

        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentActivity activity = getActivity();
                if (activity instanceof DialogHandler) {
                    DialogHandler dialogHandler = (DialogHandler) activity;
                    dialogHandler.handler(bitVaule);
                }
                dismiss();
            }
        });
    }

    public interface DialogHandler {
        void handler(int bitVaule);
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
