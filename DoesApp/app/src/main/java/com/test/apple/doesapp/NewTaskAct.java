package com.test.apple.doesapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class NewTaskAct extends AppCompatActivity {

    TextView titlePage, addTitle, addDesc, addDate;
    EditText titleDoes, descDoes, dateDoes;
    Button btnSaveTask,btnCancel;
    DatabaseReference reference;
    Integer doesNum = new Random().nextInt();
    String keyDoes = Integer.toString(doesNum);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);


        titlePage = (TextView) findViewById(R.id.titlePage);
        addTitle = (TextView) findViewById(R.id.addTitle);
        titleDoes = (EditText) findViewById(R.id.titleDoes);

        addDesc = (TextView) findViewById(R.id.addDesc);
        descDoes = (EditText) findViewById(R.id.descDoes);

        addDate = (TextView) findViewById(R.id.addDate);
        dateDoes = (EditText) findViewById(R.id.dateDoes);

        btnSaveTask = (Button) findViewById(R.id.btnSaveTask);
        btnCancel = (Button) findViewById(R.id.btnCancel);


        Typeface MLight   = Typeface.createFromAsset(getAssets(),"fonts/MLight.ttf");
        Typeface MMedium  = Typeface.createFromAsset(getAssets(),"fonts/MMedium.ttf");

        titlePage.setTypeface(MMedium);
        addTitle.setTypeface(MLight);
        titleDoes.setTypeface(MMedium);
        addDesc.setTypeface(MLight);
        descDoes.setTypeface(MMedium);
        addDate.setTypeface(MLight);
        dateDoes.setTypeface(MMedium);
        btnSaveTask.setTypeface(MMedium);
        btnCancel.setTypeface(MLight);

        btnSaveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Insert DATA to database
                reference = FirebaseDatabase.getInstance().getReference().child("MyDoes")
                        .child("Does" + keyDoes);
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(!TextUtils.isEmpty(titleDoes.getText())&&!TextUtils.isEmpty(descDoes.getText())&&
                                !TextUtils.isEmpty(dateDoes.getText())&&
                                    !TextUtils.isEmpty(keyDoes)){

                            dataSnapshot.getRef().child("titleDoes").setValue(titleDoes.getText().toString());
                            dataSnapshot.getRef().child("descDoes").setValue(descDoes.getText().toString());
                            dataSnapshot.getRef().child("dateDoes").setValue(dateDoes.getText().toString());
                            dataSnapshot.getRef().child("keyDoes").setValue(keyDoes);


                            Intent intent = new Intent(NewTaskAct.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewTaskAct.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
