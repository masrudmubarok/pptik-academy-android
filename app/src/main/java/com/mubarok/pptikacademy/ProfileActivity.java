package com.mubarok.pptikacademy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProfileActivity extends AppCompatActivity {

    private Button mBtn_editP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //menerapkan tool bar sesuai id toolbar | ToolBarAtas adalah variabel buatan sndiri
        Toolbar ToolBarAtasAccount = (Toolbar)findViewById(R.id.toolbar_profile);
        setSupportActionBar(ToolBarAtasAccount);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //inialisasi button
        mBtn_editP = (Button) findViewById(R.id.editbtnP);

        //functin button
//        mBtn_editP.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent iEditP = new Intent(getApplicationContext(),EditProfileActivity.class);
//                startActivity(iEditP);
//            }
//        });
    }
}
