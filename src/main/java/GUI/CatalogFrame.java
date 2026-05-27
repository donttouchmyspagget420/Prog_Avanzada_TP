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

        imgsWrapper = new JPanel();

        search = new TextField("buscar");

        wrapper = new JPanel(new BorderLayout());

        scrollPane = new JScrollPane(imgsWrapper);

        dropdown = new JComboBox();

        sidebar = new SideBar();

        //config
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY));
        imgsWrapper.setLayout(new BoxLayout(imgsWrapper, BoxLayout.Y_AXIS));
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

        imgsWrapper.setPreferredSize(new Dimension(imgsWrapper.getWidth(), this.getHeight() * COLUMNS));

        dropdown.addActionListener(e -> {
            showImages(dropdown.getSelectedItem().toString());
        });

        buscar.addActionListener(e -> {
            showImagesBuscar(search.getText());
        });
    }


    private void showCategorias() {
        categorias = Categorias.getCatagorias().toArray(new String[0]);

        if (categorias == null) return;

        dropdown = new JComboBox<>(categorias);

        String category = BookFrame.getCategory();
        if (category != null) dropdown.setSelectedItem(category);
    }

    private void showImages(String categoria) {
        imgsWrapper.removeAll();

        /*ArrayList<Libro> libros = Libro.verCatalogo(categoria);

        if (libros == null || libros.size() == 0) {
            imgsWrapper.add(new JPanel());
            imgsWrapper.add(new JLabel("No hay libros"));
            imgsWrapper.add(new JPanel());
            return;
        }

        ArrayList<String> list = new ArrayList<>(COLUMNS);

        JPanel imgsPanel = new JPanel();
        imgsPanel.setLayout(new BoxLayout(imgsPanel, BoxLayout.X_AXIS));

        for (int i = 0; i < libros.size(); i++) {
            if (i % COLUMNS == 0){
                imgsWrapper.add(imgsPanel);
                imgsPanel = new JPanel();
                imgsPanel.setLayout(new BoxLayout(imgsPanel, BoxLayout.X_AXIS));
            }

            imgsPanel.add(new BookCover(libros[i]));
        }
        }*/
        Libro libro = new Libro(0, "img.jpg", 69.99F, 10, "aura monster", "jomama", "", 69, 69, 6.9F, 69, 69);
        JPanel imgsPanel;
        for (int i = 0; i < 9; i++) {
            imgsPanel = new JPanel();
            imgsPanel.setLayout(new BoxLayout(imgsPanel, BoxLayout.X_AXIS));
            for (int j = 0; j < COLUMNS; j++) {
                imgsPanel.add(new BookCover(libro));
            }
            imgsWrapper.add(imgsPanel);
        }

        imgsWrapper.revalidate();
        imgsWrapper.repaint();
    }

    private void showImagesBuscar(String search) {
        imgsWrapper.removeAll();
        /*ArrayList<Libro> libros = Libro.buscarLibros(search);

        if (libros == null || libros.size() == 0) {
            imgsWrapper.add(new JPanel());
            imgsWrapper.add(new JLabel("No hay libros"));
            imgsWrapper.add(new JPanel());
            return;
        }

        ArrayList<String> list = new ArrayList<>(COLUMNS);

        JPanel imgsPanel = new JPanel();
        imgsPanel.setLayout(new BoxLayout(imgsPanel, BoxLayout.X_AXIS));

        for (int i = 0; i < libros.size(); i++) {
            if (i % COLUMNS == 0){
                imgsWrapper.add(imgsPanel);
                imgsPanel = new JPanel();
                imgsPanel.setLayout(new BoxLayout(imgsPanel, BoxLayout.X_AXIS));
            }

            imgsPanel.add(new BookCover(libros[i]));
        }
        }*/
        Libro libro = new Libro(0, "img.jpg", 69.99F, 10, "aura monster", "jomama", "", 69, 69, 6.9F, 69, 69);
        JPanel imgsPanel;
        for (int i = 0; i < 9; i++) {
            imgsPanel = new JPanel();
            imgsPanel.setLayout(new BoxLayout(imgsPanel, BoxLayout.X_AXIS));
            for (int j = 0; j < COLUMNS; j++) {
                imgsPanel.add(new BookCover(libro));
            }
            imgsWrapper.add(imgsPanel);
        }

        imgsWrapper.revalidate();
        imgsWrapper.repaint();
    }
}
