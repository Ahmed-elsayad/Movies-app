package com.example.myapplication.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.db.Favorite;
import com.example.myapplication.repository.RepositoryMovie;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class FavoriteViewModel extends ViewModel {

    private RepositoryMovie repositoryMovie;
    private LiveData<List<Favorite>> favoriteMoviesList;

    @Inject
    public FavoriteViewModel(RepositoryMovie repositoryMovie) {
        this.repositoryMovie = repositoryMovie;
        favoriteMoviesList = repositoryMovie.getFavoriteList();
    }

    public LiveData<List<Favorite>> getFavoriteMoviesList() {
        return favoriteMoviesList;
    }

    public void deleteAll ()
    {
        repositoryMovie.deleteAll();
    }
}
