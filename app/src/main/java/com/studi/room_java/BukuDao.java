package com.studi.room_java;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BukuDao {
    @Insert
    void insert(Buku buku);

    @Delete
    void delete(Buku buku);

    @Update
    void update(Buku buku);

    @Query("DELETE FROM buku WHERE id = :bukuId")
    void deleteById(int bukuId);

    // LiveData secara otomatis berjalan di background thread
    @Query("SELECT * FROM buku")
    LiveData<List<Buku>> getAll();

    @Query("SELECT * FROM buku WHERE id = :bukuId")
    LiveData<Buku> getById(int bukuId);
}