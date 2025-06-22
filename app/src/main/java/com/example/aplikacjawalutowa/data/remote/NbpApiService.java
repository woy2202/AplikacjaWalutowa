package com.example.aplikacjawalutowa.data.remote;

import com.example.aplikacjawalutowa.data.remote.dto.CurrencyHistory;
import com.example.aplikacjawalutowa.data.remote.dto.ExchangeRatesTable;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NbpApiService {


    @GET("api/exchangerates/tables/A?format=json")
    Call<List<ExchangeRatesTable>> getExchangeRatesTableA();


    @GET("api/exchangerates/rates/A/{code}/last/30?format=json")
    Call<CurrencyHistory> getCurrencyHistory(@Path("code") String currencyCode);
}