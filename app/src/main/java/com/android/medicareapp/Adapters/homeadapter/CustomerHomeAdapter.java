package com.android.medicareapp.Adapters.homeadapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.medicareapp.R;

import java.util.ArrayList;
import java.util.List;

public class CustomerHomeAdapter extends BaseAdapter
{
    Context context;
    List<CustomerHomeModel> list = new ArrayList<>();
    public CustomerHomeAdapter(Context context, List<CustomerHomeModel> list) {
        this.context = context;
        this.list = list;

    }

    public class ViewContainer
    {
        TextView text;
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
        itemView = layoutInflater.inflate(R.layout.row_home_customer, null);
        final ViewContainer holder = new ViewContainer();

        holder.text = itemView.findViewById(R.id.text);
        holder.text.setText(list.get(position).getName());

        return itemView;
    }
}
