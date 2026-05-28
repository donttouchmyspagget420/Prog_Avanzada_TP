package GUI;

import BLL.Categorias;
import BLL.Libro;
import Utils.PlatformManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CatalogFrame extends JFrame {
    private static final String NAME = "CatalogFrame";

    private TextField search;
    private JComboBox dropdown;
    private String[] categorias;
    private SideBar sidebar;
    private JPanel wrapper, imgsWrapper;
    private JScrollPane scrollPane;

    private final int COLUMNS = 3;

    protected CatalogFrame() {
        JPanel head = new JPanel(new BorderLayout());
        JButton buscar = new JButton("Buscar");
        JPanel headWrappper = new JPanel();

        imgsWrapper = new JPanel(new GridLayout(0, COLUMNS, 20, 20));

        search = new TextField("buscar");

        wrapper = new JPanel(new BorderLayout());

        scrollPane = new JScrollPane(imgsWrapper);

        sidebar = new SideBar();

        //config
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        setLayout(new BorderLayout());

        showCategorias();

        add(sidebar, BorderLayout.LINE_START);
        add(wrapper, BorderLayout.CENTER);

        headWrappper.add(dropdown);
        headWrappper.add(search);

        head.add(headWrappper, BorderLayout.WEST);
        head.add(buscar, BorderLayout.EAST);

        showImages(dropdown.getSelectedItem().toString());

        wrapper.add(head, BorderLayout.NORTH);
        wrapper.add(scrollPane);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setName(NAME);
        setVisible(true);
        setSize(1000, 1000);
        setResizable(false);

        dropdown.addActionListener(e -> {
            showImages(dropdown.getSelectedItem().toString());
        });

        buscar.addActionListener(e -> {
            showImagesBuscar(dropdown.getSelectedItem().toString(), search.getText());
        });
    }


    private void showCategorias() {
        ArrayList<String> arr = Categorias.getCatagorias();
        arr.add(0, "ninguno");

        categorias = arr.toArray(new String[0]);

        if (categorias == null) return;

        dropdown = new JComboBox<>(categorias);

        String category = BookFrame.getCategory();
        if (category != null) dropdown.setSelectedItem(category);
    }

    private void showImages(String categoria) {
        imgsWrapper.removeAll();

        ArrayList<Libro> libros = Libro.verCatalogo(categoria);

        int len = libros.size();

        if (libros == null || len == 0) {
            imgsWrapper.add(new JPanel());
            imgsWrapper.add(new JLabel("No hay libros"));
            imgsWrapper.add(new JPanel());
            return;
        }

        for (Libro libro : libros) {
            imgsWrapper.add(new BookCover(libro));
        }

        imgsWrapper.revalidate();
        imgsWrapper.repaint();
    }


    private void showImagesBuscar(String categoria, String search) {
        imgsWrapper.removeAll();
        ArrayList<Libro> libros = Libro.buscarLibros(categoria, search);

        if (libros == null || libros.size() == 0) {
            imgsWrapper.add(new JPanel());
            imgsWrapper.add(new JLabel("No hay libros"));
            imgsWrapper.add(new JPanel());
            return;
        }

        for (Libro libro : libros) {
            imgsWrapper.add(new BookCover(libro));
        }

        imgsWrapper.revalidate();
        imgsWrapper.repaint();
    }
}
