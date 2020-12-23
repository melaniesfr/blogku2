package com.example.blogku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    List<Postingan> fetchData;
    RecyclerView recyclerView;
    PostAdapter postAdapter;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);

        Button btnAdmin = findViewById(R.id.btn_admin);
        btnAdmin.setOnClickListener(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView = findViewById(R.id.list_postingan);
        recyclerView.setLayoutManager(layoutManager);
        fetchData = new ArrayList<>();


        // Inisialisasi objek untuk ke firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("post");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Iterasi mengambil postingan
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    // Mengambil value setiap child dari 'post' (judul, file_gambar, isi_post)
                    String judul = ds.child("judul").getValue().toString();
                    String file_gambar = ds.child("file_gambar").getValue().toString();
                    String isi_post = ds.child("isi_post").getValue().toString();
                    Postingan data = new Postingan(judul, file_gambar, isi_post);
                    fetchData.add(data);
                }

                postAdapter = new PostAdapter(fetchData);
                recyclerView.setAdapter(postAdapter);

                // Pindah activity apabila item pada list di click
                postAdapter.setOnItemClickListener(new PostAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        String judul = fetchData.get(position).getJudul();
                        startActivity(new Intent(MainActivity.this, DetailPostActivity.class).putExtra("judul", judul));
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Maaf terjadi kesalahan... coba beberapa saat lagi!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                Intent moveLogin = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(moveLogin);
                break;
            case R.id.btn_admin:
                Intent moveAdmin = new Intent(MainActivity.this, LoginActivityAdmin.class);
                startActivity(moveAdmin);
                break;
        }
    }
}