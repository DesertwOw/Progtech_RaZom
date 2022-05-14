package com.movies;

public class Actor extends MovieDecoratorBase{
    public Actor(MovieBase m) {
        super(m);
    }
    private String actor_name;
    private int age;
    private Movie_playedRole playedRole;
    private Gender gender;

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getActor_name() {
        return actor_name;
    }

    public void setActor_name(String actor_name) {
        this.actor_name = actor_name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Movie_playedRole getPlayedRole() {
        return playedRole;
    }

    public void setPlayedRole(Movie_playedRole playedRole) {
        this.playedRole = playedRole;
    }
}
