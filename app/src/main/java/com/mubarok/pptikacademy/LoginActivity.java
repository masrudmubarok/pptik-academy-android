package com.mubarok.pptikacademy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    Button mBtn_signin;
    Intent a;
    TextInputLayout mTxt_username, mTxt_password;
    private static String url_login = "http://192.168.43.206/pptik-academy-android/login1.php";
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //menerapkan tool bar sesuai id toolbar | ToolBarAtas adalah variabel buatan sndiri
        Toolbar ToolBarLogin = (Toolbar)findViewById(R.id.toolbar_signin);
        setSupportActionBar(ToolBarLogin);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        sessionManager = new SessionManager(this);
//        Toast.makeText(getApplicationContext(),
//                "User Login Status: " + sessionManager.isLoggedIn(), Toast.LENGTH_LONG).show();

        //inisialisasi button & text input
        mBtn_signin = (Button) findViewById(R.id.btnsignin2);
        mTxt_username = (TextInputLayout) findViewById(R.id.textInputUsernameL);
        mTxt_password = (TextInputLayout) findViewById(R.id.textInputPasswordL);

        //function button
        mBtn_signin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                String mUsername = mTxt_username.getEditText().getText().toString().trim();
                String mPassword = mTxt_password.getEditText().getText().toString().trim();

                if (!mUsername.isEmpty() || !mPassword.isEmpty()) {
                    Login(mUsername, mPassword);
                } else {
                    mTxt_username.setError("Please insert email");
                    mTxt_password.setError("Please insert password");
                }
            }
        });
    }

    private void Login(final String username, final String password) {

//        loading.setVisibility(View.VISIBLE);
//        btn_login.setVisibility(View.GONE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_login,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");

                            if (success.equals("1")) {

                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String nama_siswa = object.getString("nama_siswa").trim();
                                    String username = object.getString("username").trim();
                                    String id_siswa = object.getString("id_siswa").trim();

                                    sessionManager.createLoginSession(nama_siswa, username, id_siswa);

                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    intent.putExtra("nama_siswa", nama_siswa);
                                    intent.putExtra("username", username);
                                    startActivity(intent);
                                    finish();
                                }

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("anyText",response);
                            Toast.makeText(LoginActivity.this, "Error " +e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(LoginActivity.this, "Error " +error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
