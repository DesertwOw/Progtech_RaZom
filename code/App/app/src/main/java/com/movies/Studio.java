package com.movies;

import java.util.ArrayList;
import java.util.List;

public abstract class Studio {

    public abstract ActionMovie createActionMovie(String studio,String category, String name, int length);
    public abstract ComedyMovie createComedyMovie(String studio,String category, String name, int length);

}
