package id.ac.uniska.pbo2.p02;

public class Majalah extends Koleksi {

    private final String edisi;

    public Majalah(String kode, String judul, int tahunTerbit, String edisi) {
        super(kode, judul, tahunTerbit);
        this.edisi = edisi;
    }

    public String getEdisi() {
        return edisi;
    }

    @Override
    public int batasHariPinjam() {
        return 3;
    }

    @Override
    public long hitungDenda(int hariTerlambat) {
        return hariTerlambat * 500L;
    }

    @Override
    public String keterangan() {
        return "Majalah edisi " + edisi;
    }
}