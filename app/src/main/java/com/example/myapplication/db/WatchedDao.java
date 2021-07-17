package com.example.myapplication.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WatchedDao {

    @Insert
    void insertWatched(Watched watched);

    @Query("dELETE FROM watched_table WHERE Id = :moviesId")
    void deleteWatched(int moviesId);

    @Query("DELETE FROM watched_table")
    void deleteAllWatchedList ();

    @Query("SELECT * FROM watched_table")
    LiveData<List<Watched>> getWatchedList();

    @Query("SELECT * FROM watched_table WHERE Id = :moviesId")
    Watched getWatchedMovie (int moviesId);
}
