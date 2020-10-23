package com.csee5590.hellowordapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.content.Intent;


public class Welcome extends AppCompatActivity {

    private Button logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        logout = findViewById(R.id.logout);

        //Add a click event listener on logout button
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent redirect_home = new Intent(Welcome.this, MainActivity.class);
                //Redirect to home
                startActivity(redirect_home);
            }
        });
    }
}