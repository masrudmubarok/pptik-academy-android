package com.mubarok.pptikacademy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HelpActivity extends AppCompatActivity {

    private Button mBtn_contactus, mBtn_about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        //menerapkan tool bar sesuai id toolbar | ToolBarAtas adalah variabel buatan sndiri
        Toolbar ToolBarAtasAccount = (Toolbar)findViewById(R.id.toolbar_help);
        setSupportActionBar(ToolBarAtasAccount);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //inialisasi button
        mBtn_contactus = (Button) findViewById(R.id.contactusbutton);
        mBtn_about = (Button) findViewById(R.id.aboutbutton);

        //function button
        mBtn_contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iContactus = new Intent(getApplicationContext(),ContactUsActivity.class);
                startActivity(iContactus);
            }
        });
        mBtn_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iAbout = new Intent(getApplicationContext(),AboutActivity.class);
                startActivity(iAbout);
            }
        });
    }
}
