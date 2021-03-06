package com.dtelec.electric.app;

import com.dtelec.electric.BuildConfig;
import com.dtelec.electric.model.API;

import com.dtelec.core.common.net.RetrofitManager;

public class MyApplication extends com.dtelec.MyApplication {

    MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        InitializeService.start(this);

        RetrofitManager.Configs.getInstance()
                .baseUrl(API.BASE_URL)
                .connectTimeout(20)
                .readTimeout(20)
                .writeTimeout(20)
                .showLog(BuildConfig.DEBUG)
                .token("")
                .apply();
    }
}
