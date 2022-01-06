package com.test.apple.fstopwatch;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvSplash,tvSubSplash;
    Button btnget;
    Animation atg,btgone,btgtwo;
    ImageView ivSplash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvSplash = (TextView) findViewById(R.id.tvSplash);
        tvSubSplash = (TextView) findViewById(R.id.tvSubSplash);
        btnget = (Button) findViewById(R.id.btnget);
        ivSplash = (ImageView) findViewById(R.id.ivSplash);
        //Load Animation
        atg = AnimationUtils.loadAnimation(this,R.anim.atg);
        btgone = AnimationUtils.loadAnimation(this,R.anim.btgone);
        btgtwo = AnimationUtils.loadAnimation(this,R.anim.btgtwo);
        //Passing Animation
        ivSplash.startAnimation(atg);
        tvSplash.startAnimation(btgone);
        tvSubSplash.startAnimation(btgone);
        btnget.startAnimation(btgtwo);


        //Import Fonts
        Typeface MLight = Typeface.createFromAsset(getAssets(),"fonts/MLight.ttf");
        Typeface MMedium = Typeface.createFromAsset(getAssets(),"fonts/MMedium.ttf");
        Typeface MRegular = Typeface.createFromAsset(getAssets(),"fonts/MRegular.ttf");

        //Customize Fonts

        tvSplash.setTypeface(MRegular);
        tvSubSplash.setTypeface(MLight);
        btnget.setTypeface(MMedium);

        btnget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this,StopWatchAct.class);
                a.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(a);
            }
        });




    }
}
