package com.example.aplikacjawalutowa.ui.history;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.aplikacjawalutowa.data.remote.dto.CurrencyHistory;
import com.example.aplikacjawalutowa.data.repository.CurrencyRepository;

public class HistoryViewModel extends AndroidViewModel {
    private final CurrencyRepository repository;
    private final MutableLiveData<CurrencyHistory> currencyHistory = new MutableLiveData<>();

    public HistoryViewModel(@NonNull Application application) {
        super(application);
        repository = CurrencyRepository.getInstance(application);
    }

    public LiveData<CurrencyHistory> getCurrencyHistory() {
        return currencyHistory;
    }

    public LiveData<Boolean> getIsLoading() {
        return repository.getIsLoading();
    }

    public LiveData<String> getErrorMessage() {
        return repository.getErrorMessage();
    }

    public void fetchHistory(String code) {
        repository.fetchCurrencyHistory(code, currencyHistory);
    }
}