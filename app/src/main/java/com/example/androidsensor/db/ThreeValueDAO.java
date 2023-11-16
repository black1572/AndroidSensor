package com.example.androidsensor.db;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

public interface ThreeValueDAO {
    @Query("SELECT * FROM threevaluetable ORDER ID")
    List<ThreeValue>loadAllThreeValue();
    @Insert
    void insertPerson(ThreeValue threeValue);

    @Update
    void updatePerson(ThreeValue threeValue);

    @Delete
    void deletePerson(ThreeValue threeValue);

    @Query("SELECT * FROM threevaluetable WHERE id = :id")
    ThreeValue loadthreevalueById(int id);
}
