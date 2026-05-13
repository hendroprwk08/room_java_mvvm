package com.studi.room_java;

/**
 * Class ini bertindak sebagai Dependency Provider sederhana.
 * Menghubungkan Repository dengan ViewModel Factory.
 */

import android.app.Application;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyApplication extends Application {

    // Pengganti CoroutineScope: ExecutorService untuk tugas latar belakang global
    // FixedThreadPool(4) umum digunakan, atau SingleThreadExecutor untuk urutan database
    private final ExecutorService applicationExecutor = Executors.newFixedThreadPool(4);

    private BukuDatabase database;
    private BukuRepository repository;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public BukuDatabase getDatabase() {
        if (database == null) {
            database = BukuDatabase.getDatabase(this);
        }
        return database;
    }

    public BukuRepository getRepository() {
        if (repository == null) {
            repository = new BukuRepository(getDatabase().bukuDao());
        }
        return repository;
    }

    public ExecutorService getApplicationExecutor() {
        return applicationExecutor;
    }
}
