package com.movies;

public class ComedyMovie extends Genres{

    private String studio;
    private String category;
    private String name;
    private int length;

    public ComedyMovie(String studio, String category, String name, int length){
        this.studio = studio;
        this.category = category;
        this.name = name;
        this.length = length;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String toString() {
        return  "studio='"+studio+'\''+
                "category='"+category+ '\''+
                "name='"+name+ '\''+
                ", length='" + length + '\''+
                '!';
    }
}
