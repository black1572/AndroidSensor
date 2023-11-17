package com.example.androidsensor.db;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ThreeValue.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ThreeValueDAO threeValueDAO();

    public static AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                Log.d("gao", "getInstance: ");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                                AppDatabase.class, AppDatabase.DATABASE_NAME)
                        .build();
            }
        }
        Log.d("gao", "getInstance: finish");
        return sInstance;
    }
    public abstract ThreeValueDAO threeValueDAO();


}
