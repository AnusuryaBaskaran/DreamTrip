package com.example.dreamtrip;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DestinationDetailsActivity extends AppCompatActivity {

    TextView tvName, tvCountry, tvDescription, tvAttractions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination_details);

        tvName = findViewById(R.id.tvDestinationName);
        tvCountry = findViewById(R.id.tvDestinationCountry);
        tvDescription = findViewById(R.id.tvDestinationDescription);
        tvAttractions = findViewById(R.id.tvAttractions);

        Destination destination = (Destination) getIntent().getSerializableExtra("destination");

        if(destination != null) {
            tvName.setText(destination.getName());
            tvCountry.setText(destination.getCountry());
            tvDescription.setText(destination.getDescription());

            StringBuilder attractionsStr = new StringBuilder();
            for(String attraction : destination.getAttractions()) {
                attractionsStr.append("• ").append(attraction).append("\n");
            }
            tvAttractions.setText(attractionsStr.toString());
        }
    }
}