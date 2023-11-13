package com.example.auctionapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;

import com.example.auctionapp.Adapter.ReadAllAdapter;
import com.example.auctionapp.Model.PdfImage;
import com.example.auctionapp.R;
import com.example.auctionapp.databinding.ActivityReadAllBinding;

import java.util.ArrayList;

public class ReadAll extends AppCompatActivity {
    ActivityReadAllBinding activityReadAllBinding;
    ReadAllAdapter readAllAdapter;
    ArrayList<PdfImage> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityReadAllBinding = DataBindingUtil.setContentView(this, R.layout.activity_read_all);
        setContentView(activityReadAllBinding.getRoot());
        Intent uId = getIntent();
        String thisUid = uId.getStringExtra("uIdNotification");
//        Log.d("ReadAll", "onCreate: " + thisUid);
        getSupportActionBar().setTitle(thisUid);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.lavender)));

        addDb(thisUid);
        onView();
    }

    private void onView() {
        readAllAdapter = new ReadAllAdapter(arrayList);
        activityReadAllBinding.rvReadAll.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        activityReadAllBinding.rvReadAll.setAdapter(readAllAdapter);
    }

    private void addDb(String thisUid) {
        arrayList.add(new PdfImage(R.drawable.m0001));
        arrayList.add(new PdfImage(R.drawable.m0002));
        arrayList.add(new PdfImage(R.drawable.m0003));
        arrayList.add(new PdfImage(R.drawable.m0004));
        arrayList.add(new PdfImage(R.drawable.m0005));
        arrayList.add(new PdfImage(R.drawable.m0006));
        arrayList.add(new PdfImage(R.drawable.m0007));
    }
}