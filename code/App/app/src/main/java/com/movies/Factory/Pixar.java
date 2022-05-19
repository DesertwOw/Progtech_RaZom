package com.movies.Factory;

public class Pixar extends Studio {

    @Override
    public ActionMovie createActionMovie(String studio, String category, String name, int length) {
        return new ActionMovie("UniversalPictures","Comedy","asdasd",123);
    }

    @Override
    public ComedyMovie createComedyMovie(String studio, String category, String name, int length) {
        return new ComedyMovie("Pixar", "Comedy", "Üvegtigris",100);
    }



}
