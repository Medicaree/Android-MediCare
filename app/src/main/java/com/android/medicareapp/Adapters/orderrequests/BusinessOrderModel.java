package com.android.medicareapp.Adapters.orderrequests;

public class BusinessOrderModel {
    String orderBy;
    String date;
    String address;

    public BusinessOrderModel(String orderBy, String date, String address) {
        this.orderBy = orderBy;
        this.date = date;
        this.address = address;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public String getDate() {
        return date;
    }

    public String getAddress() {
        return address;
    }
}
