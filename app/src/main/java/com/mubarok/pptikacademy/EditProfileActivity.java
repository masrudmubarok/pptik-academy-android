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
import android.widget.EditText;
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
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditProfileActivity extends AppCompatActivity {

    String HttpURL = "https://pptikacademy01.000webhostapp.com/api/profile-update.php";
    Button mBtn_savep, mBtn_cancelp;
    EditText mTxt_fullnamep, mTxt_emailp, mTxt_cityp, mTxt_countryp;
    String getId;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        //menerapkan tool bar sesuai id toolbar | ToolBarAtas adalah variabel buatan sndiri
        Toolbar ToolBarAtasAccount = (Toolbar)findViewById(R.id.toolbar_editprofile);
        setSupportActionBar(ToolBarAtasAccount);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        sessionManager = new SessionManager(this);

        //inisialisasi button & edit text
        mBtn_savep = (Button) findViewById(R.id.savebtnep);
        mBtn_cancelp = (Button) findViewById(R.id.cancelbtnep);
        mTxt_fullnamep = (EditText) findViewById(R.id.editTextFullnameP);
        mTxt_emailp = (EditText) findViewById(R.id.editTextEmailP);
        mTxt_cityp = (EditText) findViewById(R.id.editTextCityP);
        mTxt_countryp = (EditText) findViewById(R.id.editTextCountryP);

        // Receive Data from ProfileActivity
        mTxt_fullnamep.setText(getIntent().getStringExtra("nama_siswa"));
        mTxt_emailp.setText(getIntent().getStringExtra("email"));
        mTxt_cityp.setText(getIntent().getStringExtra("kota"));
        mTxt_countryp.setText(getIntent().getStringExtra("negara"));

        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.KEY_ID);

        //function button
        mBtn_savep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Update();
                Intent intent = new Intent(EditProfileActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish();
            }

        });

        mBtn_cancelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
                Intent intent = new Intent(EditProfileActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish();
            }

        });
        
    }

    //save
    private void Update() {

        final String nama_siswa = this.mTxt_fullnamep.getText().toString().trim();
        final String email = this.mTxt_emailp.getText().toString().trim();
        final String kota = this.mTxt_cityp.getText().toString().trim();
        final String negara = this.mTxt_countryp.getText().toString().trim();
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
                                Toast.makeText(EditProfileActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                                sessionManager.updateProfileSession(nama_siswa, email, kota, negara, id_siswa);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(EditProfileActivity.this, "Error "+ e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(EditProfileActivity.this, "Error "+ error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("nama_siswa", nama_siswa);
                params.put("email", email);
                params.put("kota", kota);
                params.put("negara", negara);
                params.put("id_siswa", id_siswa);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    public void reset(){
        mTxt_fullnamep.setText("");
        mTxt_emailp.setText("");
        mTxt_cityp.setText("");
        mTxt_countryp.setText("");
    }
}
