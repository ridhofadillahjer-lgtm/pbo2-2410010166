package id.ac.uniska.pbo2.p06;

public final class Validator {

    private Validator() {
        // kelas utilitas, tidak perlu dibuat object-nya
    }

    public static String wajibDiisi(String nilai, String namaIsian) {
        if (nilai == null || nilai.isBlank()) {
            return namaIsian + " wajib diisi";
        }
        return null;
    }

    public static String npm(String npm) {
        String kosong = wajibDiisi(npm, "NPM");
        if (kosong != null) {
            return kosong;
        }
        if (!npm.trim().matches("\\d{10}")) {
            return "NPM harus berisi 10 digit angka";
        }
        return null;
    }

    public static String nama(String nama) {
    String kosong = wajibDiisi(nama, "Nama");
    if (kosong != null) {
        return kosong;
    }
    if (nama.trim().length() < 3) {
        return "Nama minimal 3 huruf";
    }
    if (nama.matches(".*\\d.*")) {
        return "Nama tidak boleh berisi angka";
    }
    return null;
    }

    public static String email(String email) {
        String kosong = wajibDiisi(email, "Email");
        if (kosong != null) {
            return kosong;
        }
        if (!email.trim().matches("[\\w.+-]+@[\\w-]+(\\.[\\w-]+)+")) {
            return "Format email belum benar, contoh: nama@contoh.com";
        }
        return null;
    }

    public static String nomorHp(String hp) {
        String kosong = wajibDiisi(hp, "Nomor HP");
        if (kosong != null) {
            return kosong;
        }
        if (!hp.trim().matches("08\\d{8,11}")) {
            return "Nomor HP diawali 08 dan berisi 10 sampai 13 digit";
        }
        return null;
    }
}