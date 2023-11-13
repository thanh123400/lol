package com.example.auctionapp.Fragment.Register;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.auctionapp.Activity.RegisterActivity;
import com.example.auctionapp.Interface.OnItemClickListener;
import com.example.auctionapp.R;
import com.example.auctionapp.databinding.FragmentStep1Binding;


import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.regex.Pattern;

import com.android.volley.RequestQueue;

import org.json.JSONObject;

public class Step1 extends Fragment {
    FragmentStep1Binding binding;
    private OnItemClickListener mItemClickListener;

    private String API_SITE_KEY = "6LfcdIUoAAAAAMI1KP80vnAkB8-9MQMf7z6t9MmD";
    private String SECRET_KEY = "6LfcdIUoAAAAAMzV_7M2v_y7bBtKLzS49PQck3sa";
    RequestQueue queue;
    public int onChoose = 0;
    public String username = "";
    public String password = "";
    public boolean checkCaptcha = false;
    private boolean passwordVisible = false;
    private boolean rePasswordVisible = false;

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_step1, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        queue = Volley.newRequestQueue(requireContext().getApplicationContext());
        binding.radioTwoStep1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == binding.radio1.getId()) {
                    onChoose = 1;
                }
                if (checkedId == binding.radio2.getId()) {
                    onChoose = 2;
                }
            }
        });
        binding.editTextThreeStep1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String text = binding.editTextThreeStep1.getText().toString();
                    int state = checkAccountAccept(text);
                    //True account
                    if (state == 0) {
                        username = text;
                        GradientDrawable border = new GradientDrawable();
                        border.setStroke(3, ContextCompat.getColor(getContext(), R.color.green));
                        border.setCornerRadius(getResources().getDimensionPixelSize(R.dimen.corner_radius));
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                            binding.editTextThreeStep1.setBackgroundDrawable(border);
                        } else {
                            binding.editTextThreeStep1.setBackground(border);
                        }
                        binding.hintThreeStep1.setHint(getResources().getString(R.string.register_hint_1_1_0));
                        binding.hintThreeStep1.setHintTextColor(ContextCompat.getColor(getContext(), R.color.green));
                    }
                    //Number <= 5
                    else if (state == 1) {
                        GradientDrawable border = new GradientDrawable();
                        border.setStroke(3, ContextCompat.getColor(getContext(), R.color.red));
                        border.setCornerRadius(getResources().getDimensionPixelSize(R.dimen.corner_radius));
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                            binding.editTextThreeStep1.setBackgroundDrawable(border);
                        } else {
                            binding.editTextThreeStep1.setBackground(border);
                        }
                        binding.hintThreeStep1.setHint(getResources().getString(R.string.register_hint_1_1_1));
                        binding.hintThreeStep1.setHintTextColor(ContextCompat.getColor(getContext(), R.color.red));
                    }
                    //Not valid
                    else if (state == 2) {
                        GradientDrawable border = new GradientDrawable();
                        border.setStroke(3, ContextCompat.getColor(getContext(), R.color.red));
                        border.setCornerRadius(getResources().getDimensionPixelSize(R.dimen.corner_radius));
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                            binding.editTextThreeStep1.setBackgroundDrawable(border);
                        } else {
                            binding.editTextThreeStep1.setBackground(border);
                        }
                        binding.hintThreeStep1.setHint(getResources().getString(R.string.register_hint_1_1_2));
                        binding.hintThreeStep1.setHintTextColor(ContextCompat.getColor(getContext(), R.color.red));
                    } else if (state == 3) {
                        GradientDrawable border = new GradientDrawable();
                        border.setStroke(3, ContextCompat.getColor(getContext(), R.color.red));
                        border.setCornerRadius(getResources().getDimensionPixelSize(R.dimen.corner_radius));
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                            binding.editTextThreeStep1.setBackgroundDrawable(border);
                        } else {
                            binding.editTextThreeStep1.setBackground(border);
                        }
                        binding.hintThreeStep1.setHint(getResources().getString(R.string.register_hint_1_1_3));
                        binding.hintThreeStep1.setHintTextColor(ContextCompat.getColor(getContext(), R.color.red));

                    }
                } else {
                    GradientDrawable border = new GradientDrawable();
                    border.setStroke(5, ContextCompat.getColor(getContext(), R.color.forgot));
                    border.setCornerRadius(getResources().getDimensionPixelSize(R.dimen.corner_radius));
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                        binding.editTextThreeStep1.setBackgroundDrawable(border);
                    } else {
                        binding.editTextThreeStep1.setBackground(border);
                    }
                    binding.hintThreeStep1.setHint(getResources().getString(R.string.register_hint_1_1_default));
                    binding.hintThreeStep1.setHintTextColor(ContextCompat.getColor(getContext(), R.color.gray));
                }
            }
        });

        binding.passwordEditTextThreeStep1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String text = binding.passwordEditTextThreeStep1.getText().toString();
                    int state = checkPasswordAccept(text);
                    //True password
                    if (state == 0) {
                        GradientDrawable border = new GradientDrawable();
                        border.setStroke(3, ContextCompat.getColor(getContext(), R.color.green));
                        border.setCornerRadius(getResources().getDimensionPixelSize(R.dimen.corner_radius));
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                            binding.passwordEditTextThreeStep1.setBackgroundDrawable(border);
                        } else {
                            binding.passwordEditTextThreeStep1.setBackground(border);
                        }
                        binding.passwordHintThreeStep1.setHint(getResources().getString(R.string.register_hint_2_1_0));
                        binding.passwordHintThreeStep1.setHintTextColor(ContextCompat.getColor(getContext(), R.color.green));
                    }
                    //Not valid
                    else if (state == 1) {
                        GradientDrawable border = new GradientDrawable();
                        border.setStroke(3, ContextCompat.getColor(getContext(), R.color.red));
                        border.setCornerRadius(getResources().getDimensionPixelSize(R.dimen.corner_radius));
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                            binding.passwordEditTextThreeStep1.setBackgroundDrawable(border);
                        } else {
                            binding.passwordEditTextThreeStep1.setBackground(border);
                        }
                        binding.passwordHintThreeStep1.setHint(getResources().getString(R.string.register_hint_2_1_1));
                        binding.passwordHintThreeStep1.setHintTextColor(ContextCompat.getColor(getContext(), R.color.red));
                    }
                } else {
                    GradientDrawable border = new GradientDrawable();
                    border.setStroke(5, ContextCompat.getColor(getContext(), R.color.forgot));
                    border.setCornerRadius(getResources().getDimensionPixelSize(R.dimen.corner_radius));
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                        binding.passwordEditTextThreeStep1.setBackgroundDrawable(border);
                    } else {
                        binding.passwordEditTextThreeStep1.setBackground(border);
                    }
                    binding.passwordHintThreeStep1.setHint(getResources().getString(R.string.register_hint_2_1_default));
                    binding.passwordHintThreeStep1.setHintTextColor(ContextCompat.getColor(getContext(), R.color.gray));
                }
            }

        });

        binding.rePasswordEditTextThreeStep1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String text = binding.rePasswordEditTextThreeStep1.getText().toString();
                String res = binding.passwordEditTextThreeStep1.getText().toString();
                int state = checkRePasswordAccept(text, res);
                if (!hasFocus) {
                    //Ok !
                    if (state == 0) {
                        password = res;
                        GradientDrawable border = new GradientDrawable();
                        border.setStroke(3, ContextCompat.getColor(getContext(), R.color.green));
                        border.setCornerRadius(getResources().getDimensionPixelSize(R.dimen.corner_radius));
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                            binding.rePasswordEditTextThreeStep1.setBackgroundDrawable(border);
                        } else {
                            binding.rePasswordEditTextThreeStep1.setBackground(border);
                        }
                        binding.rePasswordHintThreeStep1.setHint(getResources().getString(R.string.register_hint_3_1_0));
                        binding.rePasswordHintThreeStep1.setHintTextColor(ContextCompat.getColor(getContext(), R.color.green));
                    }
                    //Not same
                    else if (state == 1) {
                        GradientDrawable border = new GradientDrawable();
                        border.setStroke(3, ContextCompat.getColor(getContext(), R.color.red));
                        border.setCornerRadius(getResources().getDimensionPixelSize(R.dimen.corner_radius));
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                            binding.rePasswordEditTextThreeStep1.setBackgroundDrawable(border);
                        } else {
                            binding.rePasswordEditTextThreeStep1.setBackground(border);
                        }
                        binding.rePasswordHintThreeStep1.setHint(getResources().getString(R.string.register_hint_3_1_1));
                        binding.rePasswordHintThreeStep1.setHintTextColor(ContextCompat.getColor(getContext(), R.color.red));
                    }
                } else {
                    GradientDrawable border = new GradientDrawable();
                    border.setStroke(5, ContextCompat.getColor(getContext(), R.color.forgot));
                    border.setCornerRadius(getResources().getDimensionPixelSize(R.dimen.corner_radius));
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                        binding.rePasswordEditTextThreeStep1.setBackgroundDrawable(border);
                    } else {
                        binding.rePasswordEditTextThreeStep1.setBackground(border);
                    }
                    binding.rePasswordHintThreeStep1.setHint(getResources().getString(R.string.register_hint_3_1_default));
                    binding.rePasswordHintThreeStep1.setHintTextColor(ContextCompat.getColor(getContext(), R.color.gray));
                }
            }

        });

        binding.passwordEditTextThreeStep1.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right = 2;
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= binding.passwordEditTextThreeStep1.getRight() - binding.passwordEditTextThreeStep1.getCompoundDrawables()[Right].getBounds().width()) {
                        int selection = binding.passwordEditTextThreeStep1.getSelectionEnd();
                        if (passwordVisible) {
                            binding.passwordEditTextThreeStep1.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.hidden_password, 0);
                            binding.passwordEditTextThreeStep1.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        } else {
                            binding.passwordEditTextThreeStep1.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.show_password, 0);
                            binding.passwordEditTextThreeStep1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        }
                        passwordVisible = !passwordVisible;
                        binding.passwordEditTextThreeStep1.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });

        binding.rePasswordEditTextThreeStep1.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right = 2;
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= binding.rePasswordEditTextThreeStep1.getRight() - binding.rePasswordEditTextThreeStep1.getCompoundDrawables()[Right].getBounds().width()) {
                        int selection = binding.rePasswordEditTextThreeStep1.getSelectionEnd();
                        if (rePasswordVisible) {
                            binding.rePasswordEditTextThreeStep1.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.hidden_password, 0);
                            binding.rePasswordEditTextThreeStep1.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        } else {
                            binding.rePasswordEditTextThreeStep1.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.show_password, 0);
                            binding.rePasswordEditTextThreeStep1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        }
                        rePasswordVisible = !rePasswordVisible;
                        binding.rePasswordEditTextThreeStep1.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });

        binding.checkboxNotRobot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkCaptcha) {
                    SafetyNet.getClient(requireContext()).verifyWithRecaptcha(API_SITE_KEY)
                            .addOnSuccessListener(requireActivity(), new OnSuccessListener<SafetyNetApi.RecaptchaTokenResponse>() {
                                @Override
                                public void onSuccess(SafetyNetApi.RecaptchaTokenResponse response) {
                                    // Indicates communication with reCAPTCHA service was successful.
                                    String userResponseToken = response.getTokenResult();

                                    if (!userResponseToken.isEmpty()) {
                                        handleSiteVerify(response.getTokenResult());
                                    }
                                }
                            })
                            .addOnFailureListener(requireActivity(), new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    if (e instanceof ApiException) {

                                        ApiException apiException = (ApiException) e;
                                        Log.d("??", "Error message: " +
                                                CommonStatusCodes.getStatusCodeString(apiException.getStatusCode()));
                                    } else {
                                        Log.d("??", "Unknown type of error: " + e.getMessage());
                                    }
                                }
                            });
                }
            }
        });
        binding.sevenStep1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int state = checkSubmit();
