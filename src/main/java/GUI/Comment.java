package GUI;

import BLL.Comentario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;

public class Comment extends JPanel {
    Comment(Comentario comentario) {
        HashMap<String, String> author = comentario.getAuthor();

        String username = "", img = "";

        if (author != null) {
            username = author.get("btnUsername");
            img = author.get("pfp");
        }

        ButtonLink btnUsername = new ButtonLink(username, Color.DARK_GRAY);
        JLabel clasificacion = new JLabel(comentario.getClasificacion() + "/10");
        JTextArea content = new JTextArea(comentario.getContenido());
        ImagePanel pfp = new ImagePanel(img);
        JPanel textWrapper = new JPanel();
        JPanel topWrapper = new JPanel(new BorderLayout(10, 0));

        setBorder(new EmptyBorder(20, 20, 20, 20));
        pfp.setBorder(new EmptyBorder(10, 10, 10, 10));

        btnUsername.setFont(new Font("comic sans", Font.BOLD, 16));
        clasificacion.setFont(new Font("comic sans", Font.PLAIN, 14));
        content.setFont(new Font("comic sans", Font.PLAIN, 12));

        content.setFocusable(false);
        content.setEditable(false);
        content.setLineWrap(true);
        content.setWrapStyleWord(true);

        setLayout(new BorderLayout(10, 10));
        textWrapper.setLayout(new BoxLayout(textWrapper, BoxLayout.Y_AXIS));

        topWrapper.add(btnUsername, BorderLayout.LINE_START);
        topWrapper.add(clasificacion, BorderLayout.CENTER);

        textWrapper.add(topWrapper);
        textWrapper.add(content);

        add(pfp, BorderLayout.LINE_START);
        add(textWrapper, BorderLayout.CENTER);

        setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));
        pfp.setPreferredSize(new Dimension(50, 50));
    }
}
