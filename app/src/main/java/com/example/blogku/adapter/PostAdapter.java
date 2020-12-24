package com.example.blogku.adapter;

import android.content.Context;
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
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    // List untuk menampung semua data postingan dari firebase
    private com.example.blogku.adapter.PostAdapter.OnItemClickListener mListener;

    List<PostList> postLists;
    Context ct;

    public PostAdapter(List<PostList> postLists, Context ct) {
        this.postLists = postLists;
        this.ct = ct;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        com.example.blogku.adapter.PostAdapter.ViewHolder viewHolder = new com.example.blogku.adapter.PostAdapter.ViewHolder(view, mListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PostList postList = postLists.get(position);
        holder.judul.setText(postList.getJudul());
        holder.isi_post.setText(postList.getIsiPost());

        Glide.with(ct)
                .load(postList.getFileGambar())
                .into(holder.file_gambar);
    }

    @Override
    public int getItemCount() {
        return postLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView judul, isi_post;
        ImageView file_gambar;

        public ViewHolder(@NonNull View itemView, final com.example.blogku.adapter.PostAdapter.OnItemClickListener listener) {
            super(itemView);
            judul = itemView.findViewById(R.id.tv_judul);
            file_gambar = itemView.findViewById(R.id.tv_file_gambar);
            isi_post = itemView.findViewById(R.id.tv_isi_post);

            // Dilakukan agar item pada list dapat berpindah ke activity lain
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(com.example.blogku.adapter.PostAdapter.OnItemClickListener listener) {
        mListener = listener;
    }
}
