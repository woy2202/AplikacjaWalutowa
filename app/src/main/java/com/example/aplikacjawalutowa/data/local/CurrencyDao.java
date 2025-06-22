package com.example.aplikacjawalutowa.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CurrencyDao {


    @Query("SELECT * FROM currencies ORDER BY code ASC")
    LiveData<List<CurrencyEntity>> getAllCurrencies();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<CurrencyEntity> currencies);


    @Query("DELETE FROM currencies")
    void deleteAll();
}