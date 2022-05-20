package com.movies;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class Api_functions {

    public void Login(String username, String passwd){
        if(!username.equals("") && !passwd.equals("") ) {

            String[] field = new String[2];
            field[0] = "username";
            field[1] = "passwd";;
            String[] data = new String[2];
            data[0] = username;
            data[1] = passwd;
            PutData putData = new PutData(Config.showURL + "login.php", "POST", field, data);
            //cmd -> ipconfig -> ipv4 address
            if (putData.startPut()) {
                if (putData.onComplete()) {
                    String result = putData.getResult();
                    if (result.equals("Login Success")){
                        System.out.println("Login success");
                    }
                    else {
                        System.out.println("Login failed");
                    }
                }


            }
        }
        else {
            System.out.println("Missing datas");
        }
    }

    public void Register(String username, String passwd, String first_name, String last_name, String email) {
        if (!username.equals("") && !passwd.equals("") && !first_name.equals("") && !last_name.equals("") && !email.equals("")) {
            String[] field = new String[5];
            field[0] = "username";
            field[1] = "passwd";
            field[2] = "first_name";
            field[3] = "last_name";
            field[4] = "email";
            String[] data = new String[5];
            data[0] = username;
            data[1] = passwd;
            data[2] = first_name;
            data[3] = last_name;
            data[4] = email;
            PutData putData = new PutData(Config.showURL + "signup.php", "POST", field, data);
            //cmd -> ipconfig -> ipv4 address
            if (putData.startPut()) {
                if (putData.onComplete()) {
                    String result = putData.getResult();
                    if (result.equals("Sign up Failed")) {
                        System.out.println("Register success");
                    } else {
                        System.out.println("Register failed");
                    }
                }
            }

        } else {
            System.out.println("Missing datas");
        }
    }

    public void AddMovie(String studio, String category, String name, String lenght, String rate){
        if(!name.equals("") && !lenght.equals("") && isNumeric(lenght) && !rate.equals("") && isNumeric(rate)) {

                    String[] field = new String[5];
                    field[0] = "movie_studio";
                    field[1] = "movie_category";
                    field[2] = "movie_name";
                    field[3] = "movie_length";
                    field[4] = "movie_rate";
                    String[] data = new String[5];
                    data[0] = studio;
                    data[1] = category;
                    data[2] = name;
                    data[3] = lenght;
                    data[4] = rate;
                    PutData putData = new PutData(Config.showURL + "Add_movie.php", "POST", field, data);
                    //cmd -> ipconfig -> ipv4 address
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            String result = putData.getResult();
                            if (result.equals("Movie added successfully")) {
                                System.out.println("Movie added success");

                            } else {
                                System.out.println("Movie added failed");
                            }
                        }
                    }

        }else{
            System.out.println("Missing datas");
        }
    }

    private static boolean isNumeric(String str){
        return str != null && str.matches("[0-9.]+");
    }

    public void AddActor(String name, String age, String gender){
        if(!name.equals("") && !age.equals("") && !gender.equals("") && (isNumeric(age) && Integer.parseInt(age) > 0)) {
                    String[] field = new String[3];
                    field[0] = "name";
                    field[1] = "age";
                    field[2] = "gender";
                    String[] data = new String[3];
                    data[0] = name;
                    data[1] = age;
                    data[2] = gender;
                    PutData putData = new PutData(Config.showURL + "addActor.php", "POST", field, data);
                    //cmd -> ipconfig -> ipv4 address
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            String result = putData.getResult();
                            if (result.equals("Actor added Success")){
                                System.out.println("Actor added succes");
                            }
                            else {
                                System.out.println("Actor added failed");
                            }
                        }
                    }
        }
        else {
            System.out.println("Missing datas");
        }
    }

    public void AddDirector(String name){
        if(!name.equals("")) {
                    String[] field = new String[1];
                    field[0] = "name";
                    String[] data = new String[1];
                    data[0] = name;
                    PutData putData = new PutData(Config.showURL + "addDirector.php", "POST", field, data);
                    //cmd -> ipconfig -> ipv4 address
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            String result = putData.getResult();
                            if (result.equals("Director added Success")){
                                System.out.println("Director added succes");
                            }
                            else {
                                System.out.println("Director added failed");
                            }
                        }
                    }
        }
        else {
            System.out.println("Missing datas");
        }
    }


}
