package com.mubarok.pptikacademy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class ExamActivity extends AppCompatActivity {

    String HttpURL = "http://192.168.43.206/pptik-academy-android/profile-update.php";
    Button mBtn_register;
    TextInputLayout mExt_idSiswaE, mExt_idKursusE, mExt_nameE, mExt_emailE, mExt_dateE, mExt_subjectE;
    String getId, getName, getEmail;
    String TempIdSiswa, TempIdKursus, TempDate;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        //menerapkan tool bar sesuai id toolbar | ToolBarAtas adalah variabel buatan sndiri
        Toolbar ToolBar = (Toolbar)findViewById(R.id.toolbar_exam);
        setSupportActionBar(ToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);

        sessionManager = new SessionManager(this);

        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.KEY_ID);
        getName = user.get(sessionManager.KEY_NAME);
        getEmail = user.get(sessionManager.KEY_EMAIL);

        //inisialisasi button & edit text
        mBtn_register = (Button) findViewById(R.id.btn_registerexam);
        mExt_idSiswaE = (TextInputLayout) findViewById(R.id.idSiswaExam);
        mExt_idKursusE = (TextInputLayout) findViewById(R.id.idKursusExam);
        mExt_nameE = (TextInputLayout) findViewById(R.id.nameExam);
        mExt_emailE = (TextInputLayout) findViewById(R.id.emailExam);
        mExt_dateE = (TextInputLayout) findViewById(R.id.dateRegistExam);
        mExt_subjectE = (TextInputLayout) findViewById(R.id.subjectExam);

        // Receive Data from SessionManager
        mExt_idSiswaE.getEditText().setText(getId);
        mExt_nameE.getEditText().setText(getName);
        mExt_emailE.getEditText().setText(getEmail);

        // Receive Data from LearningActivity
        mExt_idKursusE.getEditText().setText(getIntent().getStringExtra("id_kursus"));
        mExt_subjectE.getEditText().setText(getIntent().getStringExtra("nama_kursus"));

        // Receive Data from Current Date
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
        mExt_dateE.getEditText().setText(currentDate);

        // Function button
        mBtn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GetData();
                InsertData(TempIdSiswa, TempIdKursus, TempDate);
                Intent a = new Intent(ExamActivity.this, LearningActivity.class);
                startActivity(a);
                finish();
            }

        });

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
}
