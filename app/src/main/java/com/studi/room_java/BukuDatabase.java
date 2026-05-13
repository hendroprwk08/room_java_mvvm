package com.studi.room_java;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.Executors;

// Entity dan Versi Database
@Database(entities = {Buku.class}, version = 1, exportSchema = false)
public abstract class BukuDatabase extends RoomDatabase {

    // DAO yang akan digunakan
    public abstract BukuDao bukuDao();

    // Singleton: untuk mencegah banyak instans database
    // terbuka secara bersamaan
    private static volatile BukuDatabase INSTANCE;

    public static BukuDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (BukuDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    BukuDatabase.class, "buku_db")
                            .setQueryExecutor(Executors.newSingleThreadExecutor()) // Memisahkan thread query
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
