package com.example.blogku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class AdminActivity extends AppCompatActivity {

    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        btnBack = findViewById(R.id.btn_back);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void pindah(View view) {
        switch (view.getId()){
            case R.id.admin_profile :
                startActivity(new Intent(AdminActivity.this, AdminProfileActivity.class));
                break;
            case R.id.admin_data_kategori :
                startActivity(new Intent(AdminActivity.this, AdminDataKategoriActivity.class));
                break;
            case R.id.admin_data_penulis :
                startActivity(new Intent(AdminActivity.this, AdminDataPenulisActivity.class));
                break;
            case R.id.admin_rekap_post :
                startActivity(new Intent(AdminActivity.this, AdminRekapPostActivity.class));
                break;
        }
    }
}