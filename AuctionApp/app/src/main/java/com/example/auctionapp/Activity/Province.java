package com.example.auctionapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.auctionapp.Adapter.provinceAdapter;
import com.example.auctionapp.ItemEffect.ProvinceItemDecoration;
import com.example.auctionapp.R;
import com.example.auctionapp.databinding.ActivityProvinceBinding;

import java.util.ArrayList;
import java.util.List;

public class Province extends AppCompatActivity {
    ActivityProvinceBinding activityProvinceBinding;
    provinceAdapter provinceAdapter;
    ArrayList<String> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityProvinceBinding = DataBindingUtil.setContentView(this, R.layout.activity_province);
        setContentView(activityProvinceBinding.getRoot());
        getSupportActionBar().setTitle(R.string.province_header);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.lavender)));
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        supportArrayList();
        setItem();
        onResult();
    }

    private void onResult() {
        activityProvinceBinding.btnProvince.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultProvince = new Intent();
//                Log.d("113", "onClick: " + provinceAdapter.getOnChoose());
                resultProvince.putExtra("resultProvince", provinceAdapter.getOnChoose());
                setResult(RESULT_OK, resultProvince);
                finish();
            }
        });
    }



    private void setItem() {
        activityProvinceBinding.rvProvince.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        provinceAdapter = new provinceAdapter(arrayList);
        activityProvinceBinding.rvProvince.setAdapter(provinceAdapter);
        activityProvinceBinding.rvProvince.addItemDecoration(new ProvinceItemDecoration(arrayList.size() - 1));

    }
    private void supportArrayList() {
        arrayList.add("Hà Nội");
        arrayList.add("Hồ Chí Minh");
        arrayList.add("Hải Phòng");
        arrayList.add("Đà Nẵng");
        arrayList.add("Cần Thơ");
        arrayList.add("An Giang");
        arrayList.add("Bà Rịa - Vũng Tàu");
        arrayList.add("Bắc Giang");
        arrayList.add("Bắc Kạn");
        arrayList.add("Bạc Liêu");
        arrayList.add("Bắc Ninh");
        arrayList.add("Bến Tre");
        arrayList.add("Bình Định");
        arrayList.add("Bình Dương");
        arrayList.add("Bình Phước");
        arrayList.add("Bình Thuận");
        arrayList.add("Cà Mau");
        arrayList.add("Cao Bằng");
        arrayList.add("Đắk Lắk");
        arrayList.add("Đắk Nông");
        arrayList.add("Điện Biên");
        arrayList.add("Đồng Nai");
        arrayList.add("Đồng Tháp");
        arrayList.add("Gia Lai");
        arrayList.add("Hà Giang");
        arrayList.add("Hà Nam");
        arrayList.add("Hà Tĩnh");
        arrayList.add("Hải Dương");
        arrayList.add("Hậu Giang");
        arrayList.add("Hòa Bình");
        arrayList.add("Hưng Yên");
        arrayList.add("Khánh Hòa");
        arrayList.add("Kiên Giang");
        arrayList.add("Kon Tum");
        arrayList.add("Lai Châu");
        arrayList.add("Lâm Đồng");
        arrayList.add("Lạng Sơn");
        arrayList.add("Lào Cai");
        arrayList.add("Long An");
        arrayList.add("Nam Định");
        arrayList.add("Nghệ An");
        arrayList.add("Ninh Bình");
        arrayList.add("Ninh Thuận");
        arrayList.add("Phú Thọ");
        arrayList.add("Quảng Bình");
        arrayList.add("Quảng Nam");
        arrayList.add("Quảng Ngãi");
        arrayList.add("Quảng Ninh");
        arrayList.add("Quảng Trị");
        arrayList.add("Sóc Trăng");
        arrayList.add("Sơn La");
        arrayList.add("Tây Ninh");
        arrayList.add("Thái Bình");
        arrayList.add("Thái Nguyên");
        arrayList.add("Thanh Hóa");
        arrayList.add("Thừa Thiên-Huế");
        arrayList.add("Tiền Giang");
        arrayList.add("Trà Vinh");
        arrayList.add("Tuyên Quang");
        arrayList.add("Vĩnh Long");
        arrayList.add("Vĩnh Phúc");
        arrayList.add("Yên Bái");
        arrayList.add("");
    }

}