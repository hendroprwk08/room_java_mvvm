package com.studi.room_java;

import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.studi.room_java.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding binding;
    private BukuViewModel bukuViewModel;
    private int idBuku = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Back Button
        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // langkah 1: Setup ViewModel
        MyApplication app = (MyApplication) getApplication();
        BukuViewModelFactory factory = new BukuViewModelFactory(app.getRepository());
        bukuViewModel = new ViewModelProvider(this, factory).get(BukuViewModel.class);

        // langkah 2: Ambil ID dari Intent
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            idBuku = bundle.getInt("b_id", -1);
        }

        if (idBuku == -1) {
            finish();
            return;
        }

        // langkah 3: Observasi Data Buku Berdasarkan ID
        bukuViewModel.getBukuById(idBuku).observe(this, buku -> {
            if (buku != null) {
                binding.etJudul.setText(buku.getJudul());
                binding.etPenulis.setText(buku.getPenulis());
            } else {
                // Jika data sudah dihapus dari tempat lain, tutup layar ini
                finish();
            }
        });

        binding.btHapus.setOnClickListener(v -> showDeleteConfirmation());

        binding.btSimpan.setOnClickListener(v -> updateBuku());
    }

    private void updateBuku() {
        String updatedJudul = binding.etJudul.getText().toString().trim();
        String updatedPenulis = binding.etPenulis.getText().toString().trim();

        if (!updatedJudul.isEmpty() && !updatedPenulis.isEmpty()) {
            // Pastikan ID disertakan agar Room tahu proses ini
            // gunakan UPDATE, bukan INSERT
            Buku updatedBuku = new Buku(updatedJudul, updatedPenulis);
            updatedBuku.setId(idBuku);

            bukuViewModel.update(updatedBuku);
            finish();
        }
    }

    private void showDeleteConfirmation() {
        new AlertDialog.Builder(this)
                .setTitle("Hapus Buku")
                .setMessage("Apakah Anda yakin ingin menghapus buku ini?")
                .setPositiveButton("Ya", (dialog, which) -> {
                    bukuViewModel.deleteBukuById(idBuku);
                    finish();
                })
                .setNegativeButton("Tidak", null)
                .show();
    }

    // Menangani aksi tombol back di Toolbar
    @Override
    public boolean onSupportNavigateUp() {
        getOnBackPressedDispatcher().onBackPressed();
        return true;
    }
}