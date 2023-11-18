package com.example.androidsensor.db;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Entity(tableName = "threevaluetable")
public class ThreeValue {
    private static final Object LOCK = new Object();
    private static ThreeValue sInstance;
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;
    public float x;
    public float y;
    public float z;
    public float fyj;


    public ThreeValue(float x, float y, float z, float fyj) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.fyj = fyj;
    }

  /*  @Ignore
    public void threevalue(int id, float x, float y, float z, float fyj){
        this.id = id;
        this.x = x;
        this.y = y;
        this.z = z;
        this.fyj = fyj;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public void setFyj(float fyj) {
        this.fyj = fyj;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public float getFyj() {
        return fyj;
    }



}
