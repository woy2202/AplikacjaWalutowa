package com.example.aplikacjawalutowa.data.remote.dto;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ExchangeRatesTable {
    @SerializedName("effectiveDate")
    private String effectiveDate;

    @SerializedName("rates")
    private List<Rate> rates;


    public String getEffectiveDate() {
        return effectiveDate;
    }

    public List<Rate> getRates() {
        return rates;
    }
}