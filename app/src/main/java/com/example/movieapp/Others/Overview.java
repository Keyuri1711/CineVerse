package com.example.movieapp.Others;

import java.util.ArrayList;

public class Overview {
    private String original_title;
    private String overview;
    private String first_air_date;
    private String status;
    private String release_date;
    private int runtime;
    private String poster_path;
    private float vote_average;
    private String backdrop_path;
    private ArrayList<Genres> genres;

    public String getOriginal_title() {
        return original_title;
    }

    public String getOverview() {
        return overview;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public String getStatus() {
        return status;
    }

    public String getRelease_date() {
        return release_date;
    }

    public int getRuntime() {
        return runtime;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public float getVote_average() {
        return vote_average;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public ArrayList<Genres> getGenres() {
        return genres;
    }
}
