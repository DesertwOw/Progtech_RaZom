package com.movies;

import com.movies.Factory.ComedyMovie;

import org.junit.Test;

public class RatingTest {
    @Test
    public void DecoratorTest(){
        ComedyMovie movie = new ComedyMovie("Pixar", "Comedy", "Csodacsibe", 150);

        Rating rating = new Rating(movie);
        rating.setRate(5);

        System.out.println(rating.getStudio() + " - " + rating.getCategory() + " - " + rating.getName() + " - " + rating.getLength() + " - " + rating.getRate());
    }

}