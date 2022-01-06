package com.test.apple.animationapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView titleGet, subtitleGet;
    Button btngetStarted;
    Animation btt,bttdua,bttiga;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titleGet = (TextView) findViewById(R.id.titleGet);
        subtitleGet = (TextView) findViewById(R.id.subtitleGet);
        btngetStarted = (Button) findViewById(R.id.btngetstarted);

        btt = AnimationUtils.loadAnimation(this,R.anim.btt);
        bttdua = AnimationUtils.loadAnimation(this,R.anim.bttdua);
        bttiga = AnimationUtils.loadAnimation(this,R.anim.bttiga);

        titleGet.startAnimation(btt);
        subtitleGet.startAnimation(bttdua);
        btngetStarted.setAnimation(bttiga);



        Typeface MLight = Typeface.createFromAsset(getAssets(),"fonts/MLight.ttf");
        Typeface Vidaloka = Typeface.createFromAsset(getAssets(),"fonts/Vidaloka.ttf");

        titleGet.setTypeface(Vidaloka);
        subtitleGet.setTypeface(MLight);

        btngetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,GetStartedEmail.class);
                startActivity(i);
            }
        });


    }
}
