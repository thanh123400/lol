package com.example.auctionapp.ItemEffect;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class NotificationItemDecoration extends RecyclerView.ItemDecoration {


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);

        if (position == 0 || position == 1) {
            view.setAlpha(0);
        }
        else {
            view.setAlpha(1f);
        }

    }
}
