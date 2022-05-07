package com.movies;

public class responseModel {

    String movie_id, movie_studio, movie_category, movie_name;
    int movie_lenght;

    public responseModel() {

    }

    public responseModel(String movie_id, String movie_studio, String movie_category, String movie_name, Integer movie_length){
        this.movie_id = movie_id;
        this.movie_studio = movie_studio;
        this.movie_category = movie_category;
        this.movie_name = movie_name;
        this.movie_lenght = movie_length;

    }

    public String getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(String movie_id) {
        this.movie_id = movie_id;
    }

    public String getMovie_studio() {
        return movie_studio;
    }

    public void setMovie_studio(String movie_studio) {
        this.movie_studio = movie_studio;
    }

    public String getMovie_category() {
        return movie_category;
    }

    public void setMovie_category(String movie_category) {
        this.movie_category = movie_category;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public int getMovie_lenght() {
        return movie_lenght;
    }

    public void setMovie_lenght(int movie_lenght) {
        this.movie_lenght = movie_lenght;
    }
}
