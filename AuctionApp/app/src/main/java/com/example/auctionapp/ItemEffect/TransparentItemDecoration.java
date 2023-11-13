package com.example.auctionapp.ItemEffect;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class TransparentItemDecoration extends RecyclerView.ItemDecoration {

    //onDraw cập nhật liên tục
    //getItemOffsets chỉ dùng cho vị trí duy nhất
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        if (position == 0) {
            view.setAlpha(0);
        }
        else {
            view.setAlpha(1f);
        }
    }
}