# Radio Streaming App (Android Jetpack Compose)

Aplikasi Android Radio Streaming modern yang dibuat menggunakan **Kotlin**, **Jetpack Compose (Material 3)**, dan **Media3 ExoPlayer**, serta dilengkapi dengan otomatisasi **GitHub Actions** untuk build APK secara online tanpa perlu instal SDK di komputer lokal.

---

## 📱 Fitur Utama (3 Menu Navigasi)

1. **Search (Eksplorasi Radio & Player)**
   - Kolom pencarian stasiun radio berdasarkan nama atau genre.
   - Filter cepat menggunakan genre chips (*Pop Indo*, *Rock*, *Jazz*, *Dangdut*, *Chill / Lo-Fi*, *News*).
   - Tombol putar / jeda instan dengan indikator *buffering*.
   - Tombol toggle favorit (ikon hati).

2. **Favorit (Stasiun Pilihan)**
   - Menampilkan daftar stasiun radio yang telah Anda simpan / beri tanda bintang.
   - Tampilan *empty state* yang informatif jika belum ada stasiun yang difavoritkan.

3. **Setting (Pengaturan)**
   - **Kebijakan Privasi (Privacy Policy):** Dialog penjelasan privasi data dan izin streaming jaringan.
   - **Beri Nilai Kami (Rate Us):** Dialog penilaian bintang (1-5) dan tautan ke Play Store.
   - **Tentang Aplikasi:** Informasi versi aplikasi.

4. **Floating Mini Player**
   - Bar pemutar audio mini yang selalu aktif di bagian bawah layar saat radio sedang berputar, meskipun Anda berpindah antar menu (*Search*, *Favorit*, *Setting*).

---

## 🚀 Cara Upload ke GitHub & Build APK Otomatis

Ikuti langkah mudah berikut untuk mengunggah proyek ini ke GitHub Anda dan mendapatkan file `.apk`:

### Langkah 1: Buat Repositori Baru di GitHub
1. Buka [github.com/new](https://github.com/new).
2. Beri nama repositori (misalnya: `radio-streaming-app`).
3. Pilih **Public** atau **Private**.
4. Klik **Create repository** (jangan centang README/gitignore karena sudah ada di proyek ini).

### Langkah 2: Inisialisasi & Push Kode dari Komputer Anda
Buka Terminal / PowerShell di folder proyek ini (`C:\Users\wachi\.gemini\antigravity\scratch\radio-streaming-app`), lalu jalankan perintah berikut:

```bash
git init
git add .
git commit -m "Initial commit: Radio Streaming App with Jetpack Compose & GitHub Actions"
git branch -M main
git remote add origin https://github.com/USERNAME-ANDA/radio-streaming-app.git
git push -u origin main
```
*(Ganti `USERNAME-ANDA` dan nama repo sesuai milik Anda)*.

---

## 📥 Cara Mengunduh File APK Hasil Build

1. Buka halaman repositori GitHub Anda di browser.
2. Klik tab **Actions** di bagian atas menu.
3. Anda akan melihat alur kerja **"Build Android APK"** sedang berjalan secara otomatis (ada lingkaran kuning/hijau).
4. Tunggu sekitar 2–4 menit hingga proses build selesai (centang hijau).
5. Klik pada proses build tersebut, lalu gulir ke bagian paling bawah ke bagian **Artifacts**.
6. Klik **`RadioStreaming-Debug-APK`** untuk mengunduh file `app-debug.apk`.
7. Kirim file APK tersebut ke HP Android Anda dan instal! 🎉
