package com.test.apple.fstopwatch;

import android.graphics.Typeface;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;

public class StopWatchAct extends AppCompatActivity {

    Button btnstart,btnstop;
    ImageView icanchor;
    Animation roundingalone;
    Chronometer timerhere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);
        btnstart = (Button) findViewById(R.id.btnstart);
        btnstop = (Button) findViewById(R.id.btnstop);
        timerhere = (Chronometer) findViewById(R.id.timerhere);


        icanchor = (ImageView) findViewById(R.id.icanchor);

        roundingalone = AnimationUtils.loadAnimation(this,R.anim.roundingalone );

        //Import Fonts
        Typeface MLight = Typeface.createFromAsset(getAssets(),"fonts/MLight.ttf");
        Typeface MMedium = Typeface.createFromAsset(getAssets(),"fonts/MMedium.ttf");
        Typeface MRegular = Typeface.createFromAsset(getAssets(),"fonts/MRegular.ttf");

        //Customize Fonts

        btnstart.setTypeface(MLight);
        btnstop.setTypeface(MLight);
        btnstop.setAlpha(0);


        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roundingalone.setDuration(4000);
                icanchor.startAnimation(roundingalone);
                btnstop.animate().alpha(1).translationY(-80).setDuration(300).start();
                btnstart.animate().alpha(0).setDuration(300).start();

                //start time
                timerhere.setBase(SystemClock.elapsedRealtime());
                timerhere.start();
            }
        });

        btnstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timerhere.stop();
                roundingalone.setDuration(0);
                icanchor.startAnimation(roundingalone);
                btnstart.animate().alpha(1).translationY(-10).setDuration(300).start();
                btnstop.animate().alpha(0).setDuration(300).start();


            }
        });
    }
}
