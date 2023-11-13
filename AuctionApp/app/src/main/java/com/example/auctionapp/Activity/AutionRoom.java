package com.example.auctionapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.auctionapp.Adapter.AutionRoomAdapter;
import com.example.auctionapp.Adapter.CMAuctionAdapter;
import com.example.auctionapp.Model.BsAuction;
import com.example.auctionapp.Model.BsCarHome;
import com.example.auctionapp.Model.MessageInAuction;
import com.example.auctionapp.Model.PdfImage;
import com.example.auctionapp.R;
import com.example.auctionapp.databinding.ActivityAutionRoomBinding;

import java.util.ArrayList;

public class AutionRoom extends AppCompatActivity {
    ActivityAutionRoomBinding activityAutionRoomBinding;
    BsCarHome item;
    BsAuction car;

    CountDownTimer  countDownTimer;
    ArrayList<MessageInAuction> arrayList = new ArrayList<>();
    AutionRoomAdapter autionRoomAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAutionRoomBinding = DataBindingUtil.setContentView(this, R.layout.activity_aution_room);
        setContentView(activityAutionRoomBinding.getRoot());
        Intent uIdBsCar = getIntent();
        getSupportActionBar().setTitle("Phòng đấu giá");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.lavender)));
        
        addDb(uIdBsCar.getStringExtra("uIdBsCar"));
        onView();
        event();

        
        long totalTimeInMillis = calculateTotalTimeInMillis();
        startCountdown(totalTimeInMillis);
    }

    private void onView() {
        autionRoomAdapter = new AutionRoomAdapter(this, arrayList);
        //Khai báo interface

        activityAutionRoomBinding.rvARoom.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        activityAutionRoomBinding.rvARoom.setAdapter(autionRoomAdapter);
    }

    private long calculateTotalTimeInMillis() {
        String timeAuctionEnd = item.getTimeAuctionEnd();
        //Có thể tính = timeAuctionEnd - currentTime
        //Giả sử
        int hours = 1;
        long millisecondsInHour = 60 * 60 * 1000;
        return hours * millisecondsInHour;
    }
    private void startCountdown(long totalTimeInMillis) {
        countDownTimer = new CountDownTimer(totalTimeInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateCountdownText(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                onUpdate();
                activityAutionRoomBinding.textTimeToEnd.setText("End auction!");
            }
        };

        countDownTimer.start();
    }

    private void onUpdate() {
        //Cập nhật bảng BsCarHome : trạng thái, số tiền trúng, ...
    }

    private void updateCountdownText(long millisUntilFinished) {
        long hours = millisUntilFinished / (60 * 60 * 1000);
        long minutes = (millisUntilFinished % (60 * 60 * 1000)) / (60 * 1000);
        long seconds = (millisUntilFinished % (60 * 1000)) / 1000;

        String countdownText = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        activityAutionRoomBinding.textTimeToEnd.setText(countdownText);
    }
    private void event() {

        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        activityAutionRoomBinding.getRoot().setOnClickListener(v -> {
            activityAutionRoomBinding.textCostMax.clearFocus();
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        });

        activityAutionRoomBinding.textCostMax.setText(String.valueOf(car.getCost()));
        activityAutionRoomBinding.imgCar.setImageResource(item.getImage().getImageResourceId());

        activityAutionRoomBinding.submit.setOnClickListener(v -> {
            if (checkCost(activityAutionRoomBinding.cost.getText().toString())) {
                arrayList.add(new MessageInAuction("111", activityAutionRoomBinding.cost.getText().toString()));
                autionRoomAdapter.notifyDataSetChanged();
                //Cập nhật database 2 thuộc tính là cost, messageInAuctionList ở BsAuction
            }
        });
    }

    private boolean checkCost(String cost) {
        if (Integer.parseInt(cost) > car.getCost()) {
            activityAutionRoomBinding.textCostMax.setText(cost);
            return true;
        }
        Toast.makeText(this, "Giá đưa ra phải cao hơn giá hiện tại", Toast.LENGTH_SHORT).show();
        return false;
    }

    private void addDb(String uIdBsCar) {
        //Test
        item = new BsCarHome("3", new PdfImage(R.drawable.bs), "9:30", "10:30", "22/10/2023");
        car = new BsAuction("3", 1000000, new ArrayList<>());
        for (MessageInAuction i : car.getMessageInAuctionList()) {
            arrayList.add(i);
        }
    }
}