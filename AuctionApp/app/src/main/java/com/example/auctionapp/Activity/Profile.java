package com.example.auctionapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.auctionapp.Model.UserInfor;
import com.example.auctionapp.R;
import com.example.auctionapp.databinding.ActivityProfileBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Profile extends AppCompatActivity {
    ActivityProfileBinding activityProfileBinding;
    String UID = "";

    //Role lấy từ đăng nhập
    String role = "CaNhan";
    UserInfor dataUser;
    String Gender;

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private String authVerificationId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityProfileBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        setContentView(activityProfileBinding.getRoot());
        getSupportActionBar().setTitle(R.string.change_profile_header);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.lavender)));
        //getIntent
        Intent intent = getIntent();
        if (intent != null) {
            UID = intent.getStringExtra("UID");
            role = intent.getStringExtra("role");
        }
        setUp(role);
        getDataFrom(UID);
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
//        Log.d("Profile", "onCreate: " + imm );
        activityProfileBinding.getRoot().setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                activityProfileBinding.textChangePassword.clearFocus();
                activityProfileBinding.textChangeEmail.clearFocus();
                activityProfileBinding.textChangeLocation.clearFocus();
                activityProfileBinding.textPhone.clearFocus();
                activityProfileBinding.textPhoneKey.clearFocus();
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
            return true;
        });
        passWordChange();
        emailChange();
        locationChange();

        birthChange();
        genderChange();
        CCCD();
        dateCCCD();
        placeCCCD();

        phoneChange();

        submit();
    }




    private void submit() {
        activityProfileBinding.submit.setOnClickListener(v -> {
            if (!dataUser.getPassword().equals(activityProfileBinding.textChangePassword.getText().toString())) {
                //
            }
            if (!dataUser.getEmail().equals(activityProfileBinding.textChangeEmail.getText().toString())) {
                //
            }
            if (!dataUser.getLocation().equals(activityProfileBinding.textChangeLocation.getText().toString())) {
                //
            }
            if (!dataUser.getBirth().equals(activityProfileBinding.changeBirth.getText().toString())) {
                //
            }

            if (!dataUser.getGender().equals(Gender)) {
                //
            }

            // Cập nhật database

            Intent complete = new Intent(this, HomePage.class);
            complete.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(complete);
        });
    }

    private void placeCCCD() {
        activityProfileBinding.textUIdPlace.setText(dataUser.getPlaceUID());
    }

    private void dateCCCD() {
        activityProfileBinding.textUIdDate.setText(dataUser.getDateCreateUID());
    }

    private void CCCD() {
        activityProfileBinding.textUId.setText(dataUser.getuId());
    }

    private void genderChange() {
        Gender = dataUser.getGender();
        if (Gender.equals("Male")) {
            activityProfileBinding.male.setChecked(true);
        }
        else {
            activityProfileBinding.female.setChecked(true);
        }
        activityProfileBinding.changeGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == activityProfileBinding.male.getId()) {
                    Gender = "Male";
                }
                else  if (checkedId == activityProfileBinding.female.getId()) {
                    Gender = "Female";
                }
            }
        });
    }

    private void birthChange() {
        activityProfileBinding.changeBirth.setText(dataUser.getBirth());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        activityProfileBinding.changeBirth.setOnClickListener(v -> {
            try {
                Date date = dateFormat.parse(activityProfileBinding.changeBirth.getText().toString());
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);

                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(Profile.this,
                        (view, year1, monthOfYear, dayOfMonth) -> {
                            String selectedDate = year1 + "-" + (monthOfYear + 1) + "-" + dayOfMonth;

                            activityProfileBinding.changeBirth.setText(selectedDate);
                        },
                        year,
                        month,
                        day
                );

                datePickerDialog.show();

            } catch (ParseException e) {
                e.printStackTrace();
            }
        });



    }

    private void locationChange() {
        activityProfileBinding.textChangeLocation.setText(dataUser.getLocation());
    }

    private void emailChange() {
        activityProfileBinding.textChangeEmail.setText(dataUser.getEmail());
    }

    private void passWordChange() {
        activityProfileBinding.textChangePassword.setText(dataUser.getPassword());
    }

    //Thay đổi hiện thị với role khác nhau
    private void setUp(String role) {
        if (role.equals("CaNhan")) {

        }
        else {

        }
    }

    private void getDataFrom(String UID) {
        //Truy vấn database kiểu dữ liệu trả vể là một UserInfor
        dataUser = new UserInfor("Th2209@", "t@gmail.com", "Viet Nam", "2003-09-22", "Male", "333333",
                "2003-09-22", "Viet Nam", "999999");
    }
    private void phoneChange() {
        activityProfileBinding.submitPhone.setOnClickListener(v -> {
            String phoneNumber = activityProfileBinding.textPhone.getText().toString();
            if (phoneNumber.length() == 9) {
                otpSend();
            }

        });
        activityProfileBinding.submitPhoneKey.setOnClickListener(v -> {
            String code = activityProfileBinding.textPhoneKey.getText().toString().trim();
            if (code != null && authVerificationId != "") {
                otpCheck(code);
            }
        });
    }
    public void otpSend() {
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {

            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                //verificationId ->
                authVerificationId = verificationId;

            }
        };
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(firebaseAuth)
                        .setPhoneNumber("+84" + activityProfileBinding.textPhone.getText().toString().trim())       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // (optional) Activity for callback binding
                        // If no activity is passed, reCAPTCHA verification can not be used.
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
    public void otpCheck(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(authVerificationId, code);
        FirebaseAuth
                .getInstance()
                .signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            activityProfileBinding.textPhone.setEnabled(false);
                            activityProfileBinding.submitPhone.setClickable(false);
                            activityProfileBinding.submitPhoneKey.setClickable(false);
                            Toast.makeText(getApplicationContext(), "OTP success", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "OTP not valid", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}