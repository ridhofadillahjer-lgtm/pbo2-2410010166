package id.ac.uniska.pbo2.p07;

/**
 * Status sebuah koleksi perpustakaan. Enum membatasi nilai hanya pada pilihan yang sah.
 * Nilai dari toString() dipakai sebagai teks di JComboBox dan tabel.
 */
public enum StatusKoleksi {
    TERSEDIA("Tersedia"),
    DIPINJAM("Dipinjam");

    private final String label;

    StatusKoleksi(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}