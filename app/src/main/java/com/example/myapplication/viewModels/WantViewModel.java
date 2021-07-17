package com.example.myapplication.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.db.Want;
import com.example.myapplication.repository.RepositoryMovie;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class WantViewModel extends ViewModel {

    private RepositoryMovie repository;
    private LiveData<List<Want>> wantMoviesList;

    @Inject
    public WantViewModel(RepositoryMovie repository) {
        this.repository = repository;
        wantMoviesList = repository.getWantList();
    }

    public LiveData<List<Want>> getWantListMovieList() {
        return wantMoviesList;
    }

    public void deleteAllWant(){
        repository.deleteAllWant();
    }

}
