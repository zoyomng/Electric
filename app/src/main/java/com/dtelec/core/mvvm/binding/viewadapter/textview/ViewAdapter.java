package com.dtelec.core.mvvm.binding.viewadapter.textview;

import android.graphics.drawable.Drawable;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.dtelec.MyApplication;
import com.dtelec.electric.R;

public class ViewAdapter {
    @BindingAdapter(value = {"state1", "tip1", "state2", "tip2"}, requireAll = true)
    public static void setText(TextView textView, boolean state1, String tip1, boolean state2, String tip2) {
        if (state1) {
            textView.setText(tip1);
            return;
        }
        if (state2) {
            textView.setText(tip2);
            return;
        }
        textView.setText("");
    }

}
