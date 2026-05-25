package BLL;

import DLL.ControllerComentario;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class Comentario {
    private static ControllerComentario controller = new ControllerComentario();

    private int id;
    private int clasificacion;
    private String contenido;
    private int fkAutor;

    public Comentario(int id, int clasificacion, String contenido, int fkAutor) {
        this.id = id;
        this.clasificacion = clasificacion;
        this.contenido = contenido;
        this.fkAutor = fkAutor;
    }

    public int dejarComentario(int libroId, int clasificacion, String contenido) {
        int userId = Cliente.getSession().getId();

        try {
            return controller.dejarComentarioBase(userId, libroId, clasificacion, contenido);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "no puede dejar el comentario, razon:\n" + e.getMessage());
            return -1;
        }
    }

    public String getAuthor() {
        try {
            return controller.getAuthorBase(fkAutor);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public int getClasificacion() {
        return clasificacion;
    }

    public String getContenido() {
        return contenido;
    }

    public static ArrayList<Comentario> getComentarios(int libroid) {
        ArrayList<Comentario> res = new ArrayList<>();

        try {
            res = controller.getComentariosBase(libroid);

        } catch (SQLException e) {
            return null;
        }

        return res;
    }
}
