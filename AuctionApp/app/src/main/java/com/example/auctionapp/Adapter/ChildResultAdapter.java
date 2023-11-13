package com.example.auctionapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.auctionapp.Model.BsCarHome;
import com.example.auctionapp.databinding.ItemChildResultRvBinding;


import java.util.ArrayList;


public class ChildResultAdapter extends RecyclerView.Adapter<ChildResultAdapter.MyViewHolder> implements Filterable {
    ArrayList<BsCarHome> arrayList;
    Context context;
    public ChildResultAdapter(Context context, ArrayList<BsCarHome> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemChildResultRvBinding binding = ItemChildResultRvBinding.inflate(inflater, parent, false);
        return new ChildResultAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildResultAdapter.MyViewHolder holder, int position) {
        BsCarHome item = arrayList.get(position);
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        holder.itemView.getRoot().setOnClickListener(v -> {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        });
        holder.itemView.Bs.setText(item.getNameBs());
        holder.itemView.typeCar.setText(item.getTypeCar());
        holder.itemView.province.setText(item.getProvince());
        holder.itemView.Cost.setText(item.getCost() + " đ");
    }
    public int getItemCount() {
        return arrayList != null ? arrayList.size() : 0;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ItemChildResultRvBinding itemView;

        public MyViewHolder(@NonNull ItemChildResultRvBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
//
//                FilterResults filterResults = new FilterResults();
//                filterResults.values = arrayList;
//                return filterResults;
                return null;
            }

            @Override

            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
//                listWord = (List<Item_Word>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
