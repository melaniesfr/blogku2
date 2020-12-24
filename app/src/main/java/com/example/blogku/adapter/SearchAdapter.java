package com.example.blogku.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.blogku.R;
import com.example.blogku.model.PostList;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class SearchAdapter extends FirebaseRecyclerAdapter<PostList, SearchAdapter.myviewholder> {

    public SearchAdapter(@NonNull FirebaseRecyclerOptions<PostList> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull PostList postList) {
        holder.judul.setText(postList.getJudul());
        holder.isi_post.setText(postList.getIsiPost());
        Glide.with(holder.file_gambar.getContext()).load(postList.getFileGambar()).into(holder.file_gambar);
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder {
        TextView judul, isi_post;
        ImageView file_gambar;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            judul = (TextView) itemView.findViewById(R.id.tv_judul);
            file_gambar = (ImageView)itemView.findViewById(R.id.tv_file_gambar);
            isi_post = (TextView)itemView.findViewById(R.id.tv_isi_post);
        }
    }
}