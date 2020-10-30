package com.example.vijaya.myorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Summary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        TextView summary = (TextView) findViewById(R.id.sum_text);

        //Using intent get the values from mainactivity
        final String Name = getIntent().getStringExtra("Name");
        final String Summary = getIntent().getStringExtra("Summary");
        summary.setText(Summary);

        Button Goto_order_btn = findViewById(R.id.gotodorder);

        Goto_order_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go back to the order page without initialization
                finish();
            }
        });
    }
}