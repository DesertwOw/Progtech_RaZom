package com.movies;

public class ComedyMovie extends Genres{

    private String name;
    private int length;

    public ComedyMovie(String name, int length){
        this.name = name;
        this.length = length;
    }

    @Override
    public String getTitle() {
        return null;
    }
}
