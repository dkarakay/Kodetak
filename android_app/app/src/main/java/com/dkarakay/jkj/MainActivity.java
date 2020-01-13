package com.dkarakay.jkj;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public Button getBtn;
    public TextView result;
    MediaPlayer mp,mp2,mp3,mp4,mp5;
    int val = 0;
    float multip = 0;
    boolean speakIsOn = true,isTurkish = true;
    String line;
    String[] lines = {"no","no","no"};
    String[] listItems = {"English", "Türkçe"};


    int lxr;
    float multiplier;
    float leftorright;
    String label;
    TextToSpeech t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = (TextView) findViewById(R.id.hello);
        int volume = 100;
        final int durationMs = 1000;


        //Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Dil seçin");
        int checkedItem = 0;
        builder.setSingleChoiceItems(listItems, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == 0){
                    mp2= MediaPlayer.create(getApplicationContext(), R.raw.person);
                    mp3= MediaPlayer.create(getApplicationContext(), R.raw.chair_en);
                    mp4= MediaPlayer.create(getApplicationContext(), R.raw.laptop_en);
                    mp5= MediaPlayer.create(getApplicationContext(), R.raw.table_en);
                    isTurkish = false;

                }else{
                    isTurkish = true;
                    mp2= MediaPlayer.create(getApplicationContext(), R.raw.insan);
                    mp3= MediaPlayer.create(getApplicationContext(), R.raw.chair_tr);
                    mp4= MediaPlayer.create(getApplicationContext(), R.raw.laptop_tr);
                    mp5= MediaPlayer.create(getApplicationContext(), R.raw.table_tr);
                    isTurkish = false;
                }
                dialog.dismiss();

            }
        });
        builder.setCancelable(false);

        AlertDialog dialog = builder.create();
        dialog.show();

        //Change Mode Button
        final Button langButton = (Button) findViewById(R.id.btn_lang);
        langButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(speakIsOn){
                    speakIsOn = false;
                    langButton.setText("Beep mode"); //Beep mode is on
                }
                else{
                    speakIsOn = true;
                    langButton.setText("Object mode"); //Object mode is on
                }

            }
        });


        mp = MediaPlayer.create(getApplicationContext(), R.raw.cen);
        mp.setLooping(false);

        //TextToSpeech
        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    // t1.setLanguage(Locale.FRENCH);

                }
            }
        });


        //Start Button
        getBtn = (Button) findViewById(R.id.getBtn);
        getBtn.setText("Start");
        getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new WebTest().execute();
            }
            });

        }


        private class WebTest extends AsyncTask<Void,Void,Void>{
            @Override
            protected  void onPreExecute(){

            }
            @Override
            protected Void doInBackground(Void... params) { //Accessing WebSite and fetch data
                try {
                    StringBuilder builder = new StringBuilder();
                    Document doc = Jsoup.connect("http://192.168.43.147:5050/").get(); //Local URL
                    builder.append(doc.body().text());
                    line = builder.toString();
                    if(line.startsWith("#")){ //If no object found
                        line = "?";
                    }else {
                        lines = line.split(" ");
                        Log.i("OBJECT: ", lines[0]);
                        Log.i("LOC: ", lines[1]);
                        Log.i("MUL: ", lines[2]);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
            @Override
            protected void onPostExecute(Void avoid)// After fetching data
            {
                //Update Result TextView with the published HTML data
                result.setText("Object: " + lines[0] + "Location: " + lines[1]  + " Multiplier:" + lines[2]);

                label = lines[0];
                leftorright = Float.parseFloat(lines[1]);
                multiplier = Float.parseFloat(lines[2]);

                //Left 1 Right 0
                //If object mode is on
                if (speakIsOn) {
                    //TTS integration
                    //   t1.speak(label, TextToSpeech.QUEUE_FLUSH, null);
                    if(leftorright == 1 ||leftorright == -1){ //Comes from center
                        mp2.setVolume(1f*leftorright, 1f*leftorright);
                        mp3.setVolume(1f*leftorright, 1f*leftorright);
                        mp4.setVolume(1f*leftorright, 1f*leftorright);
                        mp5.setVolume(1f*leftorright, 1f*leftorright);
                    }else if(leftorright >= 0){ //Comes from left
                        mp2.setVolume(1f*leftorright, 0f);
                        mp3.setVolume(1f*leftorright, 0f);
                        mp4.setVolume(1f*leftorright, 0f);
                        mp5.setVolume(1f*leftorright, 0f);
                    }else if(leftorright <= 0){ //Comes from right
                        mp2.setVolume(0, Math.abs(leftorright*1f));
                        mp3.setVolume(0, Math.abs(leftorright*1f));
                        mp4.setVolume(0, Math.abs(leftorright*1f));
                        mp5.setVolume(0, Math.abs(leftorright*1f));
                    }
                    switch (label){ //Prepared multilanguage voice support (4 examples)
                        case "person": mp2.start(); break;
                        case "chair": mp3.start(); break;
                        case "laptop": mp4.start(); break;
                        case "table": mp5.start(); break;

                    }
                    //If beep mode is on
                }else{
                    if(leftorright == 1 ||leftorright == -1){
                        mp.setVolume(1f*leftorright, 1f*leftorright);
                    }else if(leftorright >= 0){
                        mp.setVolume(1f*leftorright, 0f);
                    }else if(leftorright <= 0){
                        mp.setVolume(0, Math.abs(leftorright*1f));
                    }
                    mp.start();
                }

                new WebTest().execute(); //Recall the WebTest and update the values
            }
        }
    }
