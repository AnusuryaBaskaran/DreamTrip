package com.example.dreamtrip;

import androidx.annotation.NonNull;
import java.io.Serializable;

public class Destination implements Serializable {
    private final String name;
    private final String country;
    private final String description;
    private final double latitude;
    private final double longitude;

    public Destination(String name, String country, String description, double latitude, double longitude) {
        this.name = name;
        this.country = country;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() { return name; }
    public String getCountry() { return country; }
    public String getDescription() { return description; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }

    @NonNull
    @Override
    public String toString() {
        return name + ", " + country;
    }
}
