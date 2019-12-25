package com.dtelec.core.mvvm.binding.viewadapter.imageview;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dtelec.electric.R;

public class ViewAdapter {
    @BindingAdapter(value = {"url", "placeholderRes"}, requireAll = false)
    public static void setImageUri(ImageView imageView, String url, int placeholderRes) {
        if (!TextUtils.isEmpty(url)) {
            Glide.with(imageView.getContext())
                    .load(url)
                    .apply(new RequestOptions().placeholder(placeholderRes))
                    .into(imageView);
        }
    }

    @BindingAdapter(value = {"operation", "breaker"}, requireAll = true)
    public static void setImageUri(ImageView imageView, boolean operation, boolean breaker) {
        if (operation) {
            imageView.setImageResource(R.drawable.ic_switch_error);
            return;
        }
        imageView.setImageResource(breaker ? R.drawable.ic_switch_off : R.drawable.ic_switch_on);
    }

    @BindingAdapter(value = {"state1", "drawableRes1", "state2", "drawableRes2"}, requireAll = true)
    public static void setText(ImageView imageView, boolean state1, Drawable drawableRes1, boolean state2, Drawable drawableRes2) {
        if (state1) {
            imageView.setImageDrawable(drawableRes1);
            return;
        }
        if (state2) {
            imageView.setImageDrawable(drawableRes2);
            return;
        }
        imageView.setImageResource(0);
    }

}
