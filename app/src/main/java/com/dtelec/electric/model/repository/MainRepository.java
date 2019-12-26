package com.dtelec.electric.model.repository;

import androidx.lifecycle.MutableLiveData;

import com.dtelec.electric.model.API;
import com.dtelec.electric.model.bean.HighClosetResponse;
import com.dtelec.electric.model.bean.LowClosetResponse;

import com.dtelec.core.common.constants.Constants;
import com.dtelec.core.common.rx.RxSubscriber;
import com.dtelec.core.common.rx.RxUtil;
import com.dtelec.core.mvvm.base.BaseRepository;
import com.dtelec.core.mvvm.base.OnDataCallback;


public class MainRepository extends BaseRepository {
    private API api = retrofit.create(API.class);

    public void request(final MutableLiveData<Integer> statusValue, final OnDataCallback<LowClosetResponse> onDataCallback) {
        statusValue.postValue(Constants.STAUTS_LOADING);

        addSubscribe(api.request()
                .compose(RxUtil.<LowClosetResponse>rxSchedulerHelper())
                .subscribeWith(new RxSubscriber<LowClosetResponse>() {
                    @Override
                    protected void onNoNetwork() {
                        statusValue.setValue(Constants.STATUS_NETWORK_ERROR);
                    }

                    @Override
                    protected void onSuccess(LowClosetResponse response) {
                        statusValue.setValue(Constants.STATUS_SUCCESS);
                        onDataCallback.onData(response);
                    }

                    @Override
                    protected void onFailure(String message) {
                        statusValue.setValue(Constants.STATUS_SERVER_ERROR);
                    }
                }));
    }

    public void write(final MutableLiveData<Integer> statusValue, String davearea, int byteValue, int bitValue, boolean state, final OnDataCallback<LowClosetResponse> onDataCallback) {
        statusValue.postValue(Constants.STAUTS_LOADING);

        addSubscribe(api.write(davearea, byteValue, bitValue, state)
                .compose(RxUtil.<LowClosetResponse>rxSchedulerHelper())
                .subscribeWith(new RxSubscriber<LowClosetResponse>() {
                    @Override
                    protected void onNoNetwork() {
                        statusValue.setValue(Constants.STATUS_NETWORK_ERROR);
                    }

                    @Override
                    protected void onSuccess(LowClosetResponse response) {
                        statusValue.setValue(Constants.STATUS_SUCCESS);
                        onDataCallback.onData(response);
                    }

                    @Override
                    protected void onFailure(String message) {
                        statusValue.setValue(Constants.STATUS_SERVER_ERROR);
                    }
                }));
    }

    public void highElecWrite(final MutableLiveData<Integer> statusValue, String davearea, int byteValue, int bitValue, boolean state, final OnDataCallback<HighClosetResponse> onDataCallback) {
        statusValue.postValue(Constants.STAUTS_LOADING);

        addSubscribe(api.highElecWrite(davearea, byteValue, bitValue, state)
                .compose(RxUtil.<HighClosetResponse>rxSchedulerHelper())
                .subscribeWith(new RxSubscriber<HighClosetResponse>() {
                    @Override
                    protected void onNoNetwork() {
                        statusValue.setValue(Constants.STATUS_NETWORK_ERROR);
                    }

                    @Override
                    protected void onSuccess(HighClosetResponse response) {
                        statusValue.setValue(Constants.STATUS_SUCCESS);
                        onDataCallback.onData(response);
                    }

                    @Override
                    protected void onFailure(String message) {
                        statusValue.setValue(Constants.STATUS_SERVER_ERROR);
                    }
                }));
    }

    public void requestHighClosetData(final MutableLiveData<Integer> statusValue, final OnDataCallback<HighClosetResponse> onDataCallback) {
//        statusValue.postValue(Constants.STAUTS_LOADING);

        addSubscribe(api.requestHighAll()
                .compose(RxUtil.<HighClosetResponse>rxSchedulerHelper())
                .subscribeWith(new RxSubscriber<HighClosetResponse>() {
                    @Override
                    protected void onNoNetwork() {
                        statusValue.setValue(Constants.STATUS_NETWORK_ERROR);
                    }

                    @Override
                    protected void onSuccess(HighClosetResponse response) {
                        statusValue.setValue(Constants.STATUS_SUCCESS);
                        onDataCallback.onData(response);
                    }

                    @Override
                    protected void onFailure(String message) {
                        statusValue.setValue(Constants.STATUS_SERVER_ERROR);
                    }
                }));
    }
}
