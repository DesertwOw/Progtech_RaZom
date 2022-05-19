package com.movies;

import com.movies.Factory.ActionMovie;
import com.movies.Factory.ComedyMovie;

import org.junit.Test;

public class ContainerTest {

    @Test
    public void fillContainer() {
        Container.Movies.clear();
         String studio = null;
         String title = null;
         String category = null;
         String length = null;
         Container.collectData();
         Container.fillContainer(studio, title, category, length);

        for(int i = 0; i < Container.Movies.size(); i++){
            System.out.println(Container.Movies.get(i).toString());
        }
        Container.Movies.clear();

    }

    @Test
    public void ContainerListTest(){
        Container.Movies.clear();
        ActionMovie action = new ActionMovie("Universal Pictures", "Action", "Halálos Iramban", 120);
        ActionMovie action1 = new ActionMovie("Universal Pictures", "Action", "Harry Potter", 150);
        ActionMovie action2 = new ActionMovie("Marvel", "Action", "Pókember", 127);
        ComedyMovie comedy = new ComedyMovie("Pixar", "Comedy", "Szörny Rt", 100);
        ComedyMovie comedy1 = new ComedyMovie("Pixar", "Comedy", "Toy Story", 97);
        ComedyMovie comedy2 = new ComedyMovie("Pixar", "Comedy", "Fel", 86);
        Container.Movies.add(action);
        Container.Movies.add(action1);
        Container.Movies.add(action2);
        Container.Movies.add(comedy);
        Container.Movies.add(comedy1);
        Container.Movies.add(comedy2);
        for(int i = 0; i < Container.Movies.size(); i++){
            System.out.println(Container.Movies.get(i).toString());
        }
        Container.Movies.clear();
    }
}