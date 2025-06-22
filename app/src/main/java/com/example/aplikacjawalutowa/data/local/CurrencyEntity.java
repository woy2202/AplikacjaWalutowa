package com.example.aplikacjawalutowa.data.local;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.io.Serializable;


@Entity(tableName = "currencies")
public class CurrencyEntity implements Serializable {

    @PrimaryKey
    @NonNull
    public String code;

    public String currencyName;

    public double rate;


    public CurrencyEntity(@NonNull String code, String currencyName, double rate) {
        this.code = code;
        this.currencyName = currencyName;
        this.rate = rate;
    }
}