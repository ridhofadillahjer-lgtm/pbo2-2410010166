package id.ac.uniska.pbo2.p02;

/**
 * Kontrak untuk semua koleksi yang dapat dipinjam.
 * Interface hanya menyebutkan apa yang harus bisa dilakukan, bukan caranya.
 */
public interface BisaDipinjam {

    /** Lama peminjaman maksimal dalam hari. */
    int batasHariPinjam();

    /** Denda keterlambatan dalam rupiah. */
    long hitungDenda(int hariTerlambat);
}