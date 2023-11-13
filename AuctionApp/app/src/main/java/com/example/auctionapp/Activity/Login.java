package com.example.auctionapp.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.auctionapp.R;
import com.example.auctionapp.databinding.ActivityLoginBindingImpl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Login extends AppCompatActivity {

    private static final int SIGN_UP_REQUEST_CODE = 2;
    private ActivityLoginBindingImpl mLoginBinding;
    private boolean passwordVisible = false;
    Button login_google, login_facebook, submit;
    TextView create;

    AutoCompleteTextView account;
    EditText password;
    List<String> DataListAccount = new ArrayList<>();
    List<String> DataListPassword = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        setContentView(mLoginBinding.getRoot());

        submit = mLoginBinding.submit;
        account = mLoginBinding.account;
        password = mLoginBinding.password;
        login_google = mLoginBinding.loginGoogle;
        login_facebook = mLoginBinding.loginFacebook;
        create = mLoginBinding.create;

        supportAutoCompleteTextView();
//        settingLanguage();

        //Ẩn hiện password
        password.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right = 2;
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= password.getRight() - password.getCompoundDrawables()[Right].getBounds().width()) {
                        int selection = password.getSelectionEnd();
                        if (passwordVisible) {
                            password.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.hidden_password, 0);
                            password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        }
                        else {
                            password.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.show_password, 0);
                            password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        }
                        passwordVisible = !passwordVisible;
                        password.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });
        //
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Ẩn bàn phím ảo khi submit
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                account.clearFocus();
                password.clearFocus();
                //
                if (account.getText().toString().isEmpty()) {
                    Toast toast = new Toast(getApplicationContext());
                    LayoutInflater inflater = getLayoutInflater();
                    View toastView = inflater.inflate(R.layout.toast_1, null);
                    toast.setGravity(Gravity.BOTTOM, 0, 0);
                    toast.setView(toastView);
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.show();

                } else if (password.getText().toString().isEmpty()) {
                    Toast toast = new Toast(getApplicationContext());
                    LayoutInflater inflater = getLayoutInflater();
                    View toastView = inflater.inflate(R.layout.toast_2, null);
                    toast.setGravity(Gravity.BOTTOM, 0, 0);
                    toast.setView(toastView);
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.show();
                } else {

                    checkAuthPass(account.getText().toString(), password.getText().toString());

                    //XL
                    Gson gson = new Gson();
                    String jsonValueListAccount = gson.toJson(DataListAccount);
                    String jsonValueListPassword = gson.toJson(DataListPassword);

                    SharedPreferences sharedPreferences = getSharedPreferences("Accounts", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString("account", jsonValueListAccount);
                    editor.putString("password", jsonValueListPassword);

                    editor.apply();

                    String roleUser = "2";
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    intent.putExtra("roleUser", roleUser);
                    startActivity(intent);
                    finish();
                }
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCreateClicked();
            }
        });
    }

    private void settingLanguage() {
        Intent mainIntent = this.getIntent();
        String statusLanguage = mainIntent.getStringExtra("ChangeLanguage");
        if (statusLanguage == null || statusLanguage.equals("L1")) {
            applyLanguage();
            mainIntent.putExtra("ChangeLanguage", "L2");
            recreate();
        }
    }

    public void onCreateClicked() {
        Intent intentCreate = new Intent(this, RegisterActivity.class);
        startActivityForResult(intentCreate, SIGN_UP_REQUEST_CODE);
    }

    private void checkAuthPass(String account, String password) {
//        DataListAccount.add(account);
//        DataListPassword.add(password);
    }
    private void supportAutoCompleteTextView() {
        SharedPreferences sharedPreferences = getSharedPreferences("Accounts", Context.MODE_PRIVATE);
        Type type = new TypeToken<List<String>>() {}.getType();

        Gson gson = new Gson();

        String savedJsonValueListA = sharedPreferences.getString("account", "");

        DataListAccount = (gson.fromJson(savedJsonValueListA, type) == null)? DataListAccount : gson.fromJson(savedJsonValueListA, type);
        String savedJsonValueListP = sharedPreferences.getString("password", "");

        DataListPassword = (gson.fromJson(savedJsonValueListP, type) == null)? DataListPassword: gson.fromJson(savedJsonValueListP, type);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, DataListAccount);
        account.setAdapter(adapter);

        account.setOnItemClickListener(((adapterView, view, i, l) -> {
            for (int position = 0; position < DataListAccount.size(); ++ position)
                if (DataListAccount.get(position).equals(adapterView.getItemAtPosition(i).toString())) {
                    password.setText(DataListPassword.get(position));
                    break;
                }
        }));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == SIGN_UP_REQUEST_CODE) {
            String roleUser = data.getStringExtra("roleUser");
            Intent intent = new Intent(Login.this, MainActivity.class);
            intent.putExtra("roleUser", roleUser);
            startActivity(intent);
            finish();
        }
    }
    private void applyLanguage() {
        SharedPreferences sharedPreferences = getSharedPreferences("Language", Context.MODE_PRIVATE);
        String currentLanguage = sharedPreferences.getString("Language", "");
        setLocal(Login.this, currentLanguage);
    }
    private void setLocal(Activity activity, String selectedLanguage) {
        Locale locale = new Locale(selectedLanguage);
        Locale.setDefault(locale);
        Resources resource = activity.getResources();
        Configuration configuration = activity.getResources().getConfiguration();
        configuration.setLocale(locale);
        resource.updateConfiguration(configuration, resource.getDisplayMetrics());
    }
}