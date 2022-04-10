package com.android.medicareapp.Adapters.customeroperation;

public class OperationModel {
    OrderModel orderModel;
    BookingModel bookingModel;
    MessageModel messageModel;
    public static int i;
    public OperationModel(OrderModel orderModel)
    {
        this.orderModel = orderModel;
        i=0;
    }
    public OperationModel(BookingModel bookingModel)
    {
        this.bookingModel = bookingModel;
        i=1;
    }
    public OperationModel(MessageModel messageModel)
    {
        this.messageModel = messageModel;
        i=2;
    }
}
