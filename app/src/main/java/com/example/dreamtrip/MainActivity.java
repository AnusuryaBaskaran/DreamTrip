package com.example.dreamtrip;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Lottie animation view
        LottieAnimationView lottieBackground = findViewById(R.id.lottieBackground);
        lottieBackground.setAnimation("background.json"); // Make sure this file is in assets folder
        lottieBackground.playAnimation();
        lottieBackground.setRepeatCount(LottieDrawable.INFINITE); // Correct constant

        // Buttons
        Button buttonDestinations = findViewById(R.id.buttonDestinations);
        Button buttonViewTrips = findViewById(R.id.buttonViewTrips);
        Button buttonChat = findViewById(R.id.buttonChat);

        // Navigate to destinations list
        buttonDestinations.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
        });

        // Navigate to saved trips
        buttonViewTrips.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TripListActivity.class);
            startActivity(intent);
        });

        // Navigate to AI chat assistant
        buttonChat.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ChatActivity.class);
            startActivity(intent);
        });
    }
}
