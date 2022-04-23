package com.movies;

public class Main {

    public static void main(String args[])  //static method
    {
        Studio universalPictures = new UniversalPictures();
        ActionMovie Casper = universalPictures.createActionMovie();

        System.out.println(Casper.getTitle());
    }
}
