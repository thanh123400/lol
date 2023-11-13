package com.example.auctionapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

import com.example.auctionapp.Adapter.NotificationAdapter;
import com.example.auctionapp.Adapter.ResultAdapter;
import com.example.auctionapp.ItemEffect.NotificationItemDecoration;
import com.example.auctionapp.ItemEffect.TransparentItemDecoration;
import com.example.auctionapp.Model.BsCarHome;
import com.example.auctionapp.Model.NotificationDs;
import com.example.auctionapp.R;
import com.example.auctionapp.databinding.ActivityNotificationAuctionBinding;

import java.util.ArrayList;

public class NotificationAuction extends AppCompatActivity {
    ActivityNotificationAuctionBinding activityNotificationAuctionBinding;
    ArrayList<NotificationDs> arrayList = new ArrayList<>();
    NotificationAdapter notificationAdapter;
    int sum = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityNotificationAuctionBinding = DataBindingUtil.setContentView(this, R.layout.activity_notification_auction);
        setContentView(activityNotificationAuctionBinding.getRoot());
        getSupportActionBar().setTitle(R.string.notification_header);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.lavender)));
        addDb();
        onView();
        event();
    }

    private void event() {
        activityNotificationAuctionBinding.fb.setOnClickListener(v -> {
            //loadMore
        });

        activityNotificationAuctionBinding.searchHome.setOnClickListener(v -> {
            String content = activityNotificationAuctionBinding.content.getText().toString().trim();
            String startDate = activityNotificationAuctionBinding.textChooseStartDate.getText().toString();
            String endDate = activityNotificationAuctionBinding.textChooseEndDate.getText().toString();
            content = content.equals("")? "All" : content;
            startDate = startDate.equals("")? "All" : startDate;
            endDate = endDate.equals("")? "All" : endDate;
        });

        activityNotificationAuctionBinding.showAllSearch.setOnClickListener(view -> {
            activityNotificationAuctionBinding.cardView.setVisibility(View.VISIBLE);
            activityNotificationAuctionBinding.cardView2.setVisibility(View.GONE);
        });
    }

    private void addDb() {
        arrayList.add(new NotificationDs());
        arrayList.add(new NotificationDs());
        arrayList.add(new NotificationDs("1", "22-09-2023", "Thông báo về abc xyz"));
        arrayList.add(new NotificationDs("2","10-09-2023", "Thông báo về abc hihi"));
        arrayList.add(new NotificationDs("3","25-09-2023", "Thông báo về mm xyz"));
        arrayList.add(new NotificationDs("4","2-09-2023", "Thông báo về mm nnnn"));
        arrayList.add(new NotificationDs("1", "22-09-2023", "Thông báo về abc xyz"));
        arrayList.add(new NotificationDs("2","10-09-2023", "Thông báo về abc hihi"));
        arrayList.add(new NotificationDs("3","25-09-2023", "Thông báo về mm xyz"));
        arrayList.add(new NotificationDs("4","2-09-2023", "Thông báo về mm nnnn"));
    }

    private void onView() {
        notificationAdapter = new NotificationAdapter(this, arrayList);

        activityNotificationAuctionBinding.rvResult.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        activityNotificationAuctionBinding.rvResult.setAdapter(notificationAdapter);
        NotificationItemDecoration itemDecoration = new NotificationItemDecoration();

// Áp dụng itemDecoration chỉ cho item đầu tiên
        activityNotificationAuctionBinding.rvResult.addItemDecoration(itemDecoration);

        activityNotificationAuctionBinding.rvResult.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                    activityNotificationAuctionBinding.cardView.setVisibility(View.VISIBLE);
                    activityNotificationAuctionBinding.cardView2.setVisibility(View.GONE);
                } else if (sum > 20) {
                    activityNotificationAuctionBinding.cardView.setVisibility(View.GONE);
                    activityNotificationAuctionBinding.cardView2.setVisibility(View.VISIBLE);
                    onChangeCardView2();
                    LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int firstVisibleItemPosition = layoutManager.findFirstCompletelyVisibleItemPosition();
                    if (recyclerView.getChildAt(2).getTop() > activityNotificationAuctionBinding.cardView2.getBottom() + 100
                            && firstVisibleItemPosition == 1 && dy > 0
                    ) {
                        recyclerView.smoothScrollBy(0, 50);
                    }
                }
            }
        });
    }
    private void onChangeCardView2() {
        activityNotificationAuctionBinding.cardView21.setText(activityNotificationAuctionBinding.content.getText().toString().trim());
        activityNotificationAuctionBinding.cardView22.setText(
                activityNotificationAuctionBinding.textChooseStartDate.getText().toString() + "  " +
                        activityNotificationAuctionBinding.textChooseEndDate.getText().toString());
    }
}