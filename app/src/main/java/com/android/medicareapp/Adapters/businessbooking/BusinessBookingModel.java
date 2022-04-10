package com.android.medicareapp.Adapters.businessbooking;

public class BusinessBookingModel {
    String bookingBy;
    String date;
    String time;

    public BusinessBookingModel(String bookingBy, String date, String time) {
        this.bookingBy = bookingBy;
        this.date = date;
        this.time = time;
    }

    public String getBookingBy() {
        return bookingBy;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
