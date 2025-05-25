package com.example.dreamtrip;

import java.io.Serializable;
import java.util.List;

public class Destination implements Serializable {

    private String name;
    private String country;
    private String description;
    private List<String> attractions;

    // Constructor
    public Destination(String name, String country, String description, List<String> attractions) {
        this.name = name;
        this.country = country;
        this.description = description;
        this.attractions = attractions;
    }

    // Empty constructor (useful for serialization or Firebase)
    public Destination() {}

    // Getters
    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getAttractions() {
        return attractions;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAttractions(List<String> attractions) {
        this.attractions = attractions;
    }

    // Utility method to return attractions as comma-separated string
    public String getAttractionsAsString() {
        return attractions != null ? String.join(", ", attractions) : "No attractions listed";
    }
}
