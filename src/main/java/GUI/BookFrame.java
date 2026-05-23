package GUI;

import BLL.Libro;

import javax.swing.*;
import java.awt.*;

public class BookFrame extends JFrame {
    private String name;

    protected BookFrame(Libro libro) {
        name = libro.getTitulo();

        JPanel panel = new JPanel(new BorderLayout());
        Button back = new Button("Atrás");
        JLabel title = new JLabel(name, JLabel.RIGHT);

        JPanel wrapper = new JPanel(new BorderLayout());
        JLabel cover = new JLabel(new ImageIcon(libro.getPortada()));
        JPanel textWrapper = new JPanel();
        JLabel mainTitle = new JLabel(name, JLabel.LEFT);
        JLabel clasification = new JLabel(String.valueOf(libro.getClasificacion()), JLabel.LEFT);
        Button buy = new Button("Comprar");

        JPanel descriptionWrapper = new JPanel();
        JLabel descriptionLabel = new JLabel("Descripcion", JLabel.CENTER);
        JLabel description = new JLabel(libro.getDescripcion(), JLabel.LEFT);

        descriptionWrapper.setLayout(new BoxLayout(descriptionWrapper, BoxLayout.Y_AXIS));
        textWrapper.setLayout(new BoxLayout(textWrapper, BoxLayout.Y_AXIS));

        panel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.LIGHT_GRAY));

        wrapper.add(cover, BorderLayout.LINE_START);
        wrapper.add(textWrapper, BorderLayout.LINE_END);

        textWrapper.add(mainTitle);
        textWrapper.add(clasification);
        textWrapper.add(buy);

        descriptionWrapper.add(descriptionLabel);
        descriptionWrapper.add(description);

        panel.add(back, BorderLayout.LINE_START);
        panel.add(title, BorderLayout.CENTER);

        add(panel);
        add(wrapper);
        add(descriptionWrapper);

        setName(name);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(1000, 1000);
        setVisible(true);

        panel.setPreferredSize(new Dimension(getWidth(), 200));

        back.addActionListener(e -> {
            StateManager.setVisible(true);
            this.dispose();
        });

        buy.addActionListener(EventManager.getInstanse());
    }
}
