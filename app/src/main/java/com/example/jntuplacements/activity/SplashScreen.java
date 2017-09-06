package com.example.jntuplacements.activity;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.example.jntuplacements.R;

/**
 * Created by vamshi-4397 on 6/9/17.
 */

public class SplashScreen extends Activity{

    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                //this will run once the timer is over.
                SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                boolean isLoggedIn = sharedPreferences.getBoolean(LoginActivity.IS_LOGGED_IN,false);

                if(isLoggedIn || "true".equals(isLoggedIn)){
                    String uEmail = sharedPreferences.getString(LoginActivity.USER_EMAIL,null);
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    i.putExtra("UserEmail", uEmail);
                    startActivity(i);
                    finish();
                } else {
                    Intent i = new Intent(SplashScreen.this, LoginActivity.class);
                    startActivity(i);
                    // close this activity
                    finish();
                }
            }
        }, SPLASH_TIME_OUT);
    }

}
