package com.movies;

import static org.junit.Assert.*;

import org.junit.Test;

public class Api_functionsTest {

    @Test
    public void login() {
        String name = "balint";
        String passwd = "2000";
        Api_functions api = new Api_functions();
        api.Login(name, passwd);
    }

    @Test
    public void Regist(){
        String name = "Ádám";
        String passwd = "0000";
        String firstname = "Ádám";
        String lastname = "Éva";
        String email = "adam@eva.com";
        Api_functions api = new Api_functions();
        api.Register(name, passwd, firstname, lastname, email);
    }

    @Test
    public void MovieAdd(){
        String name = "Up";
        String category = "Comedy";
        String studio = "Pixar";
        String length = "86";
        String rate = "3.5";
        Api_functions api = new Api_functions();
        api.AddMovie(studio, category, name, length, rate);
    }

    @Test
    public void ActorAdd(){
        String name = "Tesztelő János";
        String age = "32";
        String gender = "Man";
        Api_functions api = new Api_functions();
        api.AddActor(name, age, gender);
    }

    @Test
    public void DirectorAdd(){
        String name = "Rendező Enikő";
        Api_functions api = new Api_functions();
        api.AddDirector(name);
    }
}