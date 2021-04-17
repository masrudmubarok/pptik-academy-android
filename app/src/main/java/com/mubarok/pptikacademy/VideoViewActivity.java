package com.mubarok.pptikacademy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.MediaController;
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

import java.util.HashMap;
import java.util.Map;

public class VideoViewActivity extends AppCompatActivity {

    private static final String TAG = VideoViewActivity.class.getSimpleName(); //getting the info
    VideoView videoView;
    String getId;

    // Adding HTTP Server URL to string variable.
    String HttpURL = "http://192.168.43.206/pptik-academy-android/videomodulview-send-videomodullearning.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);

        //menerapkan tool bar sesuai id toolbar | ToolBarAtas adalah variabel buatan sndiri
        Toolbar ToolBarLogin = (Toolbar)findViewById(R.id.toolbar_videoview);
        setSupportActionBar(ToolBarLogin);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);

        // Receive Data from LearnignActivity
        getId = getIntent().getStringExtra("id_kursus");

        // Declaration
        videoView = (VideoView) findViewById(R.id.videoViewLearning);

        String linkVideo = ("rise.mp4");
        Uri videoUri = Uri.parse("http://192.168.43.206/pptik-academy-web/assets/video/"+linkVideo);
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
