package com.example.auctionapp.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.auctionapp.Interface.ClearFocus;
import com.example.auctionapp.Model.BsCarHome;
import com.example.auctionapp.R;
import com.example.auctionapp.databinding.DialogRegisterAuctionBinding;
import com.example.auctionapp.databinding.ItemChildResultRvBinding;
import com.example.auctionapp.databinding.ItemDialogCmAuctionBinding;
import com.example.auctionapp.databinding.ItemRvCmAuctionBinding;

import java.util.ArrayList;

public class CMAuctionAdapter extends RecyclerView.Adapter<CMAuctionAdapter.MyViewHolder> {
    ArrayList<BsCarHome> arrayList;
    ClearFocus clearFocus;

    public void setClearFocus(ClearFocus clearFocus) {
        this.clearFocus = clearFocus;
    }

    Context context;
    public CMAuctionAdapter(Context context, ArrayList<BsCarHome> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemRvCmAuctionBinding binding = ItemRvCmAuctionBinding.inflate(inflater, parent, false);
        return new CMAuctionAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CMAuctionAdapter.MyViewHolder holder, int position) {
        BsCarHome item = arrayList.get(position);
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        holder.itemView.getRoot().setOnClickListener(v -> {
            if (clearFocus != null) {
                clearFocus.onClearFocus();

            }
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        });
        //Ảnh
        holder.itemView.imgCar.setImageResource(item.getImage().getImageResourceId());
        //
        holder.itemView.province.setText(item.getProvince());
        holder.itemView.typeCar.setText(item.getTypeCar());
        holder.itemView.countFavourite.setText(String.valueOf(item.getCountFavourite()));

        holder.itemView.favourite.setOnClickListener(v -> {
            // Thêm biển số xe này vào id người dùng -> firebase người dùng update
        });

        holder.itemView.submit.setOnClickListener(v -> {
            openRegisterDialog(item.getNameBs(), item.getDateAuction(), item.getTimeAuctionStart());
        });

        holder.itemView.showMoreInfor.setOnClickListener(v -> {
            openDialog(item.getNameBs(), item.getDateAuction(), item.getTimeAuctionStart(), item.getTimeAuctionEnd(), item.getTypeCar(), item.getProvince());
        });
    }

    private void openRegisterDialog(String nameBs, String dateAuction, String timeAuctionStart) {
        Dialog dialog = new Dialog(context);
        DialogRegisterAuctionBinding binding = DialogRegisterAuctionBinding.inflate(LayoutInflater.from(context));
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
        binding.header.setText("Chấp thuận đăng ký biển số " + nameBs);
        binding.date.setText("Thời gian đấu giá vào " + timeAuctionStart + " " + dateAuction);
        binding.accept.setOnClickListener(v -> {
            //Thêm biển số vào mục chuẩn bị đấu giá người dùng
        });
        binding.decline.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }

    private void openDialog(String nameBs, String dateAuction, String timeAuctionStart, String timeAuctionEnd, String typeCar, String province) {
        Dialog dialog = new Dialog(context);
        ItemDialogCmAuctionBinding binding = ItemDialogCmAuctionBinding.inflate(LayoutInflater.from(context));
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
        binding.textBs.setText(nameBs);
        binding.textStart.setText(timeAuctionStart + " " + dateAuction);
        binding.textEnd.setText(timeAuctionEnd + " " + dateAuction);
        binding.textTypeCar.setText(typeCar);
        binding.textProvince.setText(province);
        dialog.show();
    }


    public int getItemCount() {
        return arrayList != null ? arrayList.size() : 0;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ItemRvCmAuctionBinding itemView;

        public MyViewHolder(@NonNull ItemRvCmAuctionBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }
    }

}
