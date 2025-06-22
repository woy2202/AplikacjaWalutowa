package com.example.aplikacjawalutowa.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.aplikacjawalutowa.databinding.ActivityRegisterBinding;
import com.example.aplikacjawalutowa.ui.main.MainActivity;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();

        setupListeners();
    }

    private void setupListeners() {
        binding.buttonRegister.setOnClickListener(v -> registerUser());

        binding.textViewLoginPrompt.setOnClickListener(v -> {
            finish();
        });
    }

    private void registerUser() {
        String email = binding.editTextEmail.getText().toString().trim();
        String password = binding.editTextPassword.getText().toString().trim();

        if (!validateForm(email, password)) {
            return;
        }

        showProgressBar(true);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    showProgressBar(false);
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "Rejestracja pomyślna. Zalogowano.", Toast.LENGTH_SHORT).show();
                        goToMainActivity();
                    } else {
                        Toast.makeText(this, "Rejestracja nie powiodła się: " + task.getException().getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
    }

    private boolean validateForm(String email, String password) {
        if (TextUtils.isEmpty(email)) {
            binding.editTextEmail.setError("E-mail jest wymagany.");
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            binding.editTextPassword.setError("Hasło jest wymagane.");
            return false;
        }
        if (password.length() < 6) {
            binding.editTextPassword.setError("Hasło musi mieć co najmniej 6 znaków.");
            return false;
        }
        return true;
    }

    private void showProgressBar(boolean show) {
        binding.progressBarRegister.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}