package com.example.bharath_instagram;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;


public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_splash_screen);


        Handler handler = new Handler();
        SharedPreferences sharedPreferences = getSharedPreferences("Insta", MODE_PRIVATE);


        // Define the delay in milliseconds
        long delayMillis = 900;

        // Create and post a Runnable with the Intent
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Create the Intent you want to launch
                Intent intent = null;

                if (sharedPreferences.getBoolean("User", false)) {
                    intent = new Intent(SplashScreenActivity.this, ProfileActivity.class);
                } else {
                    intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                }

                // Start the activity using the Intent
                startActivity(intent);
            }
        }, delayMillis);
    }
}
