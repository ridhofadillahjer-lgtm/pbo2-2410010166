package id.ac.uniska.pbo2.uts;

public enum Kategori {
    ALAT_TULIS("Alat Tulis"),
    MAKANAN("Makanan"),
    MINUMAN("Minuman"),
    LAINNYA("Lainnya");

    private final String label;

    Kategori(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static Kategori dariLabel(String label) {
        for (Kategori k : values()) {
            if (k.label.equals(label)) {
                return k;
            }
        }
        throw new IllegalArgumentException("Kategori tidak dikenal: " + label);
    }

    @Override
    public String toString() {
        return label;
    }
}