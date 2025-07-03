package com.example.dreamtrip;

import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class ChatActivity extends AppCompatActivity {

    private LinearLayout messageContainer;
    private ScrollView scrollView;
    private EditText userInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        messageContainer = findViewById(R.id.messageContainer);
        scrollView = findViewById(R.id.scrollView);
        userInput = findViewById(R.id.userInput);
        Button sendButton = findViewById(R.id.sendButton);

        sendButton.setOnClickListener(v -> {
            String input = userInput.getText().toString().trim();
            if (!input.isEmpty()) {
                addMessage(input, true);  // User message
                respondWithAI(input);
                userInput.setText("");
            }
        });
    }

    private void addMessage(String message, boolean isUser) {
        TextView textView = new TextView(this);
        textView.setText(message);
        textView.setTextSize(16);
        textView.setPadding(24, 16, 24, 16);
        textView.setBackgroundResource(isUser ? android.R.color.holo_blue_light : android.R.color.darker_gray);
        textView.setTextColor(ContextCompat.getColor(this, android.R.color.black));

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = isUser ? Gravity.END : Gravity.START;
        layoutParams.setMargins(8, 8, 8, 8);
        textView.setLayoutParams(layoutParams);

        messageContainer.addView(textView);
        scrollView.post(() -> scrollView.fullScroll(ScrollView.FOCUS_DOWN));
    }

    private void respondWithAI(String userMessage) {
        // Replace this with your real AI integration logic
        String response = "AI: You said \"" + userMessage + "\"";
        addMessage(response, false);
    }
}
