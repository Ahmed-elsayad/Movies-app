package com.example.myapplication.db;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WantDao {

    @Insert
    void insertWanted(Want want);

    @Query("DELETE FROM want_table WHERE Id = :moviesId")
    void deleteWant(int moviesId);

    @Query("DELETE FROM want_table")
    void deleteAllWant();

    @Query("SELECT * FROM want_table")
    LiveData<List<Want>> getWantList();

    @Query("SELECT * FROM want_table WHERE  Id = :moviesId")
    Want getWantMovie(int moviesId);


}
