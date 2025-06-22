package com.example.aplikacjawalutowa.data.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.aplikacjawalutowa.data.local.AppDatabase;
import com.example.aplikacjawalutowa.data.local.CurrencyDao;
import com.example.aplikacjawalutowa.data.local.CurrencyEntity;
import com.example.aplikacjawalutowa.data.remote.NbpApiService;
import com.example.aplikacjawalutowa.data.remote.dto.CurrencyHistory;
import com.example.aplikacjawalutowa.data.remote.dto.ExchangeRatesTable;
import com.example.aplikacjawalutowa.data.remote.dto.Rate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CurrencyRepository {


    private static volatile CurrencyRepository INSTANCE;

    private final CurrencyDao currencyDao;
    private final NbpApiService nbpApiService;
    private final ExecutorService executorService;

    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();


    private CurrencyRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        this.currencyDao = db.currencyDao();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.nbp.pl/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.nbpApiService = retrofit.create(NbpApiService.class);

        this.executorService = Executors.newSingleThreadExecutor();
    }


    public static CurrencyRepository getInstance(final Application application) {
        if (INSTANCE == null) {
            synchronized (CurrencyRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CurrencyRepository(application);
                }
            }
        }
        return INSTANCE;
    }

    public LiveData<String> getErrorMessage() { return errorMessage; }
    public LiveData<Boolean> getIsLoading() { return isLoading; }
    public LiveData<List<CurrencyEntity>> getAllCurrencies() {
        return currencyDao.getAllCurrencies();
    }


    public void refreshCurrencies() {
        isLoading.postValue(true);
        nbpApiService.getExchangeRatesTableA().enqueue(new Callback<List<ExchangeRatesTable>>() {
            @Override
            public void onResponse(Call<List<ExchangeRatesTable>> call, Response<List<ExchangeRatesTable>> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                    List<Rate> rates = response.body().get(0).getRates();
                    saveRatesToDatabase(rates);
                } else {
                    errorMessage.postValue("Błąd pobierania danych: " + response.code());
                }
                isLoading.postValue(false);
            }

            @Override
            public void onFailure(Call<List<ExchangeRatesTable>> call, Throwable t) {
                errorMessage.postValue("Błąd sieci: " + t.getMessage());
                isLoading.postValue(false);
            }
        });
    }

    private void saveRatesToDatabase(List<Rate> rates) {
        executorService.execute(() -> {
            List<CurrencyEntity> entities = new ArrayList<>();
            for (Rate rate : rates) {
                entities.add(new CurrencyEntity(rate.getCode(), rate.getCurrency(), rate.getMid()));
            }
            entities.add(new CurrencyEntity("PLN", "polski złoty", 1.0));

            currencyDao.deleteAll();
            currencyDao.insertAll(entities);
        });
    }

    public void fetchCurrencyHistory(String code, MutableLiveData<CurrencyHistory> historyData) {
        isLoading.postValue(true);
        nbpApiService.getCurrencyHistory(code).enqueue(new Callback<CurrencyHistory>() {
            @Override
            public void onResponse(Call<CurrencyHistory> call, Response<CurrencyHistory> response) {
                if (response.isSuccessful()) {
                    historyData.postValue(response.body());
                } else {
                    errorMessage.postValue("Błąd pobierania historii: " + response.code());
                }
                isLoading.postValue(false);
            }

            @Override
            public void onFailure(Call<CurrencyHistory> call, Throwable t) {
                errorMessage.postValue("Błąd sieci: " + t.getMessage());
                isLoading.postValue(false);
            }
        });
    }
}