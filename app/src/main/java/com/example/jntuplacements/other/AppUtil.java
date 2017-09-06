package com.example.jntuplacements.other;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.jntuplacements.activity.LoginActivity;
import com.example.jntuplacements.activity.MainActivity;

/**
 * Created by vamshi-4397 on 7/9/17.
 */

public class AppUtil {

    public static void logOut(Context context) {
        Intent intent  = new Intent(context, LoginActivity.class);
        SharedPreferences sharedPreferences = context.getSharedPreferences(LoginActivity.SHARED_PREF_NAME,context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
