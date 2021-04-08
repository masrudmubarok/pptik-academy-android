package com.mubarok.pptikacademy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashScreen extends Activity {

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        sessionManager = new SessionManager(getApplicationContext());

        Thread thread = new Thread() {
            public void run(){
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    sessionManager.checkLogin();
                    finish();
                }
            }
        };
        thread.start();
    }
}
