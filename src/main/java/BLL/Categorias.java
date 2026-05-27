package BLL;

import DLL.ControllerCategoria;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class Categorias {
    private static ControllerCategoria controller = new ControllerCategoria();
    private static ArrayList<String> categorias;

    public static ArrayList<String> getCatagorias() {
        if (categorias != null) return categorias;

        try {
            categorias = controller.getCategoriasBase();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }

        return categorias;
    }

    public static String getNombre(int fkCategoria) {
        try {
            return controller.getNombreBase(fkCategoria);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }

}
