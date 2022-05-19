package com.movies;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class Api_functions extends AppCompatActivity {

    static final String LOGINREQG = "Request sent";
    static final String LOGINREQB = "Login failed";
    static final String BADCRED = "Bad credentials";
    static final String GOODCRED = "Good credentials";

    public boolean Login(String username, String passwd, Context context){
        final boolean[] success = {false};
        if(!username.equals("") && !passwd.equals("") ) {
            Handler handler = new Handler();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    String[] field = new String[2];
                    field[0] = "username";
                    field[1] = "passwd";;
                    String[] data = new String[2];
                    data[0] = username;
                    data[1] = passwd;
                    Log.i(LOGINREQG,"Request to login");

                    PutData putData = new PutData(Config.showURL + "login.php", "POST", field, data);
                    //cmd -> ipconfig -> ipv4 address
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            String result = putData.getResult();
                            if (result.equals("Login Success")){
                                User.username = username;
                                User.password = passwd;
                                Toast.makeText(context,result,Toast.LENGTH_SHORT).show();
                                Log.i(GOODCRED,"Login successfull");
                                success[0] = true;
                            }
                            else {
                                Toast.makeText(context, result,Toast.LENGTH_SHORT).show();
                                Log.e(BADCRED,"Login Failed due to bad credentials");
                                success[0] = false;
                            }
                        }
                    }
                }
            });
        }
        else {
            Toast.makeText(context,"All fields are required!", Toast.LENGTH_SHORT).show();
            Log.e(LOGINREQB,"Request handled badly");
            success[0] = false;
        }
        return success[0] == true;
    }
}
