package com.movies;

import static org.junit.Assert.*;

import org.junit.Test;

public class ActorTest {
    @Test
    public void DecoratorTest(){
        ActionMovie movie = new ActionMovie("Pixar","Comedy", "Üvegtigris", 140 );
        Actor actor = new Actor(movie);
        actor.setActor_name("Teszt Elek");
        actor.setGender(Gender.Man);
        actor.setAge(25);
        actor.setPlayedRole(Movie_playedRole.Main_Character);

        System.out.println(actor.getName() + " - " + actor.getStudio() + " - " + actor.getCategory() + " - " + actor.getLength() + " - " + actor.getActor_name() + " - " + actor.getAge() + " - " + actor.getGender() + " - " + actor.getPlayedRole());
    }

}