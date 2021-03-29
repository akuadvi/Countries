package com.example.countries;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DAO {
    @Insert
    public void add(countries c);

    @Query("Select * from countries")
    public List<countries> get();

    @Query("DELETE FROM countries")
    void delete();
}
