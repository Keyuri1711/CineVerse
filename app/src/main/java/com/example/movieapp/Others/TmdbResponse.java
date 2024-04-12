package com.example.movieapp.Others;

import java.util.Date;

public class TmdbResponse {

    private final int id;
    private final String title;
    private final Date release_date;
    private final float vote_average;
    private final String poster_path;
    private final String backdrop_path;

    public TmdbResponse(int id, String title, Date release_date, float vote_average, String poster_path, String backdrop_path) {
        this.id = id;
        this.title = title;
        this.release_date = release_date;
        this.vote_average = vote_average;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Date getRelease_date() {
        return release_date;
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

