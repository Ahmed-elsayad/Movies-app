package com.example.myapplication.utils;

import java.util.HashMap;

public class Info {
    //https://api.themoviedb.org/3/movie/550?api_key=97d588d9bbcb9e5d8281f4d02644fd38

    public static final String BASE_URL = "https://api.themoviedb.org/";

    public static final String API_KEY = "97d588d9bbcb9e5d8281f4d02644fd38";

    public static final String Popular = "Popular";
    public static final String TopRated = "TopRated";
    public static final String Upcoming = "Upcoming";
    public static final String Latest = "Latest";

    public static HashMap<Integer,String> getGenreMap(){
        HashMap<Integer,String> genreMap = new HashMap<>();
        genreMap.put(28,"Action");
        genreMap.put(12,"Adventure");
        genreMap.put(16,"Animation");
        genreMap.put(35,"Comedy");
        genreMap.put(80,"Crime");
        genreMap.put(99,"Documentary");
        genreMap.put(18,"Drama");
        genreMap.put(10751,"Family");
        genreMap.put(14,"Fantasy");
        genreMap.put(36,"History");
        genreMap.put(27,"Horror");
        genreMap.put(10402,"Music");
        genreMap.put(9648,"Mystery");
        genreMap.put(10749,"Romance");
        genreMap.put(878,"Science Fiction");
        genreMap.put(53,"Thriller");
        genreMap.put(10752,"War");
        genreMap.put(37,"Western");
        genreMap.put(10770,"TV Movie");

        return genreMap;
    }




}
