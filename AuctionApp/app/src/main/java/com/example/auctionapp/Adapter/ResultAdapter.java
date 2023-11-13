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

import com.example.auctionapp.Activity.ResultTimeMore;
import com.example.auctionapp.Model.BsCarHome;
import com.example.auctionapp.databinding.ItemRvResultBinding;

import java.util.ArrayList;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.MyViewHolder> {
    ArrayList<BsCarHome> arrayList;
    Context context;
    public ResultAdapter(Context context, ArrayList<BsCarHome> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemRvResultBinding binding = ItemRvResultBinding.inflate(inflater, parent, false);
        return new ResultAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultAdapter.MyViewHolder holder, int position) {
        BsCarHome item = arrayList.get(position);
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        holder.itemView.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });
//        dd/mm/yyyy
        holder.itemView.dateEnd.setText("Kết quả đấu giá ngày " + item.getDateAuction());
//        hh:mm
        holder.itemView.timeFrom.setText("Thời gian từ " + item.getTimeAuctionStart() + " đến " + item.getTimeAuctionEnd());
        // xxx.xxx.xxx đ
        holder.itemView.MaxCost.setText(item.getCost());
        holder.itemView.seeMore.setOnClickListener(v -> {

            Intent resultTimeMore = new Intent(v.getContext(), ResultTimeMore.class);
            resultTimeMore.putExtra("date", item.getDateAuction());
            resultTimeMore.putExtra("timeStart", item.getTimeAuctionStart());
            resultTimeMore.putExtra("timeEnd", item.getTimeAuctionEnd());
            ((Activity) v.getContext()).startActivity(resultTimeMore);
        });
    }
    public int getItemCount() {
        return arrayList != null ? arrayList.size() : 0;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ItemRvResultBinding itemView;

        public MyViewHolder(@NonNull ItemRvResultBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }
    }
}
