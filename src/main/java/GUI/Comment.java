package GUI;

import BLL.Comentario;

import javax.swing.*;
import java.awt.*;

public class Comment extends JPanel {
    Comment(Comentario comentario) {
        JPanel wrapper = new JPanel();

        JLabel username = new JLabel(comentario.getAuthor());
        JLabel clasificacion = new JLabel(comentario.getClasificacion() + "/10");
        JTextArea content = new JTextArea(comentario.getContenido());

        username.setFont(new Font("comic sans", Font.PLAIN, 25));
        clasificacion.setFont(new Font("comic sans", Font.PLAIN, 16));
        content.setFont(new Font("comic sans", Font.PLAIN, 14));

        content.setFocusable(false);
        content.setEditable(false);
        content.setLineWrap(true);

        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));

        wrapper.add(username);
        wrapper.add(clasificacion);
        wrapper.add(content);

        add(wrapper);
    }
}
