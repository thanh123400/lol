package com.example.auctionapp.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.auctionapp.Interface.ClearFocus;
import com.example.auctionapp.Model.BsCarHome;
import com.example.auctionapp.Model.MessageInAuction;
import com.example.auctionapp.databinding.ItemRvAuctionRoomBinding;
import com.example.auctionapp.databinding.ItemRvCmAuctionBinding;

import java.util.ArrayList;

public class AutionRoomAdapter extends RecyclerView.Adapter<AutionRoomAdapter.MyViewHolder> {
    ArrayList<MessageInAuction> arrayList;
    ClearFocus clearFocus;

    public void setClearFocus(ClearFocus clearFocus) {
        this.clearFocus = clearFocus;
    }

    Context context;

    public AutionRoomAdapter(Context context, ArrayList<MessageInAuction> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemRvAuctionRoomBinding binding = ItemRvAuctionRoomBinding.inflate(inflater, parent, false);
        return new AutionRoomAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AutionRoomAdapter.MyViewHolder holder, int position) {
        MessageInAuction item = arrayList.get(position);
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        holder.itemView.getRoot().setOnClickListener(v -> {
            if (clearFocus != null) {
                clearFocus.onClearFocus();
            }
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        });
        holder.itemView.userID.setText(item.getIdUser());
        holder.itemView.Cost.setText(item.getCostUser());
    }


    public int getItemCount() {
        return arrayList != null ? arrayList.size() : 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ItemRvAuctionRoomBinding itemView;

        public MyViewHolder(@NonNull ItemRvAuctionRoomBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }
    }
}

