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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.auctionapp.Activity.AutionRoom;
import com.example.auctionapp.Model.BsCarHome;

import com.example.auctionapp.R;
import com.example.auctionapp.databinding.DialogJoinAuctionBinding;
import com.example.auctionapp.databinding.DialogRegisterAuctionBinding;
import com.example.auctionapp.databinding.ItemRvRegisterUserBinding;

import java.util.ArrayList;

public class RegisterAdapter  extends RecyclerView.Adapter<RegisterAdapter.MyViewHolder> {
    ArrayList<BsCarHome> arrayList;
    Context context;
    public RegisterAdapter(Context context, ArrayList<BsCarHome> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemRvRegisterUserBinding binding = ItemRvRegisterUserBinding.inflate(inflater, parent, false);
        return new RegisterAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RegisterAdapter.MyViewHolder holder, int position) {
        BsCarHome item = arrayList.get(position);
        String timeStart = item.getDateAuction() + " " + item.getTimeAuctionStart();
        String timeEnd = item.getDateAuction() + " " + item.getTimeAuctionEnd();
        holder.itemView.timeStart.setText(timeStart);
        holder.itemView.timeEnd.setText(timeEnd);
        holder.itemView.BS.setText(item.getNameBs());
        holder.itemView.typeCar.setText(item.getTypeCar());
        holder.itemView.province.setText(item.getProvince());
        //check show button
        holder.itemView.goAuctionRoom.setOnClickListener(v -> {
            openDialog();

        });
    }
    private void openDialog() {
        Dialog dialog = new Dialog(context);
        DialogJoinAuctionBinding binding = DialogJoinAuctionBinding.inflate(LayoutInflater.from(context));
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
        binding.accept.setOnClickListener(v -> {
            Intent goAuctionRoom = new Intent(v.getContext(), AutionRoom.class);
            goAuctionRoom.putExtra("uIdBsCar", "2209"); //Test
            v.getContext().startActivity(goAuctionRoom);
            dialog.dismiss();
//            Toast.makeText(v.getContext(), "Bạn đăng ký tham gia thành công", Toast.LENGTH_SHORT).show();
//            status[position] = true;
//            notifyItemChanged(position);
        });
        binding.decline.setOnClickListener(view -> dialog.dismiss());
        window.setAttributes(windowAttributes);
        dialog.show();

    }
    public int getItemCount() {
        return arrayList != null ? arrayList.size() : 0;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ItemRvRegisterUserBinding itemView;

        public MyViewHolder(@NonNull ItemRvRegisterUserBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }
    }
}
