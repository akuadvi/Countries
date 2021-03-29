package com.example.countries;

import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@androidx.room.Database(entities = countries.class,version = 5)
@TypeConverters({Converters.class})

public abstract class Database extends RoomDatabase{
    public abstract DAO dao();
}
