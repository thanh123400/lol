package com.example.auctionapp.Activity;


import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.auctionapp.R;
import com.example.auctionapp.databinding.ActivityMainBinding;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mActivityMainBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setContentView(mActivityMainBinding.getRoot());
        Intent intent = getIntent();
        String roleUser = intent.getStringExtra("roleUser");
        if (roleUser == null) {
            startLogin();
        }
        else {
            isLogin();
        }
    }
    public void startLogin() {
        Intent openLogin = new Intent(getApplicationContext(), Login.class);
        openLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(openLogin);
    }
    public void isLogin() {
        Intent openHomePage = new Intent(getApplicationContext(), HomePage.class);
        openHomePage.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(openHomePage);
    }
}