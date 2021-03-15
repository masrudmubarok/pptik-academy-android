package com.mubarok.pptikacademy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AccountActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtn_profile, mBtn_security, mBtn_help, mBtn_logout;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        session = new SessionManager(getApplicationContext());

        //menerapkan tool bar sesuai id toolbar | ToolBarAtas adalah variabel buatan sndiri
        Toolbar ToolBarAtasAccount = (Toolbar)findViewById(R.id.toolbar_account);
        setSupportActionBar(ToolBarAtasAccount);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //inialisasi button
        mBtn_profile = (Button) findViewById(R.id.profilebutton);
        mBtn_security = (Button) findViewById(R.id.securitybutton);
        mBtn_help = (Button) findViewById(R.id.helpbutton);
        mBtn_logout = (Button) findViewById(R.id.logoutbutton);

        //function button
        mBtn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iProfile = new Intent(getApplicationContext(),ProfileActivity.class);
                startActivity(iProfile);
            }
        });
        mBtn_security.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iSecurity = new Intent(getApplicationContext(),SecurityActivity.class);
                startActivity(iSecurity);
            }
        });
        mBtn_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iHelp = new Intent(getApplicationContext(),HelpActivity.class);
                startActivity(iHelp);
            }
        });
        mBtn_logout.setOnClickListener((View.OnClickListener) this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logoutbutton:
                //Creating
                AlertDialog.Builder alertLogout = new AlertDialog.Builder(this);
                //Setting Dialog
                alertLogout.setTitle("Logout");
                alertLogout.setMessage("Are you sure to logout?");
                alertLogout.setIcon(R.drawable.logout);
                alertLogout.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        session.logoutUser();
                        finish();
                    }
                });
                alertLogout.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent iDashboard = new Intent(getApplicationContext(), AccountActivity.class);
                        startActivity(iDashboard);
                        finish();
                    }
                });
                alertLogout.show();
                break;
        }
    }
}
