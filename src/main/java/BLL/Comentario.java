package BLL;

import DLL.ControllerComentario;

import javax.swing.*;
import java.sql.SQLException;

import static BLL.Cliente.session;

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
        int userId = session.getId();

        try {
            return controller.dejarComentario(userId, libroId, clasificacion, contenido);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "no puede dejar el comentario, razon:\n" + e.getMessage());
            return -1;
        }
    }
}
