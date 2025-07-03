package com.example.dreamtrip;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;

import java.io.Serializable;

/**
 * Represents a saved trip plan for a user.
 * Required for Firebase to function properly with data binding.
 */
public class TripPlan implements Serializable {
    private String userId;
    private String destination;
    private String date;
    private String notes;


    // Required empty constructor for Firebase
    public TripPlan() {}

    // Constructor to quickly create a TripPlan object
    public TripPlan(String userId, String destination, String date, String notes) {
        this.userId = userId;
        this.destination = destination;
        this.date = date;
        this.notes = notes;
    }

    // Getters
    public String getUserId() {
        return userId;
    }

    public String getDestination() {
        return destination;
    }

    public String getDate() {
        return date;
    }

    public String getNotes() {
        return notes;
    }

    // Setters
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    // Optional: To show summary if used in a list adapter
    @NonNull
    @Override
    public String toString() {
        return destination + " on " + date;
    }
}
