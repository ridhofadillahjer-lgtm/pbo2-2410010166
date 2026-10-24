package id.ac.uniska.pbo2.p05;

public final class KonverterSuhu {

    private KonverterSuhu() {
        // kelas utilitas, tidak perlu dibuat object-nya
    }

    public static double keFahrenheit(double celsius) {
        return celsius * 9 / 5 + 32;
    }

    public static double keKelvin(double celsius) {
        return celsius + 273.15;
    }

    public static double keReamur(double celsius) {
        return celsius * 4 / 5;
    }
}