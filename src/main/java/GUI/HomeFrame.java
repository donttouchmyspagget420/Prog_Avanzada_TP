package GUI;

import BLL.Libro;
import BLL.Popularidad;
import Utils.PlatformManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class HomeFrame extends JFrame {
    private static final String NAME = "HomeFrame";

    private SideBar sidebar;
    private JPanel imgsWrapper;

    private int columns = 3;

    protected HomeFrame() {
        sidebar = new SideBar();
        imgsWrapper = new JPanel(new GridLayout(0, columns, 20, 20));

        JPanel wrapper = new JPanel();
        JLabel label = new JLabel("Los 10 Libros mas populares");
        JPanel labelWrapper = new JPanel();
        JScrollPane scrollPane = new JScrollPane(imgsWrapper);

        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        setLayout(new BorderLayout());
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        labelWrapper.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

        showImages();

        labelWrapper.add(label);

        wrapper.add(labelWrapper);
        wrapper.add(scrollPane);

        add(sidebar, BorderLayout.LINE_START);
        add(wrapper, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(1000, 1000);
        setResizable(false);
        setName(NAME);
    }


    private void showImages() {
        ArrayList<Libro> libros = Popularidad.getLibros();

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