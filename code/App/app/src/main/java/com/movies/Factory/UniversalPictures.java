package com.movies.Factory;

import com.movies.Factory.ActionMovie;
import com.movies.Factory.ComedyMovie;
import com.movies.Factory.Studio;

public class UniversalPictures extends Studio {

    @Override
    public ActionMovie createActionMovie(String studio, String category, String name, int length) {
        return new ActionMovie(studio, category, name,length);
    }
    public ComedyMovie createComedyMovie(String studio, String category, String name, int length)
    {
        return  new ComedyMovie(studio,category,name,length);
    }


}
