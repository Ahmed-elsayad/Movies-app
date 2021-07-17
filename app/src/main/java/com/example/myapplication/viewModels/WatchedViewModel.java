package com.example.myapplication.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.db.Watched;
import com.example.myapplication.repository.RepositoryMovie;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class WatchedViewModel extends ViewModel {
    private RepositoryMovie repository;
    private LiveData<List<Watched>> watchedMoviesList;

    @Inject
    public WatchedViewModel(RepositoryMovie repository) {
        this.repository = repository;
        watchedMoviesList = repository.getWatchedList();
    }

    public LiveData<List<Watched>> getWatchedListMovieList() {
        return watchedMoviesList;
    }

    public void deleteAllWatched(){
        repository.deleteAllWatched();
    }

}
