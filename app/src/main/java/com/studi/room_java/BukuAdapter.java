package com.studi.room_java;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.studi.room_java.databinding.RvItemsBinding;

public class BukuAdapter extends ListAdapter<Buku, BukuAdapter.MyViewHolder> {

    public BukuAdapter() {
        super(new BukuDiffCallback());
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return MyViewHolder.from(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Buku currentBuku = getItem(position);
        holder.bind(currentBuku);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final RvItemsBinding binding;

        public MyViewHolder(RvItemsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Buku buku) {
            binding.tvJudul.setText(buku.getJudul());
            binding.tvPenulis.setText(buku.getPenulis());

            binding.btDetil.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                // buku.getId() disesuaikan dengan getter di Entity Buku Java Anda
                bundle.putInt("b_id", buku.getId());

                Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                intent.putExtras(bundle);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                itemView.getContext().startActivity(intent);
            });
        }

        public static MyViewHolder from(ViewGroup parent) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            RvItemsBinding binding = RvItemsBinding.inflate(layoutInflater, parent, false);
            return new MyViewHolder(binding);
        }
    }

    // Callback untuk komparasi data yang efisien (DiffUtil)
    private static class BukuDiffCallback extends DiffUtil.ItemCallback<Buku> {
        @Override
        public boolean areItemsTheSame(@NonNull Buku oldItem, @NonNull Buku newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Buku oldItem, @NonNull Buku newItem) {
            // Karena di Java Buku bukan 'data class', pastikan Anda sudah
            // meng-override method equals() di class Buku atau bandingkan manual field-nya.
            return oldItem.getJudul().equals(newItem.getJudul()) &&
                    oldItem.getPenulis().equals(newItem.getPenulis());
        }
    }
}
