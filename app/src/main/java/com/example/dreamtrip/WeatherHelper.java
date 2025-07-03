package com.example.dreamtrip;

import java.util.Calendar;

public class WeatherHelper {

    public static String getWeather(String city) {
        city = city.toLowerCase();
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;

        switch (city) {
            case "paris":
                return getSeasonal(month, "Mild Spring ☀️", "Warm & Sunny ☀️", "Cold & Cloudy 🌧️");

            case "tokyo":
                return getSeasonal(month, "Pleasant & Blooming ☀️", "Humid & Rainy 🌧️", "Cool & Dry ❄️");

            case "new york city":
                return getSeasonal(month, "Mild & Breezy ☀️", "Hot & Humid ☀️", "Snowy & Cold ❄️");

            case "sydney":
                return getSouthernHemisphere(month, "Pleasant Spring ☀️", "Hot & Sunny ☀️", "Cool & Dry ❄️");

            case "cape town":
                return getSouthernHemisphere(month, "Warm & Sunny ☀️", "Rainy & Cool 🌧️", "Rainy & Cool 🌧️");

            case "rome":
                return getSeasonal(month, "Pleasant ☀️", "Hot & Dry ☀️", "Cool & Cloudy 🌧️");

            case "rio de janeiro":
                return getSouthernHemisphere(month, "Hot & Rainy 🌧️", "Warm & Breezy ☀️", "Warm & Breezy ☀️");

            case "bangkok":
                return getSeasonal(month, "Hot & Dry ☀️", "Rainy & Humid 🌧️", "Hot & Dry ☀️");

            case "barcelona":
                return getSeasonal(month, "Cool & Breezy 🌧️", "Sunny & Warm ☀️", "Cool & Breezy 🌧️");

            case "dubai":
                return getSeasonal(month, "Warm & Pleasant ☀️", "Extremely Hot ☀️", "Warm & Pleasant ☀️");

            case "istanbul":
                return getSeasonal(month, "Mild & Sunny ☀️", "Hot & Humid ☀️", "Cold & Wet 🌧️");

            case "moscow":
                return getSeasonal(month, "Mild & Cool ☀️", "Mild & Cool ☀️", "Very Cold & Snowy ❄️");

            case "san francisco":
                return getSeasonal(month, "Mild & Breezy ☀️", "Foggy & Cool 🌫️", "Mild & Breezy ☀️");

            case "london":
                return getSeasonal(month, "Cloudy & Mild 🌧️", "Cloudy & Mild 🌧️", "Cold & Rainy 🌧️");

            case "amsterdam":
                return getSeasonal(month, "Cool & Breezy 🌧️", "Cool & Breezy 🌧️", "Cold & Damp 🌧️");

            case "seoul":
                return getSeasonal(month, "Pleasant ☀️", "Hot & Humid ☀️", "Freezing & Snowy ❄️");

            case "venice":
                return getSeasonal(month, "Cool & Romantic 🌫️", "Hot & Humid ☀️", "Cool & Romantic 🌫️");

            case "santorini":
                return getSeasonal(month, "Mild & Windy 🌫️", "Sunny & Hot ☀️", "Mild & Windy 🌫️");

            case "cairo":
                return getSeasonal(month, "Warm & Sunny ☀️", "Extremely Hot ☀️", "Warm & Sunny ☀️");

            case "maui":
                return getSeasonal(month, "Warm & Rainy 🌧️", "Hot & Tropical ☀️", "Warm & Rainy 🌧️");

            case "queenstown":
                return getSouthernHemisphere(month, "Warm & Sunny ☀️", "Cool & Dry 🌫️", "Snowy & Cold ❄️");

            default:
                return "Weather info unavailable 🌥️";
        }
    }

    private static String getSeasonal(int month, String spring, String summer, String winter) {
        if (month >= 3 && month <= 5) return spring;
        else if (month >= 6 && month <= 8) return summer;
        else return winter;
    }

    private static String getSouthernHemisphere(int month, String summer, String autumn, String winter) {
        if (month >= 12 || month <= 2) return summer; // Southern summer
        else if (month <= 5) return autumn;
        else return winter;
    }
}
