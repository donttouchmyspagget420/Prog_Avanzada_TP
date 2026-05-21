package GUI;

import BLL.Categorias;
import BLL.Libro;
import Utils.PlatformManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CatalogFrame extends JFrame {
    private static final String NAME = "CatalogFrame";

    private static JComboBox dropdown;
    private static String[] categorias;
    private static SideBar sidebar;
    private static JPanel wrapper, imgsWrapper;
    private static JScrollPane scrollPane;

    protected CatalogFrame() {
        JPanel labelWraperr = new JPanel();
        JLabel label = new JLabel("Ver el catálogo");

        imgsWrapper = new JPanel();

        wrapper = new JPanel(new BorderLayout());

        scrollPane = new JScrollPane(imgsWrapper);

        dropdown = new JComboBox();

        sidebar = new SideBar();

        imgsWrapper.setLayout(new BoxLayout(imgsWrapper, BoxLayout.Y_AXIS));
        label.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        setLayout(new BorderLayout());

        showCategorias();

        add(sidebar, BorderLayout.LINE_START);
        add(wrapper, BorderLayout.CENTER);

        labelWraperr.add(label);
        labelWraperr.add(dropdown);

        showImages();

        wrapper.add(labelWraperr, BorderLayout.NORTH);
        wrapper.add(scrollPane);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        setName(NAME);
    }


    private void showCategorias() {
        categorias = Categorias.getCatagorias().toArray(new String[0]);

        if (categorias == null) return;

        dropdown = new JComboBox<>(categorias);
    }

    private void showImages() {
        /*ArrayList<Libro> libros = Libro.verCatalogo(dropdown.getSelectedItem().toString());

        if (libros == null || libros.size() == 0) {
            imgsWrapper.add(new JPanel());
            imgsWrapper.add(new JLabel("No hay libros"));
            imgsWrapper.add(new JPanel());
            return;
        }

        for (Libro libro : libros) {
            imgsWrapper.add(new BookCover(libro.getPortada()));
        }*/

        for (int i = 0; i < 6; i++) {
            JPanel imgsPanel = new JPanel();
            imgsPanel.setLayout(new BoxLayout(imgsPanel, BoxLayout.X_AXIS));
            for (int j = 0; j < 3; j++) {
                imgsPanel.add(new BookCover(PlatformManager.getPathImgs() + "img.jpg"));
            }
            imgsWrapper.add(imgsPanel);
        }

        imgsWrapper.setPreferredSize(new Dimension(imgsWrapper.getWidth(), imgsWrapper.getHeight() * 2));
    }
}
