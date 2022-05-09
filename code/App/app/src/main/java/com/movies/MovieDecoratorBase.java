package com.movies;

public class MovieDecoratorBase extends MovieBase{
    private MovieBase movie;
    public MovieDecoratorBase(MovieBase m){movie = m;}
    @Override
    public String getStudio() {
        return movie.getStudio();
    }

    @Override
    public String getCategory() {
        return movie.getCategory();
    }

    @Override
    public String getName() {
        return movie.getName();
    }

    @Override
    public int getLength() {
        return movie.getLength();
    }
}
