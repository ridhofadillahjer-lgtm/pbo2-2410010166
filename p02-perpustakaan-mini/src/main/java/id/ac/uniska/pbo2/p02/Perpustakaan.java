package id.ac.uniska.pbo2.p02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Perpustakaan {

    private final List<Koleksi> daftarKoleksi = new ArrayList<>();
    private final Map<String, Anggota> peminjam = new HashMap<>();

    public void tambah(Koleksi koleksi) {
        daftarKoleksi.add(koleksi);
    }

    public Koleksi cari(String kode) {
        for (Koleksi koleksi : daftarKoleksi) {
            if (koleksi.getKode().equals(kode)) {
                return koleksi;
            }
        }
        return null;
    }

    public boolean pinjam(String kode, Anggota anggota) {
        Koleksi koleksi = cari(kode);

        if (koleksi == null) {
            return false;
        }

        boolean berhasil = koleksi.pinjam();

        if (berhasil) {
            peminjam.put(kode, anggota);
        }

        return berhasil;
    }

    public long kembalikan(String kode, int hariTerlambat) {
        Koleksi koleksi = cari(kode);

        if (koleksi == null) {
            return 0;
        }

        koleksi.kembalikan();
        peminjam.remove(kode);

        return koleksi.hitungDenda(hariTerlambat);
    }

    public Anggota getPeminjam(String kode) {
        return peminjam.get(kode);
    }

    public long jumlahTersedia() {
        return daftarKoleksi.stream()
                .filter(koleksi -> koleksi.getStatus() == StatusKoleksi.TERSEDIA)
                .count();
    }

    public List<Koleksi> getDaftarKoleksi() {
        return List.copyOf(daftarKoleksi);
    }

    public List<Koleksi> cariJudul(String kataKunci) {
        String kataKunciKecil = kataKunci.toLowerCase();

        return daftarKoleksi.stream()
                .filter(koleksi ->
                        koleksi.getJudul().toLowerCase().contains(kataKunciKecil))
                .toList();
    }
}