# Katalog Buku - Mobile Programming Project

Aplikasi pengelolaan data buku sederhana yang dibangun sebagai implementasi arsitektur modern pada platform Android. Proyek ini mendemonstrasikan integrasi antara database lokal, manajemen state yang reaktif, dan standar desain terbaru dari Google.

## 🛠 Teknologi & Arsitektur
Aplikasi ini dikembangkan menggunakan bahasa pemrograman **Java** dengan menerapkan komponen-komponen berikut:

*   **Arsitektur MVVM (Model-View-ViewModel)**: Memisahkan logika bisnis dari UI untuk meningkatkan skalabilitas dan mempermudah pengujian.
*   **Room Persistence Library**: Digunakan sebagai lapisan abstraksi di atas SQLite untuk manajemen database lokal yang lebih aman dan efisien.
*   **LiveData & ViewModel**: Memastikan UI selalu sinkron dengan data terbaru dan menjaga data tetap bertahan saat terjadi perubahan konfigurasi (seperti rotasi layar).
*   **View Binding**: Interaksi antar komponen UI yang lebih aman dari *null pointer exception*.
*   **Material 3 (M3)**: Implementasi standar desain Google terbaru untuk antarmuka yang lebih modern dan adaptif.

## 📱 Tampilan Aplikasi
Aplikasi memiliki antarmuka yang bersih dengan dukungan komponen Material 3 seperti `TextInputLayout`, `MaterialCard`, dan `FloatingActionButton`.

<p align="center">
  <img src="https://github.com/user-attachments/assets/077f1979-0a2b-466f-b6f3-e103ce49201e" width="200" />
  <img src="https://github.com/user-attachments/assets/b6fb3a37-6196-43c8-b940-cb09ea02a68e" width="200" />
  <img src="https://github.com/user-attachments/assets/54d2476f-f7f5-4618-a378-53c82985195d" width="200" />
  <img src="https://github.com/user-attachments/assets/5fe31744-dbf0-4147-a11f-a5601c86e74b" width="200" />
</p>

## 🚀 Fitur Utama
- **Create**: Menambah data buku baru dengan validasi input.
- **Read**: Menampilkan daftar buku secara real-time menggunakan `RecyclerView`.
- **Update**: Memperbarui informasi buku yang sudah ada melalui layar detail.
- **Delete**: Menghapus data buku dengan konfirmasi dialog keamanan.

## 📂 Struktur Proyek
Proyek ini diorganisir berdasarkan tanggung jawab kelasnya:
- `model`: Berisi entitas database (Room Entity).
- `repository`: Menangani sumber data (Data Source).
- `viewmodel`: Jembatan antara data dan UI.
- `ui`: Berisi Activity dan Adapter untuk tampilan.

---
*Proyek ini dikembangkan untuk tujuan pembelajaran Mobile Programming.*
