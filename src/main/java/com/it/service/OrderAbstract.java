package com.it.service;


public abstract class OrderAbstract {

    private String orderName;

    public void getOrderInfo(){
        System.out.println("Info");
    }

    public abstract String getOrderName();


}
