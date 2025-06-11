package com.example.dreamtrip;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class TripListActivity extends AppCompatActivity {

    private TripsAdapter tripsAdapter;
    private ArrayList<Trip> tripList;
    private ProgressBar progressBar;
    private TextView emptyMessage;

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "TripListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_list);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewTrips);
        progressBar = findViewById(R.id.progressBar);
        emptyMessage = findViewById(R.id.emptyMessage);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        tripList = new ArrayList<>();
        tripsAdapter = new TripsAdapter(tripList);
        recyclerView.setAdapter(tripsAdapter);

        fetchTrips();
    }

    @SuppressLint({"NotifyDataSetChanged", "SetTextI18n"})
    private void fetchTrips() {
        progressBar.setVisibility(View.VISIBLE);
        emptyMessage.setVisibility(View.GONE);

        db.collection("trips")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    tripList.clear();
                    if (!queryDocumentSnapshots.isEmpty()) {
                        for (DocumentSnapshot snapshot : queryDocumentSnapshots) {
                            Trip trip = snapshot.toObject(Trip.class);
                            if (trip != null) {
                                tripList.add(trip);
                                Log.d(TAG, "Trip loaded: " + trip.getTripName());
                            }
                        }
                        tripsAdapter.notifyDataSetChanged();
                        emptyMessage.setVisibility(View.GONE);
                    } else {
                        Log.d(TAG, "No trips found.");
                        emptyMessage.setText("No saved trips yet.");
                        emptyMessage.setVisibility(View.VISIBLE);
                    }
                    progressBar.setVisibility(View.GONE);
                })
                .addOnFailureListener(e -> {
                    Log.e(TAG, "Error fetching trips", e);
                    emptyMessage.setText("Failed to load trips.");
                    emptyMessage.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                });
    }
}
