package com.example.jorjborj.ordrs;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginBtn = (Button)findViewById(R.id.loginBtn);
        TextInputEditText username = (TextInputEditText)findViewById(R.id.usernameInput);
        TextInputEditText password = (TextInputEditText)findViewById(R.id.passwordInput);



    }
}
