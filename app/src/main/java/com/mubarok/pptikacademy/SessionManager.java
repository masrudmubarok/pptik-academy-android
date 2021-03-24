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

    // nama sharepreference
    private static final String PREF_NAME = "LOGIN";

    // All Shared Preferences Keys
    private static final String LOGIN = "IS_LOGIN";
    public static final String NAME = "NAME";
    public static final String EMAIL = "EMAIL";

    // Constructor
    public SessionManager(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = ((SharedPreferences) sharedPreferences).edit();
    }

    /**
     * Create login session
     * */
    public void createLoginSession(String name, String email){
        // Storing login value as TRUE
        editor.putBoolean(LOGIN, true);
        editor.putString(NAME, name);
        editor.putString(EMAIL, email);
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
            Intent i = new Intent(context, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
            ((CourseActivity) context).finish();
        }
    }

    /**
     *  Get stored session data
     * */
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String > ();
        user.put(NAME, sharedPreferences.getString(NAME, null));
        user.put(EMAIL, sharedPreferences.getString(EMAIL, null));
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
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}
