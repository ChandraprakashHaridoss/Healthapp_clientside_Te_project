 package com.feedbackhealthdata.healthcdataf;

import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android. view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


 public class RegisterActivity extends AppCompatActivity {
     private ProgressDialog progressBar;
     private int progressBarStatus =0;
     private Handler progressBarHandler=new Handler();
     private long fileSize =0;


     Spinner spCategory;
     ArrayAdapter<CharSequence> adapter;

     String CategorySelected = "";

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_register);

         spCategory =  (Spinner) findViewById(R.id.spCategory);
         adapter = ArrayAdapter.createFromResource(this,R.array.tv1,android.R.layout.simple_spinner_item);
         adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         spCategory.setAdapter(adapter);

         spCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                 CategorySelected = String.valueOf(adapter.getItem(position));
             }

             @Override
             public void onNothingSelected(AdapterView<?> adapterView) {

             }
         });


         final EditText et1 = (EditText) findViewById(R.id.et1);
         final EditText et2 = (EditText) findViewById(R.id.et2);
         final EditText et4 = (EditText) findViewById(R.id.et4);
         final EditText et5 = (EditText) findViewById(R.id.et5);
         final EditText et6 = (EditText) findViewById(R.id.et6);
         final EditText et7 = (EditText) findViewById(R.id.et7);
         final Button b1 = (Button) findViewById(R.id.b1);
         final TextView tvm =(TextView)findViewById(R.id.tvm);



         final Response.Listener<String> responseListener = new Response.Listener<String>() {
             @Override
             public void onResponse(String response) {
                 try {
                     Log.e("HC", "Response received");
                     Log.e("HC", response);
                     JSONObject jsonResponse = new JSONObject(response);
                     boolean success = jsonResponse.getBoolean("success");

                     if (success) {
                         tvm.setText("sucessfully Registered");

                         Intent intent = new Intent(RegisterActivity.this, loginActivity.class);
                         RegisterActivity.this.startActivity(intent);

                     } else {
                         AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                         builder.setMessage("Register Failed")
                                 .setNegativeButton("Retry", null)
                                 .create().show();
                     }
                 } catch (JSONException e) {
                     e.printStackTrace();
                 }
             }
         };


         b1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View V) {
                 progressBar=new ProgressDialog(V.getContext());
                 progressBar.setCancelable(true);
                 progressBar.setMessage("loading...");
                 progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                 progressBar.setProgress(0);
                 progressBar.setMax(100);
                 progressBar.show();
                 progressBarStatus=0;
                 final String hospital_name = et1.getText().toString();
                 final String area_pincode = et2.getText().toString();
                 final String CategorySelected = spCategory.toString();
                 final String hospital_address = et4.getText().toString();
                 final String email_ID = et5.getText().toString();
                 final String pass_word = et6.getText().toString();
                 final String contact_no = et7.getText().toString();

                 if (hospital_name.isEmpty() || area_pincode.isEmpty() || CategorySelected.isEmpty() || hospital_address.isEmpty() || email_ID.isEmpty() || pass_word.isEmpty() || contact_no.isEmpty()) {
                     AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                     builder.setMessage("all filds are mandetory!!")
                             .setNegativeButton("Retry", null)
                             .create()
                             .show();

                 }
                 else
                 {
                 RegisterRequest registerRequest = new RegisterRequest(hospital_name, area_pincode, CategorySelected, hospital_address,
                         email_ID, pass_word, contact_no, responseListener, new Response.ErrorListener() {
                     @Override
                     public void onErrorResponse(VolleyError error) {
                         Log.e("HC", String.valueOf(error));
                     }
                 });
                 Log.e("HC", "Sending request");
                 RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);

                 queue.add(registerRequest);
             }
             }
         });
     }
 }