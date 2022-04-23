package com.movies;

public class UniversalPictures extends Studio {
    @Override
    public ActionMovie createActionMovie(String name, int length) {
        return new ActionMovie(name,length);
    }
    public ComedyMovie createComedyMovie()
    {
        return null;
    }
}
