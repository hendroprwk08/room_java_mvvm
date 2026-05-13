package com.studi.room_java;

import androidx.lifecycle.LiveData;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BukuRepository {

    private final BukuDao bukuDao;
    private final LiveData<List<Buku>> allBuku;

    // Executor untuk menjalankan tugas di background thread (pengganti suspend)
    private final ExecutorService executorService;

    public BukuRepository(BukuDao bukuDao) {
        this.bukuDao = bukuDao;
        this.allBuku = bukuDao.getAll();
        // Membuat pool thread tunggal untuk operasi database yang berurutan
        this.executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<Buku>> getAllBuku() {
        return allBuku;
    }

    public void insert(Buku buku) {
        executorService.execute(() -> bukuDao.insert(buku));
    }

    public void update(Buku buku) {
        executorService.execute(() -> bukuDao.update(buku));
    }

    public void delete(Buku buku) {
        executorService.execute(() -> bukuDao.delete(buku));
    }

    public void deleteBukuById(int id) {
        executorService.execute(() -> bukuDao.deleteById(id));
    }

    // LiveData tidak perlu executor karena Room sudah menanganinya otomatis
    public LiveData<Buku> getBukuById(int id) {
        return bukuDao.getById(id);
    }
}
