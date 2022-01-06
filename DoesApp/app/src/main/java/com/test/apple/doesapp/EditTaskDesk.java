package com.test.apple.doesapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditTaskDesk extends AppCompatActivity {

    EditText titleDoes, descDoes, dateDoes;
    Button btnSaveUpdate,btnDelete;
    DatabaseReference reference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task_desk);


        titleDoes = (EditText) findViewById(R.id.titleDoes);
        descDoes = (EditText) findViewById(R.id.descDoes);
        dateDoes = (EditText) findViewById(R.id.dateDoes);

        btnSaveUpdate = (Button) findViewById(R.id.btnSaveUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);

        titleDoes.setText(getIntent().getStringExtra("titleDoes"));
        descDoes.setText(getIntent().getStringExtra("descDoes"));
        dateDoes.setText(getIntent().getStringExtra("dateDoes"));

        final String doesNum = getIntent().getStringExtra("keyDoes");

        reference = FirebaseDatabase.getInstance().getReference().child("MyDoes")
                .child("Does" + doesNum);

        btnSaveUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(!TextUtils.isEmpty(titleDoes.getText())&&!TextUtils.isEmpty(descDoes.getText())&&
                                !TextUtils.isEmpty(dateDoes.getText())&&
                                !TextUtils.isEmpty(doesNum)){


                            dataSnapshot.getRef().child("titleDoes").setValue(titleDoes.getText().toString());
                            dataSnapshot.getRef().child("descDoes").setValue(descDoes.getText().toString());
                            dataSnapshot.getRef().child("dateDoes").setValue(dateDoes.getText().toString());
                            dataSnapshot.getRef().child("keyDoes").setValue(doesNum);

                            Intent intent = new Intent(EditTaskDesk.this,MainActivity.class);
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


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Intent intent = new Intent(EditTaskDesk.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }else {
                            Toast.makeText(getApplicationContext(),"Something is wrong!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}
