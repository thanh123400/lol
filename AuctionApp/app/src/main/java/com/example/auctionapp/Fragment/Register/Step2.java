package com.example.auctionapp.Fragment.Register;

import android.app.DatePickerDialog;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.auctionapp.Interface.OnItemClickListener;
import com.example.auctionapp.R;
import com.example.auctionapp.databinding.FragmentStep2Binding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;


public class Step2 extends Fragment {

    private OnItemClickListener mItemClickListener;
    FragmentStep2Binding binding;
    private FirebaseAuth firebaseAuth;
    private String authVerificationId = "";
    private boolean checkVerify = false;

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    public int gender = 0;
    String name = "";
    String phoneNumber = "";
    public void setOnItemClickListener(OnItemClickListener listener) {
        mItemClickListener = listener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_step2, container, false);
        firebaseAuth = FirebaseAuth.getInstance();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.nameTwoStep2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String text = binding.nameTwoStep2.getText().toString().trim();
                    boolean state = checkNameAccept(text);
                    GradientDrawable border = new GradientDrawable();
                    if (state) {
                        name = text;
                        border.setStroke(3, ContextCompat.getColor(getContext(), R.color.green));
                        border.setCornerRadius(getResources().getDimensionPixelSize(R.dimen.corner_radius));
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                            binding.nameTwoStep2.setBackgroundDrawable(border);
                        } else {
                            binding.nameTwoStep2.setBackground(border);
                        }
                        binding.hintNameTwoStep2.setHint(getResources().getString(R.string.register_hint_1_2_0));
                        binding.hintNameTwoStep2.setHintTextColor(ContextCompat.getColor(getContext(), R.color.green));
                    } else {
                        border.setStroke(3, ContextCompat.getColor(getContext(), R.color.red));
                        border.setCornerRadius(getResources().getDimensionPixelSize(R.dimen.corner_radius));
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                            binding.nameTwoStep2.setBackgroundDrawable(border);
                        } else {
                            binding.nameTwoStep2.setBackground(border);
                        }
                        binding.hintNameTwoStep2.setHint(getResources().getString(R.string.register_hint_1_2_1));
                        binding.hintNameTwoStep2.setHintTextColor(ContextCompat.getColor(getContext(), R.color.red));
                    }
                } else {
                    GradientDrawable border = new GradientDrawable();
                    border.setStroke(5, ContextCompat.getColor(getContext(), R.color.forgot));
                    border.setCornerRadius(getResources().getDimensionPixelSize(R.dimen.corner_radius));
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                        binding.nameTwoStep2.setBackgroundDrawable(border);
                    } else {
                        binding.nameTwoStep2.setBackground(border);
                    }
                    binding.hintNameTwoStep2.setHint(getResources().getString(R.string.register_hint_1_2_default));
                    binding.hintNameTwoStep2.setHintTextColor(ContextCompat.getColor(getContext(), R.color.gray));
                }
            }
        });
        binding.radioStep2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == binding.male.getId()) {
                    gender = 1;
                }
                if (checkedId == binding.female.getId()) {
                    gender = 2;
                }
            }
        });

        binding.localFourStep2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {

                }
                else {

                }
            }
        });
        binding.dateFiveStep2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                                binding.dateFiveStep2.setText(selectedDate);
                            }
                        },
                        year,
                        month,
                        day
                );

                datePickerDialog.show();
            }
        });
        binding.submitPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = binding.phoneSixStep2.getText().toString().trim();
                if (phone.length() == 9) {
                    otpSend();
                }
            }
        });

        binding.submitCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = binding.verify.getText().toString().trim();
                if (code != null && authVerificationId != "") {
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(authVerificationId, code);
                    FirebaseAuth
                            .getInstance()
                            .signInWithCredential(credential)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        checkVerify = true;
                                        binding.phoneSixStep2.setEnabled(false);
                                        binding.submitCode.setClickable(false);
                                        binding.verify.setClickable(false);
                                        phoneNumber = binding.phoneSixStep2.getText().toString().trim();
                                        Toast.makeText(requireContext().getApplicationContext(), "OTP success", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(requireContext().getApplicationContext(), "OTP not valid", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
        binding.sevenStep2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("name", name);
                bundle.putString("phone", phoneNumber);
                bundle.putInt("gender", gender);
                bundle.putString("date", binding.dateFiveStep2.getText().toString());
                bundle.putString("local", binding.localFourStep2.getText().toString());
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(3, bundle);
                }
            }
        });
    }

    public boolean checkNameAccept(String name) {
        String[] words = name.split(" ");
        if (words.length >= 2 && words.length <= 5) {
            for (String i : words) {
                char firstChar = i.charAt(0);
                String remainingChars = i.substring(1);
                if (!Character.isUpperCase(firstChar) || !remainingChars.equals(remainingChars.toLowerCase())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public void otpSend() {
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {

            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Toast.makeText(requireContext().getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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
                        .setPhoneNumber("+84" + binding.phoneSixStep2.getText().toString().trim())       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(requireActivity())                 // (optional) Activity for callback binding
                        // If no activity is passed, reCAPTCHA verification can not be used.
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
}