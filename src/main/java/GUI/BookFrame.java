package GUI;

import BLL.Comentario;
import BLL.Libro;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Enumeration;

public class BookFrame extends JFrame {
    private String name;

    private static Libro libroStatic;

    private JLabel cantidad;
    private JPanel commentsWrapper;

    private ArrayList<Comentario> arr;

    private boolean comprado;

    protected BookFrame(Libro libro) {
        libroStatic = libro;
        comprado = libro.checkComprado();

        name = libro.getTitulo();

        JPanel panel = new JPanel(new BorderLayout());
        Button back = new Button("Atrás");
        JLabel title = new JLabel(name, JLabel.RIGHT);

        JPanel wrapper = new JPanel(new BorderLayout(100, 0));
        ImagePanel cover = new ImagePanel(libro.getPortada());
        JPanel textWrapper = new JPanel();
        JLabel mainTitle = new JLabel(name);
        JLabel clasification = new JLabel(libro.getClasificacion() + "/10", JLabel.CENTER);
        Button buy = new Button((comprado) ? "leer el principio" : "comprar");
        JTextArea description = new JTextArea(libro.getDescripcion());

        JPanel commentsGeneralWrapper = new JPanel();
        JPanel textCommentsWrapper = new JPanel();
        JPanel writeComentWrapper = new JPanel();
        JTextArea writeComment = new JTextArea(10, 69);
        Button post = new Button("postear");
        ButtonGroup clasificar = new ButtonGroup();
        JPanel clasificarWrapper = new JPanel();
        JRadioButton btn1 = new JRadioButton("1");
        JRadioButton btn2 = new JRadioButton("2");
        JRadioButton btn3 = new JRadioButton("3");
        JRadioButton btn4 = new JRadioButton("4");
        JRadioButton btn5 = new JRadioButton("5");

        commentsWrapper = new JPanel();
        cantidad = new JLabel();

        JScrollPane scrollPane = new JScrollPane(commentsWrapper);

        cantidad.setFont(new Font("comic sans", Font.BOLD, 14));
        mainTitle.setFont(new Font("comic sans", Font.BOLD, 25));
        clasification.setFont(new Font("comic sans", Font.PLAIN, 16));
        description.setFont(new Font("comic sans", Font.PLAIN, 14));

        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        description.setAlignmentX(JTextArea.LEFT_ALIGNMENT);
        description.setEditable(false);
        description.setFocusable(false);
        description.setLineWrap(true);

        commentsGeneralWrapper.setLayout(new BoxLayout(commentsGeneralWrapper, BoxLayout.Y_AXIS));
        commentsWrapper.setLayout(new BoxLayout(commentsWrapper, BoxLayout.Y_AXIS));
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        textWrapper.setLayout(new BoxLayout(textWrapper, BoxLayout.Y_AXIS));

        scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        commentsWrapper.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        buy.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
        panel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.LIGHT_GRAY));
        mainTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        clasification.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        wrapper.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        textCommentsWrapper.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.LIGHT_GRAY));

        clasificar.add(btn1);
        clasificar.add(btn2);
        clasificar.add(btn3);
        clasificar.add(btn4);
        clasificar.add(btn5);

        commentFill();

        clasificarWrapper.add(btn1);
        clasificarWrapper.add(btn2);
        clasificarWrapper.add(btn3);
        clasificarWrapper.add(btn4);
        clasificarWrapper.add(btn5);


        textCommentsWrapper.add(cantidad);

        writeComentWrapper.add(clasificarWrapper);
        writeComentWrapper.add(post);
        writeComentWrapper.add(writeComment);

        wrapper.add(cover, BorderLayout.LINE_START);
        wrapper.add(textWrapper, BorderLayout.CENTER);

        commentsGeneralWrapper.add(textCommentsWrapper);
        if (comprado) commentsGeneralWrapper.add(writeComentWrapper);
        commentsGeneralWrapper.add(scrollPane);

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
        commentsGeneralWrapper.setMinimumSize(new Dimension(getWidth(), getHeight() / 2));
        cover.setPreferredSize(new Dimension(wrapper.getWidth() / 5, wrapper.getHeight()));
        commentsWrapper.setPreferredSize(new Dimension(commentsWrapper.getWidth(), (commentsWrapper.getComponentCount()) * commentsWrapper.getComponent(0).getHeight()));

        back.addActionListener(e -> {
            StateManager.setVisible(true);
            this.dispose();
        });

        buy.addActionListener(EventManager.getInstanse());

        post.addActionListener(e -> {
            int clasificacion = -1;

            String content = writeComment.getText();

            if (content.isBlank()) return;

            Enumeration<AbstractButton> mod = clasificar.getElements();

            while (mod.hasMoreElements()) {
                AbstractButton btn = mod.nextElement();

                if (!btn.isSelected()) continue;

                clasificacion = Integer.valueOf(btn.getText());
            }

            if (clasificacion <= 0) return;

            libro.dejarClasificacion(libro.getId(), clasificacion);
            Comentario.dejarComentario(libro.getId(), clasificacion, content);
        });
    }

    public static Libro getLibroStatic() {
        return libroStatic;
    }

    private void commentFill() {
        //ArrayList<Comentario> arr = Comentario.getComentarios(this.libro.getId());
        arr = new ArrayList<>();

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
