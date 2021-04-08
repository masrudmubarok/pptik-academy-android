package com.mubarok.pptikacademy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

public class SecurityActivity extends AppCompatActivity {

    private Button mBtn_editS;
    HttpResponse httpResponse;
    TextView mTxt_idS, mTxt_usernameS, mTxt_passwordS;
    JSONObject jsonObject = null ;
    String StringHolder = "" ;
    // Adding HTTP Server URL to string variable.
    String HttpURL = "http://192.168.43.206/pptik-academy-android/security-read.php";

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security);

        sessionManager = new SessionManager(getApplicationContext());
        sessionManager.loginCheck();

        //menerapkan tool bar sesuai id toolbar | ToolBarAtas adalah variabel buatan sndiri
        Toolbar ToolBarAtasAccount = (Toolbar)findViewById(R.id.toolbar_securtiy);
        setSupportActionBar(ToolBarAtasAccount);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // inisialisasi textview
        mTxt_idS = (TextView)findViewById(R.id.textIdSD);
        mTxt_usernameS = (TextView)findViewById(R.id.textUsernameSD);
        mTxt_passwordS = (TextView)findViewById(R.id.textPasswordSD);

        // Retrieve data from sessionManager
        HashMap<String, String> user = sessionManager.getUserDetail();
        final String username = user.get(SessionManager.KEY_USERNAME);
        final String password = user.get(SessionManager.KEY_PASSWORD);
        final String id = user.get(SessionManager.KEY_ID);

        // displaying user data
        mTxt_idS.setText(id);
        mTxt_usernameS.setText(username);
        mTxt_passwordS.setText(password);

        //inialisasi button
        mBtn_editS = (Button) findViewById(R.id.editbtnS);

        //functin button
        mBtn_editS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iEditS = new Intent(SecurityActivity.this,EditSecurityActivity.class);
                iEditS.putExtra("username", username);
                iEditS.putExtra("password", password);
                iEditS.putExtra("id", id);
                startActivity(iEditS);
            }
        });

//        new GetDataFromServerIntoTextView(SecurityActivity.this).execute();
    }

    // Declaring GetDataFromServerIntoTextView method with AsyncTask.
    public class GetDataFromServerIntoTextView extends AsyncTask<Void, Void, Void> {

        // Declaring CONTEXT.
        public Context context;

        public GetDataFromServerIntoTextView(Context context)
        {
            this.context = context;
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            HttpClient httpClient = new DefaultHttpClient();

            // Adding HttpURL to my HttpPost oject.
            HttpPost httpPost = new HttpPost(HttpURL);

            try {
                httpResponse = httpClient.execute(httpPost);

                StringHolder = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");

            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                // Passing string holder variable to JSONArray.
                JSONArray jsonArray = new JSONArray(StringHolder);
                jsonObject = jsonArray.getJSONObject(0);


            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(Void result)
        {
            try {

                // Adding JSOn string to textview after done loading.
                mTxt_idS.setText(jsonObject.getString("id_siswa"));
                mTxt_usernameS.setText(jsonObject.getString("username"));
                mTxt_passwordS.setText(jsonObject.getString("password"));

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
