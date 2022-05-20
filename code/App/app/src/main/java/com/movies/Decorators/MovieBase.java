package com.movies.Decorators;

public abstract class MovieBase {
    private String studio;
    private String category;
    private String name;
    private int length;

    public abstract String getStudio();

    public abstract String getCategory();

    public abstract String getName();

    public abstract int getLength();

}
