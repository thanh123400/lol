package com.example.auctionapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.example.auctionapp.Adapter.CMAuctionAdapter;
import com.example.auctionapp.Interface.ClearFocus;
import com.example.auctionapp.Model.BsCarHome;
import com.example.auctionapp.Model.PdfImage;
import com.example.auctionapp.R;
import com.example.auctionapp.databinding.ActivityCmauctionBinding;

import java.util.ArrayList;

public class CMAuction extends AppCompatActivity implements ClearFocus {
    ActivityCmauctionBinding activityCmauctionBinding;
    CMAuctionAdapter cmAuctionAdapter;
    ArrayList<BsCarHome> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCmauctionBinding = DataBindingUtil.setContentView(this, R.layout.activity_cmauction);
        setContentView(activityCmauctionBinding.getRoot());
        getSupportActionBar().setTitle(R.string.coming_header);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.lavender)));

        addDb();
        event();
        onView();
    }

    private void onView() {
        cmAuctionAdapter = new CMAuctionAdapter(this, arrayList);
        //Khai báo interface
        cmAuctionAdapter.setClearFocus(this);
        activityCmauctionBinding.rvCM.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        activityCmauctionBinding.rvCM.setAdapter(cmAuctionAdapter);
    }



    private void event() {
        activityCmauctionBinding.fb.setOnClickListener(v -> {
            //loadMore
        });
        activityCmauctionBinding.Bs.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                openDialog();
            }
        });
    }

    private void openDialog() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_cm_auction_search);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setWindowAnimations(R.style.SlideUpAnimation);
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = Gravity.TOP;
        windowAttributes.x = 0;
        windowAttributes.y = 350;
        window.setAttributes(windowAttributes);
        dialog.show();

    }
    private void addDb() {

        arrayList.add(new BsCarHome(new PdfImage(R.drawable.bs), "10K-001.89", "22/10/2023", "10:30",
                "11:30", 5 , "Xe con", "Hà Nội"));
        arrayList.add(new BsCarHome(new PdfImage(R.drawable.bs), "20K-001.89", "22/10/2023", "10:30",
                "11:30", 5 , "Xe con", "Hà Nội"));
        arrayList.add(new BsCarHome(new PdfImage(R.drawable.bs), "30K-001.89", "25/10/2023", "10:30",
                "11:30", 5 , "Xe con", "Nam Định"));
        arrayList.add(new BsCarHome(new PdfImage(R.drawable.bs), "10K-011.89", "26/10/2023", "10:30",
                "11:30", 5 , "Xe con", "Hà Nội"));
        arrayList.add(new BsCarHome(new PdfImage(R.drawable.bs), "10K-771.89", "22/10/2023", "10:30",
                "11:30", 5 , "Xe con", "Nghệ An"));
        arrayList.add(new BsCarHome(new PdfImage(R.drawable.bs), "10K-661.89", "22/10/2023", "10:30",
                "11:30", 5 , "Xe con", "Hà Nội"));
        arrayList.add(new BsCarHome(new PdfImage(R.drawable.bs), "10K-331.89", "20/10/2023", "10:30",
                "11:30", 5 , "Xe con", "Nam Định"));
    }
    // Triển khai interface
    @Override
    public void onClearFocus() {
        activityCmauctionBinding.Bs.clearFocus();
    }
}