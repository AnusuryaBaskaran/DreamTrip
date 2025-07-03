package com.example.dreamtrip;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TripsAdapter extends RecyclerView.Adapter<TripsAdapter.TripViewHolder> {

    private final List<Trip> tripList;

    public TripsAdapter(List<Trip> tripList) {
        this.tripList = tripList;
    }

    @NonNull
    @Override
    public TripViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trip, parent, false);
        return new TripViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull TripViewHolder holder, int position) {
        Trip trip = tripList.get(position);

        String tripName = trip.getTripName() != null ? trip.getTripName() : "Unnamed Trip";
        String destination = trip.getDestination() != null ? trip.getDestination() : "Unknown Destination";
        String startDate = TripListActivity.convertMillisToDate(trip.getStartDate());
        String endDate = TripListActivity.convertMillisToDate(trip.getEndDate());

        holder.tripName.setText(tripName);
        holder.destination.setText("Destination: " + destination);
        holder.dates.setText("Dates: " + startDate + " to " + endDate);
    }

    @Override
    public int getItemCount() {
        return tripList.size();
    }

    public static class TripViewHolder extends RecyclerView.ViewHolder {
        TextView tripName, destination, dates;

        public TripViewHolder(@NonNull View itemView) {
            super(itemView);
            tripName = itemView.findViewById(R.id.tripName);
            destination = itemView.findViewById(R.id.destination);
            dates = itemView.findViewById(R.id.dates);
        }
    }
}
