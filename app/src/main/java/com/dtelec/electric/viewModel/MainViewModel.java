package com.dtelec.electric.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.dtelec.electric.model.bean.MainResponse;
import com.dtelec.electric.model.repository.MainRepository;
import com.zoyo.core.common.net.Response;
import com.zoyo.core.mvvm.base.BaseViewModel;
import com.zoyo.core.mvvm.base.OnDataCallback;

public class MainViewModel extends BaseViewModel<MainRepository> {


    public MainViewModel(@NonNull Application application) {
        super(application);

        request();
    }

    public void request() {
        repository.request(statusValue, new OnDataCallback<Response<MainResponse>>() {
            @Override
            public void onData(Response<MainResponse> mainResponseResponse) {
                System.out.println("===============" + mainResponseResponse.toString());
            }
        });
    }

}
