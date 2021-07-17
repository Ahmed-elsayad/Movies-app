package com.example.myapplication.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Favorite.class}, version = 5,exportSchema = false)
public abstract class MoviesDb extends RoomDatabase {
    public abstract MovieDao movieDao();
}
