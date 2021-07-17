package com.example.myapplication.utils;

import com.example.myapplication.model.Movies;
import com.example.myapplication.response.MoviesListResponse;

import java.util.HashMap;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface MovieApiService {

    //https://api.themoviedb.org/3/movie/popular?api_key=<>
    @GET("/3/movie/popular")
    io.reactivex.rxjava3.core.Observable<MoviesListResponse> getPopularMovies(
            @QueryMap HashMap<String,String> queries
    );

    //https://api.themoviedb.org/3/search/movie?api_key={api_key}&query=Jack+Reacher
    @GET("/3/search/movie")
    io.reactivex.rxjava3.core.Observable<MoviesListResponse> getSearchedMove(
            @QueryMap HashMap<String,String> queries
    );

    //https://api.themoviedb.org/3/movie/top_rated?api_key=<api_key>
    @GET("/3/movie/top_rated")
    io.reactivex.rxjava3.core.Observable<MoviesListResponse> getTopRatedMovies(
            @QueryMap HashMap<String,String> queries
    );

    //https://api.themoviedb.org/3/movie/now_playing?api_key=<<api_key>>
    @GET("/3/movie/now_playing")
    io.reactivex.rxjava3.core.Observable<MoviesListResponse> getLatestMovies(
            @QueryMap HashMap<String,String> queries
    );

    //https://api.themoviedb.org/3/movie/upcoming?api_key=<<api_key>>
    @GET("/3/movie/upcoming")
    Observable<MoviesListResponse> getUpcomingMovies (
            @QueryMap HashMap<String,String> queries
    );

    //https://api.themoviedb.org/3/movie/{movie_id}?api_key=<<api_key>>
    @GET("/3/movie/{movie_id}")
    io.reactivex.rxjava3.core.Observable<Movies> getMovieDetial(
            @Path("movie_id") int id,
            @QueryMap HashMap<String,String> queries
    );


}
