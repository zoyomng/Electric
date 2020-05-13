package com.dtelec.electric.model;


import com.dtelec.electric.model.bean.HighClosetResponse;
import com.dtelec.electric.model.bean.LowClosetResponse;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface API {

    //    String BASE_URL = "http://192.168.88.90:8080/";
    String BASE_URL = "http://192.168.77.199:8080/";
//    String BASE_URL = "http://192.168.88.18:8080/";
//    String BASE_URL = "http://192.168.137.1:8080/";

    //    http://192.168.88.90:8080/plc/all
    @GET("plc/all")
    Flowable<LowClosetResponse> request();

    @GET("plc/highall")
    Flowable<HighClosetResponse> requestHighAll();

    @GET("plc/{davearea}/{byteValue}/{bitValue}/{State}")
    Flowable<LowClosetResponse> write(@Path("davearea") String davearea, @Path("byteValue") int byteValue, @Path("bitValue") int bitValue, @Path("State") boolean state);

    @GET("plc/{davearea}/{byteValue}/{bitValue}/{State}")
    Flowable<HighClosetResponse> highElecWrite(@Path("davearea") String davearea, @Path("byteValue") int byteValue, @Path("bitValue") int bitValue, @Path("State") boolean state);

//    1、读值接口：{(例子：M23.0（davearea：M或V；byteValue：23；bitValue：0）}
//    http://ip:8080/plc/{davearea}/{byteValue}/{bitValue}
//     2、写值接口：{(例子：M23.0写入true（davearea：M或V；byteValue：23；bitValue：0；State：1）}
//    http://ip:8080/plc/{davearea}/{byteValue}/{bitValue}/{State}
}
