package com.mubarok.pptikacademy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditSecurityActivity extends AppCompatActivity {

    String HttpURL = "http://192.168.43.206/pptik-academy-android/security-update.php";
    Button mBtn_saves, mBtn_cancels;
    TextInputLayout mTxt_usernames, mTxt_passwords;
    String getId;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_security);

        //menerapkan tool bar sesuai id toolbar | ToolBarAtas adalah variabel buatan sndiri
        Toolbar ToolBarAtasAccount = (Toolbar)findViewById(R.id.toolbar_editsecurity);
        setSupportActionBar(ToolBarAtasAccount);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        sessionManager = new SessionManager(this);

        //inisialisasi button & edit text
        mBtn_saves = (Button) findViewById(R.id.savebtnES);
        mBtn_cancels = (Button) findViewById(R.id.cancelbtnES);
        mTxt_usernames = (TextInputLayout) findViewById(R.id.textInputUsernameES);
        mTxt_passwords = (TextInputLayout) findViewById(R.id.textInputPasswordES);

        // Receive Data from ProfileActivity
        mTxt_usernames.getEditText().setText(getIntent().getStringExtra("username"));
        mTxt_passwords.getEditText().setText(getIntent().getStringExtra("password"));

        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.KEY_ID);

        //function button
        mBtn_saves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Update();
            }

        });

        mBtn_cancels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditSecurityActivity.this, SecurityActivity.class);
                startActivity(intent);
                finish();
            }

        });

    }

    //save
    private void Update() {

        final String username = this.mTxt_usernames.getEditText().getText().toString().trim();
        final String password = this.mTxt_passwords.getEditText().getText().toString().trim();;
        final String id_siswa = getId;

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Saving...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")){
                                Toast.makeText(EditSecurityActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                                sessionManager.updateSecuritySession(username, password, id_siswa);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(EditSecurityActivity.this, "Error "+ e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(EditSecurityActivity.this, "Error "+ error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                params.put("id_siswa", id_siswa);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

}
