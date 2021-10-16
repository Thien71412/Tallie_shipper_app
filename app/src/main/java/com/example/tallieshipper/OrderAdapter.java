package com.example.tallieshipper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tallieshipper.models.Order;

import java.util.List;


public class OrderAdapter extends ArrayAdapter<Order> {

    private final Context mContext;
    private final int mResource;
    private final List<Order> orders;

    public OrderAdapter(@NonNull Context context, int resource, List<Order> orders) {
        super(context, resource, orders);
        this.mContext = context;
        this.mResource = resource;
        this.orders = orders;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView= LayoutInflater.from(mContext).inflate(mResource, parent, false);

        ViewHolder holder = new ViewHolder();
        holder.id_order = convertView.findViewById(R.id.id_order);
        holder.time_created = convertView.findViewById(R.id.time_created);
        holder.id_order.setText(orders.get(position).get_id());
        holder.time_created.setText(orders.get(position).getCreatedAt());

        convertView.setTag(holder);

        return convertView;
    }

    static class ViewHolder {
        TextView id_order, time_created;
    }
}
