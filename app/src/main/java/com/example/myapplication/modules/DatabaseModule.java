package com.example.myapplication.modules;

import android.app.Application;

import androidx.room.Room;

import com.example.myapplication.db.MovieDao;
import com.example.myapplication.db.MoviesDb;
import com.example.myapplication.db.WantDao;
import com.example.myapplication.db.WantDb;
import com.example.myapplication.db.WatchedDao;
import com.example.myapplication.db.WatchedDb;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DatabaseModule {
    @Provides
    @Singleton
    public static MoviesDb provideMoviesDb(Application application)
    {
        return Room.databaseBuilder( application, MoviesDb.class
                , "FavoriteDB").fallbackToDestructiveMigration().allowMainThreadQueries().build();
    }

    @Provides
    @Singleton
    public static WantDb provideWantDb(Application application)
    {
        return Room.databaseBuilder( application, WantDb.class
                , "WantDB").fallbackToDestructiveMigration().allowMainThreadQueries().build();
    }
    @Provides
    @Singleton
    public static WatchedDb provideWatchDb(Application application)
    {
        return Room.databaseBuilder( application, WatchedDb.class
                , "WatchedDB").fallbackToDestructiveMigration().allowMainThreadQueries().build();
    }

    @Provides
    @Singleton
    public static MovieDao provideMoviesDao(MoviesDb moviesDb)
    {
        return moviesDb.movieDao();
    }

    @Provides
    @Singleton
    public static
    WatchedDao provideWatchedDao(WatchedDb watchedDb)
    {
        return watchedDb.watchedDao();
    }

    @Provides
    @Singleton
    public static WantDao provideWantDao(WantDb wantDb)
    {
        return wantDb.wantDao();
    }


}
