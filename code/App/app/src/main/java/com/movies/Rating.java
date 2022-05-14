package com.movies;

public class Rating extends MovieDecoratorBase{
    public Rating(MovieBase m) {
        super(m);
    }
    private int rate;

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
