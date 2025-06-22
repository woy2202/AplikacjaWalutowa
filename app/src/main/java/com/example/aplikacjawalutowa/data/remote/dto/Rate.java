package com.example.aplikacjawalutowa.data.remote.dto;

import com.google.gson.annotations.SerializedName;

public class Rate {
    @SerializedName("currency")
    private String currency;

    @SerializedName("code")
    private String code;

    @SerializedName("mid")
    private double mid;


    public String getCurrency() {
        return currency;
    }

    public String getCode() {
        return code;
    }

    public double getMid() {
        return mid;
    }
}