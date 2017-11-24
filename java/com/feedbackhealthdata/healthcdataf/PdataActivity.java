
package com.feedbackhealthdata.healthcdataf;

        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.content.Intent;
        import android.util.Log;
        import android. view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;

        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.Volley;

        import org.json.JSONException;
        import org.json.JSONObject;


public class PdataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdata);


        final EditText et41 = (EditText) findViewById(R.id.et41);
        final EditText et42 = (EditText) findViewById(R.id.et42);
        final EditText et43 = (EditText) findViewById(R.id.et43);
        final EditText et44 = (EditText) findViewById(R.id.et44);
        final EditText et45 = (EditText) findViewById(R.id.et45);
        final EditText et46 = (EditText) findViewById(R.id.et46);
        final EditText et47 = (EditText) findViewById(R.id.et47);
        final TextView tvs = (TextView) findViewById(R.id.textView);
        final Button b41 = (Button) findViewById(R.id.b41);
        final Button b42 = (Button) findViewById(R.id.b42);
        final Button b43 = (Button) findViewById(R.id.b43);

        final Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    Log.e("HC", "Response received");
                    Log.e("HC", response);
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    tvs.setText("sucessfully submited");

                    if (!success) {


                        AlertDialog.Builder builder = new AlertDialog.Builder(PdataActivity.this);
                        builder.setMessage("data feeding Failed")
                                .setNegativeButton("Retry", null)
                                .create().show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };


        b41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                final String patient_name = et41.getText().toString();
                final String case_no = et42.getText().toString();
                final String case_details = et43.getText().toString();
                final String test_required = et44.getText().toString();
                final String additional_requirements = et45.getText().toString();
                final String patient_cellno = et46.getText().toString();
                final String patient_addresss = et47.getText().toString();


                if (patient_name.isEmpty() || case_no.isEmpty() || case_details.isEmpty() || test_required.isEmpty() || additional_requirements.isEmpty() || patient_cellno.isEmpty() || patient_addresss.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(PdataActivity.this);
                    builder.setMessage("all filds are mandetory!!")
                            .setNegativeButton("Retry", null)
                            .create()
                            .show();

                }
                else {

                    Pdatarequest Pdatarequest = new Pdatarequest(patient_name, case_no, case_details, test_required, additional_requirements, patient_cellno, patient_addresss, responseListener, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("HC", String.valueOf(error));
                        }
                    });

                    Log.e("HC", "Sending request");
                    RequestQueue queue = Volley.newRequestQueue(PdataActivity.this);

                    queue.add(Pdatarequest);
                }
            }
        });

        b42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                et41.setText("");
                et42.setText("");
                et43.setText("");
                et44.setText("");
                et45.setText("");
                et46.setText("");
                et47.setText("");
                tvs.setText("");
            }
        });
        b43.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {

                Intent pdata = new Intent(PdataActivity.this, loginActivity.class);
                PdataActivity.this.startActivity(pdata);
            }

        });

        }
}
