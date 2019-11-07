package com.example.apphistoriamexico;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends Activity {

    private Timer timer;
    private ProgressBar progressBar;
    private int i = 0;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Metodos dinamicos
        reproducirMusicaIntro();

        // Inicio metodo de barra de progreso

                progressBar = (ProgressBar) findViewById(R.id.progressBar);
                progressBar.setProgress(0);
                final long intervalo = 45;
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if (i < 100){
                            progressBar.setProgress(i);
                            i++;
                        }else{
                            timer.cancel();
                            Intent intent = new Intent(Splash.this,MainActivity.class);

                            mp.stop();
                            mp.release();

                            startActivity(intent);
                            finish();
                        }
                    }
                },0,intervalo);


        // Fin metodo de barra de progreso

    }


    public void reproducirMusicaIntro(){
        mp = MediaPlayer.create(this, R.raw.introhistoria );
        mp.start();
        mp.setLooping(true);
    }
}
