package com.studi.room_java;

import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.studi.room_java.databinding.ActivityMainBinding;
import com.studi.room_java.databinding.InputLayoutBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private BukuAdapter bukuAdapter;
    private BukuViewModel bukuViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 1. Inflate ViewBinding untuk Activity
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 2. Inisialisasi ViewModel menggunakan Factory
        // Kita mengambil repository dari kelas MyApplication (cast dari getApplication())
        MyApplication app = (MyApplication) getApplication();
        BukuViewModelFactory factory = new BukuViewModelFactory(app.getRepository());
        bukuViewModel = new ViewModelProvider(this, factory).get(BukuViewModel.class);

        // 3. Setup RecyclerView
        bukuAdapter = new BukuAdapter();
        binding.contentMain.recyclerView.setAdapter(bukuAdapter);
        binding.contentMain.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 4. Observasi data LiveData dari ViewModel
        bukuViewModel.getAllBuku().observe(this, books -> {
            if (books != null) {
                bukuAdapter.submitList(books);
            }
        });

        // 5. Setup FAB untuk Input Data via Dialog
        binding.floatingActionButton.setOnClickListener(v -> showInputDialog());
    }

    private void showInputDialog() {
        // Inflate ViewBinding untuk layout dialog
        InputLayoutBinding inputBinding = InputLayoutBinding.inflate(getLayoutInflater());

        AlertDialog builder = new AlertDialog.Builder(this)
                .setTitle("Tambah Buku")
                .setView(inputBinding.getRoot())
                .setPositiveButton(R.string.simpan, (dialog, which) -> {
                    String etJudul = inputBinding.etJudul.getText().toString().trim();
                    String etPenulis = inputBinding.etPenulis.getText().toString().trim();

                    if (!etJudul.isEmpty() && !etPenulis.isEmpty()) {
                        // Membuat objek buku baru dan menyimpannya via ViewModel
                        bukuViewModel.insert(new Buku(etJudul, etPenulis));
                    }
                })
                .setNegativeButton(R.string.tidak, (dialog, which) -> dialog.cancel())
                .create();

        builder.setCanceledOnTouchOutside(false);
        builder.show();
    }
}