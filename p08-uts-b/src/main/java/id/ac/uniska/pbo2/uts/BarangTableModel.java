package id.ac.uniska.pbo2.uts;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class BarangTableModel extends AbstractTableModel {
    private static final String[] NAMA_KOLOM =
            {"Nama Barang", "Kategori", "Harga", "Jumlah", "Subtotal"};
    private static final long BATAS_DISKON = 100_000;

    private final List<Barang> daftarBarang = new ArrayList<>();

    @Override
    public int getRowCount() {
        return daftarBarang.size();
    }

    @Override
    public int getColumnCount() {
        return NAMA_KOLOM.length;
    }

    @Override
    public String getColumnName(int kolom) {
        return NAMA_KOLOM[kolom];
    }

    @Override
    public Object getValueAt(int baris, int kolom) {
        Barang b = daftarBarang.get(baris);
        switch (kolom) {
            case 0: return b.getNama();
            case 1: return b.getKategori();
            case 2: return formatRupiah(b.getHarga());
            case 3: return b.getJumlah();
            case 4: return formatRupiah(b.getSubtotal());
            default: return null;
        }
    }

    public void tambah(Barang barang) {
        daftarBarang.add(barang);
        int baris = daftarBarang.size() - 1;
        fireTableRowsInserted(baris, baris);
    }

    public boolean sudahAda(String nama) {
        for (Barang b : daftarBarang) {
            if (b.getNama().equalsIgnoreCase(nama.trim())) {
                return true;
            }
        }
        return false;
    }

    public long getTotal() {
        long total = 0;
        for (Barang b : daftarBarang) {
            total += b.getSubtotal();
        }
        return total;
    }

    // 10% dari total jika total >= Rp100.000; pembagian bilangan bulat = dibulatkan ke bawah
    public long getDiskon() {
        long total = getTotal();
        return total >= BATAS_DISKON ? total / 10 : 0;
    }

    public long getBayar() {
        return getTotal() - getDiskon();
    }

    public String getRingkasan() {
        return "Total " + formatRupiah(getTotal())
                + ", diskon " + formatRupiah(getDiskon())
                + ", bayar " + formatRupiah(getBayar());
    }

    public static String formatRupiah(long nilai) {
        DecimalFormatSymbols simbol = new DecimalFormatSymbols();
        simbol.setGroupingSeparator('.');
        return "Rp" + new DecimalFormat("#,##0", simbol).format(nilai);
    }
}