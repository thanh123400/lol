package com.example.auctionapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.auctionapp.Adapter.ResultAdapter;
import com.example.auctionapp.ItemEffect.TransparentItemDecoration;
import com.example.auctionapp.Model.BsCarHome;
import com.example.auctionapp.R;
import com.example.auctionapp.databinding.ActivityResultAuctionBinding;

import java.util.ArrayList;
import java.util.Calendar;


public class ResultAuction extends AppCompatActivity {
    ActivityResultAuctionBinding activityResultAuctionBinding;
    ArrayList<BsCarHome> arrayList = new ArrayList<>();
    ResultAdapter resultAdapter;
    int sum = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityResultAuctionBinding = DataBindingUtil.setContentView(this, R.layout.activity_result_auction);
        setContentView(activityResultAuctionBinding.getRoot());
        getSupportActionBar().setTitle(R.string.result_header);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.lavender)));

        addDb();
        onView();
        setup();
        search();

    }

    private void onView() {
        resultAdapter = new ResultAdapter(this, arrayList);

        activityResultAuctionBinding.rvResult.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        activityResultAuctionBinding.rvResult.setAdapter(resultAdapter);
        TransparentItemDecoration itemDecoration = new TransparentItemDecoration();

// Áp dụng itemDecoration chỉ cho item đầu tiên
        activityResultAuctionBinding.rvResult.addItemDecoration(itemDecoration);

        activityResultAuctionBinding.rvResult.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                sum += dy;
                if (sum < 0) sum = 0;
                if (sum < 20) {
                    activityResultAuctionBinding.cardView.setVisibility(View.VISIBLE);
                    activityResultAuctionBinding.cardView2.setVisibility(View.GONE);
                } else if (sum > 20) {
                    activityResultAuctionBinding.cardView.setVisibility(View.GONE);
                    activityResultAuctionBinding.cardView2.setVisibility(View.VISIBLE);
                    onChangeCardView2();
                    LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int firstVisibleItemPosition = layoutManager.findFirstCompletelyVisibleItemPosition();
                    if (recyclerView.getChildAt(1).getTop() > activityResultAuctionBinding.cardView2.getBottom() + 100
                            && firstVisibleItemPosition == 1 && dy > 0
                    ) {
                        recyclerView.smoothScrollBy(0, 50);
                    }
                }
            }
        });
    }
    private void onChangeCardView2() {
        activityResultAuctionBinding.cardView21.setText(activityResultAuctionBinding.Bs.getText().toString().trim());
        activityResultAuctionBinding.cardView22.setText(
                activityResultAuctionBinding.textChooseStartDate.getText().toString() + "  " +
                        activityResultAuctionBinding.textChooseEndDate.getText().toString());
    }

    private void search() {
        activityResultAuctionBinding.searchHome.setOnClickListener(view -> {
            String nameIdCar = activityResultAuctionBinding.Bs.getText().toString().trim();
            String startDate = activityResultAuctionBinding.textChooseStartDate.getText().toString();
            String endDate = activityResultAuctionBinding.textChooseEndDate.getText().toString();
            nameIdCar = nameIdCar.equals("")? "All" : nameIdCar;
            startDate = startDate.equals("")? "All" : startDate;
            endDate = endDate.equals("")? "All" : endDate;
            //Truy Vấn Database

        });
        activityResultAuctionBinding.showAllSearch.setOnClickListener(view -> {
            activityResultAuctionBinding.cardView.setVisibility(View.VISIBLE);
            activityResultAuctionBinding.cardView2.setVisibility(View.GONE);
        });
    }

    private void setup() {

        activityResultAuctionBinding.textChooseStartDate.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(ResultAuction.this,
                    (view, year1, monthOfYear, dayOfMonth) -> {
                        String selectedDate = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year1;
                        activityResultAuctionBinding.textChooseStartDate.setText(selectedDate);
                    },
                    year,
                    month,
                    day
            );
            datePickerDialog.show();
        });

        activityResultAuctionBinding.textChooseEndDate.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(ResultAuction.this,
                    (view, year1, monthOfYear, dayOfMonth) -> {
                        String selectedDate = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year1;
                        activityResultAuctionBinding.textChooseEndDate.setText(selectedDate);
                    },
                    year,
                    month,
                    day
            );
            datePickerDialog.show();
        });

        activityResultAuctionBinding.fb.setOnClickListener(v -> {
            //load 10 database
        });
    }
    private void addDb() {
        arrayList.add(new BsCarHome());
        arrayList.add(new BsCarHome("22/10/2023", "10:30", "11:30", "1.000.000.000"));
        arrayList.add(new BsCarHome("18/10/2023", "10:30", "11:30", "1.000.000.000"));
        arrayList.add(new BsCarHome("11/10/2023", "10:30", "11:30", "1.000.000.000"));
        arrayList.add(new BsCarHome("1/10/2023", "10:30", "11:30", "1.000.000.000"));
        arrayList.add(new BsCarHome("22/9/2023", "10:30", "11:30", "1.000.000.000"));
        arrayList.add(new BsCarHome("15/8/2023", "10:30", "11:30", "1.000.000.000"));

    }
}