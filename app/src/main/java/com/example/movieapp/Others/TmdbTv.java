package com.example.movieapp.Others;

import java.util.ArrayList;

public class TmdbTv {

    private final ArrayList<TmdbTvResponse> results;

    public TmdbTv(ArrayList<TmdbTvResponse> results) {
        this.results = results;
    }

    public ArrayList<TmdbTvResponse> getResults() {
        return results;
    }
}
