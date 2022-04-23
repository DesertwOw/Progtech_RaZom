package com.movies;

public class ActionMovie extends Genres{
    private String name;
    private int length;

    public ActionMovie(String name, int length){
        this.name = name;
        this.length = length;
    }

    @Override
    public String getTitle() {
        return name;
    }

    @Override
    public String toString() {
        return "ActionMovie{" +
                "name='"+name+ '\''+
                ", length='" + length + '\''+
                '}';
    }
}
