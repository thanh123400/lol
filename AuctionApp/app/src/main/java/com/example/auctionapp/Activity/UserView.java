package com.example.auctionapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.auctionapp.Fragment.User.Favourite;
import com.example.auctionapp.Fragment.User.Register;
import com.example.auctionapp.R;
import com.example.auctionapp.databinding.ActivityUserViewBinding;
import com.google.android.material.navigation.NavigationBarView;

public class UserView extends AppCompatActivity {
    ActivityUserViewBinding activityUserViewBinding;
    String UserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUserViewBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_view);
        setContentView(activityUserViewBinding.getRoot());
        Intent uIdBsCar = getIntent();
//        UserId = uIdBsCar.getStringExtra("uIdBsCar");
        UserId = "1111"; // Test
        getSupportActionBar().setTitle(R.string.user_view_header);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.lavender)));

        event();
    }

    private void event() {
        Fragment favouriteMenu = new Favourite();
        Intent intentFavourite = new Intent(UserView.this, Favourite.class);
        intentFavourite.putExtra("userId", UserId);
        favouriteMenu.setArguments(intentFavourite.getExtras());

        Fragment registerMenu = new Register();
        Intent intentRegister = new Intent(UserView.this, Register.class);
        intentRegister.putExtra("userId", UserId);
        registerMenu.setArguments(intentRegister.getExtras());

        replaceFragment(favouriteMenu);

        activityUserViewBinding.bottomNV.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.favouriteMenu) {
                replaceFragment(favouriteMenu);
            } else if (itemId == R.id.registerMenu) {
                replaceFragment(registerMenu);
            }
            return true;
        });
    }


    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.FL, fragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //updateDb
    }
}