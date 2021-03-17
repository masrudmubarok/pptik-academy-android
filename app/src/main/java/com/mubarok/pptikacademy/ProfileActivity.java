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

public class ProfileActivity extends AppCompatActivity {

    private Button mBtn_editP;
    HttpResponse httpResponse;
    TextView mTxt_idP, mTxt_nameP, mTxt_emailP, mTxt_cityP, mTxt_countryP;
    JSONObject jsonObject = null ;
    String StringHolder = "" ;
    // Adding HTTP Server URL to string variable.
    String HttpURL = "http://192.168.43.114/pptik-academy-android/profile-read.php";

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
        mBtn_editP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iEditP = new Intent(getApplicationContext(),EditProfileActivity.class);
                startActivity(iEditP);
            }
        });

        // inisialisasi textview
        mTxt_idP = (TextView)findViewById(R.id.textIdPD);
        mTxt_nameP = (TextView)findViewById(R.id.textNamePD);
        mTxt_emailP = (TextView)findViewById(R.id.textEmailPD);
        mTxt_cityP = (TextView)findViewById(R.id.textCityPD);
        mTxt_countryP = (TextView)findViewById(R.id.textCountryPD);

        new GetDataFromServerIntoTextView(ProfileActivity.this).execute();
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
                mTxt_idP.setText(jsonObject.getString("id_siswa"));
                mTxt_nameP.setText(jsonObject.getString("nama_siswa"));
                mTxt_emailP.setText(jsonObject.getString("email"));
                mTxt_cityP.setText(jsonObject.getString("kota"));
                mTxt_countryP.setText(jsonObject.getString("negara"));

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
