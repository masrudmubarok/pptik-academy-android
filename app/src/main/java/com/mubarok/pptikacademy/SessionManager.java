package com.mubarok.pptikacademy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import java.util.HashMap;

public class SessionManager {

    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    int PRIVATE_MODE = 0;

    // nama sharepreference
    private static final String PREF_NAME = "LOGIN";

    // All Shared Preferences Keys
    private static final String LOGIN = "IS_LOGIN";
    public static final String KEY_NAME = "nama_siswa";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_CITY = "kota";
    public static final String KEY_COUNTRY = "negara";
    public static final String KEY_ID = "id_siswa";

    // Constructor
    public SessionManager(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = ((SharedPreferences) sharedPreferences).edit();
    }

    /**
     * Create login session
     * */
    public void createLoginSession(String nama_siswa, String username, String password, String email, String kota, String negara, String id_siswa){
        // Storing login value as TRUE
        editor.putBoolean(LOGIN, true);
        editor.putString(KEY_NAME, nama_siswa);
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_PASSWORD, password);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_CITY, kota);
        editor.putString(KEY_COUNTRY, negara);
        editor.putString(KEY_ID, id_siswa);
        editor.apply();
    }

    /**
     * Update profile login session
     * */
    public void updateProfileSession(String nama_siswa, String email, String kota, String negara, String id_siswa){
        // Storing login value as TRUE
        editor.putBoolean(LOGIN, true);
        editor.putString(KEY_NAME, nama_siswa);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_CITY, kota);
        editor.putString(KEY_COUNTRY, negara);
        editor.putString(KEY_ID, id_siswa);
        editor.apply();
    }

    /**
     * Update security login session
     * */
    public void updateSecuritySession(String username, String password, String id_siswa){
        // Storing login value as TRUE
        editor.putBoolean(LOGIN, true);
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_PASSWORD, password);
        editor.putString(KEY_ID, id_siswa);
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
        if (!this.isLoggedIn()) {
            Intent i = new Intent(context, IntroActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        } else {
            Intent i = new Intent(context, CourseActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }

    public void loginCheck() {
        // Check login status
        if (!this.isLoggedIn()) {
            Intent i = new Intent(context, IntroActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }

    /**
     *  Get stored session data
     * */
    public HashMap<String, String> getUserDetail() {
        HashMap<String, String> user = new HashMap<String, String > ();
        user.put(KEY_NAME, sharedPreferences.getString(KEY_NAME, null));
        user.put(KEY_USERNAME, sharedPreferences.getString(KEY_USERNAME, null));
        user.put(KEY_PASSWORD, sharedPreferences.getString(KEY_PASSWORD, null));
        user.put(KEY_EMAIL, sharedPreferences.getString(KEY_EMAIL, null));
        user.put(KEY_CITY, sharedPreferences.getString(KEY_CITY, null));
        user.put(KEY_COUNTRY, sharedPreferences.getString(KEY_COUNTRY, null));
        user.put(KEY_ID, sharedPreferences.getString(KEY_ID, null));
        return user;
    }

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String > ();
        user.put(KEY_NAME, sharedPreferences.getString(KEY_NAME, null));
        user.put(KEY_USERNAME, sharedPreferences.getString(KEY_USERNAME, null));
        user.put(KEY_PASSWORD, sharedPreferences.getString(KEY_PASSWORD, null));
        user.put(KEY_EMAIL, sharedPreferences.getString(KEY_EMAIL, null));
        user.put(KEY_CITY, sharedPreferences.getString(KEY_CITY, null));
        user.put(KEY_COUNTRY, sharedPreferences.getString(KEY_COUNTRY, null));
        user.put(KEY_ID, sharedPreferences.getString(KEY_ID, null));
        return user;
    }

    /**
     *  Clear session details
     * */
    public void logoutUser() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();
        Intent i = new Intent(context, IntroActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}
