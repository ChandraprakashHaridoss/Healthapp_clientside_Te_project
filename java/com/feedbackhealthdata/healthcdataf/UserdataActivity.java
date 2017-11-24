package com.feedbackhealthdata.healthcdataf;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android. view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;


public class UserdataActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdata);

        final TextView tv31 = (TextView) findViewById(R.id.tv31);
        final Button b42 = (Button) findViewById(R.id.b41);


        b42.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Intent feeddata = new Intent(UserdataActivity.this, feedbackActivity.class);
                UserdataActivity.this.startActivity(feeddata);


            }


        });


    }


}


