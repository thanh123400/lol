package com.example.auctionapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

import com.example.auctionapp.Adapter.ChildResultAdapter;
import com.example.auctionapp.Adapter.ResultAdapter;
import com.example.auctionapp.Model.BsCarHome;
import com.example.auctionapp.R;
import com.example.auctionapp.databinding.ActivityResultTimeMoreBinding;

import java.util.ArrayList;

public class ResultTimeMore extends AppCompatActivity {
    ActivityResultTimeMoreBinding activityResultTimeMoreBinding;
    ArrayList<BsCarHome> arrayList = new ArrayList<>();
    ChildResultAdapter childResultAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityResultTimeMoreBinding = DataBindingUtil.setContentView(this, R.layout.activity_result_time_more);
        setContentView(activityResultTimeMoreBinding.getRoot());

        Intent resultAuctionFrom = getIntent();

        getSupportActionBar().setTitle(R.string.result_header);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.lavender)));

        String resultTimeMoreTitle = "Kết quả đấu giá ngày " + resultAuctionFrom.getStringExtra("date") + " " +
                "thời gian từ " + resultAuctionFrom.getStringExtra("timeStart") +
                " đến " + resultAuctionFrom.getStringExtra("timeEnd");

        activityResultTimeMoreBinding.title.setText(resultTimeMoreTitle);

        addDb(resultAuctionFrom.getStringExtra("date"), resultAuctionFrom.getStringExtra("timeStart")
                , resultAuctionFrom.getStringExtra("timeEnd"));

        event();
        onView();
        search();
    }

    private void event() {
        activityResultTimeMoreBinding.fb.setOnClickListener(v -> {
            //loadMore
        });
    }

    private void search() {
    }

    private void onView() {
        childResultAdapter = new ChildResultAdapter(this, arrayList);

        activityResultTimeMoreBinding.rvChildResult.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        activityResultTimeMoreBinding.rvChildResult.setAdapter(childResultAdapter);
    }

    private void addDb(String date, String timeStart, String timeEnd) {
        arrayList.add(new BsCarHome("30K-555.55", "Hà Nội", "Xe Con", "1.000.000.000", ""));
        arrayList.add(new BsCarHome("98A-666.66", "Bắc Giang", "Xe Con", "1.000.000.000", ""));
        arrayList.add(new BsCarHome("36A-999.99", "Thanh Hóa", "Xe Con", "1.000.000.000", ""));
        arrayList.add(new BsCarHome("30K-567.89", "Hà Nội", "Xe Con", "1.000.000.000", ""));
        arrayList.add(new BsCarHome("47A-599.99", "Đắk Lắk", "Xe Con", "1.000.000.000", ""));
        arrayList.add(new BsCarHome("30K-555.55", "Hà Nội", "Xe Con", "1.000.000.000", ""));
        arrayList.add(new BsCarHome("98A-666.66", "Bắc Giang", "Xe Con", "1.000.000.000", ""));
        arrayList.add(new BsCarHome("36A-999.99", "Thanh Hóa", "Xe Con", "1.000.000.000", ""));
        arrayList.add(new BsCarHome("30K-567.89", "Hà Nội", "Xe Con", "1.000.000.000", ""));
        arrayList.add(new BsCarHome("47A-599.99", "Đắk Lắk", "Xe Con", "1.000.000.000", ""));
        arrayList.add(new BsCarHome("30K-555.55", "Hà Nội", "Xe Con", "1.000.000.000", ""));
        arrayList.add(new BsCarHome("98A-666.66", "Bắc Giang", "Xe Con", "1.000.000.000", ""));
        arrayList.add(new BsCarHome("36A-999.99", "Thanh Hóa", "Xe Con", "1.000.000.000", ""));
        arrayList.add(new BsCarHome("30K-567.89", "Hà Nội", "Xe Con", "1.000.000.000", ""));
    }

}