package com.example.auctionapp.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.auctionapp.Activity.ReadAll;
import com.example.auctionapp.Model.BsCarHome;
import com.example.auctionapp.Model.NotificationDs;
import com.example.auctionapp.R;
import com.example.auctionapp.databinding.DialogRegisterAuctionBinding;
import com.example.auctionapp.databinding.DialogUnFavouriteBinding;
import com.example.auctionapp.databinding.ItemRvFavouriteBinding;
import com.example.auctionapp.databinding.ItemRvNotificationBinding;

import java.util.ArrayList;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.MyViewHolder> {
    ArrayList<BsCarHome> arrayList;
    Context context;
    public FavouriteAdapter(Context context, ArrayList<BsCarHome> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemRvFavouriteBinding binding = ItemRvFavouriteBinding.inflate(inflater, parent, false);
        return new FavouriteAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteAdapter.MyViewHolder holder, int position) {
        BsCarHome item = arrayList.get(position);

        holder.itemView.STT.setText(String.valueOf(position));
        holder.itemView.countFavourite.setText(String.valueOf(item.getCountFavourite()));
        holder.itemView.BS.setText(item.getNameBs());
        holder.itemView.province.setText(item.getProvince());
        holder.itemView.typeCar.setText(item.getTypeCar());

        holder.itemView.favourite.setOnClickListener(v -> {
            openFavouriteDialog(item.getNameBs());
        });
    }
    private void openFavouriteDialog(String nameBs) {
        Dialog dialog = new Dialog(context);
        DialogUnFavouriteBinding binding = DialogUnFavouriteBinding.inflate(LayoutInflater.from(context));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(binding.getRoot());
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setWindowAnimations(R.style.SlideUpAnimation);

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = Gravity.CENTER;
        window.setAttributes(windowAttributes);
        binding.header.setText("Bỏ ưu thích biển số " + nameBs);
        binding.accept.setOnClickListener(v -> {
            //Xóa biển khỏi favourite người dùng
        });
        binding.decline.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }
    public int getItemCount() {
        return arrayList != null ? arrayList.size() : 0;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ItemRvFavouriteBinding itemView;

        public MyViewHolder(@NonNull ItemRvFavouriteBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }
    }
}
