package com.android.medicareapp.Adapters.customeroperation;

import android.widget.ImageView;

public class OrderModel {
    String Pname;
    String date;
    ImageView image;

    public OrderModel(String pname, String date) {
        Pname = pname;
        this.date = date;
    }
}
