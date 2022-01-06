package com.test.apple.animationapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class GetStartedEmail extends AppCompatActivity {

    Animation bttem;
    Button btngetStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started_email);
        btngetStarted = (Button) findViewById(R.id.btngetstarted);
        bttem = AnimationUtils.loadAnimation(this,R.anim.bttem);
        btngetStarted.setAnimation(bttem);

    }
}
