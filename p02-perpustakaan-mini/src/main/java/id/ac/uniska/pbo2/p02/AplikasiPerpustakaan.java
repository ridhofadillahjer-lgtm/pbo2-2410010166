package id.ac.uniska.pbo2.p02;

import java.util.List;

public class AplikasiPerpustakaan {

    public static void main(String[] args) {

        Perpustakaan perpustakaan = new Perpustakaan();

        perpustakaan.tambah(
                new Buku("B001", "Laskar Pelangi", 2005, "Andrea Hirata")
        );

        perpustakaan.tambah(
                new Buku("B002", "Clean Code", 2008, "Robert C. Martin")
        );

        perpustakaan.tambah(
                new Majalah("M001", "Majalah Teknologi Kita", 2026, "Agustus")
        );

        perpustakaan.tambah(
                new Skripsi(
                        "S001",
                        "Implementasi Sistem Informasi Perpustakaan",
                        2026,
                        "M. Ridho Fadillah",
                        "Teknik Informatika"
                )
        );

        Anggota siti = new Anggota("2410010123", "Siti Rahmah");
        Anggota budi = new Anggota("2410010456", "Budi Santoso");

        System.out.println("=== Daftar Koleksi ===");

        for (Koleksi koleksi : perpustakaan.getDaftarKoleksi()) {
            System.out.println(koleksi);
        }

        boolean pinjamSiti = perpustakaan.pinjam("B002", siti);
        
        System.out.println(
                "Siti Rahmah meminjam B002: "
                + (pinjamSiti ? "berhasil" : "gagal")
        );

        boolean pinjamBudi = perpustakaan.pinjam("B002", budi);
        System.out.println(
                "Budi Santoso meminjam B002: "
                + (pinjamBudi ? "berhasil" : "gagal")
        );

        boolean pinjamMajalah = perpustakaan.pinjam("M001", budi);
        System.out.println(
                "Budi Santoso meminjam M001: "
                + (pinjamMajalah ? "berhasil" : "gagal")
        );

        System.out.println(
                "Peminjam B002: "
                + perpustakaan.getPeminjam("B002").nama()
        );

        long dendaBuku = perpustakaan.kembalikan("B002", 2);
        System.out.println(
                "Pengembalian B002 terlambat 2 hari, denda Rp" + dendaBuku
        );

        long dendaMajalah = perpustakaan.kembalikan("M001", 3);
        System.out.println(
                "Pengembalian M001 terlambat 3 hari, denda Rp" + dendaMajalah
        );

        List<Koleksi> hasilPencarian = perpustakaan.cariJudul("code");

        System.out.println(
                "Hasil pencarian \"code\": "
                + hasilPencarian.size()
                + " koleksi"
        );

        for (Koleksi koleksi : hasilPencarian) {
            System.out.println(koleksi);
        }

        boolean pinjamSkripsi = perpustakaan.pinjam("S001", siti);

        System.out.println(
                "Siti Rahmah meminjam S001: "
                + (pinjamSkripsi ? "berhasil" : "gagal")
        );

        System.out.println(
                "Koleksi tersedia: "
                + perpustakaan.jumlahTersedia()
                + " dari "
                + perpustakaan.getDaftarKoleksi().size()
        );
    }
}