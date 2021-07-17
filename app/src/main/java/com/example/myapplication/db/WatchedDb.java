package com.example.myapplication.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Watched.class},version = 5,exportSchema = false)
public abstract class WatchedDb extends RoomDatabase {
    public  abstract  WatchedDao watchedDao();
}
