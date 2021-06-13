package com.mubarok.pptikacademy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import java.util.ArrayList;
import java.util.List;

public class CreateAccountActivity extends AppCompatActivity {

    String HttpURL = "https://pptikacademy01.000webhostapp.com/api/createaccount.php";
    TextInputLayout mTxt_fullnamec, mTxt_usernamec, mTxt_passwordc, mTxt_emailc, mTxt_cityc, mTxt_countryc;
    Button mBtn_create;
    String TempFullname, TempUsername, TempPassword, TempEmail, TempCity, TempCountry;
    Intent a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        //menerapkan tool bar sesuai id toolbar | ToolBarAtas adalah variabel buatan sndiri
        Toolbar ToolBarAtasCreateAccount = (Toolbar)findViewById(R.id.toolbar_createaccount);
        setSupportActionBar(ToolBarAtasCreateAccount);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //inisialisasi button & text input
        mBtn_create = (Button) findViewById(R.id.btn_createacc2);
        mTxt_fullnamec = (TextInputLayout) findViewById(R.id.textInputFullNameC);
        mTxt_usernamec = (TextInputLayout) findViewById(R.id.textInputUsernameC);
        mTxt_passwordc = (TextInputLayout) findViewById(R.id.textInputPasswordC);
        mTxt_emailc = (TextInputLayout) findViewById(R.id.textInputEmailC);
        mTxt_cityc = (TextInputLayout) findViewById(R.id.textInputCityC);
        mTxt_countryc = (TextInputLayout) findViewById(R.id.textInputCountryC);

        mBtn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GetData();
                InsertData(TempFullname, TempUsername, TempPassword, TempEmail, TempCity, TempCountry);
                a = new Intent(CreateAccountActivity.this, IntroActivity.class);
                startActivity(a);
                finish();
            }

        });

    }

    public void GetData() {

        TempFullname = mTxt_fullnamec.getEditText().getText().toString();
        TempUsername = mTxt_usernamec.getEditText().getText().toString();
        TempPassword = mTxt_passwordc.getEditText().getText().toString();
        TempEmail = mTxt_emailc.getEditText().getText().toString();
        TempCity = mTxt_cityc.getEditText().getText().toString();
        TempCountry = mTxt_countryc.getEditText().getText().toString();
    }

    public void InsertData(final String nama_siswa, final String username, final String password, final String email, final String kota, final String negara) {

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @SuppressLint("WrongThread")
            @Override
            protected String doInBackground(String... params) {

                String FullnameHolder = nama_siswa;
                String UsernameHolder = username;
                String PasswordHolder = password;
                String EmailHolder = email;
                String CityHolder = kota;
                String CountryHolder = negara;

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("nama_siswa", FullnameHolder));
                nameValuePairs.add(new BasicNameValuePair("username", UsernameHolder));
                nameValuePairs.add(new BasicNameValuePair("password", PasswordHolder));
                nameValuePairs.add(new BasicNameValuePair("email", EmailHolder));
                nameValuePairs.add(new BasicNameValuePair("kota", CityHolder));
                nameValuePairs.add(new BasicNameValuePair("negara", CountryHolder));

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

                Toast.makeText(CreateAccountActivity.this, "Data Submit Successfully", Toast.LENGTH_LONG).show();

            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();

        sendPostReqAsyncTask.execute(nama_siswa, username, password, email, kota, negara);
    }

}
