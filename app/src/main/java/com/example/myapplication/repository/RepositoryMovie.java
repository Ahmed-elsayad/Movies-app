package com.example.myapplication.repository;

import androidx.lifecycle.LiveData;

import com.example.myapplication.db.Favorite;
import com.example.myapplication.db.MovieDao;
import com.example.myapplication.db.Want;
import com.example.myapplication.db.WantDao;
import com.example.myapplication.db.Watched;
import com.example.myapplication.db.WatchedDao;
import com.example.myapplication.model.Movies;
import com.example.myapplication.response.MoviesListResponse;
import com.example.myapplication.utils.MovieApiService;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class RepositoryMovie {

    private MovieDao movieDao;
    private WatchedDao watchedDao;
    private WantDao wantDao;
    private MovieApiService apiService;

    @Inject
    public RepositoryMovie(MovieDao movieDao, WatchedDao watchedDao, WantDao wantDao, MovieApiService apiService) {
        this.movieDao = movieDao;
        this.watchedDao = watchedDao;
        this.wantDao = wantDao;
        this.apiService = apiService;
    }

    public Observable<MoviesListResponse> getSearchMovie(HashMap<String,String> map)
    {
        return apiService.getSearchedMove(map);
    }

    public Observable<MoviesListResponse> getPopularMovies (HashMap<String,String> map)
    {
        return apiService.getPopularMovies(map);
    }

    public Observable<MoviesListResponse> getTopRatedMovies(HashMap<String, String> map) {
        return apiService.getTopRatedMovies(map);
    }

    public Observable<MoviesListResponse> getLatestMovies(HashMap<String, String> map) {
        return apiService.getLatestMovies(map);
    }

    public Observable<MoviesListResponse> getUpcomingMovies(HashMap<String, String> map) {
        return apiService.getUpcomingMovies(map);
    }

    public Observable<Movies> getMovieDetail (int id , HashMap<String,String> map)
    {
        return apiService.getMovieDetial(id,map);
    }

    public void insertMovie (Favorite favorite)
    {
        movieDao.insertMovie(favorite);
    }

    public void deleteMovie(int movieId) {
        movieDao.deleteMovie(movieId);
    }

    public void deleteAll() {
        movieDao.deleteAll();
    }

    public LiveData<List<Favorite>> getFavoriteList() {
        return movieDao.getFavoriteList();
    }

    public Favorite getFavoriteMovie(int moviesId) {
        return movieDao.getFavoriteMovie(moviesId);
    }




    public void insertWatched(Watched watched){
        watchedDao.insertWatched(watched);
    }

    public void deleteWatched(int movieId){
        watchedDao.deleteWatched(movieId);
    }

    public void deleteAllWatched(){
        watchedDao.deleteAllWatchedList();
    }

    public LiveData<List<Watched>> getWatchedList(){
        return watchedDao.getWatchedList();
    }

    public Watched getWatchedMovie(int moviesId) {
        return watchedDao.getWatchedMovie(moviesId);
    }



    public void insertWant(Want want){
        wantDao.insertWanted(want);
    }

    public void deleteWant(int movieId){
        wantDao.deleteWant(movieId);
    }

    public void deleteAllWant(){
        wantDao.deleteAllWant();
    }

    public LiveData<List<Want>> getWantList(){ return wantDao.getWantList();}

    public Want getWantMovie(int moviesId) {
        return wantDao.getWantMovie(moviesId);
    }


}
