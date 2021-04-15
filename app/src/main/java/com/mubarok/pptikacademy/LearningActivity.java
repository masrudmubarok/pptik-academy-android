package com.mubarok.pptikacademy;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LearningActivity extends AppCompatActivity {

    private static final String TAG = LearningActivity.class.getSimpleName(); //getting the info
    Button mBtn_video, mBtn_modul, mBtn_certificate;
    TextView mTxt_idKursus, mTxt_deskripsi;
    ImageView icon;
    String getId, deskripsiTemp, iconTemp;

    // Adding HTTP Server URL to string variable.
    String HttpURL = "http://192.168.43.206/pptik-academy-android/mycourses-learn-read.php";

    private Map<String, String> getParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);

        //menerapkan tool bar sesuai id toolbar | ToolBarAtas adalah variabel buatan sndiri
        Toolbar ToolBarLogin = (Toolbar)findViewById(R.id.toolbar_learning);
        setSupportActionBar(ToolBarLogin);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //inisialisasi button & edit text
        mBtn_video = (Button) findViewById(R.id.buttonVideo);
        mBtn_modul = (Button) findViewById(R.id.buttonModul);
        mBtn_certificate = (Button) findViewById(R.id.buttonSertifikat);
        mTxt_idKursus = (TextView) findViewById(R.id.textIdL);
        mTxt_deskripsi = (TextView) findViewById(R.id.textDeskripsi);
        icon = (ImageView) findViewById(R.id.imageViewIcon);

        // Receive Data from TransaksiActivity
        getId = getIntent().getStringExtra("id_kursus");
        deskripsiTemp = getIntent().getStringExtra("deskripsi");
        iconTemp = getIntent().getStringExtra("icon");

        // Set Material
//        mTxt_deskripsi.setText(deskripsiTemp);
        mTxt_idKursus.setText(getId);
        Glide.with(this)
                .load(iconTemp)
                .into(icon);

    }

    private void getCourseDetail() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, response.toString());
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("kursus");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String nama_kursus = object.getString("nama_kursus").trim();
                        String deskripsi = object.getString("deskripsi").trim();
                        String harga = object.getString("harga").trim();
                        String icon = object.getString("icon").trim();
                        String jumlah_video = object.getString("jumlah_video").trim();
                        String jumlah_modul = object.getString("jumlah_modul").trim();

                        mTxt_deskripsi.setText(deskripsi);


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(LearningActivity.this, "Error Reading Detail "+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LearningActivity.this, "Error Reading Detail "+error.toString(), Toast.LENGTH_SHORT).show();
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

    @Override
    protected void onResume() {
        super.onResume();
        getCourseDetail();
    }
}
