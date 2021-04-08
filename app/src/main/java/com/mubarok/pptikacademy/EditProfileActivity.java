package com.mubarok.pptikacademy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EditProfileActivity extends AppCompatActivity {

    String HttpURL = "http://192.168.43.206/pptik-academy-android/profile-edit.php";
    Button mBtn_savep, mBtn_cancelp;
    EditText mTxt_fullnamep, mTxt_emailp, mTxt_cityp, mTxt_countryp;
    String TempFullnamep, TempEmailp, TempCityp, TempCountryp, Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        //menerapkan tool bar sesuai id toolbar | ToolBarAtas adalah variabel buatan sndiri
        Toolbar ToolBarAtasAccount = (Toolbar)findViewById(R.id.toolbar_editprofile);
        setSupportActionBar(ToolBarAtasAccount);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //inisialisasi button & edit text
        mBtn_savep = (Button) findViewById(R.id.savebtnep);
        mBtn_cancelp = (Button) findViewById(R.id.cancelbtnep);
        mTxt_fullnamep = (EditText) findViewById(R.id.editTextFullnameP);
        mTxt_emailp = (EditText) findViewById(R.id.editTextEmailP);
        mTxt_cityp = (EditText) findViewById(R.id.editTextCityP);
        mTxt_countryp = (EditText) findViewById(R.id.editTextCountryP);

        // Receive Data from ProfileActivity
        Id = getIntent().getStringExtra("id");
        mTxt_fullnamep.setText(getIntent().getStringExtra("name"));
        mTxt_emailp.setText(getIntent().getStringExtra("email"));
        mTxt_cityp.setText(getIntent().getStringExtra("city"));
        mTxt_countryp.setText(getIntent().getStringExtra("country"));



        //function button
        mBtn_savep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GetData();
                UpdateData(Id, TempFullnamep, TempEmailp, TempCityp, TempCountryp);
                reset();
            }

        });

        mBtn_cancelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }

        });
        
    }

    public void GetData() {

        TempFullnamep = mTxt_fullnamep.getText().toString();
        TempEmailp = mTxt_emailp.getText().toString();
        TempCityp = mTxt_cityp.getText().toString();
        TempCountryp = mTxt_countryp.getText().toString();
    }

    public void UpdateData(final String id, final String nama_siswa, final String email, final String kota, final String negara) {

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @SuppressLint("WrongThread")
            @Override
            protected String doInBackground(String... params) {

                String IdHolder = id;
                String FullnamepHolder = nama_siswa;
                String EmailpHolder = email;
                String CitypHolder = kota;
                String CountrypHolder = negara;

                Log.d(TAG, "doInBackground: IdHolder"+id);
                Log.d(TAG, "doInBackground: FullnamepHolder"+nama_siswa);
                Log.d(TAG, "doInBackground: EmailpHolder"+email);
                Log.d(TAG, "doInBackground: CitypHolder"+kota);
                Log.d(TAG, "doInBackground: CountrypHolder"+negara);

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("id_siswa", IdHolder));
                nameValuePairs.add(new BasicNameValuePair("nama_siswa", FullnamepHolder));
                nameValuePairs.add(new BasicNameValuePair("email", EmailpHolder));
                nameValuePairs.add(new BasicNameValuePair("kota", CitypHolder));
                nameValuePairs.add(new BasicNameValuePair("negara", CountrypHolder));
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
                Toast.makeText(EditProfileActivity.this, "Data Submit Successfully", Toast.LENGTH_LONG).show();

            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();

        sendPostReqAsyncTask.execute(id, nama_siswa, email, kota, negara);
    }

    public void reset(){
        mTxt_fullnamep.setText("");
        mTxt_emailp.setText("");
        mTxt_cityp.setText("");
        mTxt_countryp.setText("");
    }

    private static final String TAG = "EditProfileActivity";
}
