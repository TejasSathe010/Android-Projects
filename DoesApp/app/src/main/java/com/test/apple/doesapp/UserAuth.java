package com.test.apple.doesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UserAuth extends AppCompatActivity {

    EditText userName;
    Button access;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_auth);

        userName = (EditText) findViewById(R.id.userName);
        access = (Button) findViewById(R.id.access);

        access.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(userName.getText())){
                    Intent intent = new Intent(UserAuth.this,MainActivity.class);
                    UserName.getInstance().setUserName(userName.getText().toString());
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
