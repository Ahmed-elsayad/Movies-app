package com.example.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Movies {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("Title")
    @Expose
    private String title;

    @SerializedName("release_date")
    @Expose
    private String release_date;

    @SerializedName("poster_path")
    @Expose
    private String poster_path;

    @SerializedName("vote_average")
    @Expose
    private float vote_average;

    @SerializedName("overview")
    @Expose
    private String overview;

    @SerializedName("runtime")
    @Expose
    private int runtime;

    @SerializedName("genre_ids")
    @Expose
    private ArrayList<Integer> genre_ids;
    private ArrayList<String> genre_names;
    private ArrayList<Genres> genres;

    public Movies() {
    }

    public Movies(int id, String title, String release_date, String poster_path, float vote_average, String overview, int runtime, ArrayList<Integer> genre_ids, ArrayList<String> genre_names, ArrayList<Genres> genres) {
        this.id = id;
        this.title = title;
        this.release_date = release_date;
        this.poster_path = poster_path;
        this.vote_average = vote_average;
        this.overview = overview;
        this.runtime = runtime;
        this.genre_ids = genre_ids;
        this.genre_names = genre_names;
        this.genres = genres;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public float getVote_average() {
        return vote_average;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public ArrayList<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(ArrayList<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    public ArrayList<String> getGenre_names() {
        return genre_names;
    }

    public void setGenre_names(ArrayList<String> genre_names) {
        this.genre_names = genre_names;
    }

    public ArrayList<Genres> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<Genres> genres) {
        this.genres = genres;
    }
}
