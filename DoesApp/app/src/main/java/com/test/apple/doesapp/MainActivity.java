package com.test.apple.doesapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView titlePage, subtitlePage, endPage;
    Button btnAddNew;
    DatabaseReference reference;
    RecyclerView ourDoes;
    ArrayList<MyDoes> list;
    DoesAdapter doesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titlePage = (TextView) findViewById(R.id.titlePage);
        subtitlePage = (TextView) findViewById(R.id.subtitlePage);
        endPage = (TextView) findViewById(R.id.endPage);
        btnAddNew = (Button) findViewById(R.id.btnAddNew);

        Typeface MLight   = Typeface.createFromAsset(getAssets(),"fonts/MLight.ttf");
        Typeface MMedium  = Typeface.createFromAsset(getAssets(),"fonts/MMedium.ttf");
        Typeface MRegular = Typeface.createFromAsset(getAssets(),"fonts/MRegular.ttf");

        titlePage.setTypeface(MMedium);
        subtitlePage.setTypeface(MLight);
        endPage.setTypeface(MLight);
        btnAddNew.setTypeface(MLight);

        ourDoes = findViewById(R.id.ourdoes);
        ourDoes.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        ourDoes.setItemAnimator(new DefaultItemAnimator());
        list = new ArrayList<MyDoes>();
        doesAdapter = new DoesAdapter(MainActivity.this, list);
        ourDoes.setAdapter(doesAdapter);
        doesAdapter.notifyDataSetChanged();
        if (list.size() > 0){
            endPage.setText(list.size() + " More Item ");
        }


        FirebaseApp.initializeApp(this);
        reference = FirebaseDatabase.getInstance().getReference().child("MyDoes");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //to retrieve Data

                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    MyDoes p = dataSnapshot1.getValue(MyDoes.class);
                    list.add(p);
                }
                doesAdapter = new DoesAdapter(MainActivity.this, list);
                ourDoes.setAdapter(doesAdapter);
                doesAdapter.notifyDataSetChanged();
                if (list.size() > 0){
                    endPage.setText(list.size() + " More Item ");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
            }
        });

        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,NewTaskAct.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
