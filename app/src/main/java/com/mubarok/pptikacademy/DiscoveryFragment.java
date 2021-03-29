package com.mubarok.pptikacademy;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;

import org.apache.http.HttpResponse;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoveryFragment extends Fragment {

    View v;
    HttpResponse httpResponse;
    JSONObject jsonObject = null ;
    String StringHolder = "" ;

    Context context;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private RecyclerView recyclerView;
    ArrayList<HashMap<String , String >> listdata;

    // Adding HTTP Server URL to string variable.
    String HttpURL = "http://192.168.43.114/pptik-academy-android/transaksiview.php";

    public DiscoveryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_discovery, container, false);
        return v;


    }

}
