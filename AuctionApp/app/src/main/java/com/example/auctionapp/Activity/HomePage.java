package com.example.auctionapp.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.auctionapp.Adapter.homeAdapter;
import com.example.auctionapp.ItemEffect.TransparentItemDecoration;
import com.example.auctionapp.Model.BsCarHome;
import com.example.auctionapp.R;
import com.example.auctionapp.databinding.ActivityHomePageBinding;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {
    private ActivityHomePageBinding activityHomePageBinding;
    private static final int CODE_PROVINCE = 2209;
    private static final int CODE_TYPE_CAR = 2011;
    //UID lấy từ đăng nhập
    String UID = "";

    //Role lấy từ đăng nhập
    String role = "";
    homeAdapter homeAdapter;
    ArrayList<BsCarHome> arrayList = new ArrayList<>();
    int sum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityHomePageBinding = DataBindingUtil.setContentView(this, R.layout.activity_home_page);
        setContentView(activityHomePageBinding.getRoot());
        setSupportActionBar(activityHomePageBinding.toolbar);
        getSupportActionBar().setTitle(R.string.home_header);
        activityHomePageBinding.navView.bringToFront();
        activityHomePageBinding.navView.setItemIconTintList(null);

        ActionBarDrawerToggle toggle = new
                ActionBarDrawerToggle(this, activityHomePageBinding.drawerLayout
                , activityHomePageBinding.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        activityHomePageBinding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        activityHomePageBinding.navView.setNavigationItemSelectedListener(this::onNavigationItemSelected);
//        activityHomePageBinding.navView.setCheckedItem(R.id.nav_home);
        addTest();
        setItem();
        chooseProvider();
        chooseTypeCar();
        searchHome();
        loadMore();
    }

    private void loadMore() {
        activityHomePageBinding.fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Load thêm 25 dữ liệu khi click
            }
        });
    }

    private void searchHome() {
        activityHomePageBinding.searchHome.setOnClickListener(view -> {
            String nameIdCar = activityHomePageBinding.F2.getText().toString().trim();
            String province = activityHomePageBinding.textChooseProvince.getText().toString();
            String typeCar = activityHomePageBinding.textChooseTypeCar.getText().toString();
            nameIdCar = nameIdCar.equals("")? "All" : nameIdCar;
            province = province.equals("")? "All" : province;
            typeCar = typeCar.equals("")? "All" : typeCar;
            //Truy Vấn Database

        });
        activityHomePageBinding.showAllSearch.setOnClickListener(view -> {
            activityHomePageBinding.cardView.setVisibility(View.VISIBLE);
            activityHomePageBinding.cardView2.setVisibility(View.GONE);
        });
    }

    private void chooseTypeCar() {
        activityHomePageBinding.chooseTypeCar.setOnClickListener(view -> {
            Intent chooseTypeCar = new Intent(getApplicationContext(), TypeCar.class);
            chooseTypeCar.putExtra("resultTypeCar", activityHomePageBinding.textChooseTypeCar.getText().toString());
            startActivityForResult(chooseTypeCar, CODE_TYPE_CAR);
        });
    }

    private void chooseProvider() {
        activityHomePageBinding.chooseProvince.setOnClickListener(view -> {
            Intent chooseProvider = new Intent(getApplicationContext(), Province.class);
            startActivityForResult(chooseProvider, CODE_PROVINCE);
        });
    }

    private void setItem() {
        homeAdapter = new homeAdapter(arrayList, this);

        activityHomePageBinding.rvHome.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        activityHomePageBinding.rvHome.setAdapter(homeAdapter);
        TransparentItemDecoration itemDecoration = new TransparentItemDecoration();

// Áp dụng itemDecoration chỉ cho item đầu tiên
        activityHomePageBinding.rvHome.addItemDecoration(itemDecoration);

        activityHomePageBinding.rvHome.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                    activityHomePageBinding.cardView.setVisibility(View.VISIBLE);
                    activityHomePageBinding.cardView2.setVisibility(View.GONE);
                } else if (sum > 20) {
                    activityHomePageBinding.cardView.setVisibility(View.GONE);
                    activityHomePageBinding.cardView2.setVisibility(View.VISIBLE);
                    onChangeCardView2();
                    LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int firstVisibleItemPosition = layoutManager.findFirstCompletelyVisibleItemPosition();
                    if (recyclerView.getChildAt(1).getTop() > activityHomePageBinding.cardView2.getBottom() + 100
                            && firstVisibleItemPosition == 1 && dy > 0
                    ) {
                        recyclerView.smoothScrollBy(0, 50);
                    }
//                    Log.d("HH", "hihi: " + recyclerView.getChildAt(1).getTop() + " " + sum);
                }
            }
        });
    }

    private void onChangeCardView2() {
        activityHomePageBinding.cardView21.setText(activityHomePageBinding.F2.getText().toString().trim());
        activityHomePageBinding.cardView22.setText(
                activityHomePageBinding.textChooseProvince.getText().toString() + "  " +
                        activityHomePageBinding.textChooseTypeCar.getText().toString());
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        if (menuItem.getItemId() == R.id.s1) {
        } else if (menuItem.getItemId() == R.id.s2) {
            Intent intent = new Intent(getApplicationContext(), CMAuction.class);
            startActivity(intent);
        } else if (menuItem.getItemId() == R.id.s3) {
            Intent intent = new Intent(getApplicationContext(), UserView.class);
            startActivity(intent);
        }
        else if (menuItem.getItemId() == R.id.s4) {
            Intent intent = new Intent(getApplicationContext(), ResultAuction.class);
            startActivity(intent);
        }
        else if (menuItem.getItemId() == R.id.s5) {
            Intent intent = new Intent(getApplicationContext(), NotificationAuction.class);
            startActivity(intent);
        }
        //Profile có 2 loại dựa trên 2 loại role
        else if (menuItem.getItemId() == R.id.s6) {
            Intent intent = new Intent(getApplicationContext(), Profile.class);
            intent.putExtra("UID", UID);
            intent.putExtra("role", role);
            startActivity(intent);
        }
        //Cài đặt
        else if (menuItem.getItemId() == R.id.s7) {
            Intent intent = new Intent(getApplicationContext(), Setting.class);
            startActivity(intent);
        }
        //Đăng xuất
        else if (menuItem.getItemId() == R.id.s8) {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        activityHomePageBinding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == CODE_PROVINCE) {
            String resultProvince = data.getStringExtra("resultProvince");
//            Log.d("222", "onActivityResult: " + resultProvince);
            activityHomePageBinding.textChooseProvince.setText(resultProvince);
        }
        if (resultCode == RESULT_OK && requestCode == CODE_TYPE_CAR) {
            String resultTypeCar = data.getStringExtra("resultTypeCar");
            activityHomePageBinding.textChooseTypeCar.setText(resultTypeCar);
        }
    }
    private void addTest() {
        //Truy vấn database
        arrayList.add(new BsCarHome("30K-555.55", "Hà Nội", "Xe Con", 10));
        arrayList.add(new BsCarHome("98A-666.66", "Bắc Giang", "Xe Con", 100));
        arrayList.add(new BsCarHome("36A-999.99", "Thanh Hóa", "Xe Con", 10));
        arrayList.add(new BsCarHome("30K-567.89", "Hà Nội", "Xe Con", 10));
        arrayList.add(new BsCarHome("47A-599.99", "Đắk Lắk", "Xe Con", 1000));
        arrayList.add(new BsCarHome("30K-555.55", "Hà Nội", "Xe Con", 10));
        arrayList.add(new BsCarHome("98A-666.66", "Bắc Giang", "Xe Con", 100));
        arrayList.add(new BsCarHome("36A-999.99", "Thanh Hóa", "Xe Con", 10));
        arrayList.add(new BsCarHome("30K-567.89", "Hà Nội", "Xe Con", 10));
        arrayList.add(new BsCarHome("47A-599.99", "Đắk Lắk", "Xe Con", 1000));
        arrayList.add(new BsCarHome("30K-555.55", "Hà Nội", "Xe Con", 10));
        arrayList.add(new BsCarHome("98A-666.66", "Bắc Giang", "Xe Con", 100));
        arrayList.add(new BsCarHome("36A-999.99", "Thanh Hóa", "Xe Con", 10));
        arrayList.add(new BsCarHome("30K-567.89", "Hà Nội", "Xe Con", 10));
        arrayList.add(new BsCarHome("47A-599.99", "Đắk Lắk", "Xe Con", 1000));
    }
}