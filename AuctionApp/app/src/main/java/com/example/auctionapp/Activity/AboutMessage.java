package com.example.auctionapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.auctionapp.R;
import com.example.auctionapp.databinding.ActivityAboutMessageBinding;

public class AboutMessage extends AppCompatActivity {
    ActivityAboutMessageBinding activityAboutMessageBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAboutMessageBinding = DataBindingUtil.setContentView(this, R.layout.activity_about_message);
        setContentView(activityAboutMessageBinding.getRoot());
        getSupportActionBar().setTitle(R.string.about_message_header);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.lavender)));
        activityAboutMessageBinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Bạn đã gửi thành công, hãy chờ phản hồi", Toast.LENGTH_SHORT).show();
            }
        });
    }
}