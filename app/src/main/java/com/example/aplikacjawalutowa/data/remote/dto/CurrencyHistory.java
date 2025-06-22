package com.example.aplikacjawalutowa.data.remote.dto;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class CurrencyHistory {
    @SerializedName("code")
    private String code;

    @SerializedName("rates")
    private List<HistoryRate> rates;

    public String getCode() {
        return code;
    }

    public List<HistoryRate> getRates() {
        return rates;
    }

    public static class HistoryRate {
        @SerializedName("effectiveDate")
        private String effectiveDate;

        @SerializedName("mid")
        private double mid;

        public String getEffectiveDate() {
            return effectiveDate;
        }

        public double getMid() {
            return mid;
        }
    }
}