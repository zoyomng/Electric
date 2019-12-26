package com.dtelec.electric.viewModel;

import android.app.Application;
import android.view.View;

import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.dtelec.core.common.widget.toast.Toasty;
import com.dtelec.core.mvvm.base.BaseViewModel;
import com.dtelec.core.mvvm.base.OnDataCallback;
import com.dtelec.core.mvvm.binding.adapter.recyclerview.BaseAdapter;
import com.dtelec.core.mvvm.binding.adapter.recyclerview.ItemViewHolder;
import com.dtelec.electric.R;
import com.dtelec.electric.databinding.ItemClosetBinding;
import com.dtelec.electric.model.bean.HighClosetResponse;
import com.dtelec.electric.model.bean.ItemBean;
import com.dtelec.electric.model.bean.LowClosetResponse;
import com.dtelec.electric.model.repository.MainRepository;

import java.util.ArrayList;
import java.util.List;


public class MainViewModel extends BaseViewModel<MainRepository> {

    public String highElecDetailTitle = "高压柜详情";
    public String elecDiagramTitle = "电路图";
    public BaseAdapter baseAdapter;
    private List<ItemBean> list;
    public MutableLiveData<ItemBean> showDialog = new MutableLiveData<>();
    public MutableLiveData<HighClosetResponse> highClosetBean = new MutableLiveData<>();

    public MainViewModel(@NonNull Application application) {
        super(application);
    }


    /**
     * 初始化高低压电柜
     */
    public void initLowClosetLayoutFragment() {
        //显示dialog
        showDialog.setValue(null);

        if (list == null) {
            list = new ArrayList<>();
            ItemBean itemBean1 = new ItemBean();
            itemBean1.davearea = "M";
            itemBean1.byteValue = 20;
            itemBean1.bitValue = 0;
            itemBean1.index = "1#";


            ItemBean itemBean2 = new ItemBean();
            itemBean2.davearea = "M";
            itemBean2.byteValue = 21;
            itemBean2.bitValue = 0;
            itemBean2.index = "2#";


            ItemBean itemBean3 = new ItemBean();
            itemBean3.davearea = "M";
            itemBean3.byteValue = 22;
            itemBean3.bitValue = 0;
            itemBean3.index = "3#";


            ItemBean itemBean4 = new ItemBean();
            itemBean4.davearea = "M";
            itemBean4.byteValue = 23;
            itemBean4.bitValue = 0;
            itemBean4.index = "4#";

            list.add(itemBean1);
            list.add(itemBean2);
            list.add(itemBean3);
            list.add(itemBean4);
        }
        baseAdapter = new BaseAdapter<ItemBean>(R.layout.item_closet, com.dtelec.electric.BR.model, list) {
            @Override
            public void addItemClickListener(ItemViewHolder viewHolder, final ItemBean itemBean, final int position) {
                ((ItemClosetBinding) viewHolder.getDataBinding()).ivClosetLeft.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //读
                        request();
                        //写

                        if (!itemBean.operation) {
                            //脱扣->分闸
                            itemBean.bitValue = 1;

                            showDialog.setValue(itemBean);
                            return;
                        }

                        if (itemBean.breaker) {
                            //合闸->分闸
                            //在工作位置，在远程，合闸或者脱扣状态，就可以分闸。
                            if (itemBean.remote && itemBean.workStation && (itemBean.operation || itemBean.breaker)) {
                                showDialog.setValue(itemBean);
                            } else {
                                Toasty.warning(getApplication(), "当前工作状态不能进行分闸操作", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            //分闸->合闸
                            //在工作位置，在远程，没有脱扣，没有合闸，就可以合闸。
                            if (itemBean.remote && itemBean.workStation && !itemBean.breaker) {
                                showDialog.setValue(itemBean);
                            } else {
                                Toasty.warning(getApplication(), "当前工作状态不能进行合闸操作", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        };

//        subscribe = Observable.interval(0, 5, TimeUnit.SECONDS)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Long>() {
//                    @Override
//                    public void accept(Long aLong) throws Exception {
//                        request();
//                        System.out.println("============3===========");
//
//                    }
//                });
        request();
    }

    public void request() {
        repository.request(statusValue, new OnDataCallback<LowClosetResponse>() {

            @Override
            public void onData(LowClosetResponse lowClosetResponse) {
                handleData(lowClosetResponse);
            }
        });
    }

    private void handleData(LowClosetResponse lowClosetResponse) {


        ItemBean itemBean1 = list.get(0);
        itemBean1.breaker = lowClosetResponse.breaker11;
        itemBean1.operation = lowClosetResponse.operation11;
        itemBean1.workStation = lowClosetResponse.workStation11;
        itemBean1.remote = lowClosetResponse.remote11;
        //op分闸
        itemBean1.bitValue = lowClosetResponse.breaker11 ? 1:0;

        ItemBean itemBean2 = list.get(1);
        itemBean2.breaker = lowClosetResponse.breaker12;
        itemBean2.operation = lowClosetResponse.operation12;
        itemBean2.workStation = lowClosetResponse.workStation12;
        itemBean2.remote = lowClosetResponse.remote12;
        //op分闸 0->合闸
        itemBean2.bitValue = lowClosetResponse.breaker12 ? 1:0;

        ItemBean itemBean3 = list.get(2);
        itemBean3.breaker = lowClosetResponse.breaker13;
        itemBean3.operation = lowClosetResponse.operation13;
        itemBean3.workStation = lowClosetResponse.workStation13;
        itemBean3.remote = lowClosetResponse.remote13;
        //op分闸
        itemBean3.bitValue = lowClosetResponse.breaker13 ? 1:0;

        ItemBean itemBean4 = list.get(3);
        itemBean4.breaker = lowClosetResponse.breaker14;
        itemBean4.operation = lowClosetResponse.operation14;
        itemBean4.workStation = lowClosetResponse.workStation14;
        itemBean4.remote = lowClosetResponse.remote14;
        //op分闸
        itemBean4.bitValue = lowClosetResponse.breaker14 ? 1:0;

        baseAdapter.notifyDataSetChanged();
    }

    /**
     * 低压写
     *
     * @param itemBean
     */
    public void write(ItemBean itemBean) {
        repository.write(statusValue, itemBean.davearea, itemBean.byteValue, itemBean.bitValue, true, new OnDataCallback<LowClosetResponse>() {
            @Override
            public void onData(LowClosetResponse lowClosetResponse) {
                handleData(lowClosetResponse);
            }
        });
    }

    public void initHighClosetLayoutFragment() {
        repository.requestHighClosetData(statusValue, new OnDataCallback<HighClosetResponse>() {

            @Override
            public void onData(HighClosetResponse highClosetResponse) {
                highClosetBean.setValue(highClosetResponse);
            }
        });
    }

    /**
     * 高压写
     *
     * @param bitVaule
     */
    public void highElecWrite(int bitVaule) {
        HighClosetResponse beanValue = highClosetBean.getValue();
        repository.highElecWrite(statusValue, beanValue.davearea, beanValue.byteValue, bitVaule, true, new OnDataCallback<HighClosetResponse>() {
            @Override
            public void onData(HighClosetResponse highClosetResponse) {
                highClosetBean.setValue(highClosetResponse);
            }
        });
    }


//    @Override
//    public void onStop() {
//        super.onStop();
//        if (subscribe != null && !subscribe.isDisposed()) {
//            subscribe.dispose();
//        }
//    }
}
