package com.example.aplikacjawalutowa.ui.converter;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.aplikacjawalutowa.data.local.CurrencyEntity;
import com.example.aplikacjawalutowa.databinding.ActivityConverterBinding;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConverterActivity extends AppCompatActivity {

    public static final String EXTRA_CURRENCIES = "EXTRA_CURRENCIES";
    private ActivityConverterBinding binding;
    private List<CurrencyEntity> currencyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConverterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        Serializable serializableExtra = getIntent().getSerializableExtra(EXTRA_CURRENCIES);
        if (serializableExtra instanceof ArrayList) {
            currencyList = (ArrayList<CurrencyEntity>) serializableExtra;
        }

        if (currencyList == null || currencyList.isEmpty()) {
            Toast.makeText(this, "Błąd wczytywania walut!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        setupSpinners();
        setupButton();
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    private void setupSpinners() {
        List<String> currencyCodes = currencyList.stream()
                .map(currency -> currency.code)
                .collect(Collectors.toList());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, currencyCodes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        binding.spinnerFrom.setAdapter(adapter);
        binding.spinnerTo.setAdapter(adapter);

        binding.spinnerFrom.setSelection(adapter.getPosition("EUR"));
        binding.spinnerTo.setSelection(adapter.getPosition("PLN"));
    }

    private void setupButton() {
        binding.buttonCalculate.setOnClickListener(v -> calculateConversion());
    }

    private void calculateConversion() {
        String amountStr = binding.editTextAmount.getText().toString();
        if (amountStr.isEmpty()) {
            Toast.makeText(this, "Wpisz kwotę", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double amount = Double.parseDouble(amountStr);
            String fromCode = (String) binding.spinnerFrom.getSelectedItem();
            String toCode = (String) binding.spinnerTo.getSelectedItem();

            CurrencyEntity fromCurrency = findCurrencyByCode(fromCode);
            CurrencyEntity toCurrency = findCurrencyByCode(toCode);

            if (fromCurrency == null || toCurrency == null) {
                Toast.makeText(this, "Nie można znaleźć wybranej waluty.", Toast.LENGTH_SHORT).show();
                return;
            }

            double result;
            if (!fromCode.equals("PLN") && toCode.equals("PLN")) {
                result = amount * fromCurrency.rate;
            } else if (fromCode.equals("PLN") && !toCode.equals("PLN")) {
                result = amount / toCurrency.rate;
            } else if (!fromCode.equals("PLN") && !toCode.equals("PLN")) {
                result = amount * (fromCurrency.rate / toCurrency.rate);
            } else {
                result = amount;
            }

            String resultText = String.format("%,.2f %s = %,.2f %s", amount, fromCode, result, toCode);
            binding.textViewResult.setText(resultText);

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Nieprawidłowy format kwoty", Toast.LENGTH_SHORT).show();
        }
    }

    private CurrencyEntity findCurrencyByCode(String code) {
        return currencyList.stream()
                .filter(currency -> currency.code.equals(code))
                .findFirst()
                .orElse(null);
    }
}