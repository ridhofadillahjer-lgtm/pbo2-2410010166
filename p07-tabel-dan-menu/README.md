# P07 - Tabel dan Menu (Aplikasi Daftar Kontak)

Mata kuliah Pemrograman Berbasis Objek 2 (TIF1508), Pertemuan 7.
Proyek Maven: `p07-tabel-dan-menu` (package `id.ac.uniska.pbo2.p07`).

## Isi Proyek

| File | Keterangan |
|---|---|
| `Kategori.java` | Enum kategori kontak (Keluarga, Teman, Kampus, Kerja) |
| `Kontak.java` | Record data satu kontak |
| `KontakTableModel.java` | `AbstractTableModel` yang menghubungkan `List<Kontak>` dengan `JTable` |
| `DialogKontak.java` + `.form` | `JDialog Form` modal untuk tambah dan ubah kontak |
| `AplikasiKontak.java` + `.form` | `JFrame Form` utama: tabel, tombol, pencarian, menu, status |

## Peta Event

| Komponen | Event | Aksi |
|---|---|---|
| `tambahButton`, `tambahItem` | `actionPerformed` | `tambah()` |
| `ubahButton`, `ubahItem` | `actionPerformed` | `ubah()` |
| `hapusButton`, `hapusItem` | `actionPerformed` | `hapus()` |
| `cariField` | `keyReleased` | `saring()` |
| `kontakTable` | `mouseClicked` | klik dua kali memanggil `ubah()` |
| `kontakTable` (selection model) | `ListSelectionListener` di konstruktor | `perbaruiTombol()` |
| `keluarItem` | `actionPerformed` | `dispose()` |
| `tentangItem` | `actionPerformed` | dialog informasi |

## Jawaban Eksperimen Praktikum 5

**1. Jadikan `fireTableRowsInserted(baris, baris);` sebagai komentar, lalu tambah kontak baru. Apakah tabel langsung berubah?**
Tidak langsung berubah. Di `KontakTableModel.tambah()` (baris 45), `daftar.add(kontak)` tetap berjalan sehingga data masuk ke `List`, tetapi tabel tidak diberi tahu bahwa ada baris baru. `JTable` hanya menggambar ulang struktur barisnya ketika menerima event dari model. Akibatnya tampilan tidak sinkron dengan data: label status sudah menghitung kontak baru lewat `model.getRowCount()`, sedangkan baris barunya tidak langsung muncul di tabel. `ISI_HASIL_PENGAMATAN_SENDIRI` (tulis apa yang kamu lihat saat menjalankannya). Setelah baris dikembalikan, tabel kembali berubah seketika.

**2. Ganti isi `barisTerpilih` menjadi `return kontakTable.getSelectedRow();`. Cari "ah", pilih Ahmad Fauzi, klik Hapus dan pilih No. Nama siapa yang muncul di dialog konfirmasi?**
Yang muncul adalah **Budi Santoso**, bukan Ahmad Fauzi. Setelah pencarian "ah", tabel menampilkan Siti Rahmah (baris tampilan 0), Ahmad Fauzi (1), dan Nur Aisyah (2). Ahmad Fauzi berada di baris tampilan 1, tetapi di model ia berada di baris 2. Tanpa `convertRowIndexToModel`, angka 1 dipakai langsung sebagai nomor baris model, dan `model.get(1)` adalah Budi Santoso. Bila dijawab Yes, kontak yang salah (Budi) yang terhapus.

**3. Ubah `super(pemilik, judul, true)` menjadi `false`. Saat dialog terbuka, coba klik jendela utama. Apa yang terjadi pada kontak yang ditambahkan?**
Dialog menjadi non-modal, jadi jendela utama tetap bisa diklik dan dipakai sementara dialog terbuka. Kontak yang ditambahkan **tidak masuk ke tabel**. Pada dialog non-modal, `setVisible(true)` di `tampilkan()` tidak menunggu dialog ditutup, sehingga `return hasil` langsung dijalankan ketika `hasil` masih `null`. `tambah()` menerima `null` dan melewati `model.tambah(baru)`. Saat pengguna menekan Simpan kemudian, `hasil` terisi, tetapi tidak ada lagi yang membacanya.

