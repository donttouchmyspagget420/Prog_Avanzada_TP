package GUI;

import BLL.Libro;

import javax.swing.*;
import java.awt.*;

public class BookFrame extends JFrame {
    private String name;

    protected BookFrame(Libro libro) {
        JPanel panel = new JPanel(new BorderLayout());
        Button back = new Button("Atrás");

        setLayout(new BorderLayout());

        panel.add(back, BorderLayout.LINE_START);

        add(panel, BorderLayout.NORTH);

        setName(name);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(1000, 1000);
        setVisible(true);

        back.addActionListener(e -> {
            StateManager.setVisible(true);
            this.dispose();
        });
    }
}
