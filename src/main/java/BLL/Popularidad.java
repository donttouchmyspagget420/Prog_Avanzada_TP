package BLL;

import DLL.ControllerPopularidad;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class Popularidad {
    private static ArrayList<Libro> topLibros = null;

    private static ControllerPopularidad controller = new ControllerPopularidad();

    public static ArrayList<Libro> getLibros() {
        if (topLibros == null) calcularPopularidad();
        return topLibros;
    }

    public static void calcularPopularidad() {
        try {
            topLibros = controller.calcularPopularidadBase();
        } catch (SQLException e) {
            System.out.println("no puede calcular la popularidad: " + "razon\n" + e.getMessage());
        }
    }
}
