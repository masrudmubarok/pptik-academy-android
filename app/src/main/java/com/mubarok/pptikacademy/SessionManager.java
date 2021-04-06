package com.mubarok.pptikacademy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import java.util.HashMap;

public class SessionManager {

    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "LOGIN";
    private static final String LOGIN = "IS_LOGIN";
    public static final String NAMA_SISWA = "NAMA_SISWA";
    public static final String USERNAME = "USERNAME";
    public static final String ID_SISWA= "ID_SISWA";

    // Constructor
    public SessionManager(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = ((SharedPreferences) sharedPreferences).edit();
    }

    /**
     * Create login session
     * */
    public void createLoginSession(String nama_siswa, String username, String id_siswa){
        // Storing login value as TRUE
        editor.putBoolean(LOGIN, true);
        editor.putString(NAMA_SISWA, nama_siswa);
        editor.putString(USERNAME, username);
        editor.putString(ID_SISWA, id_siswa);
        editor.apply();
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(LOGIN, false);
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * * */
    public void checkLogin()  {
        // Check login status
        if (!this.isLoggedIn()){
            Intent i = new Intent(context, IntroActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }else {
            Intent i = new Intent(context, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }

    /**
     *  Get stored session data
     * */
    public HashMap<String, String> getUserDetail(){

        HashMap<String, String> user = new HashMap<>();
        user.put(NAMA_SISWA, sharedPreferences.getString(NAMA_SISWA, null));
        user.put(USERNAME, sharedPreferences.getString(USERNAME, null));
        user.put(ID_SISWA, sharedPreferences.getString(ID_SISWA, null));

        return user;
    }

    /**
     *  Clear session details
     * */
    public void logoutUser() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();
        Intent i = new Intent(context, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}
