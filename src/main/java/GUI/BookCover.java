package GUI;

import BLL.Libro;

import javax.swing.*;
import java.awt.*;

public class BookCover extends JPanel {
    protected BookCover(Libro libro) {
        String path = libro.getPortada();
        ImagePanel cover = new ImagePanel(path);

        ButtonLink title = new ButtonLink(libro.getTitulo(), Color.DARK_GRAY);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(cover);
        this.add(title);

        setPreferredSize(new Dimension(240, 339));

        title.addActionListener(e -> {
            StateManager.setVisible(false);
            new BookFrame(libro);
        });
    }
}
