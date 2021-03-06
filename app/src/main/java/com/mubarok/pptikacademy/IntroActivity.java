package com.mubarok.pptikacademy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IntroActivity extends Activity {

    private Button mBtn_createacc, mBtn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        //inisialisasi button
        mBtn_createacc = (Button) findViewById(R.id.btn_createacc);
        mBtn_login = (Button)findViewById(R.id.btn_login);

        //function button
        mBtn_createacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iCreateacc = new Intent(getApplicationContext(),CreateAccountActivity.class);
                startActivity(iCreateacc);
            }
        });
        mBtn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iLogin = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(iLogin);
            }
        });

    }
}
