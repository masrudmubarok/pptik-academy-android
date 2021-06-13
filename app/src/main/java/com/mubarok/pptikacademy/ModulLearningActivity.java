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

public class ModulLearningActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = ModulLearningActivity.class.getSimpleName(); //getting the info
    Button mBtn_modul1, mBtn_modul2, mBtn_modul3, mBtn_modul4, mBtn_modul5, mBtn_modul6, mBtn_modul7, mBtn_modul8, mBtn_modul9, mBtn_modul10;
    String getId, modulTemp1, modulTemp2, modulTemp3, modulTemp4, modulTemp5, modulTemp6, modulTemp7, modulTemp8, modulTemp9, modulTemp10;

    // Adding HTTP Server URL to string variable.
    String HttpURL = "https://pptikacademy01.000webhostapp.com/api/videomodul-send-learning.php";
    String HttpURL1 = "https://pptikacademy01.000webhostapp.com/api/modullearning-send-modulview.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modul_learning);

        //menerapkan tool bar sesuai id toolbar | ToolBarAtas adalah variabel buatan sndiri
        Toolbar ToolBarLogin = (Toolbar)findViewById(R.id.toolbar_modullearning);
        setSupportActionBar(ToolBarLogin);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);

        // Declaration
        mBtn_modul1 = (Button) findViewById(R.id.buttonModulC1);
        mBtn_modul2 = (Button) findViewById(R.id.buttonModulC2);
        mBtn_modul3 = (Button) findViewById(R.id.buttonModulC3);
        mBtn_modul4 = (Button) findViewById(R.id.buttonModulC4);
        mBtn_modul5 = (Button) findViewById(R.id.buttonModulC5);
        mBtn_modul6 = (Button) findViewById(R.id.buttonModulC6);
        mBtn_modul7 = (Button) findViewById(R.id.buttonModulC7);
        mBtn_modul8 = (Button) findViewById(R.id.buttonModulC8);
        mBtn_modul9 = (Button) findViewById(R.id.buttonModulC9);
        mBtn_modul10 = (Button) findViewById(R.id.buttonModulC10);

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
            case R.id.buttonModulC1:
                sendModul1();
                break;
            case R.id.buttonModulC2:
                sendModul2();
                break;
            case R.id.buttonModulC3:
                sendModul3();
                break;
            case R.id.buttonModulC4:
                sendModul4();
                break;
            case R.id.buttonModulC5:
                sendModul5();
                break;
            case R.id.buttonModulC6:
                sendModul6();
                break;
            case R.id.buttonModulC7:
                sendModul7();
                break;
            case R.id.buttonModulC8:
                sendModul8();
                break;
            case R.id.buttonModulC9:
                sendModul9();
                break;
            case R.id.buttonModulC10:
                sendModul10();
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
                        String nama_tutor = object.getString("nama_tutor").trim();
                        String icon = object.getString("icon").trim();

                        Intent intent = new Intent(getApplicationContext(), LearningActivity.class);
                        intent.putExtra("id_kursus", id_kursus);
                        intent.putExtra("nama_kursus", nama_kursus);
                        intent.putExtra("deskripsi", deskripsi);
                        intent.putExtra("nama_tutor", nama_tutor);
                        intent.putExtra("icon", icon);
                        startActivity(intent);
                        finish();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(ModulLearningActivity.this, "Error Reading Detail " + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ModulLearningActivity.this, "Error Reading Detail " + error.toString(), Toast.LENGTH_SHORT).show();
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
                        String modul1 = object.getString("modul1").trim();

                        Intent intent = new Intent(getApplicationContext(), ModulViewActivity.class);
                        intent.putExtra("id_kursus", id_kursus);
                        intent.putExtra("judul_modul", judulModul1);
                        intent.putExtra("modul", modul1);
                        startActivity(intent);
                        finish();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(ModulLearningActivity.this, "Error Reading Detail " + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ModulLearningActivity.this, "Error Reading Detail " + error.toString(), Toast.LENGTH_SHORT).show();
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
                        String modul2 = object.getString("modul2").trim();

                        Intent intent = new Intent(getApplicationContext(), ModulViewActivity.class);
                        intent.putExtra("id_kursus", id_kursus);
                        intent.putExtra("judul_modul", judulModul2);
                        intent.putExtra("modul", modul2);
                        startActivity(intent);
                        finish();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(ModulLearningActivity.this, "Error Reading Detail " + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ModulLearningActivity.this, "Error Reading Detail " + error.toString(), Toast.LENGTH_SHORT).show();
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

    private void sendModul3() {
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
                        String judulModul3 = object.getString("judul3").trim();
                        String modul3 = object.getString("modul3").trim();

                        Intent intent = new Intent(getApplicationContext(), ModulViewActivity.class);
                        intent.putExtra("id_kursus", id_kursus);
                        intent.putExtra("judul_modul", judulModul3);
                        intent.putExtra("modul", modul3);
                        startActivity(intent);
                        finish();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(ModulLearningActivity.this, "Error Reading Detail " + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ModulLearningActivity.this, "Error Reading Detail " + error.toString(), Toast.LENGTH_SHORT).show();
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

    private void sendModul4() {
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
                        String judulModul4 = object.getString("judul4").trim();
                        String modul4 = object.getString("modul4").trim();

                        Intent intent = new Intent(getApplicationContext(), ModulViewActivity.class);
                        intent.putExtra("id_kursus", id_kursus);
                        intent.putExtra("judul_modul", judulModul4);
                        intent.putExtra("modul", modul4);
                        startActivity(intent);
                        finish();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(ModulLearningActivity.this, "Error Reading Detail " + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ModulLearningActivity.this, "Error Reading Detail " + error.toString(), Toast.LENGTH_SHORT).show();
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

    private void sendModul5() {
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
                        String judulModul5 = object.getString("judul5").trim();
                        String modul5 = object.getString("modul5").trim();

                        Intent intent = new Intent(getApplicationContext(), ModulViewActivity.class);
                        intent.putExtra("id_kursus", id_kursus);
                        intent.putExtra("judul_modul", judulModul5);
                        intent.putExtra("modul", modul5);
                        startActivity(intent);
                        finish();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(ModulLearningActivity.this, "Error Reading Detail " + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ModulLearningActivity.this, "Error Reading Detail " + error.toString(), Toast.LENGTH_SHORT).show();
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

    private void sendModul6() {
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
                        String judulModul6 = object.getString("judul6").trim();
                        String modul6 = object.getString("modul6").trim();

                        Intent intent = new Intent(getApplicationContext(), ModulViewActivity.class);
                        intent.putExtra("id_kursus", id_kursus);
                        intent.putExtra("judul_modul", judulModul6);
                        intent.putExtra("modul", modul6);
                        startActivity(intent);
                        finish();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(ModulLearningActivity.this, "Error Reading Detail " + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ModulLearningActivity.this, "Error Reading Detail " + error.toString(), Toast.LENGTH_SHORT).show();
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

    private void sendModul7() {
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
                        String judulModul7 = object.getString("judul7").trim();
                        String modul7 = object.getString("modul7").trim();

                        Intent intent = new Intent(getApplicationContext(), ModulViewActivity.class);
                        intent.putExtra("id_kursus", id_kursus);
                        intent.putExtra("judul_modul", judulModul7);
                        intent.putExtra("modul", modul7);
                        startActivity(intent);
                        finish();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(ModulLearningActivity.this, "Error Reading Detail " + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ModulLearningActivity.this, "Error Reading Detail " + error.toString(), Toast.LENGTH_SHORT).show();
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

    private void sendModul8() {
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
                        String judulModul8 = object.getString("judul8").trim();
                        String modul8 = object.getString("modul8").trim();

                        Intent intent = new Intent(getApplicationContext(), ModulViewActivity.class);
                        intent.putExtra("id_kursus", id_kursus);
                        intent.putExtra("judul_modul", judulModul8);
                        intent.putExtra("modul", modul8);
                        startActivity(intent);
                        finish();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(ModulLearningActivity.this, "Error Reading Detail " + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ModulLearningActivity.this, "Error Reading Detail " + error.toString(), Toast.LENGTH_SHORT).show();
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

    private void sendModul9() {
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
                        String judulModul9 = object.getString("judul9").trim();
                        String modul9 = object.getString("modul9").trim();

                        Intent intent = new Intent(getApplicationContext(), ModulViewActivity.class);
                        intent.putExtra("id_kursus", id_kursus);
                        intent.putExtra("judul_modul", judulModul9);
                        intent.putExtra("modul", modul9);
                        startActivity(intent);
                        finish();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(ModulLearningActivity.this, "Error Reading Detail " + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ModulLearningActivity.this, "Error Reading Detail " + error.toString(), Toast.LENGTH_SHORT).show();
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

    private void sendModul10() {
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
                        String judulModul10 = object.getString("judul10").trim();
                        String modul10 = object.getString("modul10").trim();

                        Intent intent = new Intent(getApplicationContext(), ModulViewActivity.class);
                        intent.putExtra("id_kursus", id_kursus);
                        intent.putExtra("judul_modul", judulModul10);
                        intent.putExtra("modul", modul10);
                        startActivity(intent);
                        finish();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(ModulLearningActivity.this, "Error Reading Detail " + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ModulLearningActivity.this, "Error Reading Detail " + error.toString(), Toast.LENGTH_SHORT).show();
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
