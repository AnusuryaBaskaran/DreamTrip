package com.example.dreamtrip;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DestinationAdapter extends RecyclerView.Adapter<DestinationAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private final List<Destination> destinationList;
    private final OnItemClickListener listener;

    public DestinationAdapter(List<Destination> destinations, OnItemClickListener listener) {
        this.destinationList = destinations;
        this.listener = listener;
    }

    @NonNull
    @Override
    public DestinationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_destination, parent, false);
        return new ViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull DestinationAdapter.ViewHolder holder, int position) {
        Destination dest = destinationList.get(position);
        holder.tvName.setText(dest.getName());
        holder.tvCountry.setText(dest.getCountry());
    }

    @Override
    public int getItemCount() {
        return destinationList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvCountry;

        public ViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvCountry = itemView.findViewById(R.id.tvCountry);

            itemView.setOnClickListener(v -> {
                if(listener != null) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            });
        }
    }
}