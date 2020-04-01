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


        /**
         * Dil Seçim Ekranı için Dialog ekranı
         */
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


        Button addressButton = (Button) findViewById(R.id.button_address);
        addressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        /**
         * Mod Değiştirme Butonu
         */
        final Button langButton = (Button) findViewById(R.id.btn_lang);
        langButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(speakIsOn){
                    speakIsOn = false;
                    langButton.setText("Ses Modu");
                }
                else{
                    speakIsOn = true;
                    langButton.setText("Nesne Modu");
                }

            }
        });

        /**
         * Ses yükleme
         */
        mp = MediaPlayer.create(getApplicationContext(), R.raw.cen);
        mp.setLooping(false);

        //TextToSpeech
      /*  t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    // t1.setLanguage(Locale.FRENCH);

                }
            }
        });*/


        /**
         * Başla Butonu
         */
        getBtn = (Button) findViewById(R.id.getBtn);
        getBtn.setText("Başla");
        getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new WebTest().execute();
            }
            });

        }

    /**
     * Arka Plandan Veri Çekme İşlemi için düzenlediğim AsyncTask sınıfı
      */
    private class WebTest extends AsyncTask<Void,Void,Void>{
            @Override
            protected  void onPreExecute(){

            }
            @Override
            protected Void doInBackground(Void... params) { //Accessing WebSite and fetch data
                try {
                  //  StringBuilder builder = new StringBuilder();
                 //   Document doc = Jsoup.connect("http://192.168.43.147:5050/").get(); //Local URL
                  //  builder.append(doc.body().text());
                  //  line = builder.toString();
                    line="person -0.8 2";
                    if(line.startsWith("#")){ //If no object found
                        line = "?";
                    }else {
                        lines = line.split(" ");
                        Log.i("OBJECT: ", lines[0]);
                        Log.i("LOC: ", lines[1]);
                        Log.i("MUL: ", lines[2]);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
            @Override
            protected void onPostExecute(Void avoid)// After fetching data
            {
                /**
                 * Bilgisayardan gelen sonucu ekrana yazdır
                 */



                label = lines[0];
                leftorright = Float.parseFloat(lines[1]);
                multiplier = Float.parseFloat(lines[2]);

                String lr = "Sol";

                if(leftorright > 0f){
                    lr = "Sol";
                }else  if (leftorright < 0f){
                    lr = "Sağ";
                }else{
                    lr = "Ortada";
                }

                String str_obj = "Nesne: " + lines[0];
                String str_loc = "\nKonum: " + lr;
                String str_multiplier = "\nUzaklık Çarpanı: " + multiplier;

                result.setText(str_obj+str_loc+str_multiplier);


                /**
                 * Sol 1 Sağ 0
                 * Eğer nesne modundaysak
                 */
                if (speakIsOn) {
                    //TTS integration
                    //   t1.speak(label, TextToSpeech.QUEUE_FLUSH, null);
                    /**
                     * Nesne merkezdeyse
                     */
                    if(leftorright == 1 ||leftorright == -1){
                        mp2.setVolume(1f*leftorright, 1f*leftorright);
                        mp3.setVolume(1f*leftorright, 1f*leftorright);
                        mp4.setVolume(1f*leftorright, 1f*leftorright);
                        mp5.setVolume(1f*leftorright, 1f*leftorright);
                    }
                    /**
                     * Nesne soldaysa
                     */
                    else if(leftorright >= 0){
                        mp2.setVolume(1f*leftorright, 0f);
                        mp3.setVolume(1f*leftorright, 0f);
                        mp4.setVolume(1f*leftorright, 0f);
                        mp5.setVolume(1f*leftorright, 0f);
                    }
                    /**
                     * Nesne sağdaysa
                     */
                    else if(leftorright <= 0){
                        mp2.setVolume(0, Math.abs(leftorright*1f));
                        mp3.setVolume(0, Math.abs(leftorright*1f));
                        mp4.setVolume(0, Math.abs(leftorright*1f));
                        mp5.setVolume(0, Math.abs(leftorright*1f));
                    }
                    /**
                     * Birden Fazla Dil Desteği için düzenleme
                     */
                    switch (label){
                        case "person": mp2.start(); break;
                        case "chair": mp3.start(); break;
                        case "laptop": mp4.start(); break;
                        case "table": mp5.start(); break;

                    }
                    /**
                     * Eğer sadece ses modu açıksa
                     */
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

                /**
                 * Verileri yeniden çek ve sistemi yeniden çalıştır.
                 */
                new WebTest().execute();
            }
        }
    }
