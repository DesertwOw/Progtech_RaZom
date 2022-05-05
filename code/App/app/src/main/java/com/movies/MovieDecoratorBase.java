package com.movies;

public class MovieDecoratorBase extends MovieBase{
    private MovieBase movie;
    public MovieDecoratorBase(MovieBase m){movie = m;}
    @Override
    public String getActors() {return movie.getActors(); }

    @Override
    public String getDirector() {return movie.getDirector(); }

    @Override
    public int getRating() {return movie.getRating(); }

    @Override
    public Movie_playedRole getPlayedRole() {return movie.getPlayedRole(); }
}
