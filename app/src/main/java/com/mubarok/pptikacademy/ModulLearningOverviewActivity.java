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

public class ModulLearningOverviewActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = ModulLearningOverviewActivity.class.getSimpleName(); //getting the info
    Button mBtn_modul1, mBtn_modul2, mBtn_modul3, mBtn_modul4, mBtn_modul5, mBtn_modul6, mBtn_modul7, mBtn_modul8, mBtn_modul9, mBtn_modul10;
    String getId, modulTemp1, modulTemp2, modulTemp3, modulTemp4, modulTemp5, modulTemp6, modulTemp7, modulTemp8, modulTemp9, modulTemp10;

    // Adding HTTP Server URL to string variable.
    String HttpURL = "http://192.168.43.206/pptik-academy-android/videomodul-send-learning.php";
    String HttpURL1 = "http://192.168.43.206/pptik-academy-android/modullearning-send-modulview.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modul_learning_overview);

        //menerapkan tool bar sesuai id toolbar | ToolBarAtas adalah variabel buatan sndiri
        Toolbar ToolBar = (Toolbar)findViewById(R.id.toolbar_modullearningO);
        setSupportActionBar(ToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);

        // Declaration
        mBtn_modul1 = (Button) findViewById(R.id.buttonModulC1O);
        mBtn_modul2 = (Button) findViewById(R.id.buttonModulC2O);
        mBtn_modul3 = (Button) findViewById(R.id.buttonModulC3O);
        mBtn_modul4 = (Button) findViewById(R.id.buttonModulC4O);
        mBtn_modul5 = (Button) findViewById(R.id.buttonModulC5O);
        mBtn_modul6 = (Button) findViewById(R.id.buttonModulC6O);
        mBtn_modul7 = (Button) findViewById(R.id.buttonModulC7O);
        mBtn_modul8 = (Button) findViewById(R.id.buttonModulC8O);
        mBtn_modul9 = (Button) findViewById(R.id.buttonModulC9O);
        mBtn_modul10 = (Button) findViewById(R.id.buttonModulC10O);

        // Receive Data from LearnignActivity
        getId = getIntent().getStringExtra("id_kursus");
        modulTemp1 = getIntent().getStringExtra("judul1");
        modulTemp2 = getIntent().getStringExtra("judul2");
        modulTemp3 = getIntent().getStringExtra("judul3");
        modulTemp4 = getIntent().getStringExtra("judul4");
        modulTemp5 = getIntent().getStringExtra("judul5");
        modulTemp6 = getIntent().getStringExtra("judul6");
        modulTemp7 = getIntent().getStringExtra("judul7");
        modulTemp8 = getIntent().getStringExtra("judul8");
        modulTemp9 = getIntent().getStringExtra("judul9");
        modulTemp10 = getIntent().getStringExtra("judul10");

        // Set Material
        mBtn_modul1.setText(modulTemp1);
        mBtn_modul2.setText(modulTemp2);
        mBtn_modul3.setText(modulTemp3);
        mBtn_modul4.setText(modulTemp4);
        mBtn_modul5.setText(modulTemp5);
        mBtn_modul6.setText(modulTemp6);
        mBtn_modul7.setText(modulTemp7);
        mBtn_modul8.setText(modulTemp8);
        mBtn_modul9.setText(modulTemp9);
        mBtn_modul10.setText(modulTemp10);

        // Method
        mBtn_modul1.setOnClickListener(this);
        mBtn_modul2.setOnClickListener(this);
        mBtn_modul3.setOnClickListener(this);
        mBtn_modul4.setOnClickListener(this);
        mBtn_modul5.setOnClickListener(this);
        mBtn_modul6.setOnClickListener(this);
        mBtn_modul7.setOnClickListener(this);
        mBtn_modul8.setOnClickListener(this);
        mBtn_modul9.setOnClickListener(this);
        mBtn_modul10.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonModulC1O:
                sendModul1();
                break;
            case R.id.buttonModulC2O:
                sendModul2();
                break;
            case R.id.buttonModulC3O:
                Toast.makeText(getApplicationContext(), "Please buy the course first to open the learning modul section 3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.buttonModulC4O:
                Toast.makeText(getApplicationContext(), "Please buy the course first to open the learning modul section 4", Toast.LENGTH_SHORT).show();
                break;
            case R.id.buttonModulC5O:
                Toast.makeText(getApplicationContext(), "Please buy the course first to open the learning modul section 5", Toast.LENGTH_SHORT).show();
                break;
            case R.id.buttonModulC6O:
                Toast.makeText(getApplicationContext(), "Please buy the course first to open the learning modul section 6", Toast.LENGTH_SHORT).show();
                break;
            case R.id.buttonModulC7O:
                Toast.makeText(getApplicationContext(), "Please buy the course first to open the learning modul section 7", Toast.LENGTH_SHORT).show();
                break;
            case R.id.buttonModulC8O:
                Toast.makeText(getApplicationContext(), "Please buy the course first to open the learning modul section 8", Toast.LENGTH_SHORT).show();
                break;
            case R.id.buttonModulC9O:
                Toast.makeText(getApplicationContext(), "Please buy the course first to open the learning modul section 9", Toast.LENGTH_SHORT).show();
                break;
            case R.id.buttonModulC10O:
                Toast.makeText(getApplicationContext(), "Please buy the course first to open the learning modul section 10", Toast.LENGTH_SHORT).show();
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

                        Intent intent = new Intent(getApplicationContext(), LearningOverviewActivity.class);
                        intent.putExtra("id_kursus", id_kursus);
                        intent.putExtra("deskripsi", deskripsi);
                        intent.putExtra("icon", icon);
                        startActivity(intent);
                        finish();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(ModulLearningOverviewActivity.this, "Error Reading Detail " + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ModulLearningOverviewActivity.this, "Error Reading Detail " + error.toString(), Toast.LENGTH_SHORT).show();
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

    private void sendModul1() {
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
                        String judulModul1 = object.getString("judul1").trim();
                        String modul1 = object.getString("moduloverview1").trim();

                        Intent intent = new Intent(getApplicationContext(), ModulOverviewActivity.class);
                        intent.putExtra("id_kursus", id_kursus);
                        intent.putExtra("judul_modul", judulModul1);
                        intent.putExtra("modul", modul1);
                        startActivity(intent);
                        finish();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(ModulLearningOverviewActivity.this, "Error Reading Detail " + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ModulLearningOverviewActivity.this, "Error Reading Detail " + error.toString(), Toast.LENGTH_SHORT).show();
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

    private void sendModul2() {
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
                        String judulModul2 = object.getString("judul2").trim();
                        String modul2 = object.getString("moduloverview2").trim();

                        Intent intent = new Intent(getApplicationContext(), ModulOverviewActivity.class);
                        intent.putExtra("id_kursus", id_kursus);
                        intent.putExtra("judul_modul", judulModul2);
                        intent.putExtra("modul", modul2);
                        startActivity(intent);
                        finish();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(ModulLearningOverviewActivity.this, "Error Reading Detail " + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ModulLearningOverviewActivity.this, "Error Reading Detail " + error.toString(), Toast.LENGTH_SHORT).show();
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