**4. Tekan Ctrl+N, Ctrl+E, dan Ctrl+D. Mengapa Ctrl+E dan Ctrl+D tidak bekerja saat tidak ada baris yang dipilih?**
Ctrl+N bekerja karena `tambahItem` selalu aktif. Ctrl+E dan Ctrl+D tidak bekerja karena `perbaruiTombol()` memanggil `ubahItem.setEnabled(adaPilihan)` dan `hapusItem.setEnabled(adaPilihan)`. Saat tidak ada baris terpilih nilainya `false`, dan pintasan keyboard milik item menu yang nonaktif tidak dijalankan. Hal yang sama terjadi pada tombol Ubah dan Hapus yang tampak abu-abu.

**5. Ubah accelerator `hapusItem` menjadi tombol Delete tanpa Ctrl. Baris apa yang berubah di `initComponents()`?**
Baris `hapusItem.setAccelerator(...)` (baris 234 di `AplikasiKontak.java`). Sebelumnya:
```java
hapusItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_DOWN_MASK));
```
Sesudahnya, kunci berubah dari `VK_D` menjadi `VK_DELETE` dan modifier `CTRL_DOWN_MASK` menjadi `0`:
```java
hapusItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0));
```
`ISI_CEK_BARIS_ASLI_DI_SOURCE_LALU_SESUAIKAN_BILA_BERBEDA`

**6. Ubah deklarasi menjadi `private KontakTableModel model;` lalu isi `model = new KontakTableModel();` setelah `initComponents()`. Pesan error apa yang muncul dan mengapa?**
Muncul `java.lang.IllegalArgumentException: Cannot set a null TableModel`. Penyebabnya, `initComponents()` dipanggil di awal konstruktor dan menjalankan `kontakTable.setModel(model)` (baris 195). Saat itu `model` masih `null` karena baru diisi setelah `initComponents()`, dan `JTable.setModel` menolak model `null`. Aplikasi gagal dibuka.

## Jawaban Pertanyaan Refleksi

**1. Mengapa `JTable` disebut tidak menyimpan data?**
Data kontak disimpan di `List<Kontak> daftar` milik `KontakTableModel`. `JTable` hanya menampilkan: setiap kali menggambar, ia bertanya ke model lewat `getRowCount()`, `getColumnCount()`, dan `getValueAt()`.

**2. Apa yang terjadi jika model lupa memanggil method `fire` setelah data berubah?**
Data di `List` berubah tetapi `JTable` tidak tahu, sehingga tabel tidak diperbarui dan tampilan tidak sinkron dengan data (lihat eksperimen 1). Karena itu `tambah()`, `ubah()`, dan `hapus()` di `KontakTableModel` masing-masing memanggil `fireTableRowsInserted`, `fireTableRowsUpdated`, dan `fireTableRowsDeleted`.

**3. Jelaskan perbedaan mnemonic dan accelerator pada menu.**
Mnemonic dipakai dengan Alt untuk membuka menu dan memilih item sambil menu terbuka, misalnya Alt+K membuka menu Kontak (`kontakMenu.setMnemonic('K')`). Accelerator menjalankan aksi langsung tanpa membuka menu, misalnya Ctrl+N untuk Tambah (`tambahItem.setAccelerator(... VK_N, CTRL_DOWN_MASK)`).

**4. Mengapa dialog tambah dan ubah dibuat modal?**
Dialog modal mengunci jendela utama sampai ditutup, sehingga pengguna tidak mengubah tabel saat masih mengedit satu kontak. Selain itu, `setVisible(true)` pada dialog modal menunggu sampai dialog ditutup, sehingga `tampilkan()` bisa langsung mengembalikan `hasil` pada baris berikutnya. Eksperimen 3 menunjukkan bahwa pada dialog non-modal kontak baru tidak masuk ke tabel.

**5. Berikan contoh kasus ketika tidak memakai `convertRowIndexToModel` menyebabkan data yang salah terhapus.**
Cari "ah" sehingga tabel hanya menampilkan Siti Rahmah, Ahmad Fauzi, dan Nur Aisyah. Pilih Ahmad Fauzi (baris tampilan 1, baris model 2) lalu klik Hapus dan jawab Yes. Tanpa konversi, `model.hapus(1)` dijalankan dan yang terhapus adalah Budi Santoso (baris model 1). Hal serupa terjadi ketika tabel diurutkan dengan klik judul kolom.

**6. Mengapa atribut `model` harus sudah dibuat sebelum `initComponents()` dipanggil?**
Karena `initComponents()` langsung memakainya pada `kontakTable.setModel(model)`. Inisialisasi atribut pada deklarasi dijalankan sebelum isi konstruktor, jadi `model` sudah ada saat `initComponents()` berjalan. Jika baru dibuat sesudahnya, `model` masih `null` dan terjadi `IllegalArgumentException` (eksperimen 6).
