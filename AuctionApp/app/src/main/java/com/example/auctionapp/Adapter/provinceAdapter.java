package com.example.auctionapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.auctionapp.R;
import com.example.auctionapp.databinding.ItemProvinceBinding;
import com.example.auctionapp.databinding.ItemRvHomeBinding;

import java.util.ArrayList;

public class provinceAdapter extends RecyclerView.Adapter<provinceAdapter.MyViewHolder> {
    ArrayList<String> arrayList;
    String onChoose="";
    int positionPrevious = RecyclerView.NO_POSITION;
    int nowPosition = RecyclerView.NO_POSITION;
    public provinceAdapter(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }
    public String getOnChoose() {
        return onChoose;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemProvinceBinding binding = ItemProvinceBinding.inflate(inflater, parent, false);
        return new MyViewHolder(binding);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String province = arrayList.get(position);
        holder.itemView.textItem.setText(province);
        if (nowPosition == position) {
            onChoose = province;
            holder.itemView.imageItem.setImageDrawable(
                    ContextCompat.getDrawable(holder.itemView.getRoot().getContext()
                            , R.drawable.checked));
        }
        else {
            holder.itemView.imageItem.setImageDrawable(
                    ContextCompat.getDrawable(holder.itemView.getRoot().getContext()
                            , R.drawable.uncheck));
        }
    }
    public int getItemCount() {
        return arrayList != null ? arrayList.size() : 0;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ItemProvinceBinding itemView;
        public MyViewHolder(@NonNull ItemProvinceBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
            itemView.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    nowPosition = getAdapterPosition();
                    if (positionPrevious != RecyclerView.NO_POSITION) {
                        notifyItemChanged(positionPrevious);
                    }
                    positionPrevious = nowPosition;
                    notifyItemChanged(nowPosition);
                }
            });
        }
    }
}

