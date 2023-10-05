package com.example.bharath_instagram;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class ProfileActivity extends AppCompatActivity {

    MaterialButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        button = findViewById(R.id.Btn_Logout);
        SharedPreferences sp = getSharedPreferences("Insta", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        button.setOnClickListener(v -> {
            editor.putBoolean("User", false);
            editor.apply();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

        });

    }
}