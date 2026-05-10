package BLL;

import DLL.ControllerPopularidad;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class Popularidad {
    private String categoria;
    private Libro[] topLibros = new Libro[10];

    private static ArrayList<Popularidad> popularidades;

    private static ControllerPopularidad controller = new ControllerPopularidad();

    Popularidad(String categoria, Libro[] topLibros) {
        this.categoria = categoria;
        this.topLibros = topLibros;
        popularidades.add(this);
    }

    public static ArrayList<Popularidad> verPopularidad() {
        return popularidades;
    }

    public String getCat() {
        return categoria;
    }

    public Libro[] getLibros() {
        return topLibros;
    }

    public static Popularidad calcularPopularidad(String categoria) {
        for (Popularidad pop : popularidades) {
            if (pop.getCat().equals(categoria)) {
                return pop;
            }
        }

        try {
            Libro[] libros = controller.calcularPopularidadBase(categoria);
            return new Popularidad(categoria, libros);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "no puede calcular la popularidad en categoria: " + categoria + ",razon\n" + e.getMessage());
            return null;
        }
    }
}
