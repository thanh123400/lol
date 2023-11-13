package com.example.auctionapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.auctionapp.Activity.ReadAll;
import com.example.auctionapp.Model.NotificationDs;
import com.example.auctionapp.databinding.ItemRvNotificationBinding;
import com.example.auctionapp.databinding.ItemRvResultBinding;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {
    ArrayList<NotificationDs> arrayList;
    Context context;
    public NotificationAdapter(Context context, ArrayList<NotificationDs> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemRvNotificationBinding binding = ItemRvNotificationBinding.inflate(inflater, parent, false);
        return new NotificationAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.MyViewHolder holder, int position) {
        NotificationDs item = arrayList.get(position);
        if (position >= 2) {

            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            holder.itemView.getRoot().setOnClickListener(v -> imm.hideSoftInputFromWindow(v.getWindowToken(), 0));
            holder.itemView.dateCreate.setText(item.getDateCreate());
            holder.itemView.content.setText(item.getContent());
            holder.itemView.content.setOnClickListener(v -> {
                Intent readAll = new Intent(v.getContext(), ReadAll.class);
                readAll.putExtra("uIdNotification", item.getId());
//                Log.d("NotificationAdapter", "onBindViewHolder: " + item.getId() );
                v.getContext().startActivity(readAll);
            });
        }
    }
    public int getItemCount() {
        return arrayList != null ? arrayList.size() : 0;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ItemRvNotificationBinding itemView;

        public MyViewHolder(@NonNull ItemRvNotificationBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }
    }
}
