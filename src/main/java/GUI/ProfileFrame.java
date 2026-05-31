package GUI;

import BLL.Cliente;
import BLL.Libro;
import BLL.Venta;
import Utils.LayoutUtils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ProfileFrame extends JFrame {

    private JPanel profileContent;
    private Cliente cliente;

    private final int COLUMNS = 3;

    protected ProfileFrame() {
        cliente = Cliente.getSession();

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

    protected ProfileFrame(int profileId) {
        cliente = Cliente.getClienteById(profileId);

        SideBar sideBar = new SideBar();
        JPanel profileWrapper = new JPanel();

        JPanel profileHead = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 0));
        ImagePanel img = new ImagePanel(cliente.getPfp());
        JPanel textWeapper = new JPanel();
        JLabel username = new JLabel(cliente.getUsername());
        JLabel correo = new JLabel(cliente.getCorreo());

        profileContent = new JPanel();

        JScrollPane scrollPane = new JScrollPane(profileContent);

        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        profileWrapper.setLayout(new BorderLayout(0, 20));
        textWeapper.setLayout(new BoxLayout(textWeapper, BoxLayout.Y_AXIS));
        setLayout(new BorderLayout());

        username.setFont(new Font("comic sans", Font.BOLD, 35));
        correo.setFont(new Font("comic sans", Font.BOLD, 25));

        scrollPane.setBorder(null);

        sobre();

        profileHead.add(img);
        profileHead.add(textWeapper);

        textWeapper.add(username);
        textWeapper.add(correo);

        profileWrapper.add(profileHead, BorderLayout.NORTH);
        profileWrapper.add(scrollPane, BorderLayout.CENTER);

        add(sideBar, BorderLayout.LINE_START);
        add(profileWrapper, BorderLayout.CENTER);

        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        img.setPreferredSize(new Dimension(200, 200));
    }

    private void sobre() {
        LayoutUtils.removeAllComponents(profileContent);

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

    }

    private void compras() {
        LayoutUtils.removeAllComponents(profileContent);

        final int GAP = 20;

        profileContent.setLayout(new FlowLayout(FlowLayout.LEFT, GAP, GAP));

        ArrayList<Libro> libros = Libro.verHistorialLecturas();

        if (libros == null || libros.size() == 0) {
            profileContent.add(new JPanel());
            profileContent.add(new JLabel("No hay libros"));
            profileContent.add(new JPanel());
        } else {
            for (Libro libro : libros) {
                profileContent.add(new BookCover(libro));
            }
        }

        LayoutUtils.calculatePreferedSizeInGrid(profileContent, COLUMNS, GAP);
    }

    private void lecturas() {
        LayoutUtils.removeAllComponents(profileContent);

        final int GAP = 20;

        profileContent.setLayout(new FlowLayout(FlowLayout.LEFT, GAP, GAP));

        ArrayList<String> historial = Venta.verHistorialCompras();

        if (historial == null || historial.size() == 0) {
            profileContent.add(new JPanel());
            profileContent.add(new JLabel("No hay historial"));
            profileContent.add(new JPanel());
        } else {
            for (String venta : historial) {
                JTextArea ticket = new JTextArea(venta);

                ticket.setEditable(false);
                ticket.setFocusable(false);
                ticket.setLineWrap(true);
                ticket.setBackground(Color.BLACK);

                profileContent.add(ticket);
            }
        }

        LayoutUtils.calculatePreferedSizeInGrid(profileContent, COLUMNS, GAP);
    }

    private void editar() {
        new ProfileEditFrame();
        this.dispose();
    }
}
