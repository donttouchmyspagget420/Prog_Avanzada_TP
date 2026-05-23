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

        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        this.add(cover);
        this.add(title);

        title.addActionListener(e -> {
            StateManager.setVisible(false);
            new BookFrame(libro);
        });
    }
}
