package com.example.myapplication.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Want.class}, version = 5, exportSchema = false)
public abstract class WantDb extends RoomDatabase {

    public abstract WantDao wantDao();

}
