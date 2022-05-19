package com.movies.Factory;

import com.movies.Factory.ActionMovie;
import com.movies.Factory.ComedyMovie;

public abstract class Studio {

    public abstract ActionMovie createActionMovie(String studio, String category, String name, int length);
    public abstract ComedyMovie createComedyMovie(String studio, String category, String name, int length);

}
