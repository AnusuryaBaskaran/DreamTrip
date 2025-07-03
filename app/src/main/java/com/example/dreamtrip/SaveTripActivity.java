package com.example.dreamtrip;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SaveTripActivity extends AppCompatActivity {

    private EditText editTextDescription, editTextTripName, editTextDestination,
            editTextStartDate, editTextEndDate, editTextNotes;
    private ScrollView tripDetailsLayout;
    private FirebaseFirestore db;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_trip);

        // Initialize Firestore
        db = FirebaseFirestore.getInstance();

        // Match XML Layout Initialization Order
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextTripName = findViewById(R.id.editTextTripName);
        editTextDestination = findViewById(R.id.editTextDestination);
        editTextStartDate = findViewById(R.id.editTextStartDate);
        editTextEndDate = findViewById(R.id.editTextEndDate);
        editTextNotes = findViewById(R.id.editTextNotes);
        tripDetailsLayout = findViewById(R.id.tripDetailsLayout);

        Button buttonSaveTrip = findViewById(R.id.buttonSaveTrip);
        Button btnShareText = findViewById(R.id.btnShareText);
        Button btnShareScreenshot = findViewById(R.id.btnShareScreenshot);
        Button buttonViewTrips = findViewById(R.id.buttonViewTrips);

        // Lottie animation view
        LottieAnimationView lottieBackground = findViewById(R.id.lottieBackground);
        lottieBackground.setAnimation("savedtrips.json"); // Make sure this file is in assets folder
        lottieBackground.playAnimation();
        lottieBackground.setRepeatCount(LottieDrawable.INFINITE);


        // Set listeners
        buttonSaveTrip.setOnClickListener(view -> saveTripToFirestore());
        btnShareText.setOnClickListener(view -> shareTripAsText());
        btnShareScreenshot.setOnClickListener(view -> shareTripAsScreenshot());
        buttonViewTrips.setOnClickListener(v -> {
            Intent intent = new Intent(SaveTripActivity.this, TripListActivity.class);
            startActivity(intent);
        });
    }

    private void saveTripToFirestore() {
        String tripName = editTextTripName.getText().toString().trim();
        String destination = editTextDestination.getText().toString().trim();
        String description = editTextDescription.getText().toString().trim();
        String startDateStr = editTextStartDate.getText().toString().trim();
        String endDateStr = editTextEndDate.getText().toString().trim();

        if (tripName.isEmpty()) {
            editTextTripName.setError("Trip name is required");
            return;
        }
        if (destination.isEmpty()) {
            editTextDestination.setError("Destination is required");
            return;
        }

        long startDateMillis = 0;
        long endDateMillis = 0;
        try {
            if (!startDateStr.isEmpty())
                startDateMillis = Objects.requireNonNull(dateFormat.parse(startDateStr)).getTime();
            if (!endDateStr.isEmpty())
                endDateMillis = Objects.requireNonNull(dateFormat.parse(endDateStr)).getTime();
        } catch (ParseException e) {
            Toast.makeText(this, "Date format must be like: Jan 01, 2025", Toast.LENGTH_LONG).show();
            return;
        }

        if (startDateMillis != 0 && endDateMillis != 0 && endDateMillis < startDateMillis) {
            Toast.makeText(this, "End date cannot be before start date", Toast.LENGTH_LONG).show();
            return;
        }

        Map<String, Object> trip = new HashMap<>();
        trip.put("tripName", tripName);
        trip.put("destination", destination);
        trip.put("description", description);
        trip.put("startDate", startDateMillis);
        trip.put("endDate", endDateMillis);
        trip.put("notes", editTextNotes.getText().toString().trim());

        db.collection("trips")
                .add(trip)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(this, "Trip saved with ID: " + documentReference.getId(), Toast.LENGTH_SHORT).show();
                    clearInputs();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Error saving trip: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    Log.e("SaveTripActivity", "Failed to save trip", e);
                });
    }

    private void clearInputs() {
        editTextTripName.setText("");
        editTextDestination.setText("");
        editTextDescription.setText("");
        editTextStartDate.setText("");
        editTextEndDate.setText("");
        editTextNotes.setText("");
    }

    private void shareTripAsText() {
        String tripInfo = "Trip Name: " + editTextTripName.getText().toString() + "\n"
                + "Destination: " + editTextDestination.getText().toString() + "\n"
                + "Start Date: " + editTextStartDate.getText().toString() + "\n"
                + "End Date: " + editTextEndDate.getText().toString() + "\n"
                + "Description: " + editTextDescription.getText().toString() + "\n"
                + "Notes: " + editTextNotes.getText().toString();

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My Trip Plan");
        shareIntent.putExtra(Intent.EXTRA_TEXT, tripInfo);

        startActivity(Intent.createChooser(shareIntent, "Share Trip Details"));
    }

    private void shareTripAsScreenshot() {
        try {
            View view = tripDetailsLayout;
            view.setDrawingCacheEnabled(true);
            view.buildDrawingCache();
            Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
            view.setDrawingCacheEnabled(false);

            File cachePath = new File(getCacheDir(), "images");
            if (!cachePath.exists() && !cachePath.mkdirs()) {
                Log.e("SaveTripActivity", "Failed to create cache directory");
                Toast.makeText(this, "Unable to create directory for screenshot", Toast.LENGTH_SHORT).show();
                return;
            }

            File file = new File(cachePath, "trip_screenshot.png");
            FileOutputStream stream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            stream.close();

            Uri contentUri = FileProvider.getUriForFile(this, getPackageName() + ".provider", file);

            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("image/png");
            shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri);
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            startActivity(Intent.createChooser(shareIntent, "Share Trip Screenshot"));

        } catch (Exception e) {
            Log.e("SaveTripActivity", "Error while sharing screenshot", e);
            Toast.makeText(this, "Failed to share screenshot", Toast.LENGTH_SHORT).show();
        }
    }
}
