package com.mubarok.pptikacademy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class VideoLearningOverviewActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = VideoLearningOverviewActivity.class.getSimpleName(); //getting the info
    Button mBtn_video1, mBtn_video2, mBtn_video3, mBtn_video4, mBtn_video5, mBtn_video6, mBtn_video7, mBtn_video8, mBtn_video9, mBtn_video10;
    String getId, video1Temp1, video1Temp2, video1Temp3, video1Temp4, video1Temp5, video1Temp6, video1Temp7, video1Temp8, video1Temp9, video1Temp10;

    // Adding HTTP Server URL to string variable.
    String HttpURL = "https://zyralebags.000webhostapp.com/api/videomodul-send-learning.php";
    String HttpURL1 = "https://zyralebags.000webhostapp.com/api/videolearning-send-videoview.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_learning_overview);

        //menerapkan tool bar sesuai id toolbar | ToolBarAtas adalah variabel buatan sndiri
        Toolbar ToolBarLogin = (Toolbar)findViewById(R.id.toolbar_videolearningO);
        setSupportActionBar(ToolBarLogin);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);

        // Declaration
        mBtn_video1 = (Button) findViewById(R.id.buttonVideoC1O);
        mBtn_video2 = (Button) findViewById(R.id.buttonVideoC2O);
        mBtn_video3 = (Button) findViewById(R.id.buttonVideoC3O);
        mBtn_video4 = (Button) findViewById(R.id.buttonVideoC4O);
        mBtn_video5 = (Button) findViewById(R.id.buttonVideoC5O);
        mBtn_video6 = (Button) findViewById(R.id.buttonVideoC6O);
        mBtn_video7 = (Button) findViewById(R.id.buttonVideoC7O);
        mBtn_video8 = (Button) findViewById(R.id.buttonVideoC8O);
        mBtn_video9 = (Button) findViewById(R.id.buttonVideoC9O);
        mBtn_video10 = (Button) findViewById(R.id.buttonVideoC10O);

        // Receive Data from LearnignActivity
        getId = getIntent().getStringExtra("id_kursus");
        video1Temp1 = getIntent().getStringExtra("judul1");
        video1Temp2 = getIntent().getStringExtra("judul2");
        video1Temp3 = getIntent().getStringExtra("judul3");
        video1Temp4 = getIntent().getStringExtra("judul4");
        video1Temp5 = getIntent().getStringExtra("judul5");
        video1Temp6 = getIntent().getStringExtra("judul6");
        video1Temp7 = getIntent().getStringExtra("judul7");
        video1Temp8 = getIntent().getStringExtra("judul8");
        video1Temp9 = getIntent().getStringExtra("judul9");
        video1Temp10 = getIntent().getStringExtra("judul10");

        // Set Material
        mBtn_video1.setText(video1Temp1);
        mBtn_video2.setText(video1Temp2);
        mBtn_video3.setText(video1Temp3);
        mBtn_video4.setText(video1Temp4);
        mBtn_video5.setText(video1Temp5);
        mBtn_video6.setText(video1Temp6);
        mBtn_video7.setText(video1Temp7);
        mBtn_video8.setText(video1Temp8);
        mBtn_video9.setText(video1Temp9);
        mBtn_video10.setText(video1Temp10);

        // Method
        mBtn_video1.setOnClickListener(this);
        mBtn_video2.setOnClickListener(this);
        mBtn_video3.setOnClickListener(this);
        mBtn_video4.setOnClickListener(this);
        mBtn_video5.setOnClickListener(this);
        mBtn_video6.setOnClickListener(this);
        mBtn_video7.setOnClickListener(this);
        mBtn_video8.setOnClickListener(this);
        mBtn_video9.setOnClickListener(this);
        mBtn_video10.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonVideoC1O:
                sendVideo1();
                break;
            case R.id.buttonVideoC2O:
                sendVideo2();
                break;
            case R.id.buttonVideoC3O:
                Toast.makeText(getApplicationContext(), "Please buy the course first to open the learning video section 3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.buttonVideoC4O:
                Toast.makeText(getApplicationContext(), "Please buy the course first to open the learning video section 4", Toast.LENGTH_SHORT).show();
                break;
            case R.id.buttonVideoC5O:
                Toast.makeText(getApplicationContext(), "Please buy the course first to open the learning video section 5!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.buttonVideoC6O:
                Toast.makeText(getApplicationContext(), "Please buy the course first to open the learning video section 6!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.buttonVideoC7O:
                Toast.makeText(getApplicationContext(), "Please buy the course first to open the learning video section 7!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.buttonVideoC8O:
                Toast.makeText(getApplicationContext(), "Please buy the course first to open the learning video section 8!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.buttonVideoC9O:
                Toast.makeText(getApplicationContext(), "Please buy the course first to open the learning video section 9!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.buttonVideoC10O:
                Toast.makeText(getApplicationContext(), "Please buy the course first to open the learning video section 10!", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void sendBackCourseDetail() {
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
                        String nama_kursus = object.getString("nama_kursus").trim();
                        String deskripsi = object.getString("deskripsi").trim();
                        String harga = object.getString("harga").trim();
                        String nama_tutor = object.getString("nama_tutor").trim();
                        String icon = object.getString("icon").trim();

                        Intent intent = new Intent(getApplicationContext(), LearningOverviewActivity.class);
                        intent.putExtra("id_kursus", id_kursus);
                        intent.putExtra("nama_kursus", nama_kursus);
                        intent.putExtra("deskripsi", deskripsi);
                        intent.putExtra("nama_tutor", nama_tutor);
                        intent.putExtra("harga", harga);
                        intent.putExtra("icon", icon);
                        startActivity(intent);
                        finish();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(VideoLearningOverviewActivity.this, "Error Reading Detail " + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VideoLearningOverviewActivity.this, "Error Reading Detail " + error.toString(), Toast.LENGTH_SHORT).show();
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
                sendBackCourseDetail();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void sendVideo1() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpURL1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, response.toString());
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("kursus");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String id_kursus = object.getString("id_kursus").trim();
                        String judulVideo1 = object.getString("judul1").trim();
                        String video1 = object.getString("video1").trim();

                        Intent intent = new Intent(getApplicationContext(), VideoOverviewActivity.class);
                        intent.putExtra("id_kursus", id_kursus);
                        intent.putExtra("judul_video", judulVideo1);
                        intent.putExtra("video", video1);
                        startActivity(intent);
                        finish();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(VideoLearningOverviewActivity.this, "Error Reading Detail " + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VideoLearningOverviewActivity.this, "Error Reading Detail " + error.toString(), Toast.LENGTH_SHORT).show();
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

    private void sendVideo2() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpURL1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, response.toString());
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("kursus");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String id_kursus = object.getString("id_kursus").trim();
                        String judulVideo2 = object.getString("judul2").trim();
                        String video2 = object.getString("video2").trim();

                        Intent intent = new Intent(getApplicationContext(), VideoOverviewActivity.class);
                        intent.putExtra("id_kursus", id_kursus);
                        intent.putExtra("judul_video", judulVideo2);
                        intent.putExtra("video", video2);
                        startActivity(intent);
                        finish();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(VideoLearningOverviewActivity.this, "Error Reading Detail " + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VideoLearningOverviewActivity.this, "Error Reading Detail " + error.toString(), Toast.LENGTH_SHORT).show();
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
}
