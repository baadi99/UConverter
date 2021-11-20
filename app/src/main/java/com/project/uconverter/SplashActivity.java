package com.project.uconverter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //Create a new handler
        Handler handler = new Handler(Looper.myLooper());
        //Execute the callback after 2000ms (i.e. 2s)
        handler.postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            //Start the new activity(i.e. Main activity)
            startActivity(intent);
            //Finish the current activity(i.e. Splash activity
            finish();
        }, 2000);
    }
    
}