package com.example.dreamtrip;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;

public class DestinationDetailsActivity extends AppCompatActivity {

    private Destination destination;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination_details);

        TextView textName = findViewById(R.id.textName);
        TextView textCountry = findViewById(R.id.textCountry);
        TextView textDescription = findViewById(R.id.textDescription);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView textWeather = findViewById(R.id.textWeather); // Add in XML
        Button buttonPlanTrip = findViewById(R.id.buttonPlanTrip);
        Button buttonViewMap = findViewById(R.id.buttonViewMap);

        // Lottie animation view
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) LottieAnimationView lottieBackground = findViewById(R.id.lottieBackground);
        lottieBackground.setAnimation("destdetails.json"); // Make sure this file is in assets folder
        lottieBackground.playAnimation();
        lottieBackground.setRepeatCount(LottieDrawable.INFINITE);


        destination = (Destination) getIntent().getSerializableExtra("destination");

        if (destination != null) {
            textName.setText(destination.getName());
            textCountry.setText(destination.getCountry());
            textDescription.setText(destination.getDescription());

            // Show weather
            String weather = WeatherHelper.getWeather(destination.getName());
            textWeather.setText("Expected Weather: " + weather);

            buttonPlanTrip.setOnClickListener(v -> {
                Intent intent = new Intent(DestinationDetailsActivity.this, SaveTripActivity.class);
                intent.putExtra("destinationName", destination.getName());
                startActivity(intent);
            });

            buttonViewMap.setOnClickListener(v -> {
                String uri = "geo:" + destination.getLatitude() + "," + destination.getLongitude() +
                        "?q=" + Uri.encode(destination.getName() + ", " + destination.getCountry());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            });
        }
    }
}
