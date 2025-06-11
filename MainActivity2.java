package com.example.dreamtrip;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity{
        private ArrayList<Destination> destinations;

        @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main2);

            ListView listView = findViewById(R.id.listView);
            Button btnPlanTrip = findViewById(R.id.btnPlanTrip);
            Button buttonSaveTrip = findViewById(R.id.buttonSaveTrip);

            // Initialize destinations list
            destinations = new ArrayList<>();
            destinations.add(new Destination("Paris", "France", "The City of Light, famous for its art, fashion, gastronomy, and culture.", 48.8566, 2.3522));
            destinations.add(new Destination("Tokyo", "Japan", "A bustling metropolis blending ultra-modern and traditional culture.", 35.6895, 139.6917));
            destinations.add(new Destination("New York City", "USA", "The Big Apple, known for its skyline, Broadway, and diverse neighborhoods.", 40.7128, -74.006));
            destinations.add(new Destination("Sydney", "Australia", "Famous for its Sydney Opera House and stunning harbor.", -33.8688, 151.2093));
            destinations.add(new Destination("Cape Town", "South Africa", "Known for Table Mountain and beautiful coastal scenery.", -33.9249, 18.4241));
            destinations.add(new Destination("Rome", "Italy", "The Eternal City, with ancient ruins and vibrant street life.", 41.9028, 12.4964));
            destinations.add(new Destination("Rio de Janeiro", "Brazil", "Famous for its carnival, beaches, and the Christ the Redeemer statue.", -22.9068, -43.1729));
            destinations.add(new Destination("Bangkok", "Thailand", "Known for its vibrant street life and ornate temples.", 13.7563, 100.5018));
            destinations.add(new Destination("Barcelona", "Spain", "Famous for Gaudí's architecture and beautiful Mediterranean beaches.", 41.3851, 2.1734));
            destinations.add(new Destination("Dubai", "UAE", "Known for luxury shopping, ultramodern architecture, and lively nightlife.", 25.2048, 55.2708));
            destinations.add(new Destination("Istanbul", "Turkey", "A historic city straddling Europe and Asia with rich culture and cuisine.", 41.0082, 28.9784));
            destinations.add(new Destination("Moscow", "Russia", "Known for the Kremlin, Red Square, and colorful onion domes.", 55.7558, 37.6173));
            destinations.add(new Destination("San Francisco", "USA", "Famous for the Golden Gate Bridge, tech innovation, and vibrant culture.", 37.7749, -122.4194));
            destinations.add(new Destination("London", "United Kingdom", "A cosmopolitan city with iconic landmarks and rich history.", 51.5074, -0.1278));
            destinations.add(new Destination("Amsterdam", "Netherlands", "Known for its canals, museums, and vibrant nightlife.", 52.3676, 4.9041));
            destinations.add(new Destination("Seoul", "South Korea", "A tech-savvy city blending tradition and modern skyscrapers.", 37.5665, 126.978));
            destinations.add(new Destination("Venice", "Italy", "Famous for its canals, gondolas, and Renaissance art.", 45.4408, 12.3155));
            destinations.add(new Destination("Santorini", "Greece", "Known for stunning sunsets, whitewashed buildings, and blue domes.", 36.3932, 25.4615));
            destinations.add(new Destination("Cairo", "Egypt", "Home to the Great Pyramids and ancient Egyptian history.", 30.0444, 31.2357));
            destinations.add(new Destination("Maui", "USA", "A tropical paradise with beautiful beaches and volcanic landscapes.", 20.7984, -156.3319));
            destinations.add(new Destination("Queenstown", "New Zealand", "Adventure capital with stunning lakes and mountain scenery.", -45.0312, 168.6626));

            ArrayAdapter<Destination> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, destinations);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener((parent, view, position, id) -> {
                Destination selected = destinations.get(position);
                Intent intent = new Intent(MainActivity2.this, DestinationDetailsActivity.class);
                intent.putExtra("destination", selected); // Send the full object
                startActivity(intent);
            });


            btnPlanTrip.setOnClickListener(v -> {
                Intent intent = new Intent(com.example.dreamtrip.MainActivity2.this, PlanTripActivity.class);
                startActivity(intent);
            });
        }
    }

