package BLL;

import DLL.ControllerLibro;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class Libro {
    protected final static String TABLE = "libros";
    public static ControllerLibro controller = new ControllerLibro();
    public static ArrayList<Libro> libros = new ArrayList<>();

    private int id;
    private String portada;
    private float precio;
    private int stock;
    private String titulo;
    private String descripcion;
    private String contenido;
    private int cantidadDeClasificacion;
    private int paginas;
    private float clasificacion;
    private int fkCategoria;
    private int fkAutor;

    public Libro(int id, String portada, float precio, int stock, String titulo, String descripcion, String contenido, int cantidadDeClasificacion, int paginas, float clasificacion, int fkCategoria, int fkAutor) {
        this.id = id;
        this.portada = portada;
        this.precio = precio;
        this.stock = stock;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.contenido = contenido;
        this.cantidadDeClasificacion = cantidadDeClasificacion;
        this.paginas = paginas;
        this.clasificacion = clasificacion;
        this.fkCategoria = fkCategoria;
        this.fkAutor = fkAutor;
    }

    public int actualizarStock(int libroId, int newStock) {
        try {
            return controller.actualizarStockBase(libroId, newStock);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "no puede ver el catalogo, razon:\n" + e.getMessage());
            return -1;
        }
    }

    public ArrayList<Libro> verCatalogo(String categoria) {
        final int cantidad = 100;
        ArrayList<Libro> res;

        try {
            if (categoria.isBlank()) res = controller.verCatalogoBase(cantidad);
            else res = controller.verCatalogoBase(categoria, cantidad);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "no puede ver el catalogo, razon:\n" + e.getMessage());
            return null;
        }

        libros = res;
        return res;
    }

    public ArrayList<Libro> buscarLibros(String search) {
        ArrayList<Libro> res;

        try {
            res = controller.buscarLibrosBase(search);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "no puede ver el catalogo, razon:\n" + e.getMessage());
            return null;
        }

        libros = res;
        return res;
    }

    public int dejarClasificacion(int libroId, int clasificacion) {
        int userId = Cliente.getSession().getId();

        try {
            return controller.dejarClasificacionBase(userId, libroId, clasificacion);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "no puede dejar clasificacion, razon:\n" + e.getMessage());
            return -1;
        }
    }

    public ArrayList<Libro> verHistorialLecturas() {
        int userId = Cliente.getSession().getId();

        try {
            return controller.verHistorialLecturasBase(userId);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "no puede ver el historial, razon:\n" + e.getMessage());
            return null;
        }
    }

    public String leerPaginasLibro(int libroId) {
        int userId = Cliente.getSession().getId();

        try {
            return controller.leerPaginasLibroBase(userId, libroId);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "no puede leer, razon:\n" + e.getMessage());
            return null;
        }
    }
}
