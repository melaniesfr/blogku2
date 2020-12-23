package com.example.blogku;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter {
    // List untuk menampung semua data postingan dari firebase
    List<Postingan> fetcgDataList;
    private OnItemClickListener mListener;

    public PostAdapter(List<Postingan> fetcgDataList) {
        this.fetcgDataList = fetcgDataList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        ViewHolderClass viewHolderClass = new ViewHolderClass(view, mListener);
        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderClass viewHolderClass = (ViewHolderClass)holder;

        Postingan postingan = fetcgDataList.get(position);
        viewHolderClass.judul.setText(postingan.getJudul());
        viewHolderClass.file_gambar.setText(postingan.getFileGambar());
        viewHolderClass.isi_post.setText(postingan.getIsiPost());
    }

    @Override
    public int getItemCount() {
        return fetcgDataList.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder{
        TextView judul, file_gambar, isi_post;

        public ViewHolderClass(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            judul = itemView.findViewById(R.id.tv_judul);
            file_gambar = itemView.findViewById(R.id.tv_file_gambar);
            isi_post = itemView.findViewById(R.id.tv_isi_post);

            // Dilakukan agar item pada list dapat berpindah ke activity lain
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }
}
