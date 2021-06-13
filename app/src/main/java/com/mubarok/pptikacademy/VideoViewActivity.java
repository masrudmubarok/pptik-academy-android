package com.mubarok.pptikacademy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

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
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class VideoViewActivity extends AppCompatActivity {

    private static final String TAG = VideoViewActivity.class.getSimpleName(); //getting the info
    TextView textView;
    VideoView videoView;
    String getId, videoTemp, judulVideoTemp;

    // Adding HTTP Server URL to string variable.
    String HttpURL = "https://pptikacademy01.000webhostapp.com/api/videomodulview-send-videomodullearning.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);

        //menerapkan tool bar sesuai id toolbar | ToolBarAtas adalah variabel buatan sndiri
        Toolbar ToolBar = (Toolbar)findViewById(R.id.toolbar_videoview);
        setSupportActionBar(ToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);

        // Declaration
        videoView = (VideoView) findViewById(R.id.videoViewLearning);
        textView = (TextView) findViewById(R.id.textToolbarVideoView);

        // Receive Data from LearnignActivity
        getId = getIntent().getStringExtra("id_kursus");
        judulVideoTemp = getIntent().getStringExtra("judul_video");
        videoTemp = getIntent().getStringExtra("video");

        // Set material
        textView.setText(judulVideoTemp);

        String linkVideo = videoTemp;
        Uri videoUri = Uri.parse("https://pptikacademy01.000webhostapp.com/assets/video/"+linkVideo);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(videoUri);
        videoView.start();

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
                        String judulVideo1 = object.getString("judul1").trim();
                        String judulVideo2 = object.getString("judul2").trim();
                        String judulVideo3 = object.getString("judul3").trim();
                        String judulVideo4 = object.getString("judul4").trim();
                        String judulVideo5 = object.getString("judul5").trim();
                        String judulVideo6 = object.getString("judul6").trim();
                        String judulVideo7 = object.getString("judul7").trim();
                        String judulVideo8 = object.getString("judul8").trim();
                        String judulVideo9 = object.getString("judul9").trim();
                        String judulVideo10 = object.getString("judul10").trim();

                        Intent iVideo = new Intent(getApplicationContext(),VideoLearningActivity.class);
                        iVideo.putExtra("id_kursus", id_kursus);
                        iVideo.putExtra("judul1", judulVideo1);
                        iVideo.putExtra("judul2", judulVideo2);
                        iVideo.putExtra("judul3", judulVideo3);
                        iVideo.putExtra("judul4", judulVideo4);
                        iVideo.putExtra("judul5", judulVideo5);
                        iVideo.putExtra("judul6", judulVideo6);
                        iVideo.putExtra("judul7", judulVideo7);
                        iVideo.putExtra("judul8", judulVideo8);
                        iVideo.putExtra("judul9", judulVideo9);
                        iVideo.putExtra("judul10", judulVideo10);
                        startActivity(iVideo);
                        finish();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(VideoViewActivity.this, "Error Reading Detail " + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VideoViewActivity.this, "Error Reading Detail " + error.toString(), Toast.LENGTH_SHORT).show();
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