//                if (state == 0) {
//                    Toast.makeText(requireContext().getApplicationContext(), "Vui lòng chọn loại tài khoản", Toast.LENGTH_SHORT).show();
//                }
//                else if (state == 1){
//                    Toast.makeText(requireContext().getApplicationContext(), "Nhập lại tên tài khoản phù hợp", Toast.LENGTH_SHORT).show();
//                }
//                else if (state == 2) {
//                    Toast.makeText(requireContext().getApplicationContext(), "Nhập chính xác mật khẩu đăng ký", Toast.LENGTH_SHORT).show();
//                }
//                else if (state == 3) {
//                    Toast.makeText(requireContext().getApplicationContext(), "Verify captcha again", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    Bundle bundle = new Bundle();
//                    bundle.putString("username", username);
//                    bundle.putString("password", password);
//                    bundle.putInt("role", onChoose);
//                    if (mItemClickListener != null) {
//                        mItemClickListener.onItemClick(2, bundle);
//                    }
//                }
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(2, new Bundle());
                }
            }
        });
    }

    public int checkSubmit() {
        if (onChoose == 0) {
            return 0;
        }
        if (username.length() == 0 || !username.equals(binding.editTextThreeStep1.getText().toString())) {
            return 1;
        }
        if (password.length() == 0 || !password.equals(binding.passwordEditTextThreeStep1.getText().toString())) {
            return 2;
        }
        if (!checkCaptcha) {
            return 3;
        }
        return 4;
    }
    public int checkPasswordAccept(String text) {
        if (text.length() <= 5) {
            return 1;
        }
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).+$";
        Pattern pattern = Pattern.compile(regex);
        if (!pattern.matcher(text).matches()) {
            return 1;
        }
        return 0;
    }

    public int checkRePasswordAccept(String text, String res) {
        if (!text.equals(res)) {
            return 1;
        }
        return 0;
    }

    public int checkAccountAccept(String text) {
        if (text.length() <= 5) {
            return 1;
        }
        for (char c : text.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                return 2;
            }
        }
        return 0;
    }

    protected void handleSiteVerify(final String responseToken) {
        String url = "https://www.google.com/recaptcha/api/siteverify";
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getBoolean("success")) {
                                checkCaptcha = true;

                                binding.checkboxNotRobot.setButtonTintMode(PorterDuff.Mode.SRC_ATOP);
                                binding.checkboxNotRobot.setButtonTintList(ContextCompat.getColorStateList(getContext(), R.color.green));
                                binding.checkboxNotRobot.setChecked(true);
//                                Toast.makeText(requireActivity().getApplicationContext(),String.valueOf(jsonObject.getBoolean("success")),Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(requireActivity().getApplicationContext(), String.valueOf(jsonObject.getString("error-codes")), Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("secret", SECRET_KEY);
                params.put("response", responseToken);
                return params;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);
    }
}