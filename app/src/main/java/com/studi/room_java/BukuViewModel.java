package com.studi.room_java;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class BukuViewModel extends ViewModel {

    private final BukuRepository repository;
    private final LiveData<List<Buku>> allBuku;

    // Constructor untuk menerima repository
    public BukuViewModel(BukuRepository repository) {
        this.repository = repository;
        this.allBuku = repository.getAllBuku();
    }

    public LiveData<List<Buku>> getAllBuku() {
        return allBuku;
    }

    public void insert(Buku buku) {
        repository.insert(buku);
    }

    public void update(Buku buku) {
        repository.update(buku);
    }

    public void delete(Buku buku) {
        repository.delete(buku);
    }

    public void deleteBukuById(int id) {
        repository.deleteBukuById(id);
    }

    public LiveData<Buku> getBukuById(int id) {
        return repository.getBukuById(id);
    }
}
