package com.example.movieapp.Others;

public class TmdbTvResponse {

    private final int id;
    private final String name;
    private final float vote_average;
    private final String poster_path;
    private final String backdrop_path;

    public TmdbTvResponse(int id, String name, float vote_average, String poster_path, String backdrop_path) {
        this.id = id;
        this.name = name;
        this.vote_average = vote_average;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getVote_average() {
        return vote_average;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }
}

