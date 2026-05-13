package com.studi.room_java;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class BukuViewModelFactory implements ViewModelProvider.Factory {

    private final BukuRepository repository;

    // Constructor factory menerima repository yang dibutuhkan oleh ViewModel
    public BukuViewModelFactory(BukuRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        // Mengecek apakah modelClass yang diminta adalah BukuViewModel
        if (modelClass.isAssignableFrom(BukuViewModel.class)) {
            return (T) new BukuViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
