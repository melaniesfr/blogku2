package com.example.blogku.model;

public class PostList {
    public String judul;
    public String file_gambar;
    public String isi_post;

    public PostList() {

    }

    public PostList(String judul, String file_gambar, String isi_post) {
        this.judul = judul;
        this.file_gambar = file_gambar;
        this.isi_post = isi_post;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getFileGambar() {
        return file_gambar;
    }

    public void setFileGambar(String file_gambar) {
        this.file_gambar = file_gambar;
    }

    public String getIsiPost() {
        return isi_post;
    }

    public void setIsiPost(String isi_post) {
        this.isi_post = isi_post;
    }
}