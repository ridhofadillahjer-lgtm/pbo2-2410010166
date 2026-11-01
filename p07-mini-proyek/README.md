# P07 - Mini Proyek: Katalog Buku Perpustakaan

Mata kuliah Pemrograman Berbasis Objek 2 (TIF1508), Pertemuan 7.
Proyek Maven: `p07-mini-proyek` (package `id.ac.uniska.pbo2.p07`).
Nama: M. Ridho Fadillah (2410010166)

## Video Demo

Tautan video demo (maksimal 3 menit): (https://drive.google.com/drive/folders/1Pj8Q56cyNeAO2td0z9mlXYhV3LL_Xene?usp=sharing)

## Isi Proyek

| File | Keterangan |
|---|---|
| `BisaDipinjam.java`, `Koleksi.java`, `Buku.java` | Kelas model, dipakai ulang dan disesuaikan dari Pertemuan 2 |
| `StatusKoleksi.java` | Enum status buku (Tersedia, Dipinjam) |
| `BukuTableModel.java` | `AbstractTableModel` yang menghubungkan daftar `Buku` dengan `JTable` |
| `DialogBuku.java` + `.form` | `JDialog Form` modal untuk tambah dan ubah buku |
| `KatalogBuku.java` + `.form` | `JFrame Form` utama: tabel, tombol, pencarian, menu, status |

## Fitur

| No | Fitur | Wujud di program |
|---|---|---|
| 1 | Tabel buku | Kolom Kode, Judul, Penulis, Tahun Terbit, Status memakai `BukuTableModel` |
| 2 | Data contoh | 5 buku dibuat di `isiContoh()`, dua di antaranya berstatus Dipinjam |
| 3 | Tambah dan ubah | `DialogBuku` modal; kode dan judul wajib diisi; tahun berupa angka 1900 sampai tahun sekarang |
| 4 | Hapus | Dengan konfirmasi, memakai `convertRowIndexToModel` di `barisTerpilih()` |
| 5 | Menu | Menu File (mnemonic F) dan Buku (mnemonic B); Tambah Ctrl+N, Ubah Ctrl+E, Hapus Ctrl+D |
| 6 | Pencarian | `saring()` menyaring kolom kode, judul, dan penulis dengan `TableRowSorter` |
| 7 | Status | Label "Menampilkan x dari y buku, z tersedia" |
| 8 | Tampilan | `JFrame Form` dan `JDialog Form` dengan file `.form`, FlatLaf, Ubah dan Hapus aktif sesuai pilihan baris |

## Peta Event

| Komponen | Event | Aksi |
|---|---|---|
| `tambahButton`, `tambahItem` | `actionPerformed` | `tambah()` |
| `ubahButton`, `ubahItem` | `actionPerformed` | `ubah()` |
| `hapusButton`, `hapusItem` | `actionPerformed` | `hapus()` |
| `cariField` | `keyReleased` | `saring()` |
| `bukuTable` | `mouseClicked` | klik dua kali memanggil `ubah()` |
| `bukuTable` (selection model) | `ListSelectionListener` di konstruktor | `perbaruiTombol()` |
| `keluarItem` | `actionPerformed` | `dispose()` |
| `batalButton` (`DialogBuku`) | `actionPerformed` | `dispose()` |
| `simpanButton` (`DialogBuku`) | `actionPerformed` | `simpan()` |

## Deklarasi Penggunaan AI

Saya menggunakan Claude (Anthropic) untuk membantu menyusun kelas model (`BukuTableModel`, penyesuaian `Buku` dan `StatusKoleksi`), isi event handler dan method bantu pada `KatalogBuku` dan `DialogBuku`, serta kerangka README ini. Komponen antarmuka (JFrame Form, JDialog Form, menu, dan propertinya) saya susun sendiri di GUI Builder, dan seluruh fitur saya uji dengan menjalankan aplikasi.
