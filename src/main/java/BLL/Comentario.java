package BLL;

import DLL.ControllerComentario;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Comentario {
    private static ControllerComentario controller = new ControllerComentario();

    private int id;
    private int clasificacion;
    private String contenido;
    private int fkAutor;
    private int fkLibro;

    public Comentario(int id, int clasificacion, String contenido, int fkAutor, int fkLibro) {
        this.id = id;
        this.clasificacion = clasificacion;
        this.contenido = contenido;
        this.fkAutor = fkAutor;
        this.fkLibro = fkLibro;
    }

    public static int dejarComentario(int libroId, int clasificacion, String contenido) {
        int userId = Cliente.getSession().getId();

        try {
            return controller.dejarComentarioBase(userId, libroId, clasificacion, contenido);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "no puede dejar el comentario, razon:\n" + e.getMessage());
            return -1;
        }
    }

    public HashMap<String, String> getAuthor() {
        try {
            return controller.getAuthorBase(fkAutor);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static ArrayList<Comentario> getComentarios(int libroid) {
        ArrayList<Comentario> res;

        try {
            res = controller.getComentariosBase(libroid);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

        return res;
    }

    public int getClasificacion() {
        return clasificacion;
    }

    public String getContenido() {
        return contenido;
    }


}
