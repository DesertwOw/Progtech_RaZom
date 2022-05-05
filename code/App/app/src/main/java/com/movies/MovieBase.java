package com.movies;

public abstract class MovieBase {
    public abstract String getActors();
    private String actors;

    public abstract String getDirector();
    private String director;

    public abstract int getRating();
    private int rating;

    public abstract Movie_playedRole getPlayedRole();
    private Movie_playedRole playedRole;
}
