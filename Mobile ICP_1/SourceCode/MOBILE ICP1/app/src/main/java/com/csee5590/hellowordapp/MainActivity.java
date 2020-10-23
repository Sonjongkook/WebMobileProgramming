package com.csee5590.hellowordapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
//used to redirect
import android.content.Intent;
//used to show message
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button log;
    private EditText username;
    private EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Select element in xml
        log = (Button) findViewById(R.id.login);
        username =  (EditText) findViewById(R.id.username);
        password =  (EditText) findViewById(R.id.password);

        //Add a on click eventlister on login button
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id  = username.getText().toString();
                String pwd  = password.getText().toString();

                //Only work when id and pwd is not empty
                if(!id.isEmpty() && !pwd.isEmpty()) {
                    //When matches with my id and password it will make login work
                    if(id.equals("jongkook") && pwd.equals("6721")){
                        Toast.makeText(MainActivity.this, "Successful", Toast.LENGTH_SHORT ).show();
                        Intent redirect = new Intent(MainActivity.this, Welcome.class);
                        startActivity(redirect);
                    }else{
                        Toast.makeText(MainActivity.this, "Login information is wrong", Toast.LENGTH_SHORT ).show();
                    }
                }else {
                    Toast.makeText(MainActivity.this, "Login information is not given", Toast.LENGTH_SHORT ).show();
                }
            }
        });

    }
}