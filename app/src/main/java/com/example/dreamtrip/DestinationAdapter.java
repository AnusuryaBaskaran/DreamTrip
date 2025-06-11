package com.example.dreamtrip;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DestinationAdapter extends RecyclerView.Adapter<DestinationAdapter.ViewHolder> {

    private final List<Destination> destinationList;
    private final OnItemClickListener listener;

    // Constructor
    public DestinationAdapter(List<Destination> destinationList, OnItemClickListener listener) {
        this.destinationList = destinationList;
        this.listener = listener;
    }

    // ViewHolder class
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textName;
        public TextView textCountry;

        public ViewHolder(View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.textName);
            textCountry = itemView.findViewById(R.id.textCountry);
        }

        public void bind(final Destination destination, final OnItemClickListener listener) {
            textName.setText(destination.getName());
            textCountry.setText(destination.getCountry());

            itemView.setOnClickListener(v -> listener.onItemClick(getAdapterPosition()));
        }
    }

    @NonNull
    @Override
    public DestinationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_destination, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DestinationAdapter.ViewHolder holder, int position) {
        Destination destination = destinationList.get(position);
        holder.bind(destination, listener);
    }

    @Override
    public int getItemCount() {
        return destinationList.size();
    }

    // Interface for click events
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
