package com.example.movieapp.Others;

import java.util.ArrayList;

public class Tmdb2 {
    private final ArrayList<TmdbResponse> results;

    public Tmdb2(ArrayList<TmdbResponse> results) {
        this.results = results;
    }

    public ArrayList<TmdbResponse> getResults() {
        return results;
    }
}
