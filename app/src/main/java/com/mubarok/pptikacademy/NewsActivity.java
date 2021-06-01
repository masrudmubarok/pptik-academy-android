package com.mubarok.pptikacademy;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class NewsActivity extends AppCompatActivity {

    TextView mTxt_idBerita, mTxt_judulBerita, mTxt_keterangan, mTxt_linkBerita, mTxt_tanggalBerita;
    String getId, judulTemp, keteranganTemp, linkTemp, tanggalTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        //menerapkan tool bar sesuai id toolbar | ToolBarAtas adalah variabel buatan sndiri
        Toolbar ToolBar = (Toolbar)findViewById(R.id.toolbar_news);
        setSupportActionBar(ToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);

        //inisialisasi text view
        mTxt_idBerita = (TextView) findViewById(R.id.idNews);
        mTxt_judulBerita = (TextView) findViewById(R.id.judulNews);
        mTxt_keterangan = (TextView) findViewById(R.id.keteranganNews);
        mTxt_linkBerita = (TextView) findViewById(R.id.linkNews);
        mTxt_tanggalBerita = (TextView) findViewById(R.id.dateNews);

        // Receive Data from IntroActivity
        getId = getIntent().getStringExtra("id_berita");
        judulTemp = getIntent().getStringExtra("judul_berita");
        keteranganTemp = getIntent().getStringExtra("keterangan");
        tanggalTemp = getIntent().getStringExtra("tanggal_berita");
        linkTemp = getIntent().getStringExtra("link_berita");

        // Set Material
        mTxt_idBerita.setText(getId);
        mTxt_judulBerita.setText(judulTemp);
        mTxt_keterangan.setText(keteranganTemp);
        mTxt_linkBerita.setText(linkTemp);
        mTxt_tanggalBerita.setText(tanggalTemp);

    }
}
