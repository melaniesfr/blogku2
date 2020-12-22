package com.example.blogku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);

        Button btnAdmin = findViewById(R.id.btn_admin);
        btnAdmin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                Intent moveLogin = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(moveLogin);
                break;
            case R.id.btn_admin:
                Intent moveAdmin = new Intent(MainActivity.this, AdminActivity.class);
                startActivity(moveAdmin);
                break;
        }
    }
}