package com.example.auctionapp.ItemEffect;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class ProvinceItemDecoration extends RecyclerView.ItemDecoration {
    private int isPosition;
    public ProvinceItemDecoration(int isPosition) {
        this.isPosition = isPosition;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);

        if (isPosition != RecyclerView.NO_POSITION && position == isPosition) {
            view.setAlpha(0);
        }
        else {
            view.setAlpha(1f);
        }

    }
}