package id.ac.uniska.pbo2.p07;

public enum Kategori {
    KELUARGA("Keluarga"),
    TEMAN("Teman"),
    KAMPUS("Kampus"),
    KERJA("Kerja");

    private final String label;

    Kategori(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}