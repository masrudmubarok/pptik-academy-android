package com.mubarok.pptikacademy;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
    String HttpURL = "http://192.168.43.206/pptik-academy-android/learning-send-videomodul.php";

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
        getSupportActionBar().setHomeButtonEnabled(true);

        //inisialisasi button & edit text
        mBtn_video = (Button) findViewById(R.id.buttonVideo);
        mBtn_modul = (Button) findViewById(R.id.buttonModul);
        mBtn_certificate = (Button) findViewById(R.id.buttonSertifikat);
        mTxt_idKursus = (TextView) findViewById(R.id.textIdL);
        mTxt_deskripsi = (TextView) findViewById(R.id.textDeskripsi);
        icon = (ImageView) findViewById(R.id.imageViewIcon);

        // Receive Data from MyCourseFragment
        getId = getIntent().getStringExtra("id_kursus");
        deskripsiTemp = getIntent().getStringExtra("deskripsi");
        iconTemp = getIntent().getStringExtra("icon");

        // Set Material
        mTxt_deskripsi.setText(deskripsiTemp);
        mTxt_idKursus.setText(getId);
        Glide.with(this)
                .load(iconTemp)
                .into(icon);

        //function button
        mBtn_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendVideoDetail();
            }
        });
        mBtn_modul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendModulDetail();
            }
        });

    }

    private void sendVideoDetail() {
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
                        String judulVideo1 = object.getString("judul_video1").trim();
                        String judulVideo2 = object.getString("judul_video2").trim();
                        String judulVideo3 = object.getString("judul_video3").trim();
                        String judulVideo4 = object.getString("judul_video4").trim();
                        String judulVideo5 = object.getString("judul_video5").trim();
                        String judulVideo6 = object.getString("judul_video6").trim();
                        String judulVideo7 = object.getString("judul_video7").trim();
                        String judulVideo8 = object.getString("judul_video8").trim();
                        String judulVideo9 = object.getString("judul_video9").trim();
                        String judulVideo10 = object.getString("judul_video10").trim();

                        Intent iVideo = new Intent(getApplicationContext(),VideoLearningActivity.class);
                        iVideo.putExtra("id_kursus", id_kursus);
                        iVideo.putExtra("judul_video1", judulVideo1);
                        iVideo.putExtra("judul_video2", judulVideo2);
                        iVideo.putExtra("judul_video3", judulVideo3);
                        iVideo.putExtra("judul_video4", judulVideo4);
                        iVideo.putExtra("judul_video5", judulVideo5);
                        iVideo.putExtra("judul_video6", judulVideo6);
                        iVideo.putExtra("judul_video7", judulVideo7);
                        iVideo.putExtra("judul_video8", judulVideo8);
                        iVideo.putExtra("judul_video9", judulVideo9);
                        iVideo.putExtra("judul_video10", judulVideo10);
                        startActivity(iVideo);
                        finish();

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

    private void sendModulDetail() {
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
                        String judulModul1 = object.getString("judul_modul1").trim();
                        String judulModul2 = object.getString("judul_modul2").trim();
                        String judulModul3 = object.getString("judul_modul3").trim();
                        String judulModul4 = object.getString("judul_modul4").trim();
                        String judulModul5 = object.getString("judul_modul5").trim();
                        String judulModul6 = object.getString("judul_modul6").trim();
                        String judulModul7 = object.getString("judul_modul7").trim();
                        String judulModul8 = object.getString("judul_modul8").trim();
                        String judulModul9 = object.getString("judul_modul9").trim();
                        String judulModul10 = object.getString("judul_modul10").trim();

                        Intent iModul = new Intent(getApplicationContext(),ModulLearningActivity.class);
                        iModul.putExtra("id_kursus", id_kursus);
                        iModul.putExtra("judul_modul1", judulModul1);
                        iModul.putExtra("judul_modul2", judulModul2);
                        iModul.putExtra("judul_modul3", judulModul3);
                        iModul.putExtra("judul_modul4", judulModul4);
                        iModul.putExtra("judul_modul5", judulModul5);
                        iModul.putExtra("judul_modul6", judulModul6);
                        iModul.putExtra("judul_modul7", judulModul7);
                        iModul.putExtra("judul_modul8", judulModul8);
                        iModul.putExtra("judul_modul9", judulModul9);
                        iModul.putExtra("judul_modul10", judulModul10);
                        startActivity(iModul);
                        finish();

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
}
