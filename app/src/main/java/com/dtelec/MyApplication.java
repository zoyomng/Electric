package com.dtelec;

import android.app.Application;
import android.content.Context;
import android.view.Gravity;

import com.dtelec.core.common.net.RetrofitManager;
import com.dtelec.core.common.widget.toast.Toasty;
import com.dtelec.electric.BuildConfig;
import com.dtelec.electric.model.API;


public class MyApplication extends Application {

    private static Context applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = getApplicationContext();
        initToasty();

        initRetrofit();
    }

    private void initRetrofit() {
        RetrofitManager.Configs.getInstance()
                .baseUrl(API.BASE_URL)
                .connectTimeout(20)
                .readTimeout(20)
                .writeTimeout(20)
                .showLog(BuildConfig.DEBUG)
                .token("")
                .apply();
    }


    public static Context getAppContext() {
        return applicationContext;
    }

    /**
     * 初始化Toast配置
     */
    private void initToasty() {
        Toasty.Config.getInstance()
                .isTintIcon(true)
                .setTextSize(16)
                .setGravity(Gravity.CENTER, 0, 0)
                .apply();
    }
}
