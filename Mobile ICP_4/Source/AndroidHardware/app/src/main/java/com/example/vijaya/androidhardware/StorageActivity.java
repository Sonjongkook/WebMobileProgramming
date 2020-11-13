package com.example.vijaya.androidhardware;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class StorageActivity extends AppCompatActivity {
    EditText txt_content;
    EditText contenttoDisplay;
    String FILENAME = "JkStorages";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);
        txt_content = (EditText) findViewById(R.id.id_txt_mycontent);
        contenttoDisplay = (EditText) findViewById(R.id.id_txt_display);
    }


    // ICP Task4: Write the code to save the text

    public void saveTofile(View v) throws IOException {
        //get the value that user input
        String text = txt_content.getText().toString();
        // Used to store data as a byte stream in a file.
        FileOutputStream fos = null;
        try{
            //used for file writing into internal storage. Subsequent records are appended to the original
            fos = openFileOutput(FILENAME, MODE_APPEND);
            fos.write(text.getBytes());
            // clear the text input
            txt_content.getText().clear();
            Toast.makeText(this,"save to" + getFilesDir()+"/" + FILENAME, Toast.LENGTH_LONG).show();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (fos!=null){
                fos.close();
            }
        }


    }

    // ICP Task4: Write the code to display the above saved text


    public void retrieveFromFile(View v) throws IOException {

        FileInputStream fis = null;
        try {
            fis = openFileInput(FILENAME);
            // variables created to stored read data from a file
            InputStreamReader instream = new InputStreamReader(fis);
            BufferedReader bufferreader = new BufferedReader(instream);
            StringBuilder sbuilder = new StringBuilder();
            String txt;
            while (((txt = bufferreader.readLine())!=null)){
                sbuilder.append(txt).append("\n");
            }
            //make content to visible
            contenttoDisplay.setVisibility(View.VISIBLE);
            contenttoDisplay.setText(sbuilder.toString());

        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(fis!=null){
                try{
                    fis.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
