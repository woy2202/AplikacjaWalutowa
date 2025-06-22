package com.example.aplikacjawalutowa.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.aplikacjawalutowa.databinding.ActivityLoginBinding;
import com.example.aplikacjawalutowa.ui.main.MainActivity;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null) {
            goToMainActivity();
            return;
        }

        setupListeners();
    }

    private void setupListeners() {
        binding.buttonLogin.setOnClickListener(v -> loginUser());

        binding.buttonRegister.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });
    }

    private void loginUser() {
        String email = binding.editTextEmail.getText().toString().trim();
        String password = binding.editTextPassword.getText().toString().trim();

        if (!validateForm(email, password)) {
            return;
        }

        showProgressBar(true);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    showProgressBar(false);
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "Logowanie pomyślne.", Toast.LENGTH_SHORT).show();
                        goToMainActivity();
                    } else {
                        Toast.makeText(this, "Logowanie nie powiodło się: " + task.getException().getMessage(),
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
        return true;
    }

    private void showProgressBar(boolean show) {
        binding.progressBarLogin.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void goToMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}