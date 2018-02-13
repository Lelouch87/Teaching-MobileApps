package com.example.chase.project2;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    MediaPlayer intro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = findViewById(R.id.tv);
        ImageView iv = findViewById(R.id.iv);


        intro = MediaPlayer.create(MainActivity.this, R.raw.intro);
        intro.start();

        Animation anim1 = AnimationUtils.loadAnimation(this, R.anim.menutransition);
        tv.startAnimation(anim1);
        iv.startAnimation(anim1);

        final Intent i = new Intent(this, MainPage.class);

        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(2250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(i);
                    finish();
                }
            }

        };

        timer.start();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        intro.release();
    }



}
