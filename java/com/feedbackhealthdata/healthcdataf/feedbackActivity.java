package com.feedbackhealthdata.healthcdataf;

        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.content.Intent;
        import android.util.Log;
        import android. view.View;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.EditText;

        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.Volley;

        import org.json.JSONException;
        import org.json.JSONObject;


public class feedbackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);


        final TextView tv31 = (TextView) findViewById(R.id.tv31);
        final TextView tv32 = (TextView) findViewById(R.id.tv32);
        final TextView tv33 = (TextView) findViewById(R.id.tv33);
        final TextView tv34 = (TextView) findViewById(R.id.tv34);
        final EditText et31 = (EditText) findViewById(R.id.et31);
        final EditText et32 = (EditText) findViewById(R.id.et32);
        final Button b31 = (Button) findViewById(R.id.b31);

        final Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("HC", "Response received");
                    Log.e("HC", response);
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if (success) {
                        Intent intent = new Intent(feedbackActivity.this, PdataActivity.class);
                        feedbackActivity.this.startActivity(intent);

                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(feedbackActivity.this);
                        builder.setMessage("datafeed Failed")
                                .setNegativeButton("Retry", null)
                                .create().show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };


        b31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                final String no_of_cases = et31.getText().toString();
                final String virus = et32.getText().toString();
                Log.e("HC", "Virus:"+virus);
                Log.e("HC", "No of cases:"+no_of_cases);


                if (no_of_cases.isEmpty() || virus.isEmpty() ) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(feedbackActivity.this);
                    builder.setMessage("all filds are mandetory!!")
                            .setNegativeButton("Retry", null)
                            .create()
                            .show();


                }
                else {
                    feedbackRequest feedbackRequest = new feedbackRequest(no_of_cases, virus, responseListener, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("HC", String.valueOf(error));
                        }
                    });
                    Log.e("HC", "Sending request");
                    RequestQueue queue = Volley.newRequestQueue(feedbackActivity.this);

                    queue.add(feedbackRequest);
                    et31.setText("");
                }
            }
        });
    }
}