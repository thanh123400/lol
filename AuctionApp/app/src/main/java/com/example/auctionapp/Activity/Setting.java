package com.example.auctionapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.auctionapp.R;
import com.example.auctionapp.databinding.ActivitySettingBinding;



public class Setting extends AppCompatActivity {

    ActivitySettingBinding activitySettingBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySettingBinding = DataBindingUtil.setContentView(this, R.layout.activity_setting);
        setContentView(activitySettingBinding.getRoot());
        getSupportActionBar().setTitle(R.string.setting_header);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.lavender)));

    }
    public void onSwitchDarkMode(View view) {
        if (activitySettingBinding.btnDarkMode.isChecked()) {
            activitySettingBinding.textMode.setText("Chế độ ban ngày");
            activitySettingBinding.nightModeIcon.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.light_mode));
        }
        else {
            activitySettingBinding.textMode.setText("Chế độ bóng tối");
            activitySettingBinding.nightModeIcon.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.night_mode));
        }
    }
    public void onSwitchNotification(View view) {
        if (activitySettingBinding.btnNotification.isChecked()) {
            activitySettingBinding.textNotification.setText("Tắt nhận thông báo");
            activitySettingBinding.notificationIcon.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.on_notification));
        }
        else {
            activitySettingBinding.textNotification.setText("Bật nhận thông báo");
            activitySettingBinding.notificationIcon.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.notification));
        }
    }
    public void onAboutLanguage(View view) {
        Intent language = new Intent(this, AboutLanguage.class);
        startActivity(language);
    }
    public void onAboutUs(View view) {
        Intent language = new Intent(this, AboutUs.class);
        startActivity(language);
    }
    public void onAboutMessage(View view) {
        Intent language = new Intent(this, AboutMessage.class);
        startActivity(language);
    }
}