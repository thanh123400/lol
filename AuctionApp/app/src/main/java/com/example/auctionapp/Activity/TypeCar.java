package com.example.auctionapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;

import com.example.auctionapp.R;
import com.example.auctionapp.databinding.ActivityTypeCarBinding;

public class TypeCar extends AppCompatActivity {
    ActivityTypeCarBinding activityTypeCarBinding;
    private String typeCar = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTypeCarBinding = DataBindingUtil.setContentView(this, R.layout.activity_type_car);
        setContentView(activityTypeCarBinding.getRoot());
        getSupportActionBar().setTitle(R.string.typeCar_header);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.lavender)));
        onSelected();
        onSubmit();
    }

    private void onSubmit() {
        activityTypeCarBinding.btnTypeCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultTypeCar = new Intent();
                resultTypeCar.putExtra("resultTypeCar", typeCar);
                setResult(RESULT_OK, resultTypeCar);
                finish();
            }
        });
    }

    private void onSelected() {
        Intent typeCarFromHomePage = getIntent();
        typeCar = typeCarFromHomePage.getStringExtra("resultTypeCar");
//        Log.d("787", "onSelected: " + typeCar);
        if (typeCar != "") {
            if (typeCar.equals(activityTypeCarBinding.radioButton1.getText().toString())) {
                activityTypeCarBinding.radioButton1.setChecked(true);
            }
            else  if (typeCar.equals(activityTypeCarBinding.radioButton2.getText().toString())) {
                activityTypeCarBinding.radioButton2.setChecked(true);
            }
            else  if (typeCar.equals(activityTypeCarBinding.radioButton3.getText().toString())) {
                activityTypeCarBinding.radioButton3.setChecked(true);
            }
            else  if (typeCar.equals(activityTypeCarBinding.radioButton4.getText().toString())) {
                activityTypeCarBinding.radioButton4.setChecked(true);
            }
        }
        activityTypeCarBinding.listChooseTypeCar.setOnCheckedChangeListener((radioGroup, i) -> {
            if (i == activityTypeCarBinding.radioButton1.getId()) {
                typeCar = activityTypeCarBinding.radioButton1.getText().toString();
            }
            else  if (i == activityTypeCarBinding.radioButton2.getId()) {
                typeCar = activityTypeCarBinding.radioButton2.getText().toString();
            }
            else  if (i == activityTypeCarBinding.radioButton3.getId()) {
                typeCar = activityTypeCarBinding.radioButton3.getText().toString();
            }
            else  if (i == activityTypeCarBinding.radioButton4.getId()) {
                typeCar = activityTypeCarBinding.radioButton4.getText().toString();
            }
        });
    }
}