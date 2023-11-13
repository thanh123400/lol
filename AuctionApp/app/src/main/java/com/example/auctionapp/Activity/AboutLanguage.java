package com.example.auctionapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.auctionapp.R;
import com.example.auctionapp.databinding.ActivityAboutLanguageBinding;

import java.util.Arrays;
import java.util.List;

public class AboutLanguage extends AppCompatActivity {
    ActivityAboutLanguageBinding activityAboutLanguageBinding;
    String defaultLanguage;
    String chooseLanguage;
    List<String> languageList = Arrays.asList("vn", "en", "fr");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAboutLanguageBinding = DataBindingUtil.setContentView(this, R.layout.activity_about_language);
        setContentView(activityAboutLanguageBinding.getRoot());
        getSupportActionBar().setTitle(R.string.about_language_header);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.lavender)));
        /////
        SharedPreferences sharedPreferences = getSharedPreferences("Language", Context.MODE_PRIVATE);
        defaultLanguage = sharedPreferences.getString("Language", "");
        Log.d("AboutLanguage_1", "onCreate: " + defaultLanguage);
        ////
        solve(defaultLanguage);
        activityAboutLanguageBinding.grLanguage.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == activityAboutLanguageBinding.vn.getId()) {
                chooseLanguage = "vn";
                activityAboutLanguageBinding.vn.setChecked(true);
            }
            if (checkedId == activityAboutLanguageBinding.en.getId()) {
                chooseLanguage = "en";
                activityAboutLanguageBinding.en.setChecked(true);
            }
            if (checkedId == activityAboutLanguageBinding.fr.getId()) {
                chooseLanguage = "fr";
                activityAboutLanguageBinding.fr.setChecked(true);
            }
        });
    }
    public void onSubmit(View view) {
        if (chooseLanguage != null && !chooseLanguage.equals(defaultLanguage)) {
            Log.d("AboutLanguage_2", "onCreate: " + chooseLanguage);
            putLanguage(chooseLanguage);
            restartApp();
        }
    }
    private void solve(String defaultLanguage) {
        if (defaultLanguage.equals("vn")) {
            activityAboutLanguageBinding.vn.setChecked(true);
        }
        if (defaultLanguage.equals("en")) {
            activityAboutLanguageBinding.en.setChecked(true);
        }
        if (defaultLanguage.equals("fr")) {
            activityAboutLanguageBinding.fr.setChecked(true);
        }
    }

    private void putLanguage(String language) {
        SharedPreferences sharedPreferences = getSharedPreferences("Language", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("Language", language);
        editor.apply();

    }
    private void restartApp() {
        Intent intent = getPackageManager().getLaunchIntentForPackage(getPackageName());
        if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("ChangeLanguage", "L1");
            startActivity(intent);
            finish();
        }

    }
}