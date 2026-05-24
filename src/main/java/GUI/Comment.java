package GUI;

import BLL.Comentario;

import javax.swing.*;
import java.awt.*;

public class Comment extends JPanel {
    Comment(Comentario comentario) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel username = new JLabel(comentario.getAuthor());
        JLabel clasificacion = new JLabel(comentario.getClasificacion() + "/10");
        JTextArea content = new JTextArea(comentario.getContenido());

        username.setFont(new Font("comic sans", Font.PLAIN, 25));
        clasificacion.setFont(new Font("comic sans", Font.PLAIN, 16));
        content.setFont(new Font("comic sans", Font.PLAIN, 14));

        content.setEditable(false);
        content.setLineWrap(true);

        add(username);
        add(clasificacion);
        add(content);
    }
}
