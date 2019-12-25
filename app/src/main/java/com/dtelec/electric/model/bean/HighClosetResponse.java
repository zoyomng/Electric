package com.dtelec.electric.model.bean;

/**
 * Copyright (c) dtelec, Inc All Rights Reserved.
 */
public class HighClosetResponse {

    /**
     * groundKnife : false
     * chassis : false
     * workStation : false
     * testStation : true
     * breaker : false
     * operation : true
     * remote : false
     */

    public boolean groundKnife;
    public boolean chassis;
    public boolean workStation;
    public boolean testStation;
    public boolean breaker;
    public boolean operation;
    public boolean remote;
    public boolean groundKnifeOpen;
    public boolean groundKnifeClose;


    //    String davearea, @Path("byteValue") int byteValue, @Path("bitValue") int bitValue, @Path("State") boolean state
    public String davearea = "M";
    public int byteValue = 10;
    //2-摇入 3-摇出
    public int handcartBitValue = 2;
    //5-合闸 6-分闸
    public int breakerBitValue = 5;
    //0-合闸 1分闸
    public int knifeBitValue = 0;
    public int state;

}
