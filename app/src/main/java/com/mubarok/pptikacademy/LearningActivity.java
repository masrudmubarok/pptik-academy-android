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
    String HttpURL = "http://192.168.43.206/pptik-academy-android/learning-send-videolearning.php";

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
                sendCourseDetail();
            }
        });
        mBtn_modul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iModul = new Intent(getApplicationContext(),ModulLearningActivity.class);
                startActivity(iModul);
            }
        });

    }

    private void sendCourseDetail() {
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
                        String judulVideo1 = object.getString("judulvid1").trim();
                        String judulVideo2 = object.getString("judulvid2").trim();
                        String judulVideo3 = object.getString("judulvid3").trim();
                        String judulVideo4 = object.getString("judulvid4").trim();
                        String judulVideo5 = object.getString("judulvid5").trim();
                        String judulVideo6 = object.getString("judulvid6").trim();
                        String judulVideo7 = object.getString("judulvid7").trim();
                        String judulVideo8 = object.getString("judulvid8").trim();
                        String judulVideo9 = object.getString("judulvid9").trim();
                        String judulVideo10 = object.getString("judulvid10").trim();

                        Intent intent = new Intent(getApplicationContext(), VideoLearningActivity.class);
                        intent.putExtra("id_kursus", id_kursus);
                        intent.putExtra("judulvid1", judulVideo1);
                        intent.putExtra("judulvid2", judulVideo2);
                        intent.putExtra("judulvid3", judulVideo3);
                        intent.putExtra("judulvid4", judulVideo4);
                        intent.putExtra("judulvid5", judulVideo5);
                        intent.putExtra("judulvid6", judulVideo6);
                        intent.putExtra("judulvid7", judulVideo7);
                        intent.putExtra("judulvid8", judulVideo8);
                        intent.putExtra("judulvid9", judulVideo9);
                        intent.putExtra("judulvid10", judulVideo10);
                        startActivity(intent);
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
