package GUI;

import BLL.Cliente;
import BLL.Libro;
import BLL.Venta;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ProfileFrame extends JFrame {

    private JPanel profileContent;
    private Cliente cliente = Cliente.getSession();

    private final int COLUMNS = 3;

    protected ProfileFrame() {
        SideBar sideBar = new SideBar();
        JPanel profileWrapper = new JPanel();

        JPanel profileHead = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 0));
        ImagePanel img = new ImagePanel(cliente.getPfp());
        JPanel textWeapper = new JPanel();
        JLabel username = new JLabel(cliente.getUsername());
        JLabel correo = new JLabel(cliente.getCorreo());
        Button editar = new Button("Editar el Perfil");

        JPanel headWrapper = new JPanel(new BorderLayout());
        JPanel btnGroupWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 0));
        ButtonLink sobre = new ButtonLink("Sobre Mí", getBackground());
        ButtonLink compras = new ButtonLink("Mis Compras", getBackground());
        ButtonLink lecturas = new ButtonLink("Mis Lecturas", getBackground());

        profileContent = new JPanel();

        JScrollPane scrollPane = new JScrollPane(profileContent);

        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        profileWrapper.setLayout(new BorderLayout(0, 20));
        textWeapper.setLayout(new BoxLayout(textWeapper, BoxLayout.Y_AXIS));
        setLayout(new BorderLayout());

        username.setFont(new Font("comic sans", Font.BOLD, 35));
        correo.setFont(new Font("comic sans", Font.BOLD, 25));
        editar.setFont(new Font("comic sans", Font.PLAIN, 16));
        sobre.setFont(new Font("comic sans", Font.PLAIN, 16));
        compras.setFont(new Font("comic sans", Font.PLAIN, 16));
        lecturas.setFont(new Font("comic sans", Font.PLAIN, 16));

        scrollPane.setBorder(null);
        btnGroupWrapper.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
        editar.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));

        sobre();

        profileHead.add(img);
        profileHead.add(textWeapper);

        textWeapper.add(username);
        textWeapper.add(correo);
        textWeapper.add(editar);

        headWrapper.add(profileHead, BorderLayout.NORTH);
        headWrapper.add(btnGroupWrapper, BorderLayout.SOUTH);

        btnGroupWrapper.add(sobre);
        btnGroupWrapper.add(compras);
        btnGroupWrapper.add(lecturas);

        profileWrapper.add(headWrapper, BorderLayout.NORTH);
        profileWrapper.add(scrollPane, BorderLayout.CENTER);

        add(sideBar, BorderLayout.LINE_START);
        add(profileWrapper, BorderLayout.CENTER);

        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        img.setPreferredSize(new Dimension(200, 200));

        sobre.addActionListener(e -> {
            sobre();
        });

        compras.addActionListener(e -> {
            compras();
        });

        lecturas.addActionListener(e -> {
            lecturas();
        });

        editar.addActionListener(e -> {
            editar();
        });
    }

    private void sobre() {
        profileContent.removeAll();

        JLabel title = new JLabel("Sobre Mí");
        JTextArea sobre = new JTextArea(cliente.getSobre());

        title.setFont(new Font("comic sans", Font.BOLD, 25));
        sobre.setFont(new Font("comic sans", Font.PLAIN, 16));

        sobre.setLineWrap(true);
        sobre.setEditable(false);
        sobre.setFocusable(false);

        title.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.LIGHT_GRAY));

        profileContent.setLayout(new BorderLayout());

        profileContent.add(title, BorderLayout.NORTH);
        profileContent.add(sobre, BorderLayout.CENTER);

        profileContent.revalidate();
        profileContent.repaint();
    }

    private void compras() {
        profileContent.removeAll();

        profileContent.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));

        ArrayList<Libro> libros = Libro.verHistorialLecturas();

        if (libros == null || libros.size() == 0) {
            profileContent.add(new JPanel());
            profileContent.add(new JLabel("No hay libros"));
            profileContent.add(new JPanel());
            return;
        }

        for (Libro libro : libros) {
            profileContent.add(new BookCover(libro));
        }


        profileContent.setPreferredSize(new Dimension(profileContent.getComponent(0).getWidth() * COLUMNS, profileContent.getComponent(0).getHeight() * COLUMNS * (profileContent.getComponentCount() / COLUMNS)));
        profileContent.revalidate();
        profileContent.repaint();
    }

    private void lecturas() {
        profileContent.removeAll();

        profileContent.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));

        ArrayList<String> historial = Venta.verHistorialCompras();

        if (historial == null || historial.size() == 0) {
            profileContent.add(new JPanel());
            profileContent.add(new JLabel("No hay historial"));
            profileContent.add(new JPanel());
            return;
        }

        for (String venta : historial) {
            JTextArea ticket = new JTextArea(venta);

            ticket.setEditable(false);
            ticket.setFocusable(false);
            ticket.setLineWrap(true);
            ticket.setBackground(Color.BLACK);

            profileContent.add(ticket);
        }


        profileContent.setPreferredSize(new Dimension(profileContent.getComponent(0).getWidth() * COLUMNS, profileContent.getComponent(0).getHeight() * COLUMNS * (profileContent.getComponentCount() / COLUMNS)));
        profileContent.revalidate();
        profileContent.repaint();
    }

    private void editar() {

    }
}
