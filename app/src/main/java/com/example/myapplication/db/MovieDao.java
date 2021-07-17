package com.example.myapplication.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MovieDao {

    @Insert
    void insertMovie(Favorite favorite);

    @Query("DELETE FROM favorite_table WHERE id = :MovieId ")
    void deleteMovie(int MovieId);

    @Query("DELETE FROM favorite_table")
    void deleteAll();

    @Query("SELECT * FROM favorite_table")
    LiveData<List<Favorite>> getFavoriteList();

    @Query("SELECT * FROM favorite_table WHERE id = :movieId")
    Favorite getFavoriteMovie(int movieId);

}
