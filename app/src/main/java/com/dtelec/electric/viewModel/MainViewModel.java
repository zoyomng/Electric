package com.dtelec.electric.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.dtelec.core.mvvm.base.BaseViewModel;
import com.dtelec.electric.model.repository.MainRepository;


public class MainViewModel extends BaseViewModel<MainRepository> {

    public MutableLiveData<Boolean> isShowHighlectricCloset = new MutableLiveData<>();
    public String highElecDetailTitle = "高压柜详情";

    public MainViewModel(@NonNull Application application) {
        super(application);
        isShowHighlectricCloset.setValue(false);
        request();
    }

    public void request() {
//        repository.request(statusValue, new OnDataCallback<Response<MainResponse>>() {
//            @Override
//            public void onData(Response<MainResponse> mainResponseResponse) {
//                System.out.println("===============" + mainResponseResponse.toString());
//            }
//        });
    }

    public void show() {
        isShowHighlectricCloset.setValue(!isShowHighlectricCloset.getValue());
    }
}
