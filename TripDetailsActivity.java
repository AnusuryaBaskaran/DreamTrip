package com.example.dreamtrip;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class TripDetailsActivity extends AppCompatActivity {

    private Trip trip;
    private View tripDetailsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_trip);  // Use your trip details layout here

        tripDetailsLayout = findViewById(R.id.tripDetailsLayout);

        Button btnShareText = findViewById(R.id.btnShareText);
        Button btnShareScreenshot = findViewById(R.id.btnShareScreenshot);

        // Receive Trip object from Intent extras
        trip = (Trip) getIntent().getSerializableExtra("trip");
        if (trip == null) {
            Toast.makeText(this, "Trip data not found.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        btnShareText.setOnClickListener(v -> shareTripText());
        btnShareScreenshot.setOnClickListener(v -> shareTripScreenshot());
    }

    private void shareTripText() {
        String tripDetails = "🌍 DreamTrip AI Plan:\n" +
                "Trip Name: " + trip.getTripName() + "\n" +
                "📍 Destination: " + trip.getDestination() + "\n" +
                "📅 From: " + formatDate(trip.getStartDate()) + "\n" +
                "📅 To: " + formatDate(trip.getEndDate()) + "\n" +
                "📝 Description: " + trip.getDescription() + "\n\n" +
                "Planned with #DreamTripAI ✨";

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Check out my trip!");
        shareIntent.putExtra(Intent.EXTRA_TEXT, tripDetails);
        startActivity(Intent.createChooser(shareIntent, "Share your trip via"));
    }

    private void shareTripScreenshot() {
        try {
            // Create bitmap from view
            tripDetailsLayout.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(tripDetailsLayout.getDrawingCache());
            tripDetailsLayout.setDrawingCacheEnabled(false);

            // Store image in MediaStore and get URI
            String path = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "TripPlan", null);
            Uri imageUri = Uri.parse(path);

            // Share image
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("image/*");
            shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Planned with #DreamTripAI 🌍✨");
            startActivity(Intent.createChooser(shareIntent, "Share Trip Screenshot"));
        } catch (Exception e) {
            Toast.makeText(this, "Failed to share screenshot: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private String formatDate(long millis) {
        if (millis == 0) return "N/A";  // handle uninitialized dates
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
        return sdf.format(millis);
    }
}
