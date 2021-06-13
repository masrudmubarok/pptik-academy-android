package com.mubarok.pptikacademy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.barteksc.pdfviewer.PDFView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ModulViewActivity extends AppCompatActivity {

    private static final String TAG = ModulViewActivity.class.getSimpleName(); //getting the info
    TextView textView;
    PDFView pdfView;
    String getId, modulTemp, judulModulTemp;

    // Adding HTTP Server URL to string variable.
    String HttpURL = "https://pptikacademy01.000webhostapp.com/api/videomodulview-send-videomodullearning.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modul_view);

        //menerapkan tool bar sesuai id toolbar | ToolBarAtas adalah variabel buatan sndiri
        Toolbar ToolBar = (Toolbar)findViewById(R.id.toolbar_modulview);
        setSupportActionBar(ToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);

        // Declaration
        pdfView = findViewById(R.id.pdfView);
        textView = (TextView) findViewById(R.id.textToolbarModulView);

        // Receive Data from LearnignActivity
        getId = getIntent().getStringExtra("id_kursus");
        judulModulTemp = getIntent().getStringExtra("judul_modul");
        modulTemp = getIntent().getStringExtra("modul");

        // Set material
        textView.setText(judulModulTemp);


        //This is function read PDF from URL
        String linkModul = modulTemp;
        new RetrievePDFStream().execute("https://pptikacademy01.000webhostapp.com/assets/modul/"+linkModul); // Or any url direct PDF from internet

    }

        class RetrievePDFStream extends AsyncTask<String,Void,InputStream>
    {

        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream = null;
            try{
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                if(urlConnection.getResponseCode() == 200)
                {
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }
            }
            catch (IOException e)
            {
                return null;
            }
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            pdfView.fromStream(inputStream).load();
        }
    }

    private void sendBackVideoDetail() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, response.toString());
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("kursus");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String id_kursus = object.getString("id_kursus").trim();
                        String judulModul1 = object.getString("judul1").trim();
                        String judulModul2 = object.getString("judul2").trim();
                        String judulModul3 = object.getString("judul3").trim();
                        String judulModul4 = object.getString("judul4").trim();
                        String judulModul5 = object.getString("judul5").trim();
                        String judulModul6 = object.getString("judul6").trim();
                        String judulModul7 = object.getString("judul7").trim();
                        String judulModul8 = object.getString("judul8").trim();
                        String judulModul9 = object.getString("judul9").trim();
                        String judulModul10 = object.getString("judul10").trim();

                        Intent iVideo = new Intent(getApplicationContext(),ModulLearningActivity.class);
                        iVideo.putExtra("id_kursus", id_kursus);
                        iVideo.putExtra("judul1", judulModul1);
                        iVideo.putExtra("judul2", judulModul2);
                        iVideo.putExtra("judul3", judulModul3);
                        iVideo.putExtra("judul4", judulModul4);
                        iVideo.putExtra("judul5", judulModul5);
                        iVideo.putExtra("judul6", judulModul6);
                        iVideo.putExtra("judul7", judulModul7);
                        iVideo.putExtra("judul8", judulModul8);
                        iVideo.putExtra("judul9", judulModul9);
                        iVideo.putExtra("judul10", judulModul10);
                        startActivity(iVideo);
                        finish();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(ModulViewActivity.this, "Error Reading Detail " + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ModulViewActivity.this, "Error Reading Detail " + error.toString(), Toast.LENGTH_SHORT).show();
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
                sendBackVideoDetail();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
