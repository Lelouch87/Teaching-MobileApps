package com.example.chase.testapp1;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    MediaPlayer intro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = (TextView) findViewById(R.id.tv);
        ImageView iv = (ImageView) findViewById(R.id.iv);

        intro = MediaPlayer.create(MainActivity.this, R.raw.soft_intro_tune);
        intro.start();

        Animation anim1 = AnimationUtils.loadAnimation(this, R.anim.menutransition);
        tv.startAnimation(anim1);
        iv.startAnimation(anim1);

        final Intent i = new Intent(this, ThirdActivity.class);

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
