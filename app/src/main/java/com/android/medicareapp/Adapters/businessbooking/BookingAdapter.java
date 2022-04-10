package com.android.medicareapp.Adapters.businessbooking;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.medicareapp.Adapters.homeadapter.CustomerHomeModel;
import com.android.medicareapp.R;

import java.util.ArrayList;
import java.util.List;

public class BookingAdapter extends BaseAdapter
{
    Context context;
    List<BusinessBookingModel> list = new ArrayList<>();
    public BookingAdapter(Context context, List<BusinessBookingModel> list) {
        this.context = context;
        this.list = list;

    }

    public class ViewContainer
    {
        TextView by, date, time;
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
        itemView = layoutInflater.inflate(R.layout.row_booking_request, null);
        final ViewContainer holder = new ViewContainer();

        holder.by = itemView.findViewById(R.id.by);
        holder.date = itemView.findViewById(R.id.date);
        holder.time = itemView.findViewById(R.id.time);

        holder.by.setText(list.get(position).getBookingBy());
        holder.date.setText(list.get(position).getDate());
        holder.time.setText(list.get(position).getTime());

        return itemView;
    }
}
