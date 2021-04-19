package com.mubarok.pptikacademy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;

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

public class VideoLearningActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = VideoLearningActivity.class.getSimpleName(); //getting the info
    Button mBtn_video1, mBtn_video2, mBtn_video3, mBtn_video4, mBtn_video5, mBtn_video6, mBtn_video7, mBtn_video8, mBtn_video9, mBtn_video10;
    String getId, video1Temp1, video1Temp2, video1Temp3, video1Temp4, video1Temp5, video1Temp6, video1Temp7, video1Temp8, video1Temp9, video1Temp10;

    // Adding HTTP Server URL to string variable.
    String HttpURL = "http://192.168.43.206/pptik-academy-android/videomodul-send-learning.php";
    String HttpURL1 = "http://192.168.43.206/pptik-academy-android/videolearning-send-videoview.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_learning);

        //menerapkan tool bar sesuai id toolbar | ToolBarAtas adalah variabel buatan sndiri
        Toolbar ToolBarLogin = (Toolbar)findViewById(R.id.toolbar_videolearning);
        setSupportActionBar(ToolBarLogin);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);

        // Declaration
        mBtn_video1 = (Button) findViewById(R.id.buttonVideoC1);
        mBtn_video2 = (Button) findViewById(R.id.buttonVideoC2);
        mBtn_video3 = (Button) findViewById(R.id.buttonVideoC3);
        mBtn_video4 = (Button) findViewById(R.id.buttonVideoC4);
        mBtn_video5 = (Button) findViewById(R.id.buttonVideoC5);
        mBtn_video6 = (Button) findViewById(R.id.buttonVideoC6);
        mBtn_video7 = (Button) findViewById(R.id.buttonVideoC7);
        mBtn_video8 = (Button) findViewById(R.id.buttonVideoC8);
        mBtn_video9 = (Button) findViewById(R.id.buttonVideoC9);
        mBtn_video10 = (Button) findViewById(R.id.buttonVideoC10);

        // Receive Data from LearnignActivity
        getId = getIntent().getStringExtra("id_kursus");
        video1Temp1 = getIntent().getStringExtra("judul_video1");
        video1Temp2 = getIntent().getStringExtra("judul_video2");
        video1Temp3 = getIntent().getStringExtra("judul_video3");
        video1Temp4 = getIntent().getStringExtra("judul_video4");
        video1Temp5 = getIntent().getStringExtra("judul_video5");
        video1Temp6 = getIntent().getStringExtra("judul_video6");
        video1Temp7 = getIntent().getStringExtra("judul_video7");
        video1Temp8 = getIntent().getStringExtra("judul_video8");
        video1Temp9 = getIntent().getStringExtra("judul_video9");
        video1Temp10 = getIntent().getStringExtra("judul_video10");

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
            case R.id.buttonVideoC1:
                sendVideo1();
                break;
            case R.id.buttonVideoC2:
                sendVideo2();
                break;
            case R.id.buttonVideoC3:
                sendVideo3();
                break;
            case R.id.buttonVideoC4:
                sendVideo4();
                break;
            case R.id.buttonVideoC5:
                sendVideo5();
                break;
            case R.id.buttonVideoC6:
                sendVideo6();
                break;
            case R.id.buttonVideoC7:
                sendVideo7();
                break;
            case R.id.buttonVideoC8:
                sendVideo8();
                break;
            case R.id.buttonVideoC9:
                sendVideo9();
                break;
            case R.id.buttonVideoC10:
                sendVideo10();
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
                        String icon = object.getString("icon").trim();
                        String jumlah_video = object.getString("jumlah_video").trim();
                        String jumlah_modul = object.getString("jumlah_modul").trim();

                        Intent intent = new Intent(getApplicationContext(), LearningActivity.class);
                        intent.putExtra("id_kursus", id_kursus);
                        intent.putExtra("deskripsi", deskripsi);
                        intent.putExtra("icon", icon);
                        startActivity(intent);
                        finish();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(VideoLearningActivity.this, "Error Reading Detail " + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VideoLearningActivity.this, "Error Reading Detail " + error.toString(), Toast.LENGTH_SHORT).show();
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
                        String judulVideo1 = object.getString("judul_video1").trim();
                        String video1 = object.getString("video1").trim();

                        Intent intent = new Intent(getApplicationContext(), VideoViewActivity.class);
                        intent.putExtra("id_kursus", id_kursus);
                        intent.putExtra("judul_video", judulVideo1);
                        intent.putExtra("video", video1);
                        startActivity(intent);
                        finish();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(VideoLearningActivity.this, "Error Reading Detail " + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VideoLearningActivity.this, "Error Reading Detail " + error.toString(), Toast.LENGTH_SHORT).show();
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
                        String judulVideo2 = object.getString("judul_video2").trim();
                        String video2 = object.getString("video2").trim();

                        Intent intent = new Intent(getApplicationContext(), VideoViewActivity.class);
                        intent.putExtra("id_kursus", id_kursus);
                        intent.putExtra("judul_video", judulVideo2);
                        intent.putExtra("video", video2);
                        startActivity(intent);
                        finish();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(VideoLearningActivity.this, "Error Reading Detail " + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VideoLearningActivity.this, "Error Reading Detail " + error.toString(), Toast.LENGTH_SHORT).show();
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

    private void sendVideo3() {
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
                        String judulVideo3 = object.getString("judul_video3").trim();
                        String video3 = object.getString("video3").trim();

                        Intent intent = new Intent(getApplicationContext(), VideoViewActivity.class);
                        intent.putExtra("id_kursus", id_kursus);
                        intent.putExtra("judul_video", judulVideo3);
                        intent.putExtra("video", video3);
                        startActivity(intent);
                        finish();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(VideoLearningActivity.this, "Error Reading Detail " + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VideoLearningActivity.this, "Error Reading Detail " + error.toString(), Toast.LENGTH_SHORT).show();
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

    private void sendVideo4() {
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
                        String judulVideo4 = object.getString("judul_video4").trim();
                        String video4 = object.getString("video4").trim();

                        Intent intent = new Intent(getApplicationContext(), VideoViewActivity.class);
                        intent.putExtra("id_kursus", id_kursus);
                        intent.putExtra("judul_video", judulVideo4);
                        intent.putExtra("video", video4);
                        startActivity(intent);
                        finish();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(VideoLearningActivity.this, "Error Reading Detail " + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VideoLearningActivity.this, "Error Reading Detail " + error.toString(), Toast.LENGTH_SHORT).show();
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

    private void sendVideo5() {
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
                        String judulVideo5 = object.getString("judul_video5").trim();
                        String video5 = object.getString("video5").trim();

                        Intent intent = new Intent(getApplicationContext(), VideoViewActivity.class);
                        intent.putExtra("id_kursus", id_kursus);
                        intent.putExtra("judul_video", judulVideo5);
                        intent.putExtra("video", video5);
                        startActivity(intent);
                        finish();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(VideoLearningActivity.this, "Error Reading Detail " + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VideoLearningActivity.this, "Error Reading Detail " + error.toString(), Toast.LENGTH_SHORT).show();
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

    private void sendVideo6() {
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
                        String judulVideo6 = object.getString("judul_video6").trim();
                        String video6 = object.getString("video6").trim();

                        Intent intent = new Intent(getApplicationContext(), VideoViewActivity.class);
                        intent.putExtra("id_kursus", id_kursus);
                        intent.putExtra("judul_video", judulVideo6);
                        intent.putExtra("video", video6);
                        startActivity(intent);
                        finish();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(VideoLearningActivity.this, "Error Reading Detail " + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VideoLearningActivity.this, "Error Reading Detail " + error.toString(), Toast.LENGTH_SHORT).show();
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

    private void sendVideo7() {
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
                        String judulVideo7 = object.getString("judul_video7").trim();
                        String video7 = object.getString("video7").trim();

                        Intent intent = new Intent(getApplicationContext(), VideoViewActivity.class);
                        intent.putExtra("id_kursus", id_kursus);
                        intent.putExtra("judul_video", judulVideo7);
                        intent.putExtra("video", video7);
                        startActivity(intent);
                        finish();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(VideoLearningActivity.this, "Error Reading Detail " + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VideoLearningActivity.this, "Error Reading Detail " + error.toString(), Toast.LENGTH_SHORT).show();
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

    private void sendVideo8() {
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
                        String judulVideo8 = object.getString("judul_video8").trim();
                        String video8 = object.getString("video8").trim();

                        Intent intent = new Intent(getApplicationContext(), VideoViewActivity.class);
                        intent.putExtra("id_kursus", id_kursus);
                        intent.putExtra("judul_video", judulVideo8);
                        intent.putExtra("video", video8);
                        startActivity(intent);
                        finish();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(VideoLearningActivity.this, "Error Reading Detail " + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VideoLearningActivity.this, "Error Reading Detail " + error.toString(), Toast.LENGTH_SHORT).show();
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

    private void sendVideo9() {
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
                        String judulVideo9 = object.getString("judul_video9").trim();
                        String video9 = object.getString("video9").trim();

                        Intent intent = new Intent(getApplicationContext(), VideoViewActivity.class);
                        intent.putExtra("id_kursus", id_kursus);
                        intent.putExtra("judul_video", judulVideo9);
                        intent.putExtra("video", video9);
                        startActivity(intent);
                        finish();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(VideoLearningActivity.this, "Error Reading Detail " + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VideoLearningActivity.this, "Error Reading Detail " + error.toString(), Toast.LENGTH_SHORT).show();
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

    private void sendVideo10() {
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
                        String judulVideo10 = object.getString("judul_video10").trim();
                        String video10 = object.getString("video10").trim();

                        Intent intent = new Intent(getApplicationContext(), VideoViewActivity.class);
                        intent.putExtra("id_kursus", id_kursus);
                        intent.putExtra("judul_video", judulVideo10);
                        intent.putExtra("video", video10);
                        startActivity(intent);
                        finish();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(VideoLearningActivity.this, "Error Reading Detail " + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VideoLearningActivity.this, "Error Reading Detail " + error.toString(), Toast.LENGTH_SHORT).show();
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
