package com.example.androidsensor.db;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ThreeValue.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final Object sLock = new Object();
    private static AppDatabase INSTANCE;

    public abstract ThreeValueDAO threeValueDAO();


    public static AppDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE =
                        Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "roomName.db").allowMainThreadQueries().build();
            }
            return INSTANCE;
        }

    }
}