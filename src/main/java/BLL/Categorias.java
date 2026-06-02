package BLL;

import DLL.ControllerCategoria;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class Categorias {
    private static ControllerCategoria controller = new ControllerCategoria();

    int id;
    String nombre;

    public Categorias(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public static ArrayList<String> getCatagorias() {
        try {
            return controller.getCategoriasBase();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }

    public static String getNombre(int fkCategoria) {
        try {
            return controller.getNombreBase(fkCategoria);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }

    public static ArrayList<Categorias> selectCategorias() {
        try {
            return controller.selectCategoriasBase();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "no puede obtener categorias, razon: " + e.getMessage());
            return null;
        }
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

}
