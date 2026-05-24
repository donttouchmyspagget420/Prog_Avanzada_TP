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

        JPanel wrapper = new JPanel(new BorderLayout(100, 0));
        JLabel cover = new JLabel(new ImageIcon(libro.getPortada()));
        JPanel textWrapper = new JPanel();
        JLabel mainTitle = new JLabel(name);
        JLabel clasification = new JLabel(libro.getClasificacion() + "/10", JLabel.LEFT);
        Button buy = new Button("Comprar");
        JTextArea description = new JTextArea(libro.getDescripcion());

        JPanel commentsGeneralWrapper = new JPanel();
        JPanel textCommentsWrapper = new JPanel();
        JPanel writeComentWrapper = new JPanel();
        JPanel commentsWrapper = new JPanel();
        JTextArea writeComment = new JTextArea();

        mainTitle.setFont(new Font("comic sans", Font.BOLD, 25));
        clasification.setFont(new Font("comic sans", Font.PLAIN, 16));
        description.setFont(new Font("comic sans", Font.PLAIN, 14));

        description.setEditable(false);
        description.setLineWrap(true);

        commentsGeneralWrapper.setLayout(new BoxLayout(commentsGeneralWrapper, BoxLayout.Y_AXIS));
        textWrapper.setLayout(new BoxLayout(textWrapper, BoxLayout.Y_AXIS));
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        buy.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
        panel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.LIGHT_GRAY));
        mainTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        clasification.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        wrapper.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        wrapper.add(cover, BorderLayout.LINE_START);
        wrapper.add(textWrapper, BorderLayout.CENTER);

        textWrapper.add(mainTitle);
        textWrapper.add(clasification);
        textWrapper.add(description);
        textWrapper.add(buy);

        panel.add(back, BorderLayout.LINE_START);
        panel.add(title, BorderLayout.CENTER);

        add(panel);
        add(wrapper);
        add(commentsGeneralWrapper);

        setName(name);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(1000, 1000);
        setVisible(true);

        wrapper.setPreferredSize(new Dimension(getWidth(), getHeight() / 3));
        commentsGeneralWrapper.setPreferredSize(new Dimension(getWidth(), getHeight() / 2));

        back.addActionListener(e -> {
            StateManager.setVisible(true);
            this.dispose();
        });

        buy.addActionListener(EventManager.getInstanse());
    }
}
