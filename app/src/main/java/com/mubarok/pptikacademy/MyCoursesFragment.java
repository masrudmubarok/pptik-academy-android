package com.mubarok.pptikacademy;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyCoursesFragment extends Fragment {

    View v;
    HttpResponse httpResponse;
    JSONObject jsonObject = null ;
    String StringHolder = "" ;

    private static final String TAG = MyCoursesFragment.class.getSimpleName(); //getting the info
    Context context;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private RecyclerView recyclerView;
    String getId;
    ArrayList<HashMap<String , String >> listdata;

    SessionManager sessionManager;

    // Adding HTTP Server URL to string variable.
    String HttpURL = "https://pptikacademy.000webhostapp.com/api/mycourses-read.php";

    public MyCoursesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_my_courses, container, false);

        // --------------------
        // RecyclerView Kursus (My Courses)
        // --------------------

        sessionManager = new SessionManager(getActivity().getApplicationContext());
        sessionManager.loginCheck();

        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.KEY_ID);

        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerViewMycourses);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        listdata = new ArrayList<HashMap<String, String>>();

        stringRequest = new StringRequest(Request.Method.POST, HttpURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, response.toString());
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("kursus");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject json = jsonArray.getJSONObject(i);
                        HashMap<String, String> itemt = new HashMap<String, String>();
                        itemt.put("id_kursus", json.getString("id_kursus"));
                        itemt.put("nama_kursus", json.getString("nama_kursus"));
                        itemt.put("deskripsi", json.getString("deskripsi"));
                        itemt.put("nama_tutor", json.getString("nama_tutor"));
                        itemt.put("harga", json.getString("harga"));
                        itemt.put("icon", json.getString("icon"));
                        itemt.put("jumlah_video", json.getString("jumlah_video"));
                        itemt.put("jumlah_modul", json.getString("jumlah_modul"));
                        listdata.add(itemt);
                        RecyclerAdapterMyCourses adapter = new RecyclerAdapterMyCourses(MyCoursesFragment.this, listdata);
                        recyclerView.setAdapter(adapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }})

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String > params = new HashMap<>();
                params.put("id_siswa", getId);
                return params;
            }
        };
        requestQueue.add(stringRequest);

        return v;
    }

}
