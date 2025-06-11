package com.example.dreamtrip;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class DestinationsActivity extends AppCompatActivity implements DestinationAdapter.OnItemClickListener {

    private static final String TAG = "DestinationsActivity";

    RecyclerView recyclerView;
    List<Destination> destinationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destinations);

        recyclerView = findViewById(R.id.recyclerViewDestinations);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load destinations from assets/destinations.json
        try {
            String json = loadJSONFromAsset();
            DestinationListWrapper wrapper = new Gson().fromJson(json, DestinationListWrapper.class);
            destinationList = wrapper.destinations;
        } catch (Exception e) {
            Log.e(TAG, "Error loading destinations from asset", e);
        }

        DestinationAdapter adapter = new DestinationAdapter(destinationList, this);
        recyclerView.setAdapter(adapter);
    }

    private String loadJSONFromAsset() throws IOException {
        InputStream is = getAssets().open("destinations.json");
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        return new String(buffer, StandardCharsets.UTF_8);
    }

    @Override
    public void onItemClick(int position) {
        Destination destination = destinationList.get(position);
        Intent intent = new Intent(this, DestinationDetailsActivity.class);
        intent.putExtra("destination", (CharSequence) destination); // Serializable
        startActivity(intent);
    }


    // Helper class for GSON parsing
    private static class DestinationListWrapper {
        List<Destination> destinations;
    }
}
