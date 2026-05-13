package com.studi.room_java;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "buku")
public class Buku {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "judul")
    private String judul;

    @ColumnInfo(name = "penulis")
    private String penulis;

    // Constructor
    public Buku(String judul, String penulis) {
        this.judul = judul;
        this.penulis = penulis;
    }

    // Getter dan Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }
}
