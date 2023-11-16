package com.example.androidsensor.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ThreeValue.class},version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "threevalueList";
    private static AppDatabase sInstance;


}
