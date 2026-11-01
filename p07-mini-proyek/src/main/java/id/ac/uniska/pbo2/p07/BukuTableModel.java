package id.ac.uniska.pbo2.p07;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Menghubungkan daftar Buku dengan JTable.
 * JTable bertanya kepada model: berapa baris, berapa kolom, dan apa isi setiap sel.
 */
public class BukuTableModel extends AbstractTableModel {

    private static final String[] KOLOM = {"Kode", "Judul", "Penulis", "Tahun Terbit", "Status"};

    private final List<Buku> daftar = new ArrayList<>();

    @Override
    public int getRowCount() {
        return daftar.size();
    }

    @Override
    public int getColumnCount() {
        return KOLOM.length;
    }

    @Override
    public String getColumnName(int kolom) {
        return KOLOM[kolom];
    }

    /** Kolom tahun bertipe Integer agar pengurutan berdasarkan angka, bukan huruf. */
    @Override
    public Class<?> getColumnClass(int kolom) {
        return kolom == 3 ? Integer.class : String.class;
    }

    @Override
    public Object getValueAt(int baris, int kolom) {
        Buku b = daftar.get(baris);
        return switch (kolom) {
            case 0 -> b.getKode();
            case 1 -> b.getJudul();
            case 2 -> b.getPenulis();
            case 3 -> b.getTahunTerbit();
            default -> b.getStatus();
        };
    }

    public Buku get(int baris) {
        return daftar.get(baris);
    }

    public void tambah(Buku buku) {
        daftar.add(buku);
        int baris = daftar.size() - 1;
        fireTableRowsInserted(baris, baris); // tampilkan baris baru
    }

    public void ubah(int baris, Buku buku) {
        daftar.set(baris, buku);
        fireTableRowsUpdated(baris, baris);
    }

    public void hapus(int baris) {
        daftar.remove(baris);
        fireTableRowsDeleted(baris, baris);
    }

    /** Jumlah buku berstatus Tersedia di seluruh data (bukan hanya yang tampil). */
    public long jumlahTersedia() {
        return daftar.stream()
                .filter(b -> b.getStatus() == StatusKoleksi.TERSEDIA)
                .count();
    }
}