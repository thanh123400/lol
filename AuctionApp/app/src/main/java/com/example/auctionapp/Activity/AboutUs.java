package com.example.auctionapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.example.auctionapp.R;
import com.example.auctionapp.databinding.ActivityAboutUsBinding;

public class AboutUs extends AppCompatActivity {
    ActivityAboutUsBinding activityAboutUsBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAboutUsBinding = DataBindingUtil.setContentView(this, R.layout.activity_about_us);
        setContentView(activityAboutUsBinding.getRoot());
        getSupportActionBar().setTitle(R.string.about_us_header);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.lavender)));
    }
}