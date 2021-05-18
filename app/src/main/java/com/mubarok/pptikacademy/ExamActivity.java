package com.mubarok.pptikacademy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
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

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExamActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = ExamActivity.class.getSimpleName(); //getting the info
    String HttpURL = "http://192.168.43.206/pptik-academy-android/registerexam.php";
    String HttpURL1 = "http://192.168.43.206/pptik-academy-android/videomodul-send-learning.php";
    String HttpURL2 = "http://192.168.43.206/pptik-academy-android/learning-send-exam.php";

    Button mBtn_register;
    TextInputLayout mExt_idSiswaE, mExt_idKursusE, mExt_nameE, mExt_emailE, mExt_dateE, mExt_subjectE;
    String getId, getName, getEmail;
    String TempIdSiswa, TempIdKursus, TempDate;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        sessionManager = new SessionManager(getApplicationContext());
        sessionManager.loginCheck();

        //menerapkan tool bar sesuai id toolbar | ToolBarAtas adalah variabel buatan sndiri
        Toolbar ToolBar = (Toolbar)findViewById(R.id.toolbar_exam);
        setSupportActionBar(ToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);

        //inisialisasi button & edit text
        mBtn_register = (Button) findViewById(R.id.btn_registerexam);
        mExt_idSiswaE = (TextInputLayout) findViewById(R.id.idSiswaExam);
        mExt_idKursusE = (TextInputLayout) findViewById(R.id.idKursusExam);
        mExt_nameE = (TextInputLayout) findViewById(R.id.nameExam);
        mExt_emailE = (TextInputLayout) findViewById(R.id.emailExam);
        mExt_dateE = (TextInputLayout) findViewById(R.id.dateRegistExam);
        mExt_subjectE = (TextInputLayout) findViewById(R.id.subjectExam);

        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.KEY_ID);
        getName = user.get(sessionManager.KEY_NAME);
        getEmail = user.get(sessionManager.KEY_EMAIL);

        // Receive Data from SessionManager
        mExt_idSiswaE.getEditText().setText(getId);
        mExt_nameE.getEditText().setText(getName);
        mExt_emailE.getEditText().setText(getEmail);

        // Receive Data from LearningActivity
        mExt_idKursusE.getEditText().setText(getIntent().getStringExtra("id_kursus"));
        mExt_subjectE.getEditText().setText(getIntent().getStringExtra("nama_kursus"));

        // Receive Data from Current Date
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
        String currentDate = dateFormat.format(calendar.getTime());
        mExt_dateE.getEditText().setText(currentDate);

        // Function button
        mBtn_register.setOnClickListener((View.OnClickListener) this);

    }

    public void GetData() {

        TempIdSiswa = mExt_idSiswaE.getEditText().getText().toString();
        TempIdKursus = mExt_idKursusE.getEditText().getText().toString();
        TempDate = mExt_dateE.getEditText().getText().toString();
    }

    public void InsertData(final String id_siswa, final String id_kursus, final String tanggal_daftar) {

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @SuppressLint("WrongThread")
            @Override
            protected String doInBackground(String... params) {

                String IdSiswaHolder = id_siswa;
                String IdKursusHolder = id_kursus;
                String DateHolder = tanggal_daftar;

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("id_siswa", IdSiswaHolder));
                nameValuePairs.add(new BasicNameValuePair("id_kursus", IdKursusHolder));
                nameValuePairs.add(new BasicNameValuePair("tanggal_daftar", DateHolder));

                try {
                    HttpClient httpClient = new DefaultHttpClient();

                    HttpPost httpPost = new HttpPost(HttpURL);

                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse httpResponse = httpClient.execute(httpPost);

                    HttpEntity httpEntity = httpResponse.getEntity();


                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return "Data Inserted Successfully";
            }

            @Override
            protected void onPostExecute(String result) {

                super.onPostExecute(result);

                Toast.makeText(ExamActivity.this, "Registration Successfully", Toast.LENGTH_LONG).show();

            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();

        sendPostReqAsyncTask.execute(id_siswa, id_kursus, tanggal_daftar);
    }

    private void sendBackCourseDetail() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpURL1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, response.toString());
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("kursus");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String id_kursus = object.getString("id_kursus").trim();
                        String deskripsi = object.getString("deskripsi").trim();
                        String icon = object.getString("icon").trim();

                        Intent intent = new Intent(getApplicationContext(), LearningActivity.class);
                        intent.putExtra("id_kursus", id_kursus);
                        intent.putExtra("deskripsi", deskripsi);
                        intent.putExtra("icon", icon);
                        startActivity(intent);
                        finish();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(ExamActivity.this, "Error Reading Detail " + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ExamActivity.this, "Error Reading Detail " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> getParams = new HashMap<>();
                getParams.put("id_kursus", getId);
                return getParams;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                sendBackCourseDetail();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_registerexam:
                //Creating
                AlertDialog.Builder alertRegisterExam = new AlertDialog.Builder(this);
                //Setting Dialog
                alertRegisterExam.setTitle("Register");
                alertRegisterExam.setMessage("Are you sure to take the exam?");
                alertRegisterExam.setIcon(R.drawable.exam);
                alertRegisterExam.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        GetData();
                        InsertData(TempIdSiswa, TempIdKursus, TempDate);
                        Intent a = new Intent(ExamActivity.this, LearningActivity.class);
                        startActivity(a);
                        finish();
                    }
                });
                alertRegisterExam.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        sendExamData();
                    }
                });
                alertRegisterExam.show();
                break;
        }
    }

    private void sendExamData() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpURL1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, response.toString());
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("kursus");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String id_kursus = object.getString("id_kursus").trim();
                        String namaKursus = object.getString("nama_kursus").trim();

                        Intent iExam = new Intent(getApplicationContext(),ExamActivity.class);
                        iExam.putExtra("id_kursus", id_kursus);
                        iExam.putExtra("nama_kursus", namaKursus);
                        startActivity(iExam);
                        finish();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(ExamActivity.this, "Error Reading Detail "+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ExamActivity.this, "Error Reading Detail "+error.toString(), Toast.LENGTH_SHORT).show();
            }})

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String > getParams = new HashMap<>();
                getParams.put("id_kursus", getId);
                return getParams;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
