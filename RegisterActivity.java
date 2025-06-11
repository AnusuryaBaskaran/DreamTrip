package com.example.dreamtrip;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText, confirmPasswordEditText;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
        Button registerButton = findViewById(R.id.registerButton);
        TextView goToLoginText = findViewById(R.id.goToLoginText);

        registerButton.setOnClickListener(v -> registerUser());

        goToLoginText.setOnClickListener(v -> {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        });
    }

    private void registerUser() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString();
        String confirmPassword = confirmPasswordEditText.getText().toString();

        if (TextUtils.isEmpty(email)) {
            emailEditText.setError("Email required");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            passwordEditText.setError("Password required");
            return;
        }
        if (TextUtils.isEmpty(confirmPassword)) {
            confirmPasswordEditText.setError("Please confirm password");
            return;
        }
        if (!password.equals(confirmPassword)) {
            confirmPasswordEditText.setError("Passwords do not match");
            return;
        }
        if (password.length() < 6) {
            passwordEditText.setError("Password must be at least 6 characters");
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "Registration successful. Please login.", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Registration failed: " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}
