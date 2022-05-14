package com.movies;

public class Director extends MovieDecoratorBase{
    public Director(MovieBase m) {
        super(m);
    }
    private String director_name;

    public String getDirector_name() {
        return director_name;
    }

    public void setDirector_name(String name) {
        this.director_name = name;
    }
}
