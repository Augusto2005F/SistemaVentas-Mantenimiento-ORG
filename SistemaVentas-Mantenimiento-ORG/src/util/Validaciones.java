package util;

public class Validaciones {

    public static boolean validarDni(String dni) {
        if (dni == null) return false;
        if (dni.length() != 8) return false;
        for (char c : dni.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean validarPrecio(double precio) {
        return precio > 0;
    }

    public static boolean validarCantidad(int cantidad) {
        return cantidad > 0;
    }
}
