package com.mubarok.pptikacademy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputLayout;
import com.midtrans.sdk.corekit.callback.TransactionFinishedCallback;
import com.midtrans.sdk.corekit.core.MidtransSDK;
import com.midtrans.sdk.corekit.core.PaymentMethod;
import com.midtrans.sdk.corekit.core.TransactionRequest;
import com.midtrans.sdk.corekit.core.themes.CustomColorTheme;
import com.midtrans.sdk.corekit.models.BankType;
import com.midtrans.sdk.corekit.models.CustomerDetails;
import com.midtrans.sdk.corekit.models.ItemDetails;
import com.midtrans.sdk.corekit.models.snap.Authentication;
import com.midtrans.sdk.corekit.models.snap.CreditCard;
import com.midtrans.sdk.corekit.models.snap.TransactionResult;
import com.midtrans.sdk.uikit.SdkUIFlowBuilder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PurchaseActivity extends AppCompatActivity implements TransactionFinishedCallback {

    private static final String TAG = PurchaseActivity.class.getSimpleName(); //getting the info
    String HttpURL = "https://pptikacademy.000webhostapp.com/api/takingcourse.php";
    String HttpURL1 = "https://pptikacademy.000webhostapp.com/api/videomodul-send-learningoverview.php";
    String HttpURL2 = "https://pptikacademy.000webhostapp.com/api/validationpurchase.php";

    Button mBtn_checkout;
    TextInputLayout mExt_idSiswaPcs, mExt_idKursusPcs, mExt_qtyPcs, mExt_pricePcs, mExt_datePcs, mExt_namePcs, mExt_emailPcs;
    TextView mTxt_coursenamePcs, mTxt_qtyPcs, mTxt_pricePcs;
    ImageView mImg_iconPcs;
    String url, getIdSiswa, getName, getEmail, getIdKursus, getNamaKursus, getHargaKrs;
    int getHargaKursus;
    String TempIdTransaksi, TempIdOrder, TempJenisPembayaran, TempJumlah, TempDate, TempStatusTransaksi, TempIdSiswa, TempIdKursus;
    String id_siswa, id_kursus;

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

        //check channel notification
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel("PPTIK Academy", "PPTIK Academy", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }

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

        // Receive Data from SessionManager
        HashMap<String, String> user = sessionManager.getUserDetail();
        getIdSiswa = user.get(sessionManager.KEY_ID);
        getName = user.get(sessionManager.KEY_NAME);
        getEmail = user.get(sessionManager.KEY_EMAIL);

        // Receive Data from LearningOverviewActivity
        getIdKursus = getIntent().getStringExtra("id_kursus");
        getNamaKursus = getIntent().getStringExtra("nama_kursus");
        getHargaKrs = getIntent().getStringExtra("harga");
        getHargaKursus = Integer.parseInt(getHargaKrs);

        // Set Material
        mExt_idSiswaPcs.getEditText().setText(getIdSiswa);
        mExt_namePcs.getEditText().setText(getName);
        mExt_emailPcs.getEditText().setText(getEmail);
        mExt_idKursusPcs.getEditText().setText(getIdKursus);
        mTxt_coursenamePcs.setText(getNamaKursus);
        mTxt_pricePcs.setText(getHargaKrs);
        Glide.with(this)
                .load("https://pptikacademy.000webhostapp.com/assets/icon/"+getIntent().getStringExtra("icon"))
                .into(mImg_iconPcs);

        // Receive Data from Current Date
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
        String currentDate = dateFormat.format(calendar.getTime());
        mExt_datePcs.getEditText().setText(currentDate);

        // Testing
        id_siswa = mExt_idSiswaPcs.getEditText().getText().toString();
        id_kursus = mExt_idKursusPcs.getEditText().getText().toString();
        Log.e(TAG, "id siswa: "+id_siswa);
        Log.e(TAG, "id kursus: "+id_kursus);

        // Midtrans
        initMidtransSdk();

        //function button
        mBtn_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                purchaseCheck();
            }
        });

    }

    public void GetData() {

        TempIdSiswa = mExt_idSiswaPcs.getEditText().getText().toString();
        TempIdKursus = mExt_idKursusPcs.getEditText().getText().toString();
    }

    public void InsertData(final String transaction_id, final String order_id, final String payment_type, final String gross_amount, final String transaction_time, final String transaction_status, final String id_siswa, final String id_kursus) {

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @SuppressLint("WrongThread")
            @Override
            protected String doInBackground(String... params) {

                String IdTransaksiHolder = transaction_id;
                String IdOrderHolder = order_id;
                String PaymentTypeHolder = payment_type;
                String GrossAmountHolder = gross_amount;
                String TransactionTimeHolder = transaction_time;
                String TransactionStatusHolder = transaction_status;
                String IdSiswaHolder = id_siswa;
                String IdKursusHolder = id_kursus;

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("transaction_id", IdTransaksiHolder));
                nameValuePairs.add(new BasicNameValuePair("order_id", IdOrderHolder));
                nameValuePairs.add(new BasicNameValuePair("payment_type", PaymentTypeHolder));
                nameValuePairs.add(new BasicNameValuePair("gross_amount", GrossAmountHolder));
                nameValuePairs.add(new BasicNameValuePair("transaction_time", TransactionTimeHolder));
                nameValuePairs.add(new BasicNameValuePair("transaction_status", TransactionStatusHolder));
                nameValuePairs.add(new BasicNameValuePair("id_siswa", IdSiswaHolder));
                nameValuePairs.add(new BasicNameValuePair("id_kursus", IdKursusHolder));

                try {
                    HttpClient httpClient = new DefaultHttpClient();

                    HttpPost httpPost = new HttpPost(HttpURL);

                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse httpResponse = httpClient.execute(httpPost);

                    HttpEntity httpEntity = httpResponse.getEntity();


                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return "Data Inserted Successfully";
            }

            @Override
            protected void onPostExecute(String result) {

                super.onPostExecute(result);

                Toast.makeText(PurchaseActivity.this, "Transaction is in process", Toast.LENGTH_LONG).show();

            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();

        sendPostReqAsyncTask.execute(transaction_id, order_id, payment_type, gross_amount, transaction_time, transaction_status, id_siswa, id_kursus);
    }

    private TransactionRequest initTransactionRequest(String id, int price, int qty, String name ) {
        // Create new Transaction Request
        TransactionRequest transactionRequestNew = new
                TransactionRequest(System.currentTimeMillis() + "", Integer.parseInt(getIntent().getStringExtra("harga")));
        transactionRequestNew.setCustomerDetails(initCustomerDetails());
        ItemDetails details = new ItemDetails(id, price, qty, name);

        ArrayList<ItemDetails> itemDetails = new ArrayList<>();
        itemDetails.add(details);
        transactionRequestNew.setItemDetails(itemDetails);

        CreditCard creditCard = new CreditCard();
        creditCard.setSaveCard(false);
        creditCard.setAuthentication(Authentication.AUTH_RBA);
        creditCard.setBank(BankType.BRI);
        transactionRequestNew.setCreditCard(creditCard);
        return transactionRequestNew;
    }

    private CustomerDetails initCustomerDetails() {
        //define customer detail (mandatory for coreflow)
        CustomerDetails mCustomerDetails = new CustomerDetails();
        mCustomerDetails.setFirstName(getName);
        mCustomerDetails.setEmail(getEmail);
        mCustomerDetails.setCustomerIdentifier(getEmail);
        return mCustomerDetails;
    }

    private void initMidtransSdk() {
        SdkUIFlowBuilder.init()
                .setContext(this)
                .setMerchantBaseUrl(BuildConfig.BASE_URL)
                .setClientKey(BuildConfig.CLIENT_KEY)
                .setTransactionFinishedCallback(this)
                .enableLog(true)
                .setColorTheme(new CustomColorTheme("#FFE51255","#4383CB", "#FFE51255" ))
                .buildSDK();
    }

    private void actionButton() {
        MidtransSDK.getInstance().setTransactionRequest(initTransactionRequest(
                getIntent().getStringExtra("id_kursus"),
                Integer.parseInt(getIntent().getStringExtra("harga")),
                1,
                getIntent().getStringExtra("nama_kursus")
        ));

        MidtransSDK.getInstance().startPaymentUiFlow(this);
    }

    @Override
    public void onTransactionFinished(TransactionResult result) {
        if (result.getResponse() != null) {
            switch (result.getStatus()) {
                case TransactionResult.STATUS_SUCCESS:
//                    Toast.makeText(this, "Transaction Finished. ID: " + result.getResponse().getTransactionId(), Toast.LENGTH_LONG).show();
                    TempIdTransaksi = result.getResponse().getTransactionId();
                    TempIdOrder = result.getResponse().getOrderId();
                    TempJenisPembayaran = result.getResponse().getPaymentType();
                    TempJumlah = result.getResponse().getGrossAmount();
                    TempDate = result.getResponse().getTransactionTime();
                    TempStatusTransaksi = result.getResponse().getTransactionStatus();
                    GetData();
                    InsertData(TempIdTransaksi, TempIdOrder, TempJenisPembayaran, TempJumlah, TempDate, TempStatusTransaksi, TempIdSiswa, TempIdKursus);
                    purchaseNotification();
                    break;
                case TransactionResult.STATUS_PENDING:
                    TempIdTransaksi = result.getResponse().getTransactionId();
                    TempIdOrder = result.getResponse().getOrderId();
                    TempJumlah = result.getResponse().getGrossAmount();
                    TempJenisPembayaran = result.getResponse().getPaymentType();
                    TempDate = result.getResponse().getTransactionTime();
                    TempStatusTransaksi = result.getResponse().getTransactionStatus();
                    GetData();
                    InsertData(TempIdTransaksi, TempIdOrder, TempJenisPembayaran, TempJumlah, TempDate, TempStatusTransaksi, TempIdSiswa, TempIdKursus);
                    purchaseNotification();
                    break;
                case TransactionResult.STATUS_FAILED:
                    Toast.makeText(this, "Transaction Failed. ID: " + result.getResponse().getTransactionId() + ". Message: " + result.getResponse().getStatusMessage(), Toast.LENGTH_LONG).show();
                    break;
            }
            result.getResponse().getValidationMessages();
        } else if (result.isTransactionCanceled()) {
            Toast.makeText(this, "Transaction Canceled", Toast.LENGTH_LONG).show();
        } else {
            if (result.getStatus().equalsIgnoreCase(TransactionResult.STATUS_INVALID)) {
                Toast.makeText(this, "Transaction Invalid", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Transaction Finished with failure.", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void sendBackCourseDetailPcs() {
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
                        String nama_kursus = object.getString("nama_kursus").trim();
                        String deskripsi = object.getString("deskripsi").trim();
                        String harga = object.getString("harga").trim();
                        String nama_tutor = object.getString("nama_tutor").trim();
                        String icon = object.getString("icon").trim();

                        Intent iBackPcs = new Intent(getApplicationContext(), LearningOverviewActivity.class);
                        iBackPcs.addFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
                        iBackPcs.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        iBackPcs.putExtra("id_kursus", id_kursus);
                        iBackPcs.putExtra("nama_kursus", nama_kursus);
                        iBackPcs.putExtra("deskripsi", deskripsi);
                        iBackPcs.putExtra("nama_tutor", nama_tutor);
                        iBackPcs.putExtra("harga", harga);
                        iBackPcs.putExtra("icon", icon);
                        startActivity(iBackPcs);
                        finish();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(PurchaseActivity.this, "Error Reading Detail " + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PurchaseActivity.this, "Error Reading Detail " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> getParams = new HashMap<>();
                getParams.put("id_kursus", getIdKursus);
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
                sendBackCourseDetailPcs();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void purchaseCheck() {
        // Setting POST request ke server
        StringRequest purchaseRequest = new StringRequest(Request.Method.POST, HttpURL2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Handle response dari server ketika sukses dengan mengkonvert menjadi JSON
                        try {
                            JSONObject json = new JSONObject(response);
                            // Mengambil variable status pada response
                            String status = json.getString("success");
                            String message1 = json.getString("id_siswa");
                            String message2 = json.getString("id_kursus");

                            if(status.equals("1")){
                                Log.e("ok", "ambil data nilai sukses =" + status);
                                Log.e("ok", "id siswa =" + message1);
                                Log.e("ok", "id kursus =" + message2);
                                Toast.makeText(getApplicationContext(), "You've already take this course", Toast.LENGTH_LONG).show();
                            } else {
                                actionButton();
                                Log.e("error", "tidak bisa ambil data nilai sukses =" + status);
                                Log.e("error", "id siswa =" + message1);
                                Log.e("error", "id kursus =" + message2);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle response dari server ketika gagal
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("id_siswa", getIdSiswa);
                params.put("id_kursus", getIdKursus);
                return params;
            }
        };
        // Buat antrian request pada cache android
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        // Tambahkan Request pada antrian request
        requestQueue.add(purchaseRequest);
    }

    private void purchaseNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                PurchaseActivity.this, "PPTIK Academy")
                .setSmallIcon(R.drawable.iconpptik)
                .setContentTitle("Course Purchase")
                .setContentText("Please check your email to make sure the payment has been completed. If it's finished, then your course will appear within a maximum of 24 hours after payment. (CS : pptik@stiki.ac.id)")
                .setAutoCancel(true);

        // Add as notification
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(PurchaseActivity.this);
        managerCompat.notify(1, builder.build());
    }

}
