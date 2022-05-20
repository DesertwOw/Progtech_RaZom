package com.movies.Decorators;

import com.movies.Decorators.MovieBase;
import com.movies.Decorators.MovieDecoratorBase;

public class Rating extends MovieDecoratorBase {
    public Rating(MovieBase m) {
        super(m);
    }
    private float rate;

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
}
