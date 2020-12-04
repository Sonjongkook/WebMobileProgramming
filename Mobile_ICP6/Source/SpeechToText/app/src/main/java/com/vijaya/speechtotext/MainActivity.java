package com.vijaya.speechtotext;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import android.content.SharedPreferences;
import java.text.SimpleDateFormat;


import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int REQ_CODE_SPEECH_INPUT = 100;
    private TextView mVoiceInputTv;
    private ImageButton mSpeakBtn;

    // Add variables
    private TextToSpeech ts;
    private boolean ready = false;

    private SharedPreferences preferences;
    private SharedPreferences.Editor edit;
    private static final String PREF = "preference";
    private static final String NAME = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Speaker initialize
        preferences = getSharedPreferences(PREF,0);
        edit = preferences.edit();

        ts=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    ts.setLanguage(Locale.US);
                    ts.speak("hello", TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });



        mVoiceInputTv = (TextView) findViewById(R.id.voiceInput);
        mSpeakBtn = (ImageButton) findViewById(R.id.btnSpeak);
        mSpeakBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startVoiceInput();
            }
        });
    }

    private void startVoiceInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hello, How can I help you?");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    // call the recognize_text method
                    recognizing_text(result.get(0));
                }
                break;
            }

        }
    }

    // add code to recognize text
    private void recognizing_text(String txt){

        String username;
        mVoiceInputTv.setText(txt);
        String[] wordSpeech = txt.split(" ");

        if(txt.contains("hello")){
            ts.speak("What is your name", TextToSpeech.QUEUE_FLUSH, null);
        }
        if(txt.contains("my name is"))
        {
            username = wordSpeech[wordSpeech.length-1];
            edit.putString(NAME,username).apply();

            mVoiceInputTv.setText(username);
            ts.speak("Your name is "+preferences.getString(NAME,null), TextToSpeech.QUEUE_FLUSH, null);
        }
        if(txt.contains("I am not feeling well"))
        {
            ts.speak("I can understand. Please tell your symptoms in short.", TextToSpeech.QUEUE_FLUSH, null);
        }
        if(txt.contains("what time is it"))
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");//dd/MM/yyyy
            Date today = new Date();
            String[] dt = dateFormat.format(today).split(":");
            if(dt[1].contains("00")) {
                dt[1] = "o'clock";
            }
            ts.speak("The time is " + dateFormat.format(today), TextToSpeech.QUEUE_FLUSH, null);
        }
        if(txt.contains("what medicine should I take"))
        {
            ts.speak("I think you have fever. Please take this medicine.", TextToSpeech.QUEUE_FLUSH, null);
        }
        if(txt.contains("thank you my medical assistant"))
        {
            ts.speak("Thank you too " + preferences.getString(NAME,null) + "Take care", TextToSpeech.QUEUE_FLUSH, null);
        }
    }
}