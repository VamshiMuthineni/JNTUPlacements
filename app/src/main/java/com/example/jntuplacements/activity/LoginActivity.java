package com.example.jntuplacements.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.StringBuilderPrinter;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jntuplacements.R;

/**
 * Created by vamshi-4397 on 6/9/17.
 */

public class LoginActivity extends Activity {

    public static String IS_LOGGED_IN = "IS_LOGGED_IN";
    public static String USER_EMAIL = "USER_EMAIL";
    public static String USER_NAME = "USER_NAME";
    public static String SHARED_PREF_NAME = "PlacementsPreference";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setting default screen to login.xml
        setContentView(R.layout.activity_login);

        TextView loginButton = (TextView) findViewById(R.id.btnLogin);


        loginButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                EditText uEmail = (EditText) findViewById(R.id.userEmail);
                EditText uPass = (EditText) findViewById(R.id.userPassword);
                if(uEmail.getText().toString().equals("vamshi")&&uPass.getText().toString().equals("admin")){
                    //store some data in shared preferences
                    SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(USER_NAME,"vamshi"); //get this from database
                    editor.putString(USER_EMAIL,uEmail.getText().toString());
                    editor.putBoolean(IS_LOGGED_IN,true);
                    editor.commit();
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    i.putExtra("UserEmail", uEmail.getText().toString());
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Email or Password is wrong.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
