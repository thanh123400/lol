package com.example.auctionapp.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.auctionapp.Model.BsCarHome;
import com.example.auctionapp.R;
import com.example.auctionapp.databinding.DialogRegisterAuctionBinding;
import com.example.auctionapp.databinding.ItemRvHomeBinding;

import java.util.ArrayList;

public class homeAdapter extends RecyclerView.Adapter<homeAdapter.MyViewHolder> {
    ArrayList<BsCarHome> arrayList;
    boolean[] status = new boolean[20];

    Context context;
    public homeAdapter(ArrayList<BsCarHome> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
        for (int i = 0; i < 20; ++ i) status[i] = false;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemRvHomeBinding binding = ItemRvHomeBinding.inflate(inflater, parent, false);
        return new MyViewHolder(binding);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        BsCarHome item = arrayList.get(position);
        holder.itemView.STT.setText(String.valueOf(position));
        holder.itemView.favourite.setOnClickListener(view -> {

        });

        holder.itemView.countFavourite.setText(String.valueOf(item.getCountFavourite()));
        holder.itemView.BS.setText(item.getNameBs());
        holder.itemView.typeCar.setText(item.getTypeCar());
        holder.itemView.province.setText(item.getProvince());
        holder.itemView.register.setOnClickListener(view -> {
            openDialog(position, holder.itemView.BS.getText().toString(), holder.itemView.typeCar.getText().toString(), holder.itemView.province.getText().toString());
        });
        if (status[position]) {
            holder.itemView.register.setText("Đã đăng ký");
            holder.itemView.register.setBackgroundColor(ContextCompat.getColor(holder.itemView.getRoot().getContext(), R.color.gray));
            holder.itemView.register.setEnabled(false);
        }
    }
    private void openDialog(int position, String nameBs, String typeCar, String province) {
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
        binding.header.setText("Chấp thuận đăng ký biển số " + nameBs + "");
        binding.date.setText("Loại xe: " +typeCar + "   " + "Thành phố: " + province);
        binding.accept.setOnClickListener(v -> {
            dialog.dismiss();
            Toast.makeText(v.getContext(), "Bạn đăng ký tham gia thành công", Toast.LENGTH_SHORT).show();
            status[position] = true;
            notifyItemChanged(position);
        });
        binding.decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        window.setAttributes(windowAttributes);
        dialog.show();

    }
    public int getItemCount() {
        return arrayList != null ? arrayList.size() : 0;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ItemRvHomeBinding itemView;

        public MyViewHolder(@NonNull ItemRvHomeBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }
    }
}
