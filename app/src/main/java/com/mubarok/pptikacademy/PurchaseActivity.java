package com.mubarok.pptikacademy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;

public class PurchaseActivity extends AppCompatActivity {

    private static final String TAG = PurchaseActivity.class.getSimpleName(); //getting the info
    String HttpURL = "http://192.168.43.206/pptik-academy-android/registerexam.php";
    String HttpURL1 = "http://192.168.43.206/pptik-academy-android/videomodul-send-learning.php";

    Button mBtn_checkout;
    TextInputLayout mExt_idSiswaPcs, mExt_idKursusPcs, mExt_qtyPcs, mExt_pricePcs, mExt_datePcs, mExt_namePcs, mExt_emailPcs;
    TextView mTxt_coursenamePcs, mTxt_qtyPcs, mTxt_pricePcs;
    ImageView mImg_iconPcs;
    String getId, getName, getEmail;
    String TempIdSiswa, TempIdKursus, TempDate;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        sessionManager = new SessionManager(getApplicationContext());
        sessionManager.loginCheck();

        //menerapkan tool bar sesuai id toolbar | ToolBarAtas adalah variabel buatan sndiri
        Toolbar ToolBar = (Toolbar)findViewById(R.id.toolbar_purchase);
        setSupportActionBar(ToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);

        //inisialisasi button & edit text
        mBtn_checkout = (Button) findViewById(R.id.btn_checkout);
        mExt_idSiswaPcs = (TextInputLayout) findViewById(R.id.idSiswaPurchase);
        mExt_idKursusPcs = (TextInputLayout) findViewById(R.id.idKursusPurchase);
        mExt_qtyPcs = (TextInputLayout) findViewById(R.id.qtyEPurchase);
        mExt_pricePcs = (TextInputLayout) findViewById(R.id.priceEPurchase);
        mExt_datePcs = (TextInputLayout) findViewById(R.id.datePurchase);
        mExt_namePcs = (TextInputLayout) findViewById(R.id.namePurchase);
        mExt_emailPcs = (TextInputLayout) findViewById(R.id.emailPurchase);
        mTxt_coursenamePcs = (TextView) findViewById(R.id.coursenamePurchase);
        mTxt_qtyPcs = (TextView) findViewById(R.id.qtyTPurchase);
        mTxt_pricePcs = (TextView) findViewById(R.id.priceTPurchase);
        mImg_iconPcs = (ImageView) findViewById(R.id.imageViewIconPurchase);

        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.KEY_ID);
        getName = user.get(sessionManager.KEY_NAME);
        getEmail = user.get(sessionManager.KEY_EMAIL);

        // Receive Data from SessionManager
        mExt_idSiswaPcs.getEditText().setText(getId);
        mExt_namePcs.getEditText().setText(getName);
        mExt_emailPcs.getEditText().setText(getEmail);

        // Receive Data from LearningOverviewActivity
        mExt_idKursusPcs.getEditText().setText(getIntent().getStringExtra("id_kursus"));
        mTxt_coursenamePcs.setText(getIntent().getStringExtra("nama_kursus"));
        mTxt_pricePcs.setText(getIntent().getStringExtra("harga"));
        Glide.with(this)
                .load(getIntent().getStringExtra("icon"))
                .into(mImg_iconPcs);

        //function button
        mBtn_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PurchaseActivity.this, "Under construction..", Toast.LENGTH_SHORT).show();
            }
        });

    }


}
