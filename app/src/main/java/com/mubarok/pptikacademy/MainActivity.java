package com.mubarok.pptikacademy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    SessionManager sessionManager;
    private YouTubePlayerView youTubePlayerView1, youTubePlayerView2, youTubePlayerView3, youTubePlayerView4, youTubePlayerView5, youTubePlayerView6, youTubePlayerView7, youTubePlayerView8, youTubePlayerView9, youTubePlayerView10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        youTubePlayerView1 = findViewById(R.id.youtubeViewSection1);
        getLifecycle().addObserver(youTubePlayerView1);

        youTubePlayerView1.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = "1FJHYqE0RDg";
                youTubePlayer.cueVideo(videoId, 0);
            }
        });

        youTubePlayerView2 = findViewById(R.id.youtubeViewSection2);
        getLifecycle().addObserver(youTubePlayerView2);

        youTubePlayerView2.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = "1Eg8em_VHt4";
                youTubePlayer.cueVideo(videoId, 0);
            }
        });

        youTubePlayerView3 = findViewById(R.id.youtubeViewSection3);
        getLifecycle().addObserver(youTubePlayerView3);

        youTubePlayerView3.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = "1Eg8em_VHt4";
                youTubePlayer.cueVideo(videoId, 0);
            }
        });

        youTubePlayerView4 = findViewById(R.id.youtubeViewSection4);
        getLifecycle().addObserver(youTubePlayerView4);

        youTubePlayerView4.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = "1Eg8em_VHt4";
                youTubePlayer.cueVideo(videoId, 0);
            }
        });

        youTubePlayerView5 = findViewById(R.id.youtubeViewSection5);
        getLifecycle().addObserver(youTubePlayerView5);

        youTubePlayerView5.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = "1Eg8em_VHt4";
                youTubePlayer.cueVideo(videoId, 0);
            }
        });

        youTubePlayerView6 = findViewById(R.id.youtubeViewSection6);
        getLifecycle().addObserver(youTubePlayerView6);

        youTubePlayerView6.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = "1Eg8em_VHt4";
                youTubePlayer.cueVideo(videoId, 0);
            }
        });

        youTubePlayerView7 = findViewById(R.id.youtubeViewSection7);
        getLifecycle().addObserver(youTubePlayerView7);

        youTubePlayerView7.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = "1Eg8em_VHt4";
                youTubePlayer.cueVideo(videoId, 0);
            }
        });

        youTubePlayerView8 = findViewById(R.id.youtubeViewSection8);
        getLifecycle().addObserver(youTubePlayerView8);

        youTubePlayerView8.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = "1Eg8em_VHt4";
                youTubePlayer.cueVideo(videoId, 0);
            }
        });

        youTubePlayerView9 = findViewById(R.id.youtubeViewSection9);
        getLifecycle().addObserver(youTubePlayerView9);

        youTubePlayerView9.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = "1Eg8em_VHt4";
                youTubePlayer.cueVideo(videoId, 0);
            }
        });

        youTubePlayerView10 = findViewById(R.id.youtubeViewSection10);
        getLifecycle().addObserver(youTubePlayerView10);

        youTubePlayerView10.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = "1Eg8em_VHt4";
                youTubePlayer.cueVideo(videoId, 0);
            }
        });
    }
}
