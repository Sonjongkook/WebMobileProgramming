package com.example.vijaya.myorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Order extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        TextView summary = (TextView) findViewById(R.id.summary_text);
        Button ordernow_btn = (Button) findViewById(R.id.ordernow);
        Button goback_btn = (Button) findViewById(R.id.Goback);

        //Using intent get the values from mainactivity
        final String Name = getIntent().getStringExtra("Name");
        final String Summary = getIntent().getStringExtra("Summary");

        summary.setText(Summary);

        ordernow_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail(Name, Summary);
            }
        });

        goback_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go back to the order page without initialization
                finish();
            }
        });


    }

    public void sendEmail(String name, String output) {
        // Write the relevant code for triggering email

        Intent intent = new Intent(Intent.ACTION_SEND);
        String subject  = "order details for the customer: " + name;
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"pizzahut.gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT,subject);
        intent.putExtra(Intent.EXTRA_TEXT, output);
        intent.setType("message/rfc822");

        if (intent.resolveActivity(getPackageManager()) !=null){
            startActivity(Intent.createChooser(intent,"Choose Email Client"));
        }
    }
}