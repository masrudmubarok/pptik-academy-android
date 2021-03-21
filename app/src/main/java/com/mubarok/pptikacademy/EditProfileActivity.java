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

    String HttpURL = "http://192.168.43.114/pptik-academy-android/profile-edit.php";
    Button mBtn_savep, mBtn_cancelp;
    TextInputLayout mTxt_fullnamep, mTxt_emailp, mTxt_cityp, mTxt_countryp;
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
        mBtn_savep = (Button) findViewById(R.id.savebtnEP);
        mBtn_cancelp = (Button) findViewById(R.id.cancelbtnEP);
        mTxt_fullnamep = (TextInputLayout) findViewById(R.id.textInputFullNameEP);
        mTxt_emailp = (TextInputLayout) findViewById(R.id.textInputEmailEP);
        mTxt_cityp = (TextInputLayout) findViewById(R.id.textInputCityEP);
        mTxt_countryp = (TextInputLayout) findViewById(R.id.textInputCountryEP);

        // Receive Data from ProfileActivity
        Id = getIntent().getStringExtra("id_siswa");
        mTxt_fullnamep.getEditText().setText(getIntent().getStringExtra("nama_siswa"));
        mTxt_emailp.getEditText().setText(getIntent().getStringExtra("email"));
        mTxt_cityp.getEditText().setText(getIntent().getStringExtra("city"));
        mTxt_countryp.getEditText().setText(getIntent().getStringExtra("country"));

        //function button
        mBtn_savep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GetData();
                UpdateData(Id, TempFullnamep, TempEmailp, TempCityp, TempCountryp);
//                reset();
            }

        });
        
    }

    public void GetData() {

        TempFullnamep = mTxt_fullnamep.getEditText().getText().toString();
        TempEmailp = mTxt_emailp.getEditText().getText().toString();
        TempCityp = mTxt_cityp.getEditText().getText().toString();
        TempCountryp = mTxt_countryp.getEditText().getText().toString();
    }

    public void UpdateData(final String id, final String fullnamep, final String emailp, final String cityp, final String countryp) {

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @SuppressLint("WrongThread")
            @Override
            protected String doInBackground(String... params) {

                String IdHolder = id;
                String FullnamepHolder = fullnamep;
                String EmailpHolder = emailp;
                String CitypHolder = cityp;
                String CountrypHolder = countryp;

                Log.d(TAG, "doInBackground: IdHolder"+id);
                Log.d(TAG, "doInBackground: FullnamepHolder"+fullnamep);
                Log.d(TAG, "doInBackground: EmailpHolder"+emailp);
                Log.d(TAG, "doInBackground: CitypHolder"+cityp);
                Log.d(TAG, "doInBackground: CountrypHolder"+countryp);

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("id", IdHolder));
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

        sendPostReqAsyncTask.execute(id, fullnamep, emailp, cityp, countryp);
    }

    private static final String TAG = "EditProfileActivity";
}
