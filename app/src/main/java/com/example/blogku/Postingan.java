package com.example.blogku;

public class Postingan {
    private String judul;
    private String file_gambar;
    private String isi_post;

    public Postingan(String judul, String file_gambar, String isi_post) {
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
