package id.ac.uniska.pbo2.p07;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class KontakTableModel extends AbstractTableModel {

    private static final String[] KOLOM = {"Nama", "Telepon", "Email", "Kategori"};
    private final List<Kontak> daftar = new ArrayList<>();

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

    @Override
    public Object getValueAt(int baris, int kolom) {
        Kontak k = daftar.get(baris);
        return switch (kolom) {
            case 0 -> k.nama();
            case 1 -> k.telepon();
            case 2 -> k.email();
            default -> k.kategori();
        };
    }

    public Kontak get(int baris) {
        return daftar.get(baris);
    }

    public void tambah(Kontak kontak) {
        daftar.add(kontak);
        int baris = daftar.size() - 1;
        fireTableRowsInserted(baris, baris); // tampilkan baris baru
    }

    public void ubah(int baris, Kontak kontak) {
        daftar.set(baris, kontak);
        fireTableRowsUpdated(baris, baris);
    }

    public void hapus(int baris) {
        daftar.remove(baris);
        fireTableRowsDeleted(baris, baris);
    }
}