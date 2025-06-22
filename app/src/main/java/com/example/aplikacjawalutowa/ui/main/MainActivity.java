package com.example.aplikacjawalutowa.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.aplikacjawalutowa.R;
import com.example.aplikacjawalutowa.data.local.CurrencyEntity;
import com.example.aplikacjawalutowa.databinding.ActivityMainBinding;
import com.example.aplikacjawalutowa.ui.converter.ConverterActivity;
import com.example.aplikacjawalutowa.ui.history.HistoryActivity;
import com.example.aplikacjawalutowa.ui.login.LoginActivity;
import com.firebase.ui.auth.AuthUI;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mainViewModel;
    private ActivityMainBinding binding;
    private CurrencyAdapter adapter;
    private List<CurrencyEntity> currencyListForConverter = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        setupRecyclerView();
        setupListeners();
        observeViewModel();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener(task -> {
                        Toast.makeText(this, "Wylogowano pomyślnie", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this, LoginActivity.class));
                        finish();
                    });
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupRecyclerView() {
        binding.recyclerViewCurrencies.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewCurrencies.setHasFixedSize(true);
        adapter = new CurrencyAdapter();
        binding.recyclerViewCurrencies.setAdapter(adapter);
    }

    private void setupListeners() {
        binding.swipeRefreshLayout.setOnRefreshListener(() -> {
            mainViewModel.refreshData();
        });

        binding.fabConverter.setOnClickListener(v -> {
            if (currencyListForConverter.isEmpty()) {
                Toast.makeText(this, "Waluty jeszcze się nie załadowały.", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(this, ConverterActivity.class);
            intent.putExtra(ConverterActivity.EXTRA_CURRENCIES, (ArrayList<CurrencyEntity>) currencyListForConverter);
            startActivity(intent);
        });

        adapter.setOnItemClickListener(currency -> {
            Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
            intent.putExtra("CURRENCY_CODE", currency.code);
            startActivity(intent);
        });
    }

    private void observeViewModel() {
        mainViewModel.getAllCurrencies().observe(this, currencies -> {
            if (currencies != null) {
                adapter.submitList(currencies);
                this.currencyListForConverter = currencies;
            }
        });

        mainViewModel.getIsLoading().observe(this, isLoading -> {
            if (isLoading != null && !isLoading) {
                binding.swipeRefreshLayout.setRefreshing(false);
            }
            binding.progressBar.setVisibility(isLoading != null && isLoading ? View.VISIBLE : View.GONE);
        });

        mainViewModel.getErrorMessage().observe(this, error -> {
            if (error != null && !error.isEmpty()) {
                Snackbar.make(binding.getRoot(), error, Snackbar.LENGTH_LONG).show();
            }
        });
    }
}