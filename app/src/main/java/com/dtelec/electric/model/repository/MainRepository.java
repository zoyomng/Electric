package com.dtelec.electric.model.repository;

import androidx.lifecycle.MutableLiveData;

import com.dtelec.core.common.net.Response;
import com.dtelec.electric.model.API;
import com.dtelec.electric.model.bean.MainResponse;

import com.dtelec.core.common.constants.Constants;
import com.dtelec.core.common.rx.RxSubscriber;
import com.dtelec.core.common.rx.RxUtil;
import com.dtelec.core.mvvm.base.BaseRepository;
import com.dtelec.core.mvvm.base.OnDataCallback;


public class MainRepository extends BaseRepository {
    private API api = retrofit.create(API.class);

    public void request(final MutableLiveData<Integer> statusValue, final OnDataCallback<Response<MainResponse>> onDataCallback) {
        statusValue.setValue(Constants.STAUTS_LOADING);

//        addSubscribe(api.request()
//                .compose(RxUtil.<Response>rxSchedulerHelper())
//                .subscribeWith(new RxSubscriber<Response>() {
//                    @Override
//                    protected void onNoNetwork() {
//                        statusValue.setValue(Constants.STATUS_NETWORK_ERROR);
//                    }
//
//                    @Override
//                    protected void onSuccess(Response response) {
//                        statusValue.setValue(Constants.STATUS_SUCCESS);
//                        onDataCallback.onData(response);
//                    }
//
//                    @Override
//                    protected void onFailure(String message) {
//                        statusValue.setValue(Constants.STATUS_SERVER_ERROR);
//                    }
//                }));
    }

}
