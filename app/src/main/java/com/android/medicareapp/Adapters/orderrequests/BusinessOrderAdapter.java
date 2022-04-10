package com.android.medicareapp.Adapters.orderrequests;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.medicareapp.Adapters.businessbooking.BusinessBookingModel;
import com.android.medicareapp.R;

import java.util.ArrayList;
import java.util.List;

public class BusinessOrderAdapter extends BaseAdapter
{
    Context context;
    List<BusinessOrderModel> list = new ArrayList<>();
    public BusinessOrderAdapter(Context context, List<BusinessOrderModel> list) {
        this.context = context;
        this.list = list;

    }

    public class ViewContainer
    {
        TextView orderby, date, address;
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
        itemView = layoutInflater.inflate(R.layout.row_order_customer, null);
        final ViewContainer holder = new ViewContainer();

        holder.orderby = itemView.findViewById(R.id.pOrder);
        holder.date = itemView.findViewById(R.id.date);
        holder.address = itemView.findViewById(R.id.address);

        holder.orderby.setText(list.get(position).getOrderBy());
        holder.date.setText(list.get(position).getDate());
        holder.address.setText(list.get(position).getAddress());

        return itemView;
    }
}
