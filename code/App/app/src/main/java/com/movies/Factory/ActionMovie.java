package com.movies.Factory;

public class ActionMovie extends Genres {
    private String studio;
    private String category;
    private String name;
    private int length;


    public ActionMovie(String studio,String category, String name, int length){
        this.studio = studio;
        this.category = category;
        this.name = name;
        this.length = length;

    }

    @Override
    public String getTitle() {
        return name;
    }


    @Override
    public String toString() {
        return  "studio='"+studio+'\''+
                "category='"+category+ '\''+
                "name='"+name+ '\''+
                ", length='" + length + '\''+
                '!';
    }

    @Override
    public String getStudio() {
        return studio;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getLength() {
        return length;
    }
}
