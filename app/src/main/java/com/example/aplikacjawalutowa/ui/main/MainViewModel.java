package com.example.aplikacjawalutowa.ui.main;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.aplikacjawalutowa.data.local.CurrencyEntity;
import com.example.aplikacjawalutowa.data.repository.CurrencyRepository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private final CurrencyRepository repository;
    private final LiveData<List<CurrencyEntity>> allCurrencies;
    private final LiveData<String> errorMessage;
    private final LiveData<Boolean> isLoading;

    public MainViewModel(@NonNull Application application) {
        super(application);
        // Pobieramy jedną, globalną instancję repozytorium
        repository = CurrencyRepository.getInstance(application);
        this.allCurrencies = repository.getAllCurrencies();
        this.errorMessage = repository.getErrorMessage();
        this.isLoading = repository.getIsLoading();

        repository.refreshCurrencies();
    }

    public LiveData<List<CurrencyEntity>> getAllCurrencies() {
        return allCurrencies;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public void refreshData() {
        repository.refreshCurrencies();
    }
}