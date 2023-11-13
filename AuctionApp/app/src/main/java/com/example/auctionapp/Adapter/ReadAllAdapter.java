package com.example.auctionapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.auctionapp.Model.PdfImage;
import com.example.auctionapp.databinding.ItemRvReadAllBinding;
import com.example.auctionapp.databinding.ItemRvResultBinding;

import java.util.ArrayList;

public class ReadAllAdapter  extends RecyclerView.Adapter<ReadAllAdapter.MyViewHolder> {
    ArrayList<PdfImage> arrayList;

    public ReadAllAdapter(ArrayList<PdfImage> arrayList) {
        this.arrayList = arrayList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemRvReadAllBinding binding = ItemRvReadAllBinding.inflate(inflater, parent, false);
        return new ReadAllAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ReadAllAdapter.MyViewHolder holder, int position) {
        PdfImage item = arrayList.get(position);
        holder.itemView.im.setImageResource(item.getImageResourceId());
    }
    public int getItemCount() {
        return arrayList != null ? arrayList.size() : 0;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ItemRvReadAllBinding itemView;

        public MyViewHolder(@NonNull ItemRvReadAllBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }
    }
}

