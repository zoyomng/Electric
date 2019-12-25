package com.dtelec.electric.model.bean;

import retrofit2.http.Path;

/**
 *
 */
public class ItemBean {

    /**
     * workStation : true
     * operation : true
     * breaker : true
     * remote : true
     */

    public String index;
    //工作位置
    public boolean workStation;
    //脱口状态
    public boolean operation;
    //合闸状态
    public boolean breaker;
    //远程/就地
    public boolean remote;

//    String davearea, @Path("byteValue") int byteValue, @Path("bitValue") int bitValue, @Path("State") boolean state
    public String davearea;
    public int byteValue;
    public int bitValue;
    public int state;


}
