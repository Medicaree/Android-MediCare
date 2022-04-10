package com.android.medicareapp.Adapters.customeroperation;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;

import com.android.medicareapp.R;

import java.util.ArrayList;
import java.util.List;

public class CustomerOpsAdapter extends BaseAdapter
{
    Context context;
    List<OperationModel> list = new ArrayList<>();
    public CustomerOpsAdapter(Context context, List<OperationModel> list) {
        this.context = context;
        this.list = list;

    }

    public class Order
    {
        TextView name, date;
        ImageView presImage, camImage;
        AppCompatButton status;
    }
    public class Booking
    {
        TextView name, type, date, time;
        AppCompatButton status;
    }
    public class Message
    {
        TextView msg, replyBtn;
        ImageView crossBtn;

    }
    @Override
    public int getItemViewType(int position) {
        return OperationModel.i;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View itemView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        int type = getItemViewType(position);
        switch (type)
        {
            case 0: //order -----------------------------------------------
                final Order holder = new Order();
                itemView = layoutInflater.inflate(R.layout.row_order_customer, null);
                holder.name = itemView.findViewById(R.id.pName);
                holder.date = itemView.findViewById(R.id.date);
                holder.presImage = itemView.findViewById(R.id.presImage);
                holder.camImage = itemView.findViewById(R.id.camImage);
                holder.status = itemView.findViewById(R.id.statusBtn);

                holder.name.setText(list.get(position).orderModel.Pname);
                holder.date.setText(list.get(position).orderModel.date);

                break;
            case 1: //booking -----------------------------------------------
                final Booking _holder = new Booking();
                itemView = layoutInflater.inflate(R.layout.row_booking_customer, null);
                _holder.name = itemView.findViewById(R.id.name);
                _holder.date = itemView.findViewById(R.id.date);
                _holder.time = itemView.findViewById(R.id.time);
                _holder.type = itemView.findViewById(R.id.type);
                _holder.status = itemView.findViewById(R.id.statusBtn);

                _holder.name.setText(list.get(position).bookingModel.name);
                _holder.date.setText(list.get(position).bookingModel.date);
                _holder.time.setText(list.get(position).bookingModel.time);
                _holder.type.setText(list.get(position).bookingModel.type);

                break;
            case 2: //msg -----------------------------------------------------
                final Message _hold = new Message();
                itemView = layoutInflater.inflate(R.layout.row_msg_customer, null);

                _hold.msg = itemView.findViewById(R.id.msg);
                _hold.crossBtn = itemView.findViewById(R.id.crossBtn);
                _hold.replyBtn = itemView.findViewById(R.id.replyBtn);

                _hold.msg.setText(list.get(position).messageModel.name);
                break;
        }
        return itemView;
    }
}
