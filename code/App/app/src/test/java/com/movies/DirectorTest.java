package com.movies;

import com.movies.Decorators.Director;
import com.movies.Factory.ComedyMovie;

import org.junit.Test;

public class DirectorTest {
    @Test
    public void DecoratorTest(){
        ComedyMovie movie = new ComedyMovie(
                "Pixar",
                "Action",
                "Star Wars",
                270
        );
        Director director = new Director(movie);
        director.setDirector_name("George Lucas");

        System.out.println(director.getDirector_name() + " - " + director.getName() + " - " + director.getCategory() + " - " + director.getLength() + " - " + director.getStudio());
        Directors dir = new Directors();
        dir.AddDirector(director.getDirector_name());

    }

}