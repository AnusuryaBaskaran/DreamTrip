package com.example.dreamtrip;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PlanTripActivity extends AppCompatActivity {

    EditText editDestination, editDates, editBudget, editInterests;
    Button btnGeneratePlan;
    TextView responseText;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_trip);

        editDestination = findViewById(R.id.editDestination);
        editDates = findViewById(R.id.editDates);
        editBudget = findViewById(R.id.editBudget);
        editInterests = findViewById(R.id.editInterests);
        btnGeneratePlan = findViewById(R.id.btnGeneratePlan);
        responseText = findViewById(R.id.responseText);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button buttonSaveTrip = findViewById(R.id.buttonSaveTrip);

        btnGeneratePlan.setOnClickListener(v -> {
            String destination = editDestination.getText().toString().trim();
            String dates = editDates.getText().toString().trim();
            String budget = editBudget.getText().toString().trim();
            String interests = editInterests.getText().toString().trim();

            if(destination.isEmpty() || dates.isEmpty() || budget.isEmpty() || interests.isEmpty()) {
                responseText.setText("Please fill all fields.");
                return;
            }

            StringBuilder plan = new StringBuilder();
            plan.append("Trip to ").append(destination).append("\n");
            plan.append("Dates: ").append(dates).append("\n");
            plan.append("Budget: ").append(budget).append("USD").append("\n");
            plan.append("Interests: ").append(interests).append("\n\n");
            plan.append("Suggested Itinerary:\n");

            if(interests.toLowerCase().contains("culture")) {
                plan.append("* Visit museums and heritage sites.\n");
            }
            if(interests.toLowerCase().contains("adventure")) {
                plan.append("* Outdoor activities and adventure sports.\n");
            }
            if(interests.toLowerCase().contains("food")) {
                plan.append("* Explore local cuisine and food markets.\n");
            }
            if(interests.toLowerCase().contains("shopping")) {
                plan.append("* Visit local markets and shopping districts.\n");

            }
            plan.append("Last day: Relax and prepare for departure.\n");

            responseText.setText(plan.toString());
        });
        buttonSaveTrip.setOnClickListener(v -> {
            Intent intent = new Intent(com.example.dreamtrip.PlanTripActivity.this, SaveTripActivity.class);
            startActivity(intent);
        });
    }
}