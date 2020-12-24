package com.example.blogku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.blogku.adapter.PostAdapter;
import com.example.blogku.adapter.SearchAdapter;
import com.example.blogku.model.PostList;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PencarianActivity extends AppCompatActivity {

    ImageButton btnBack;

    RecyclerView recview;
    SearchAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pencarian);

        btnBack = findViewById(R.id.btn_back);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        recview = (RecyclerView)findViewById(R.id.list_post_search);
        recview.setLayoutManager(new LinearLayoutManager((this)));

        FirebaseRecyclerOptions<PostList> options =
                new FirebaseRecyclerOptions.Builder<PostList>()
                    .setQuery(FirebaseDatabase.getInstance().getReference().child("post"), PostList.class)
                    .build();

        adapter = new SearchAdapter(options);
        recview.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchmenu, menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                processSearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processSearch(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void processSearch(String s) {
        FirebaseRecyclerOptions<PostList> options =
                new FirebaseRecyclerOptions.Builder<PostList>()
                    .setQuery(FirebaseDatabase.getInstance().getReference().child("post").orderByChild("judul").startAt(s).endAt(s+"\uf8ff"), PostList.class)
                    .build();

        adapter = new SearchAdapter(options);
        adapter.startListening();
        recview.setAdapter(adapter);
    }
}