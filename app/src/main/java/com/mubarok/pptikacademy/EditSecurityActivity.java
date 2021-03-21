package com.mubarok.pptikacademy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EditSecurityActivity extends AppCompatActivity {

    String HttpURL = "http://192.168.43.114/pptik-academy-android/security-edit.php";
    Button mBtn_saves, mBtn_cancels;
    TextInputLayout mTxt_usernames, mTxt_passwords;
    String TempUsernames, TempPasswords, Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_security);

        //menerapkan tool bar sesuai id toolbar | ToolBarAtas adalah variabel buatan sndiri
        Toolbar ToolBarAtasAccount = (Toolbar)findViewById(R.id.toolbar_editsecurity);
        setSupportActionBar(ToolBarAtasAccount);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //inisialisasi button & edit text
        mBtn_saves = (Button) findViewById(R.id.savebtnES);
        mBtn_cancels = (Button) findViewById(R.id.cancelbtnES);
        mTxt_usernames = (TextInputLayout) findViewById(R.id.textInputUsernameES);
        mTxt_passwords = (TextInputLayout) findViewById(R.id.textInputPasswordES);

        // Receive Data from ProfileActivity
        Id = getIntent().getStringExtra("id_siswa");
        mTxt_usernames.getEditText().setText(getIntent().getStringExtra("username"));
        mTxt_passwords.getEditText().setText(getIntent().getStringExtra("password"));

        //function button
        mBtn_saves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GetData();
                UpdateData(Id, TempUsernames, TempPasswords);
//                reset();
            }

        });

    }

    public void GetData() {

        TempUsernames = mTxt_usernames.getEditText().getText().toString();
        TempPasswords = mTxt_passwords.getEditText().getText().toString();
    }

    public void UpdateData(final String id, final String usernames, final String passwords) {

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @SuppressLint("WrongThread")
            @Override
            protected String doInBackground(String... params) {

                String IdHolder = id;
                String UsernamesHolder = usernames;
                String PasswordsHolder = passwords;

                Log.d(TAG, "doInBackground: IdHolder"+id);
                Log.d(TAG, "doInBackground: UsernamesHolder"+usernames);
                Log.d(TAG, "doInBackground: PasswordsHolder"+passwords);

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("id_siswa", IdHolder));
                nameValuePairs.add(new BasicNameValuePair("username", UsernamesHolder));
                nameValuePairs.add(new BasicNameValuePair("password", PasswordsHolder));

                try {
                    HttpClient httpClient = new DefaultHttpClient();

                    HttpPost httpPost = new HttpPost(HttpURL);

                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse httpResponse = httpClient.execute(httpPost);

                    httpResponse.getEntity();
                    Log.d(TAG, "doInBackground: "+httpResponse.toString());
                    Log.d(TAG, "doInBackground: "+httpResponse.getParams());
                    Log.d(TAG, "doInBackground: "+httpResponse.getAllHeaders().toString());
                    Log.d(TAG, "doInBackground: "+httpResponse.getAllHeaders());
                    Log.d(TAG, "doInBackground: "+httpResponse.getEntity().getContent());

                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return "Data Inserted Successfully";
            }

            @Override
            protected void onPostExecute(String result) {

                super.onPostExecute(result);
                Log.d(TAG, "onPostExecute: "+result);
                Toast.makeText(EditSecurityActivity.this, "Data Submit Successfully", Toast.LENGTH_LONG).show();

            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();

        sendPostReqAsyncTask.execute(id, usernames, passwords);
    }

    private static final String TAG = "EditSecurityActivity";
}
