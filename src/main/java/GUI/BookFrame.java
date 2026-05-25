package GUI;

import BLL.Comentario;
import BLL.Libro;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BookFrame extends JFrame {
    private String name;

    private Libro libro;

    private JLabel cantidad;
    private JPanel commentsWrapper;

    protected BookFrame(Libro libro) {
        name = libro.getTitulo();
        this.libro = libro;

        JPanel panel = new JPanel(new BorderLayout());
        Button back = new Button("Atrás");
        JLabel title = new JLabel(name, JLabel.RIGHT);

        JPanel wrapper = new JPanel(new BorderLayout(100, 0));
        JLabel cover = new JLabel(new ImageIcon(libro.getPortada()));
        JPanel textWrapper = new JPanel();
        JLabel mainTitle = new JLabel(name);
        JLabel clasification = new JLabel(libro.getClasificacion() + "/10", JLabel.CENTER);
        Button buy = new Button("Comprar");
        JTextArea description = new JTextArea(libro.getDescripcion());

        JPanel commentsGeneralWrapper = new JPanel();
        JPanel textCommentsWrapper = new JPanel();
        JPanel writeComentWrapper = new JPanel();
        JTextArea writeComment = new JTextArea(20, 69);

        commentsWrapper = new JPanel();
        cantidad = new JLabel();

        JScrollPane scrollPane = new JScrollPane(commentsWrapper);

        cantidad.setFont(new Font("comic sans", Font.BOLD, 25));
        mainTitle.setFont(new Font("comic sans", Font.BOLD, 25));
        clasification.setFont(new Font("comic sans", Font.PLAIN, 16));
        description.setFont(new Font("comic sans", Font.PLAIN, 14));

        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        description.setEditable(false);
        description.setFocusable(false);
        description.setLineWrap(true);

        commentsGeneralWrapper.setLayout(new BoxLayout(commentsGeneralWrapper, BoxLayout.Y_AXIS));
        commentsWrapper.setLayout(new BoxLayout(commentsWrapper, BoxLayout.Y_AXIS));
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        textWrapper.setLayout(new BoxLayout(textWrapper, BoxLayout.Y_AXIS));

        scrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        buy.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
        panel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.LIGHT_GRAY));
        mainTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        clasification.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        wrapper.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        textCommentsWrapper.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.LIGHT_GRAY));

        commentFill();

        commentsGeneralWrapper.add(textCommentsWrapper);
        commentsGeneralWrapper.add(writeComentWrapper);
        commentsGeneralWrapper.add(scrollPane);

        textCommentsWrapper.add(cantidad);

        writeComentWrapper.add(writeComment);

        wrapper.add(cover, BorderLayout.LINE_START);
        wrapper.add(textWrapper, BorderLayout.CENTER);

        textWrapper.add(mainTitle);
        textWrapper.add(clasification);
        textWrapper.add(buy);
        textWrapper.add(description);

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

        wrapper.setPreferredSize(new Dimension(getWidth(), getHeight() / 2 - getHeight() / 9));
        commentsGeneralWrapper.setPreferredSize(new Dimension(getWidth(), getHeight() / 2));

        back.addActionListener(e -> {
            StateManager.setVisible(true);
            this.dispose();
        });

        buy.addActionListener(EventManager.getInstanse());
    }

    private void commentFill() {
        //ArrayList<Comentario> arr = Comentario.getComentarios(this.libro.getId());
        ArrayList<Comentario> arr = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            arr.add(new Comentario(0, 6, "aura monster", 3));
        }

        if (arr.size() == 0) cantidad.setText("No hay comentarios");
        else cantidad.setText(arr.size() + " Comentarios");

        for (Comentario comm : arr) {
            commentsWrapper.add(new Comment(comm));
        }
    }
}
