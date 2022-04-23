package com.movies;

public class Pixar extends Studio{
    @Override
    public ActionMovie createActionMovie(String name, int length) {
        return null;
    }

    @Override
    public ComedyMovie createComedyMovie() {
        return new ComedyMovie("Üvegtigris",100);
    }
}
